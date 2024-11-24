package com.algebrawinter.structs;

import java.util.Optional;

public class SimpleQueue<T>
{
    /*
     * https://www.geeksforgeeks.org/difference-between-queue-and-deque-queue-vs-deque/
     *
     * The queue is a container from which elements can be 'enqueued' at the rear(back) of the queue
     * and elements 'dequeued' from the front(head) of the queue.
     * Valid operations:
     *   Insert an element at the rear.
     *   Get and remove element from the front.
     *   Peek the last element
     *   Peek the first element
     *   Check the size of the queue
     *   Check if the queue is empty or not
     */
    private final SimpleLinkedList<T> linkedList;

    public SimpleQueue()
    {
        linkedList = new SimpleLinkedList<>();
    }

    public Optional<T> dequeue()
    {
        // Get and remove element from the front.
        Optional <T> element = linkedList.getFromHead();
        linkedList.removeFromHead();
        return element;
    }

    public void enqueue(T value)
    {
        // Insert an element at the rear.
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
