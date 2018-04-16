package com.datanalysis;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {
    @Test
    void getTypeOf() {
        String testInt = "15";
        String testNotInt = "f15";
        String testDouble = "15.65";
        String testNotDouble = "15.f2";
        String testString = "Blabla";

        assertEquals(Integer.class, Utils.getTypeOf(testInt));
        assertEquals(String.class, Utils.getTypeOf(testNotInt));
        assertEquals(Double.class, Utils.getTypeOf(testDouble));
        assertEquals(String.class, Utils.getTypeOf(testNotDouble));
        assertEquals(String.class, Utils.getTypeOf(testString));
    }
}