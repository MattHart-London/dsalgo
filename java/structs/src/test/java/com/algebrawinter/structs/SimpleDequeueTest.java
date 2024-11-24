package com.algebrawinter.structs;

import org.testng.annotations.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleDequeueTest
{
    @Test
    public void testEmptyDequeue()
    {
        SimpleDequeue<Integer> emptyDequeue = new SimpleDequeue<>();
        assertTrue(emptyDequeue.isEmpty());
        assertEquals(0, emptyDequeue.size());

        Optional<Integer> element = emptyDequeue.peekFirstElement();
        assertTrue(element.isEmpty());

        element = emptyDequeue.peekLastElement();
        assertTrue(element.isEmpty());

        element = emptyDequeue.dequeueFromHead();
        assertTrue(element.isEmpty());

        element = emptyDequeue.dequeueFromTail();
        assertTrue(element.isEmpty());
    }

    @Test
    public void testEnqueueAndDeQueueFromHead()
    {
        SimpleDequeue<Integer> dequeue = new SimpleDequeue<>();
        final int QUEUE_SIZE = 10;
        // We behave as a Stack, LIFO.
        for (int i = 1; i <= QUEUE_SIZE; i++)
        {
            dequeue.enqueueToHead(i);
            assertEquals(i, dequeue.size());
            assertFalse(dequeue.isEmpty());
            assertEquals(i, dequeue.peekFirstElement().get());
            assertEquals(1, dequeue.peekLastElement().get());
        }

        // Ensure they reappear in LIFO order.
        for (int i = QUEUE_SIZE; i >= 1; i--)
        {
            assertEquals(1, dequeue.peekLastElement().get());
            assertEquals(i, dequeue.peekFirstElement().get());
            assertFalse(dequeue.isEmpty());
            assertEquals(i, dequeue.size());
            assertEquals(i, dequeue.dequeueFromHead().get());
        }

        assertTrue(dequeue.isEmpty());
        assertEquals(0, dequeue.size());
    }

    @Test
    public void testEnqueueAndDeQueueFromTail()
    {
        SimpleDequeue<Integer> dequeue = new SimpleDequeue<>();
        final int QUEUE_SIZE = 10;

        // We behave as a Stack, LIFO.
        for (int i = 1; i <= QUEUE_SIZE; i++)
        {
            dequeue.enqueueToTail(i);
            assertEquals(i, dequeue.size());
            assertFalse(dequeue.isEmpty());
            assertEquals(1, dequeue.peekFirstElement().get());
            assertEquals(i, dequeue.peekLastElement().get());
        }

        // Ensure they reappear in LIFO order.
        for (int i = QUEUE_SIZE; i >= 1; i--)
        {
            assertEquals(i, dequeue.peekLastElement().get());
            assertEquals(1, dequeue.peekFirstElement().get());
            assertFalse(dequeue.isEmpty());
            assertEquals(i, dequeue.size());
            assertEquals(i, dequeue.dequeueFromTail().get());
        }

        assertTrue(dequeue.isEmpty());
        assertEquals(0, dequeue.size());
    }

    @Test
    public void testEnqueueToHeadAndDeQueueFromTail()
    {
        SimpleDequeue<Integer> dequeue = new SimpleDequeue<>();
        final int QUEUE_SIZE = 10;

        // We behave as a Queue, FIFO.
        for (int i = 1; i <= QUEUE_SIZE; i++)
        {
            dequeue.enqueueToHead(i);
            assertEquals(i, dequeue.size());
            assertFalse(dequeue.isEmpty());
            assertEquals(i, dequeue.peekFirstElement().get());
            assertEquals(1, dequeue.peekLastElement().get());
        }

        // Ensure they reappear in FIFO order.
        for (int i = 1; i <= QUEUE_SIZE; i++)
        {
            assertEquals(QUEUE_SIZE, dequeue.peekFirstElement().get());
            assertEquals(i, dequeue.peekLastElement().get());
            assertFalse(dequeue.isEmpty());
            assertEquals(QUEUE_SIZE - i + 1, dequeue.size());
            assertEquals(i, dequeue.dequeueFromTail().get());
        }

        assertTrue(dequeue.isEmpty());
        assertEquals(0, dequeue.size());
    }

    @Test
    public void testEnqueueToTailAndDeQueueFromHead()
    {
        SimpleDequeue<Integer> dequeue = new SimpleDequeue<>();
        final int QUEUE_SIZE = 10;

        // We behave as a Queue, FIFO.
        for (int i = 1; i <= QUEUE_SIZE; i++)
        {
            dequeue.enqueueToTail(i);
            assertEquals(i, dequeue.size());
            assertFalse(dequeue.isEmpty());
            assertEquals(1, dequeue.peekFirstElement().get());
            assertEquals(i, dequeue.peekLastElement().get());
        }

        // Ensure they reappear in FIFO order.
        for (int i = 1; i <= QUEUE_SIZE; i++)
        {
            assertEquals(i, dequeue.peekFirstElement().get());
            assertEquals(QUEUE_SIZE, dequeue.peekLastElement().get());
            assertFalse(dequeue.isEmpty());
            assertEquals(QUEUE_SIZE - i + 1, dequeue.size());
            assertEquals(i, dequeue.dequeueFromHead().get());
        }

        assertTrue(dequeue.isEmpty());
        assertEquals(0, dequeue.size());
    }
}
