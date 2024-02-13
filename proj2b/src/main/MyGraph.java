package main;

import java.util.ArrayList;
import java.util.List;

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
}
