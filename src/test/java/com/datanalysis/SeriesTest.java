package com.datanalysis;

import static org.junit.jupiter.api.Assertions.*;

import com.datanalysis.series.Series;
import com.datanalysis.series.SeriesFactory;
import org.junit.jupiter.api.Test;

class SeriesTest {
    @Test
    void testDefaultConstructor() {
        Series s = SeriesFactory.createSeries(Integer.class);

        assertNotNull(s);
        assertEquals(Integer.class, s.getDataType());
        assertEquals("", s.getName());
        assertEquals(0, s.getData().size());
    }

    @Test
    void testNameConstructor() {
        Series s = SeriesFactory.createSeries("Colonne 1", Integer.class);

        assertNotNull(s);
        assertEquals(Integer.class, s.getDataType());
        assertEquals("Colonne 1", s.getName());
        assertEquals(0, s.getData().size());
    }

    @Test
    void testArrayConstructor() {
        Integer[] sData = new Integer[]{0, 2, 4, 6, 8, 10};
        Series s = SeriesFactory.createSeries(sData);

        assertNotNull(s);
        assertEquals(Integer.class, s.getDataType());
        assertEquals("", s.getName());
        assertEquals(6, s.getData().size());

        int i = 0;
        for (Integer integer : sData) {
            assertEquals(integer, s.getData().get(i++));
        }
    }

    @Test
    void testConstructor() {
        Integer[] sData = new Integer[]{0, 2, 4, 6, 8, 10};
        Series s = SeriesFactory.createSeries("Col 1", sData);

        assertNotNull(s);
        assertEquals(Integer.class, s.getDataType());
        assertEquals("Col 1", s.getName());
        assertEquals(6, s.getData().size());

        int i = 0;
        for (Integer integer : sData) {
            assertEquals(integer, s.getData().get(i++));
        }
    }

    @Test
    void testAdd() {
        Integer[] sData = new Integer[]{0, 2, 4, 6, 8, 10};
        Series s = SeriesFactory.createSeries(sData);

        assertEquals(6, s.getData().size());

        s.add(18);

        assertEquals(7, s.getData().size());

        s.add(-5);
        s.add(15632);

        assertEquals(9, s.getData().size());
    }

    @Test
    void testGet() {
        Integer[] sData = new Integer[]{0, 2, 4, 6, 8, 10};
        Series s = SeriesFactory.createSeries(sData);

        assertEquals(0, s.get(0));
        assertEquals(2, s.get(1));
        assertEquals(4, s.get(2));
        assertEquals(6, s.get(3));
        assertEquals(8, s.get(4));
        assertEquals(10, s.get(5));

        assertNull(s.get(-1));
        assertNull(s.get(6));
    }

    @Test
    void testGetSize() {
        Integer[] sData = new Integer[]{0, 2, 4, 6, 8, 10};
        Series s = SeriesFactory.createSeries(sData);

        assertEquals(6, s.getSize());

        Series s2 = SeriesFactory.createSeries(Integer.class);

        assertEquals(0, s2.getSize());
    }

    @Test
    void testSetName() {
        Series s = SeriesFactory.createSeries("Colonne 1", Integer.class);
        assertEquals("Colonne 1", s.getName());

        s.setName("Colonne 2");
        assertEquals("Colonne 2", s.getName());
    }

    @Test
    void testGetName() {
        Series s = SeriesFactory.createSeries("Colonne 1", Integer.class);
        assertEquals("Colonne 1", s.getName());
    }

    @Test
    void testCalculateMin() {
        Integer[] sData = new Integer[]{4, 2, 6, 1, 8, 10};
        Series<Integer> s = SeriesFactory.createSeries(sData);

        Integer i = s.calculateMin();
        assertEquals((Integer)1, i);
    }

    @Test
    void testCalculateMax() {
        Integer[] sData = new Integer[]{4, 2, 6, 1, 10, 8};
        Series<Integer> s = SeriesFactory.createSeries(sData);

        Integer i = s.calculateMax();
        assertEquals((Integer)10, i);
    }

    @Test
    void testCalculateAvg() {
        Integer[] sData = new Integer[]{4, 2, 6, 0, 8, 10};
        Series s = SeriesFactory.createSeries(sData);

        Double i = s.calculateAvg();
        assertEquals((Double)5.0, i);
    }

    @Test
    void testConstructString() {
        String[] sData = new String[]{"A", "DS", "AZ", "ZS", "Z"};
        Series s = SeriesFactory.createSeries("String data", sData);

        assertNotNull(s);
        assertEquals(String.class, s.getDataType());
        assertEquals(5, s.getSize());
        assertEquals("String data", s.getName());
        for (int i = 0; i < sData.length; i++) {
            assertEquals(sData[i], s.getData().get(i));
        }
        assertEquals("A", s.calculateMin());
        assertEquals("ZS", s.calculateMax());
        assertNull(s.calculateAvg());

        Series s2 = SeriesFactory.createSeries("Strings", String.class);
        Series s3 = SeriesFactory.createSeries(String.class);
        Series s4 = SeriesFactory.createSeries(sData);

        assertNotNull(s2);
        assertNotNull(s3);
        assertNotNull(s4);
    }

    @Test
    void testConstructDouble() {
        Double[] sData = new Double[]{2.2, 5.9, 3.1, 1.22, 9.3, 6.3};
        Series s = SeriesFactory.createSeries("Doubles", sData);

        assertNotNull(s);
        assertEquals(Double.class, s.getDataType());
        assertEquals(6, s.getSize());
        assertEquals("Doubles", s.getName());
        for (int i = 0; i < sData.length; i++) {
            assertEquals(sData[i], s.getData().get(i));
        }
        assertEquals(1.22, s.calculateMin());
        assertEquals(9.3, s.calculateMax());
        assertEquals((Double)((2.2+5.9+3.1+1.22+9.3+6.3)/6), s.calculateAvg());

        Series s2 = SeriesFactory.createSeries("Doubles", Double.class);
        Series s3 = SeriesFactory.createSeries(Double.class);
        Series s4 = SeriesFactory.createSeries(sData);

        assertNotNull(s2);
        assertNotNull(s3);
        assertNotNull(s4);
    }

    @Test
    void testInvalidConstructs() {
        Series s = SeriesFactory.createSeries(Character.class);
        Series s2 = SeriesFactory.createSeries("Chars", Character.class);

        assertNull(s);
        assertNull(s2);
    }

    @Test
    void testInvalidCalculate() {
        Series si = SeriesFactory.createSeries(Integer.class);
        Series sd = SeriesFactory.createSeries(Double.class);

        assertNotNull(si);
        assertNull(si.calculateAvg());

        assertNotNull(sd);
        assertNull(sd.calculateAvg());
    }
}