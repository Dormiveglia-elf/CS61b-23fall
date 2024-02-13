package main;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class WordNet {
    private MyGraph myGraph;
    private HashMap synSets;
    // wrapper for a graph

    public WordNet(String synsetFileName, String hyponymFileName) {
        myGraph = new MyGraph();
        synSets = new HashMap();
        // build the graph -> add all the edges
        In in1 = new In(synsetFileName);
        In in2 = new In(hyponymFileName);

        while (in1.hasNextLine()) {
            String nextline = in1.readLine();
            String[] splitLine = nextline.split(",");
            synSets.put(Integer.valueOf(splitLine[0]), splitLine[1]);
        }

        while (in2.hasNextLine()) {
            String nextline = in2.readLine();
            String[] splitLine = nextline.split(",");
            int synsetId = Integer.valueOf(splitLine[0]);
            ArrayList<Integer> temp = new ArrayList<>();
            for (int i = 1; i < splitLine.length; i++) {
                temp.add(Integer.valueOf(splitLine[i]));
            }
            myGraph.add(synsetId, temp);
        }
    }

    // graph helper functions
}
