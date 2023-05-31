package org.example;

import java.util.PriorityQueue;

public class App
{
    public static void primMST(int[][] graph) {
        int V = graph.length;
        int[] parent = new int[V];
        int[] key = new int[V];
        boolean[] mstSet = new boolean[V];

        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        key[0] = 0;
        parent[0] = -1;

        PriorityQueue<Node> pq = new PriorityQueue<>(V, new Node());

        pq.add(new Node(0, key[0]));

        while (!pq.isEmpty()) {
            int u = pq.poll().vertex;

            mstSet[u] = true;

            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                    pq.add(new Node(v, key[v]));
                }
            }
        }

        printMST(parent, graph);
    }

    public static void printMST(int[] parent, int[][] graph) {
        System.out.println("Edge \tWeight");
        for (int i = 1; i < graph.length; i++) {
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
        }
    }

    public static void main( String[] args )
    {
        int[][] graph = {{0, 2, 0, 6, 0},
                {2, 0, 3, 8, 5},
                {0, 3, 0, 0, 7},
                {6, 8, 0, 0, 9},
                {0, 5, 7, 9, 0}};
        primMST(graph);
    }
}
