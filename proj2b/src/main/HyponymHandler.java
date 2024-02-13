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

        return "Hello, World!";
    }

}
