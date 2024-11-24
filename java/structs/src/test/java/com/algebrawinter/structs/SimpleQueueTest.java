package com.algebrawinter.structs;

import org.testng.annotations.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleQueueTest
{
    @Test
    public void testEmptyQueue()
    {
        SimpleQueue<Integer> emptyQueue = new SimpleQueue<>();
        assertTrue(emptyQueue.isEmpty());
        assertEquals(0, emptyQueue.size());

        Optional<Integer> element = emptyQueue.dequeue();
        assertTrue(element.isEmpty());

        element = emptyQueue.peekFirstElement();
        assertTrue(element.isEmpty());

        element = emptyQueue.peekLastElement();
        assertTrue(element.isEmpty());
    }

    @Test
    public void testEnqueueAndDeQueue()
    {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        for (int i = 1; i <= 10; i++)
        {
            queue.enqueue(i);
            assertEquals(i, queue.size());
            assertFalse(queue.isEmpty());
            assertEquals(1, queue.peekFirstElement().get());
            assertEquals(i, queue.peekLastElement().get());
        }

        // Ensure we come out in FIFO order.
        for (int i = 1; i <= 10; i++)
        {
            assertEquals(10, queue.peekLastElement().get());
            assertEquals(i, queue.peekFirstElement().get());
            assertFalse(queue.isEmpty());
            assertEquals(i, queue.dequeue().get());
        }

        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }
}
