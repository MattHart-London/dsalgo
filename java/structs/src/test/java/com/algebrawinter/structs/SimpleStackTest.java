package com.algebrawinter.structs;

import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimpleStackTest
{
    @Test
    public void testEmptyStack()
    {

        SimpleStack<Integer> emptyStack = new SimpleStack<>();
        assertTrue(emptyStack.isEmpty());
        assertEquals(0, emptyStack.size());

        Optional<Integer> element = emptyStack.pop();
        assertTrue(element.isEmpty());
    }

    @Test
    public void testPushAndPop()
    {
        SimpleStack<Integer> stack = new SimpleStack<>();
        for (int i = 1; i <= 10; i++)
        {
            stack.push(i);
            assertEquals(i, stack.size());
        }

        // Last-In-First-Out.
        for (int i = 10; i >= 1; i--)
        {
            assertEquals(i, stack.size());
            Optional<Integer> element = stack.pop();
            assertEquals(i, element.get());
        }
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }
}
