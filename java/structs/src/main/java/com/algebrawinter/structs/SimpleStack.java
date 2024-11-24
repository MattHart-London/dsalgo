package com.algebrawinter.structs;

import java.util.Optional;

public class SimpleStack<T>
{

    /*
     * Implement a very simple Stack container using our SimpleLinkedList
     */
    private final SimpleLinkedList<T> linkedList;

    public SimpleStack()
    {
        linkedList = new SimpleLinkedList<>();
    }

    public boolean isEmpty()
    {
        return linkedList.isEmpty();
    }

    public int size()
    {
        return linkedList.getSize();
    }

    // For A Stack we only ever operate on the head of the list.
    /*
     * We see here that pop() has a clear use case for being able to represent "no result,
     * when the Stack is empty.
     * So Option<T> provides that is the form of a value-based class
     * It's readable and encourages null-safe handling.
     */
    public Optional<T> pop()
    {
        Optional <T> element = linkedList.getFromHead();
        linkedList.removeFromHead();
        return element;
    }

    public void push(T value)
    {
        linkedList.prepend(value);
    }

    /*
     * To implement toString() I'd need to be able to iterate through the underlying structure.
     * Maybe implement an Iterator class.
     */

}
