package com.datanalysis;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class DataframeTest {
    private Series<Integer> getSeries(boolean inversed, int length, String name) {
        Integer[] column = new Integer[length];
        for (int i = 0; i < length; i++) {
            column[i] = inversed ? length - i : i;
        }

        return new Series<>(name, column);
    }

    private Dataframe getBasicDataframe(int length) {
        return this.getBasicDataframe(length, new String[]{"Col 1", "Col 2"});
    }

    private Dataframe getBasicDataframe(int length, String[] seriesNames) {
        boolean inv = false;
        Series[] ser = new Series[seriesNames.length];
        for (int i = 0; i < seriesNames.length; i++) {
            ser[i] = this.getSeries(inv, length, seriesNames[i]);
            inv = !inv;
        }

        return new Dataframe(ser);
    }

    @Test
    void testEmptyConstruct() {
        Dataframe df = new Dataframe();

        assertNotNull(df);
        assertEquals(0, df.getSize());
        assertEquals(0, df.getSeries().size());
    }

    @Test
    void testConstructSeries() {
        Dataframe df = this.getBasicDataframe(10);

        assertNotNull(df);
        assertEquals(10, df.getSize());
    }

    @Test
    void testConstructCSV() {
        Dataframe df = new Dataframe("test.csv");

        assertNotNull(df);
        assertEquals(6, df.getSize());
    }

    @Test
    void testPrintAll() {
        Dataframe df = this.getBasicDataframe(4);

        df.printAll();
    }

    @Test
    void testPrintFirstLines() {
        Dataframe df = this.getBasicDataframe(4);

        df.printFirstLines(2);
    }

    @Test
    void testPrintLastLines() {
        Dataframe df = this.getBasicDataframe(4);

        df.printLastLines(2);
    }

    @Test
    void testValidSelectLines() {
        Dataframe df = this.getBasicDataframe(5);
        Dataframe df2 = df.selectLines(2, 4);

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

        Dataframe df = this.getBasicDataframe(5, seriesNames);
        Dataframe df2 = df.selectSeries(new String[]{"Index", "Restaurants"});

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
        Dataframe df = this.getBasicDataframe(5);
        Dataframe df2 = df.selectLines(1, 6);

        assertNull(df2);
    }
}