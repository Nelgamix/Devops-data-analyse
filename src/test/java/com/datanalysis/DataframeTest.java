package com.datanalysis;

import static org.junit.jupiter.api.Assertions.*;

import com.datanalysis.series.Series;
import com.datanalysis.series.SeriesFactory;

import org.junit.jupiter.api.Test;

class DataframeTest {
    private Series getSeries(boolean inversed, int length, String name) {
        Integer[] column = new Integer[length];
        for (int i = 0; i < length; i++) {
            column[i] = inversed ? length - i : i;
        }

        return SeriesFactory.createSeries(name, column);
    }

    private DataFrame getBasicDataframe(int length) {
        return this.getBasicDataframe(length, new String[]{"Col 1", "Col 2"});
    }

    private DataFrame getBasicDataframe(int length, String[] seriesNames) {
        boolean inv = false;
        Series[] ser = new Series[seriesNames.length];
        for (int i = 0; i < seriesNames.length; i++) {
            ser[i] = this.getSeries(inv, length, seriesNames[i]);
            inv = !inv;
        }

        return new DataFrame(ser);
    }

    @Test
    void testEmptyConstruct() {
        DataFrame df = new DataFrame();

        assertNotNull(df);
        assertEquals(0, df.getSize());
        assertEquals(0, df.getSeries().size());
    }

    @Test
    void testConstructSeries() {
        DataFrame df = this.getBasicDataframe(10);

        assertNotNull(df);
        assertEquals(10, df.getSize());
    }

    @Test
    void testConstructCSV() {
        DataFrame df = new DataFrame("test.csv");

        assertNotNull(df);
        assertEquals(6, df.getSize());
    }

    @Test
    void testPrintAll() {
        DataFrame df = this.getBasicDataframe(4);

        df.printAll();
    }

    @Test
    void testPrintFirstLines() {
        DataFrame df = this.getBasicDataframe(6);

        df.printFirstLines();
        df.printFirstLines(2);
    }

    @Test
    void testPrintLastLines() {
        DataFrame df = this.getBasicDataframe(6);

        df.printLastLines();
        df.printLastLines(2);
    }

    @Test
    void testInvalidPrint() {
        DataFrame df = this.getBasicDataframe(3);

        df.printFirstLines(9);
        df.printLastLines(9);
        df.printFirstLines(-2);
        df.printLastLines(-1);
    }

    @Test
    void testValidSelectLines() {
        DataFrame df = this.getBasicDataframe(5);
        DataFrame df2 = df.selectLines(2, 4);

        assertNotNull(df2);
        assertEquals(2, df2.getSize());
        assertEquals(2, df2.getSeries().size());

        assertEquals(2, df2.getSeries().get(0).get(0));
        assertEquals(3, df2.getSeries().get(0).get(1));
        assertEquals(3, df2.getSeries().get(1).get(0));
        assertEquals(2, df2.getSeries().get(1).get(1));
    }

    @Test
    void testValidSelectSeries() {
        String[] seriesNames = new String[]{"Place", "Index", "Restaurants"};

        DataFrame df = this.getBasicDataframe(5, seriesNames);
        DataFrame df2 = df.selectSeries(new String[]{"Index", "Restaurants"});

        assertNotNull(df2);
        assertEquals(5, df2.getSize());
        assertEquals(2, df2.getSeries().size());

        assertEquals("Index", df2.getSeries().get(0).getName());
        assertEquals("Restaurants", df2.getSeries().get(1).getName());

        assertEquals(5, df2.getSeries().get(0).get(0));
        assertEquals(4, df2.getSeries().get(0).get(1));
        assertEquals(0, df2.getSeries().get(1).get(0));
        assertEquals(1, df2.getSeries().get(1).get(1));
    }

    @Test
    void testInvalidSelectLines() {
        DataFrame df = this.getBasicDataframe(5);
        DataFrame df2 = df.selectLines(1, 6);

        assertNull(df2);
    }

    @Test
    void testInvalidSelectSeries() {
        DataFrame df = this.getBasicDataframe(5);
        DataFrame df2 = df.selectSeries(new String[]{});
        DataFrame df3 = df.selectSeries(new String[]{"Named"});

        assertNull(df2);
        assertNull(df3);
    }

    @Test
    void testMixedDataframe() {
        Series s1 = SeriesFactory.createSeries(new Integer[]{2, 4, 6});
        Series s2 = SeriesFactory.createSeries(new String[]{"AZ", "PM", "GA"});
        DataFrame df = new DataFrame(s1, s2);

        assertNotNull(df);
        assertEquals(3, df.getSize());
        df.printAll();
    }
}