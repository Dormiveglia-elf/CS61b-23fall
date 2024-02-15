package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;

import java.util.*;

public class HyponymHandler extends NgordnetQueryHandler {
    WordNet wn;

    public HyponymHandler(WordNet wn) {
        this.wn = wn;
    }

    @Override
    public String handle(NgordnetQuery q) {
        Map<Double, String> tempMap = new HashMap<>();
        PriorityQueue<Double> pq = new PriorityQueue<>();

        List<String> words = q.words();
        int starYear = q.startYear();
        int lastYear = q.endYear();
        int k = q.k();

        if (k == 0) {
            String response = "";
            if (words.size() == 1) {
                response += wn.handleSingleWord(words.get(0));
            } else {
                response += wn.handleListWord(words);
            }
            return response;
        }

        for (String word: wn.handleListWord(words)) {
            double totalPop = wn.popularity.countHistory(word, starYear, lastYear).values()
                                           .stream().mapToDouble(Double::doubleValue).sum();
            tempMap.put(totalPop, word);
            if (pq.size() == k) {
                if (totalPop > pq.peek()) {
                    pq.poll();
                    pq.add(totalPop);
                }
            } else if (pq.size() < k) {
                pq.add(totalPop);
            }
        }

        List<String> returnList = new ArrayList<>();
        while (!pq.isEmpty()) {
            returnList.add(tempMap.get(pq.poll()));
        }
        Collections.sort(returnList);

        return returnList.toString();
    }

}
