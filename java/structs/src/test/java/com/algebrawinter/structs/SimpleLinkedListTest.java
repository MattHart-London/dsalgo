package com.algebrawinter.structs;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/*
 * I don't implement a search methods, because that would be a costly linear search ie O(n)
 */
public class SimpleLinkedListTest
{
    @Test
    void testEmptyLinkedList()
    {
        SimpleLinkedList<Integer> emptyList = new SimpleLinkedList<>();
        assertTrue(emptyList.isEmpty());
        assertEquals(0, emptyList.getSize());

        Optional<Integer> element = emptyList.getFromHead();
        assertTrue(element.isEmpty());

        element = emptyList.getFromTail();
        assertTrue(element.isEmpty());
    }

    @Test
    void testPrependLinkedList()
    {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        for (int i = 1; i <= 10; i++)
        {
            list.prepend(i);
            assertEquals(i, list.getSize());
            assertEquals(i, list.getFromHead().get());
        }

        for (int i = 10; i >= 1; i--)
        {
            assertTrue(list.getFromHead().isPresent());
            assertEquals(i, list.getFromHead().get());
            assertEquals(i, list.getFromHead().get());
            list.removeFromHead();
        }
        assertTrue(list.isEmpty());
    }

    @Test
    void testAppendLinkedList()
    {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        for (int i = 1; i <= 10; i++)
        {
            list.append(i);
            assertEquals(i, list.getSize());
            assertEquals(i, list.getFromTail().get());
        }

        for (int i = 10; i >= 1; i--)
        {
            assertTrue(list.getFromTail().isPresent());
            assertEquals(i, list.getFromTail().get());
            assertEquals(i, list.getFromTail().get());
            assertEquals(i, list.getSize());
            list.removeFromTail();
        }
        assertTrue(list.isEmpty());
    }
}
