package com.algebrawinter.structs;

import java.io.Serializable;

/*
 * A NodeTree is a composed of connected Nodes.
 * It's a one-way tree; currently a Node doesn't know it's parent.
 * (Maybe that would be useful if we have our own two way LinkedList of Nodes.
 * except lhs and rhs could suffice there I think).
 *
 * All we can do is:
 * a) Add nodes to it.
 * b) Find it's depth.
 * c) Visit it .. how??
 */
public class NodeTree<T> implements Serializable
{
    private Node<T> root;

    public NodeTree()
    {
        root = null;
    }

    public NodeTree(Node<T> root)
    {
        this.root = root;
    }

    public int getDepth()
    {
        if (root == null)
        {
            return 0;
        }

        return root.getDepth();
    }

    public void add(Node<T> node)
    {
        // Of course we can easily add a whole branch to our current tree.
        // Which may of course totally un-balance it.
        // Check for a completely empty tree.
        if (root == null)
        {
            root = node;
            return;
        }

        add(root, node);
    }

    private void add(Node<T> branch, Node<T> node)
    {
        // First see if we can add our node at the current level
        if (branch.getLeft() == null)
        {
            branch.setLeft(node);
            return;
        }

        if (branch.getRight() == null)
        {
            branch.setRight(node);
            return;
        }

        // Okay, we need to descend, so find the least populated way down.
        int lhsDepth = branch.getLeft().getDepth();
        int rhsDepth = branch.getRight().getDepth();
        Node<T> choice = (lhsDepth > rhsDepth) ? branch.getRight() : branch.getLeft();
        add(choice, node);
    }
}
