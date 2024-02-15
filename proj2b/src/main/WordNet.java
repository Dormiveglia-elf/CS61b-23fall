package main;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import ngrams.NGramMap;
import ngrams.TimeSeries;

import java.util.*;

public class WordNet {
    MyGraph myGraph = new MyGraph();
    Map<Integer, String[]> synSets1 = new HashMap();
    Map<String, List<Integer>> synSets2 = new HashMap();
    NGramMap popularity;
    // wrapper for a graph

    public WordNet(String synsetFileName, String hyponymFileName, String wordFile, String countFile) {
        // build the graph -> add all the edges
        In in1 = new In(synsetFileName);
        In in2 = new In(hyponymFileName);

        while (in1.hasNextLine()) {
            String nextline = in1.readLine();
            String[] splitLine = nextline.split(",");
            String[] synSetsArr = splitLine[1].split(" ");

            synSets1.put(Integer.valueOf(splitLine[0]), synSetsArr);

            for (String s: synSetsArr) {
                if (!synSets2.containsKey(s)) {
                    ArrayList<Integer> newList = new ArrayList<>();
                    newList.add(Integer.valueOf(splitLine[0]));
                    synSets2.put(s, newList);
                }
                synSets2.get(s).add(Integer.valueOf(splitLine[0]));
            }
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

        popularity = new NGramMap(wordFile, countFile);
    }

    // graph helper functions
    public Set<String> handleSingleWord(String word) {
        List<Integer> IDs = new ArrayList<>();
        if (synSets2.containsKey(word)) {
            for (int i: synSets2.get(word)) {
                IDs.add(i);
            }
        }
        Set<Integer> allID = new HashSet<>();
        for (int i: IDs) {
            myGraph.travelID(i, allID);
        }

        Set<String> returnSet = new HashSet<>();
        for (int i: allID) {
            for (String s: synSets1.get(i)) {
                returnSet.add(s);
            }
        }

        List<String> list = new ArrayList<>(returnSet);
        Collections.sort(list);
        returnSet = new LinkedHashSet<>(list);

        return  returnSet;
    }

    public Set<String> handleListWord(List<String> words) {
        Set<String> returnSet = new HashSet<>();
        for (String word: words) {
            List<Integer> IDs = new ArrayList<>();
            if (synSets2.containsKey(word)) {
                for (int i : synSets2.get(word)) {
                    IDs.add(i);
                }
            }
            Set<Integer> allID = new HashSet<>();
            for (int i : IDs) {
                myGraph.travelID(i, allID);
            }

            Set<String> tempSet = new HashSet<>();
            for (int i : allID) {
                for (String s : synSets1.get(i)) {
                    tempSet.add(s);
                }
            }
            if (returnSet.isEmpty()) {
                returnSet = tempSet;
            } else {
                returnSet.retainAll(tempSet);
            }
        }

        List<String> list = new ArrayList<>(returnSet);
        Collections.sort(list);
        returnSet = new LinkedHashSet<>(list);

        return  returnSet;
    }
}
