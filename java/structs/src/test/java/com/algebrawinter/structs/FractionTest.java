package com.algebrawinter.structs;


import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * I like Test Driven Development in this kind of example, because it reminds me to consider edge-cases
 * such as: Ensure that 2/3 and -2/-3 are same; -2/3 and 2/-3 are same.
 * In my early days of compiler development I remember my team lead remarking,
 * "Any fool can write a compiler for perfect input", ie the Happy Path.
 */
public class FractionTest
{
    @BeforeTest
    public void testBefore()
    {
        //System.setProperty(ILogger.LOGGER_CONTEXT, ILogger.PRODUCTION_CONTEXT);
        System.setProperty(ILogger.LOGGER_CONTEXT, ILogger.TEST_CONTEXT);
        LoggerFactory loggerFactory = new LoggerFactory();
        loggerFactory.getLogger().setLogLevel(BaseLogger.logLevel.TRACE);
    }
    @Test
    public void testBadFraction()
    {
        try
        {
            new Fraction(1,0);
            fail("Expected ArithmeticException but none was thrown.");
        }
        catch (Exception ex)
        {
            assertTrue(ex.getMessage().contains("Denominator must be a positive integer"));
        }
    }

    @Test
    public void testBadFractionEquals()
    {
        try
        {
            Fraction fraction = new Fraction(1,1);
            Integer integer = 1;
            assertNotEquals(fraction, integer);

            fail("Expected IllegalArgumentException but none was thrown.");
        }
        catch (Exception ex)
        {
            assertTrue(ex.getMessage().contains("Expected a Fraction object"));
        }
    }

    @Test
    public void testPrintFraction()
    {
        Fraction zero = new Fraction();
        Fraction anotherZero = new Fraction(zero);
        Fraction one = new Fraction(1);
        Fraction minusOne = new Fraction(-37, 37);
        Fraction three = new Fraction(39,13);

        Fraction half = new Fraction(12,24);
        Fraction halfAgain = new Fraction(500000, 1000000);
        Fraction third = new Fraction(9,27);
        Fraction quarter = new Fraction(3,12);
        Fraction sevenEights = new Fraction(14,16);
        Fraction two_and_a_third = new Fraction(7,3);
        Fraction million = new Fraction(1000000, 1);
        Fraction millionth = new Fraction(1, 1000000);

        // There's various approaches I can take to iterate through test assertions.

        // a) Just a set of plain assertions.
        assertEquals("0", zero.toString());
        assertEquals("-1", minusOne.toString());

        // b) A immutable list of tuples for iteration, constructed with Collections.unmodifiableList
        List<TestTuplePair<String>> pairs = new ArrayList<>();
        Collections.addAll(pairs,
                new TestTuplePair<>(zero::toString, "0"),
                new TestTuplePair<>(anotherZero::toString, "0")
                );

        List<TestTuplePair<String>> tests = Collections.unmodifiableList(pairs);
        for (TestTuplePair<String> pair : tests)
        {
            assertEquals(pair.getExpectation(), pair.getOperationResult());
        }

        // c) A immutable list of tuples for iteration, constructed with List.of
        List<TestTuplePair<String>> tuples = List.of(
                new TestTuplePair<>(minusOne::toString, "-1"),
                new TestTuplePair<>(one::toString, "1"),
                new TestTuplePair<>(three::toString, "3"),
                new TestTuplePair<>(million::toString,"1000000"),
                new TestTuplePair<>(half::toString,"1/2"),
                new TestTuplePair<>(halfAgain::toString,"1/2"),
                new TestTuplePair<>(third::toString, "1/3"),
                new TestTuplePair<>(quarter::toString,"1/4"),
                new TestTuplePair<>(sevenEights::toString,"7/8"),
                new TestTuplePair<>(two_and_a_third::toString,"2 1/3"),
                new TestTuplePair<>(millionth::toString,"1/1000000")
        );

        for (TestTuplePair<String> pair : tuples)
        {
            assertEquals(pair.getExpectation(), pair.getOperationResult());
        }
    }

    @Test
    public void testCompareFractions()
    {
        Fraction zero = new Fraction();
        Fraction anotherZero = new Fraction(zero);
        Fraction one = new Fraction(12,12);
        Fraction minusOne = new Fraction(-37, 37);
        Fraction minusTwo = new Fraction(-400, 200);
        Fraction half = new Fraction(12,24);
        Fraction halfAgain = new Fraction(500000, 1000000);
        Fraction third = new Fraction(9,27);
        Fraction million = new Fraction(1000000, 1);
        Fraction millionth = new Fraction(1, 1000000);

        assertEquals(zero.hashCode(), new Fraction(zero).hashCode());
        assertEquals(half.hashCode(), new Fraction(half).hashCode());
        assertEquals(minusOne.hashCode(), new Fraction(minusOne).hashCode());
        assertEquals(half.hashCode(), halfAgain.hashCode());
        assertNotEquals(half.hashCode(), third.hashCode());
        assertNotEquals(one.hashCode(), minusOne.hashCode());

        assertEquals(zero, anotherZero);
        assertEquals(half, halfAgain);
        assertNotEquals(half, third);
        assertNotEquals(one, minusOne);
        assertNotEquals(zero, millionth);
        assertNotEquals(million, millionth);

        assertEquals(0, zero.compareTo(new Fraction(zero)));
        assertEquals(0, million.compareTo(new Fraction(million)));
        assertEquals(0, millionth.compareTo(new Fraction(millionth)));
        assertEquals(0, half.compareTo(new Fraction(half)));
        assertEquals(0, minusOne.compareTo(new Fraction(minusOne)));

        assertEquals(1, one.compareTo(zero));
        assertEquals(-1, zero.compareTo(one));

        assertEquals(1, zero.compareTo(minusOne));
        assertEquals(-1, minusOne.compareTo(zero));

        assertEquals(1, millionth.compareTo(zero));
        assertEquals(-1, zero.compareTo(millionth));

        assertEquals(1, minusOne.compareTo(minusTwo));
        assertEquals(-1, minusTwo.compareTo(minusOne));
    }

