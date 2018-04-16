package com.datanalysis;

public class Utils {
    public static Class getTypeOf(String data) {
        try {
            Integer.parseInt(data);
            return Integer.class;
        } catch (NumberFormatException ignored) {
        }

        try {
            Double.parseDouble(data);
            return Double.class;
        } catch (NumberFormatException ignored) {
        }

        return String.class;
    }
}
