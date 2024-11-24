package com.algebrawinter.structs;

import java.util.Optional;

public class SimpleLinkedList<T>
{
    private int size;

    private Node<T> head;
    private Node<T> tail;

    public int getSize()
    {
        return size;
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    public void prepend(T value)
    {
        Node<T> item = new Node<T>(value);
        item.setData(value);

        if (head != null)
        {
            // Modify the previous head item.
            head.setLeft(item);
            item.setRight(head);
        }
        head = item;

        if (tail == null)
        {
            tail = item;    // First item in list.
        }
        size++;
    }

    public void append(T value)
    {
        Node<T> item = new Node<T>(value);
        item.setData(value);

        if (tail != null)
        {
            // Modify the previous head item.
            tail.setRight(item);
            item.setLeft(tail);
        }
        tail = item;

        if (head == null)
        {
            head = item;    // First item in list.
        }
        size++;
    }

    public void removeFromHead()
    {
        if (head != null)
        {
            head = head.getRight();
            if (head == null)
            {
                // Last item in list was removed.
                tail = null;
            }
            size--;
        }
    }

    public void removeFromTail()
    {
        if (tail != null)
        {
            tail = tail.getLeft();
            if (tail == null)
            {
                // Last item in list was removed.
                head = null;
            }
            size--;
        }
    }

    /*
     * Prior to Java 8 there were two ways to handle attempts to retrieve elements from empty containers:
     * a) Throw an exception.
     * b) Return a null. Not ideal should we wish our container to be able to contain nullable elements.
     */
    public Optional<T> getFromHead()
    {
        return Optional.ofNullable((head != null) ? head.getData() : null);
    }

    public Optional<T> getFromTail()
    {
        return Optional.ofNullable((tail != null) ? tail.getData() : null);
    }
}
