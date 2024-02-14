package main;

import edu.princeton.cs.algs4.In;

import java.util.*;

public class MyGraph {
    // variables : what is our graph representations
    // adjList representation
    List<List<Integer>> graph;

    public MyGraph() {
        graph = new ArrayList<>();
    }

    public void add(int id, ArrayList<Integer> hyponyms) {
        while (graph.size() < id + 1) {
            graph.add(new ArrayList<>());
        }

        for (int hyponym: hyponyms) {
            graph.get(id).add(hyponym);
        }
    }

    public void printAll() {
        for (List<Integer> l: graph) {
            if (l != null) {
                System.out.println(l.toString());
            }
        }
    }

    public void bfs(List<List<Integer>> graph, int start, Set<Integer> allID) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();
            allID.add(currentVertex);

            List<Integer> neighbors = graph.get(currentVertex);
            if (neighbors != null) {
                for (int neighbor: neighbors) {
                    if (!visited.contains(neighbor)) {
                        queue.offer(neighbor);
                        visited.add(neighbor);
                    }
                }
            }
        }
    }

    public void travelID(int start, Set<Integer> allID) {
        bfs(graph, start, allID);
    }
}
