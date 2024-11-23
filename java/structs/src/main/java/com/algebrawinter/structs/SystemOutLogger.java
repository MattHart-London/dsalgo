package com.algebrawinter.structs;

import java.util.function.Supplier;

public class SystemOutLogger extends BaseLogger
{
    // Private constructor to prevent instantiation
    private SystemOutLogger() {}
    public static ILogger getInstance()
    {
        if (instance  == null)
        {
            /*
             * This implementation is lazy-loaded and not thread-safe.
             * For thread safety I'd need to do one of these:
             * Mutex/synchronization
             * The Bill Pugh Singleton design with an inner static helper class.
             */
            instance = new SystemOutLogger();
        }
        return instance;
    }
    public void trace(Supplier<String> text)
    {
        if (level.compareTo(logLevel.TRACE) >= 0)
        {
            System.out.println("TRACE: " + text.get());
        }
    }
    public void debug(Supplier<String> text)
    {
        if (level.compareTo(logLevel.DEBUG) >= 0)
        {
            System.out.println("DEBUG: " + text.get());
        }
    }
    public void error(Supplier<String> text)
    {
        System.out.println("ERROR: " + text.get());
    }
    public void info(Supplier<String> text)
    {
        if (level.compareTo(logLevel.INFO) >= 0)
        {
            System.out.println("INFO: " + text.get());
        }
    }
}

// TODO:
// a) handle a partially defined class in Java

