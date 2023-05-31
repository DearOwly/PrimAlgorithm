package org.example;

import java.util.Comparator;

public class Node implements Comparator<Node> {
    int vertex;
    int weight;

    public Node() {}

    public Node(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }

    @Override
    public int compare(Node node1, Node node2) {
        return node1.weight - node2.weight;
    }
}
