package com.algebrawinter.structs;

import java.nio.ByteBuffer;
import static java.lang.Math.abs;

public final class Fraction extends Number implements Comparable<Fraction>, Cloneable
{
    /*
     * TODO: Now Dependency Injection would have me inject the Logger
     * via my ILogger interface. So I can use a MockLogger or a FileLogger for junit maybe.
     * The only ways I know how to do that currently are:
     * a) Inject my Logger of choice into the constructor
     * b) Have some method where I can change the Logger on the fly for every fraction instance..
     * c) Use a Logger factory which gives me an ILogger implementation based on a
     *    LOGGER_CONTEXT property.
     * c) Can annotations help me here?
     */
    private static final ILogger logger = SystemOutLogger.getInstance();

    // I could have made numerator and denominator longs.
    private int numerator;
    private int denominator;

    public Fraction()
    {
        this(0,1);
    }

    public Fraction(int whole)
    {
        this(whole, 1);
    }
    public Fraction(Fraction other)
    {
        this(other.getNumerator(), other.getDenominator());
    }

    public Fraction(int numerator, int denominator)
    {
        if (denominator <= 0)
        {
            throw new ArithmeticException("Denominator must be a positive integer.");
        }

        this.numerator = numerator;
        this.denominator = denominator;
        reduce();
    }
    public int getNumerator()
    {
        return numerator;
    }
    public int getDenominator()
    {
        return denominator;
    }

    @Override
    public int intValue()
    {
        return numerator;
    }

    @Override
    public long longValue()
    {
        return numerator;
    }

    @Override
    public float floatValue()
    {
        float nm = numerator;
        float dn = denominator;
        return nm/dn;
    }

    @Override
    public double doubleValue()
    {
        double nm = numerator;
        double dn = denominator;
        return nm/dn;
    }

    public String toString()
    {
        if (numerator == 0)
        {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        int nm = numerator;
        int dm = denominator;
        boolean addSpace = false;

        if (nm < 0)
        {
            sb.append("-");
            nm = -nm;
        }

        int whole = nm / dm;
        int remainder = nm % dm;

        if (whole > 0)
        {
            sb.append(whole);
            addSpace = true;
        }
        if (remainder> 0)
        {
            if (addSpace)
            {
                sb.append(" ");
            }
            sb.append(remainder);
            sb.append("/");
            sb.append(denominator);
        }
        return sb.toString();
    }

    private void reduce()
    {
        boolean negative = (numerator < 0);
        numerator = abs(numerator);
        int gcd = Math.min(numerator, denominator);
        int lcd = getGCD(numerator,denominator, gcd);
        numerator = numerator / lcd;
        if (negative)
        {
            numerator = -numerator;
        }
        denominator = (numerator == 0) ? 1 : denominator /lcd;
    }

    private int getGCD(int nm, int dm, int max)
    {
        if (max <= 1)
        {
            return 1;
        }

        if (dm%max == 0 && nm%max == 0)
        {
            return max;
        }

        return getGCD(nm, dm, max -1);
    }

    public Fraction add(int other)
    {
        return add(new Fraction(other));
    }
    public Fraction add(Fraction other)
    {
        int otherNumerator = other.getNumerator();
        int otherDenominator = other.getDenominator();

        if (otherNumerator == 0)
        {
            // Nothing to add.
            return this;
        }

        if (numerator == 0)
        {
            numerator = otherNumerator;
            denominator = otherDenominator;
            return this;
        }

        // Now we take two values and make them use the same denominator.
        int nm_me = numerator * other.getDenominator();
        int nm_other = other.getNumerator() * denominator;
        numerator = nm_me + nm_other;
        denominator = denominator * other.getDenominator();
        reduce();
        return this;
    }

    public Fraction subtract(int other)
    {
        return subtract(new Fraction(other));
    }
    public Fraction subtract(Fraction other) {
        // x - y = x + (-y)
        Fraction invert = new Fraction(-other.getNumerator(), other.getDenominator());
        add(invert);
        return this;
    }
    public Fraction divideBy(int other)
    {
        return divideBy(new Fraction(other));
    }
    public Fraction divideBy(Fraction other)
    {
        // (a/b) / (x/y) = (a/b) * (y/x)
        Fraction invert = new Fraction(other.getDenominator(), other.getNumerator());
        return multiplyBy(invert);
    }

    public Fraction multiplyBy(int other)
    {
        return multiplyBy(new Fraction(other));
    }

    public Fraction multiplyBy(Fraction other)
    {
        numerator *= other.getNumerator();
        denominator *= other.getDenominator();
        reduce();
        return this;
    }

    @Override
    public int compareTo(Fraction other)
    {
        if (this.equals(other))
        {
            return 0;
        }

        // A zero numerator is a special case.
        if (other.getNumerator() == 0 || numerator == 0)
        {
            return (numerator > other.getNumerator()) ? 1 : -1;
        }

        // Now we take two values and make them use the same denominator.
        //  3/4 versus 2/3
        // Becomes 9/12 versus 8/12
        int nm_me = numerator * other.getDenominator();
        int nm_other = other.getNumerator() * denominator;
        return Integer.compare(nm_me, nm_other);  // We are equal.
    }

    @Override
    public boolean equals(Object obj)
    {
        if ( !(obj instanceof Fraction fraction))
        {
            throw new IllegalArgumentException("Expected a Fraction object.");
        }

        return (numerator == fraction.getNumerator()) && (denominator == fraction.getDenominator());
    }

    @Override
    public int hashCode()
    {
        if (numerator == 0 || denominator == 0)
        {
            // Floating point operations will NaN this condition.
            return 0;
        }

        float nm = numerator;
        float dm = denominator;
        float f = nm/dm;
        logger.debug(() -> "Value: " + this + " has hashcode before int-ing =" + f);

        byte[] byteArray = ByteBuffer.allocate(Float.BYTES).putFloat(f).array();
        logger.trace(() -> {
            StringBuilder sb = new StringBuilder();
            for (byte b : byteArray)
            {
              sb.append(String.format("0x%02X", b));  // Convert byte to hex and append
            }
            return sb.toString();
        });

        int hash = (byteArray[0] << 24) + (byteArray[1] << 16) + (byteArray[2] << 8) + byteArray[3];
        logger.debug(() ->"Value: " + this + " has hashcode =" + hash);
        return hash;
    }
    /* ToDo
     * Implement a cloneable interface.
     * Do I use annotations to define contracts on methods?
     * constructor that takes a String parameter?
     */
}

