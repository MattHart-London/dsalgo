package com.algebrawinter.structs;

import java.util.function.Supplier;

public abstract class BaseLogger implements ILogger
{
    protected static BaseLogger instance;

    // Private constructor to prevent instantiation
    protected BaseLogger() {}

    protected logLevel level = logLevel.INFO;

    public final logLevel getLogLevel()
    {
        return level;
    }
    public final void setLogLevel(logLevel level)
    {
        this.level = level;
    }
}
