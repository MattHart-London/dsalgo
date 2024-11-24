package com.algebrawinter.structs;

import java.util.Optional;

/*
 * The double-ended queue is a queue from which elements can be inserted or deleted from
 * either the front(head) or rear(tail) of the list.
 * Valid operations:
 *   Insert an element at the back
 *   Insert an element at the front
 *   Delete the element at the back
 *   Delete the element from the front
 *   Peek the last element
 *   Peek the first element
 *   Check the size of the deque
 *   Check if the deque is empty or not
 */
public class SimpleDequeue<T>
{
    private final SimpleLinkedList<T> linkedList;

    public SimpleDequeue()
    {
        linkedList = new SimpleLinkedList<>();
    }

    public Optional<T> dequeueFromHead()
    {
        // Get and remove element from the head.
        Optional <T> element = linkedList.getFromHead();
        linkedList.removeFromHead();
        return element;
    }

    public void enqueueToHead(T value)
    {
        // Insert an element at the head.
        linkedList.prepend(value);
    }

    public Optional<T> dequeueFromTail()
    {
        // Get and remove element from the tail.
        Optional <T> element = linkedList.getFromTail();
        linkedList.removeFromTail();
        return element;
    }

    public void enqueueToTail(T value)
    {
        // Insert an element at the tail.
        linkedList.append(value);
    }

    public Optional<T> peekLastElement()
    {
        return linkedList.getFromTail();
    }
    public Optional<T> peekFirstElement()
    {
        return linkedList.getFromHead();
    }
    public int size()
    {
        return linkedList.getSize();
    }

    public boolean isEmpty()
    {
        return linkedList.isEmpty();
    }
}

