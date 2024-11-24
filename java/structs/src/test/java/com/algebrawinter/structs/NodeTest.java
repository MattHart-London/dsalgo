package com.algebrawinter.structs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.algebrawinter.structs.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class NodeTest
{
    @Test
    void testNode()
    {
        // TODO: expected and actual, swap.
        // Plus some better tests please.
        Node<Integer> node = new Node<Integer>(66);
        assertEquals(node.getData(), 66);
        node.setData(12);
        assertEquals(node.getData(), 12);

        Node<String> root = new Node<>("root");
        Node<String> left = new Node<>("left");
        Node<String> right = new Node<>("right");

        root.setLeft(left);
        root.setRight(right);
        assertTrue(root.getData().equals("root"));
        assertTrue(root.getRight().getData().equals("right"));
        assertTrue(root.getLeft().getData().equals("left"));
        assertEquals(root.getDepth(), 1);

        Node<Integer>single = new Node<>(0);
        assertEquals(single.getDepth(), 0);
    }
    @Test
    void testNodes()
    {
        List<String> list = Stream.of("elem1", "elem2", "elem3").collect(Collectors.toList());
        list.add("e");
        List<Integer> values = new ArrayList<>();
        for (int i = 0; i < 100; i++)
        {
            values.add(i);
        }
    }
}

