package ngrams;

import edu.princeton.cs.algs4.In;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static ngrams.TimeSeries.MAX_YEAR;
import static ngrams.TimeSeries.MIN_YEAR;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {

    Map<String, TimeSeries> wordsFile = new HashMap<>();
    TimeSeries countsFile = new TimeSeries();

    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        In in1 = new In(wordsFilename);
        In in2 = new In(countsFilename);

        while (in1.hasNextLine()) {
            String nextline = in1.readLine();
            String[] splitLine = nextline.split("\t");
            if (wordsFile.containsKey(splitLine[0])) {
                wordsFile.get(splitLine[0]).put(Integer.valueOf(splitLine[1]), Double.valueOf(splitLine[2]));
            } else {
                TimeSeries temp = new TimeSeries();
                temp.put(Integer.valueOf(splitLine[1]), Double.valueOf(splitLine[2]));
                wordsFile.put(splitLine[0], temp);
            }
        }

        while (in2.hasNextLine()) {
            String nextline = in2.readLine();
            String[] splitLine = nextline.split(",");
            countsFile.put(Integer.valueOf(splitLine[0]), Double.valueOf(splitLine[1]));
        }
    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        if (wordsFile.containsKey(word)) {
            return new TimeSeries(wordsFile.get(word), startYear, endYear);
        } else {
            return new TimeSeries();
        }
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word) {
        if (wordsFile.containsKey(word)) {
            return new TimeSeries(wordsFile.get(word), MIN_YEAR, MAX_YEAR);
        } else {
            return new TimeSeries();
        }
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        return new TimeSeries(countsFile, MIN_YEAR, MAX_YEAR);
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        return countHistory(word, startYear, endYear).dividedBy(totalCountHistory());
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        return countHistory(word, MIN_YEAR, MAX_YEAR).dividedBy(totalCountHistory());
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        TimeSeries re = new TimeSeries();
        for (String word: words) {
            re = re.plus(weightHistory(word, startYear, endYear));
        }
        return re;
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        TimeSeries re = new TimeSeries();
        for (String word: words) {
            re = re.plus(weightHistory(word, MIN_YEAR, MAX_YEAR));
        }
        return re;
    }
}
