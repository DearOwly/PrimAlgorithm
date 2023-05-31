package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.PriorityQueue;

public class App
{
    public static void primMST(int[][] graph) { //реализация алгоритма
        int V = graph.length;
        int[] parent = new int[V]; //массив хранения родительских вершин
        int[] key = new int[V]; //массив хранения веса ребер
        boolean[] mstSet = new boolean[V]; //массив флагов посещения

        //концептуально просто заполняем массивы длины ребер и посещений
        //используем следующие принципы
        //длины ребер ставим неадекватно большими
        //все вершины объявляем непосещенными
        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        //стартуем с 0 вершиины, говорим, что предка у нее нет
        key[0] = 0;
        parent[0] = -1;

        //приоритетная очередь, принимающая вершину и ее ключ
        PriorityQueue<Node> pq = new PriorityQueue<>(V, new Node());

        //добавляем нулевую (она же страртовая) вершину
        pq.add(new Node(0, key[0]));


        while (!pq.isEmpty()) { //пока мы не очистили очередь
            //удаляем с возвращением нынешний элемент
            int u = pq.poll().vertex;
            //ставим метку, что мы ее посетили
            mstSet[u] = true;

            //запускаем цикл для выбора вершины, в которую мы отправимся далее
            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                    pq.add(new Node(v, key[v]));
                }
            }
        }
      //  printMST(parent, graph);
    }

    //простой вывод остова
    public static void printMST(int[] parent, int[][] graph) {
        System.out.println("Edge \tWeight");
        for (int i = 1; i < graph.length; i++) {
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
        }
    }

    public static void main( String[] args ) throws IOException {
        for (int k = 10; k <= 110; k++) {
            Path path = Paths.get("src/main/java/org/example/data/data" + (k-9));
            List<String> lines = Files.readAllLines(path);
            int[][] graph = new int[lines.size()][lines.size()];
            int i = -1, j = -1;
            for(String line : lines){
                i += 1;
                for(String el: line.split(" ")){
                    j+=1;
                    graph[i][j] = Integer.parseInt(el);
                }
                j = -1;
            }

        long start = System.nanoTime();
        primMST(graph);
        long finish = System.nanoTime();
        System.out.println((finish - start) / 1000);

    }
}
}
