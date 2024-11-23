package com.algebrawinter.structs;

import java.util.function.Supplier;

public interface ILogger
{
    public static String LOGGER_CONTEXT = "LoggerContext";
    public static String TEST_CONTEXT = "Test";
    public static String PRODUCTION_CONTEXT = "Production";

    public enum logLevel
    {
        ERROR,
        WARN,
        INFO,
        DEBUG,
        TRACE;

        private logLevel()
        {
            /*
             * In Java, enums have private constructors by default to prevent instantiation
             * outside its own scope.
             * (This is of the pre-requisites of a singleton, another being the getInstance() method
             * to get a handle to that singleton object).
             * This design aligns with the purpose of enums; they represent a fixed set of constants.
             */
        }

        /*
         * An enum can also have a public static main. But that feels pretty valueless to me.
         */
    };
    public logLevel getLogLevel();
    public void setLogLevel(logLevel level);

    public void debug(Supplier<String> text);

    public void trace(Supplier<String> text);

    public void error(Supplier<String> text);

    public void info(Supplier<String> text);
}
