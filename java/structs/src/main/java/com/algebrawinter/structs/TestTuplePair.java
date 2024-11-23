package com.algebrawinter.structs;

import java.util.function.Supplier;

public class TestTuplePair<T>
{
    private final Supplier<T> operation;
    private final T expectation;

    public TestTuplePair(Supplier<T> operation, T expectation)
    {
        this.operation = operation;
        this.expectation = expectation;
    }

    public T getOperationResult()
    {
        return operation.get();
    }

    public T getExpectation()
    {
        return expectation;
    }

    @Override
    public String toString()
    {
        return "[operation:" + operation + ", expectation:" + expectation + "]";
    }
}