    @Test
    public void testAddFractions()
    {
        Fraction zero = new Fraction();
        Fraction one = new Fraction(1);
        Fraction minusOne = new Fraction(-37, 37);
        Fraction minusTwo = new Fraction(-400, 200);
        Fraction half = new Fraction(12,24);
        Fraction million = new Fraction(1000000, 1);
        Fraction millionth = new Fraction(1, 1000000);
        Fraction quarter = new Fraction(3,12);
        Fraction threeQuarters = new Fraction(6,8);

        // 0 + 0 = 0
        Fraction sum = new Fraction(zero);
        sum.add(zero);
        assertEquals(zero, sum);

        // 1000000 + 0 = 1000000
        sum = new Fraction(million);
        sum.add(0);
        assertEquals(million, sum);

        // 0 + 1/1000000  = 1/1000000
        sum = new Fraction(zero);
        sum.add(millionth);
        assertEquals(millionth, sum);

        // 1/2 + 1/2 = 1
        sum = new Fraction(half);
        sum.add(half);
        assertEquals(one, sum);

        // 1/2 + 1/4 = 3/4
        sum = new Fraction(half);
        sum.add(quarter);
        assertEquals(threeQuarters, sum);

        // -1 + -1 = -2
        sum = new Fraction(minusOne);
        sum.add(minusOne);
        assertEquals(minusTwo, sum);

        // 1 + -1 = 0
        sum = new Fraction(one);
        sum.add(-1);
        assertEquals(zero, sum);
    }

    @Test
    public void testSubtractFractions()
    {
        Fraction zero = new Fraction();
        Fraction one = new Fraction(12,12);
        Fraction two = new Fraction(3600,1800);
        Fraction minusOne = new Fraction(-37, 37);
        Fraction half = new Fraction(12,24);
        Fraction million = new Fraction(1000000, 1);
        Fraction millionth = new Fraction(1, 1000000);
        Fraction negativeMillionth = new Fraction(-1, 1000000);
        Fraction quarter = new Fraction(3,12);

        // 0 - 0 = 0
        Fraction sum = new Fraction(zero);
        sum.subtract(0);
        assertEquals(zero, sum);

        // 1000000 - 0 = 1000000
        sum = new Fraction(million);
        sum.subtract(zero);
        assertEquals(million, sum);

        // 0 - 1/1000000  = -1/1000000
        sum = new Fraction(zero);
        sum.subtract(millionth);
        assertEquals(negativeMillionth, sum);

        // 1/2 - 1/2 = 0
        sum = new Fraction(half);
        sum.subtract(half);
        assertEquals(zero, sum);

        // 1/2 - 1/4 = 1/4
        sum = new Fraction(half);
        sum.subtract(quarter);
        assertEquals(quarter, sum);

        // -1 - -1 = 0
        sum = new Fraction(minusOne);
        sum.subtract(minusOne);
        assertEquals(zero, sum);

        // 1 - -1 = 2
        sum = new Fraction(one);
        sum.subtract(-1);
        assertEquals(two, sum);
    }

    @Test
    public void testMultiplyFractions()
    {
        Fraction zero = new Fraction(0);
        Fraction one = new Fraction(1);
        Fraction two = new Fraction(2);
        Fraction four = new Fraction(4);
        Fraction third = new Fraction(1,3);
        Fraction half = new Fraction(1,2);
        Fraction quarter = new Fraction(1,4);
        Fraction sum = new Fraction(zero);
        assertEquals(sum.multiplyBy(0), zero);

        // TODO: Really I want a set of  x * y = z tuples..
        // How do I write tuples in Java ??
        sum = new Fraction(1);
        assertEquals(sum.multiplyBy(1), one);

        sum = new Fraction(2);
        assertEquals(sum.multiplyBy(2), four);

        sum = new Fraction(4);
        assertEquals(sum.multiplyBy(half), two);

        sum = new Fraction(2);
        assertEquals(sum.multiplyBy(third), new Fraction(2,3));

        sum = new Fraction(2);
        assertEquals(sum.multiplyBy(-1), new Fraction(-2));

        sum = new Fraction(-2);
        assertEquals(sum.multiplyBy(1), new Fraction(-2));

        sum = new Fraction(-2);
        assertEquals(sum.multiplyBy(-2), new Fraction(4));
    }
    @Test
    public void testChainingFractions()
    {
        Fraction one = new Fraction(1,1);
        Fraction two = new Fraction(2,1);
        Fraction three = new Fraction(3,1);
        Fraction sum = new Fraction(one);
        assertEquals(one.toString(), sum.toString());
        sum.add(one).add(1);
        assertEquals(three.toString(), sum.toString());
        sum.subtract(one).subtract(1);
        assertEquals(one.toString(), sum.toString());
    }
}

