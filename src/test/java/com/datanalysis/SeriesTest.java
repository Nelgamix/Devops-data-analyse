package com.datanalysis;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SeriesTest {
    @Test
    void testDefaultConstructor() {
        Series<Integer> s = new Series<>();

        assertNotNull(s);
        assertEquals("", s.getName());
        assertEquals(0, s.getData().size());
    }

    @Test
    void testNameConstructor() {
        Series<Integer> s = new Series<>("Colonne 1");

        assertNotNull(s);
        assertEquals("Colonne 1", s.getName());
        assertEquals(0, s.getData().size());
    }

    @Test
    void testArrayConstructor() {
        Integer[] sData = new Integer[]{0, 2, 4, 6, 8, 10};
        Series<Integer> s = new Series<>(sData);

        assertNotNull(s);
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
        Series<Integer> s = new Series<>("Col 1", sData);

        assertNotNull(s);
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
        Series<Integer> s = new Series<>(sData);

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
        Series<Integer> s = new Series<>(sData);

        assertEquals((Integer)0, s.get(0));
        assertEquals((Integer)2, s.get(1));
        assertEquals((Integer)4, s.get(2));
        assertEquals((Integer)6, s.get(3));
        assertEquals((Integer)8, s.get(4));
        assertEquals((Integer)10, s.get(5));

        assertNull(s.get(-1));
        assertNull(s.get(6));
    }

    @Test
    void testGetSize() {
        Integer[] sData = new Integer[]{0, 2, 4, 6, 8, 10};
        Series<Integer> s = new Series<>(sData);

        assertEquals(6, s.getSize());

        Series<Integer> s2 = new Series<>();

        assertEquals(0, s2.getSize());
    }

    @Test
    void testSetName() {
        Series<Integer> s = new Series<>("Colonne 1");
        assertEquals("Colonne 1", s.getName());

        s.setName("Colonne 2");
        assertEquals("Colonne 2", s.getName());
    }

    @Test
    void testGetName() {
        Series<Integer> s = new Series<>("Colonne 1");
        assertEquals("Colonne 1", s.getName());
    }

    @Test
    void testCalculateMin() {
        Integer[] sData = new Integer[]{4, 2, 6, 1, 8, 10};
        Series<Integer> s = new Series<>(sData);

        Integer i = s.calculateMin();
        assertEquals((Integer)1, i);
    }
}