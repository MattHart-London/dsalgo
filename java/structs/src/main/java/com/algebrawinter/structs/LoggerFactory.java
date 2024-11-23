package com.algebrawinter.structs;

import java.util.HashMap;
import java.util.Map;

public class LoggerFactory
{
    // Other options to consider:
    // Can annotations help here?
    // What _exactly_ does Spring offer?
    // java.util.ServiceLoader - benefits/purpose?
    private final Map<String, ILogger> loggers = new HashMap<>();
    private ILogger instance = null;

    public ILogger getLogger()
    {
        if (instance == null)
        {
            String context = System.getProperty(ILogger.LOGGER_CONTEXT);
            if (context != null && context.equals(ILogger.TEST_CONTEXT)) {
                instance = SystemOutLogger.getInstance();
            }
            else
            {
                // Default to a Production context.
                instance = FileLogger.getInstance();
            }
        }
        return instance;
    }
}
