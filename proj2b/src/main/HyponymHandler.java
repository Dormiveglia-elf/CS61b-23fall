package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;

import java.util.List;

public class HyponymHandler extends NgordnetQueryHandler {
    WordNet wn;

    public HyponymHandler(WordNet wn) {
        this.wn = wn;
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int starYear = q.startYear();
        int lastYear = q.endYear();

        String response = "";
        if (words.size() == 1) {
            response += wn.handleSingleWord(words.get(0));
        } else {
            response += wn.handleListWord(words);
        }

        return response;
    }

}
