package ngrams;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * An object for mapping a year number (e.g. 1996) to numerical data. Provides
 * utility methods useful for data analysis.
 *
 * @author Josh Hug
 */
public class TimeSeries extends TreeMap<Integer, Double> {

    public static final int MIN_YEAR = 1400;
    public static final int MAX_YEAR = 2100;

    /**
     * Constructs a new empty TimeSeries.
     */
    public TimeSeries() {
        super();
    }

    /**
     * Creates a copy of TS, but only between STARTYEAR and ENDYEAR,
     * inclusive of both end points.
     */
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {
        super();
        for (Map.Entry<Integer, Double> entry: super.entrySet()) {
            if (entry.getKey() >= startYear && entry.getKey() <= endYear) {
                ts.put(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * Returns all years for this TimeSeries (in any order).
     */
    public List<Integer> years() {
        List<Integer> re = new ArrayList<>();
        for (Map.Entry<Integer, Double> entry: super.entrySet()) {
            re.add(entry.getKey());
        }
        return re;
    }

    /**
     * Returns all data for this TimeSeries (in any order).
     * Must be in the same order as years().
     */
    public List<Double> data() {
        List<Double> re = new ArrayList<>();
        for (Map.Entry<Integer, Double> entry: super.entrySet()) {
            re.add(entry.getValue());
        }
        return re;
    }

    /**
     * Returns the year-wise sum of this TimeSeries with the given TS. In other words, for
     * each year, sum the data from this TimeSeries with the data from TS. Should return a
     * new TimeSeries (does not modify this TimeSeries).
     *
     * If both TimeSeries don't contain any years, return an empty TimeSeries.
     * If one TimeSeries contains a year that the other one doesn't, the returned TimeSeries
     * should store the value from the TimeSeries that contains that year.
     */
    public TimeSeries plus(TimeSeries ts) {
        TimeSeries newTS = new TimeSeries();
        for (Map.Entry<Integer, Double> entry: super.entrySet()) {
            newTS.put(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<Integer, Double> entry: ts.entrySet()) {
            if (newTS.containsKey(entry.getKey())) {
                newTS.put(entry.getKey(), entry.getValue()+newTS.get(entry.getKey()));
            } else {
                newTS.put(entry.getKey(), entry.getValue());
            }
        }
        return newTS;
    }

    /**
     * Returns the quotient of the value for each year this TimeSeries divided by the
     * value for the same year in TS. Should return a new TimeSeries (does not modify this
     * TimeSeries).
     *
     * If TS is missing a year that exists in this TimeSeries, throw an
     * IllegalArgumentException.
     * If TS has a year that is not in this TimeSeries, ignore it.
     */
    public TimeSeries dividedBy(TimeSeries ts) {
        TimeSeries newTS2 = new TimeSeries();
        for (Map.Entry<Integer, Double> entry: super.entrySet()) {
            if (ts.containsKey(entry.getKey())) {
                newTS2.put(entry.getKey(), entry.getValue()/ts.get(entry.getKey()));
            } else {
                throw new IllegalArgumentException();
            }
        }
        return newTS2;
    }
}
