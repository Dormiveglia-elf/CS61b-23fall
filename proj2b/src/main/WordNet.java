package main;

import edu.princeton.cs.algs4.In;

public class WordNet {
    private Graph graph;
    // wrapper for a graph

    public WordNet(String synsetFileName, String hyponymFileName) {
        graph = new Graph();
        // build the graph -> add all the edges
        In in1 = new In(synsetFileName);
        In in2 = new In(hyponymFileName);
    }

    // graph helper functions
}
