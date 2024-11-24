package com.algebrawinter.structs;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Node<T>
{
    private Node<T> lhs;
    private Node<T> rhs;
    private T data;

    public Node(T value)
    {
        this.data = value;
    }

    public Node<T> getLeft()
    {
        return lhs;
    }

    public Node<T> getRight()
    {
        return rhs;
    }

    public void setLeft(Node<T> left)
    {
        lhs = left;
    }

    public void setRight(Node<T> right)
    {
        rhs = right;
    }

    public T getData()
    {
        return data;
    }

    public void setData(T data)
    {
        this.data = data;
    }

    // TODO: Wrong class for this method.
    public int getDepth()
    {
        if (data == null)
        {
            return 0;		// Empty, typically after some kind of deletion, or re-balance.
        }

        if (lhs == null && rhs == null)
        {
            return 0;	// Cannot descend.
        }

        int leftDepth = (lhs == null) ? 0 : lhs.getDepth() + 1;
        int rightDepth = (rhs == null) ? 0 : rhs.getDepth() + 1;
        return Math.max(leftDepth, rightDepth);
    }
}
