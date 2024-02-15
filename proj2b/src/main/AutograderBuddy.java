package main;

import browser.NgordnetQueryHandler;


public class AutograderBuddy {
    /** Returns a HyponymHandler */
    public static NgordnetQueryHandler getHyponymHandler(
            String wordFile, String countFile,
            String synsetFile, String hyponymFile) {
        WordNet wn = new WordNet(synsetFile, hyponymFile, wordFile, countFile);
        return new HyponymHandler(wn);
    }
}
