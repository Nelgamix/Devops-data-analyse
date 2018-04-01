package com.datanalysis.series;

public class SeriesFactory {
    public static Series createSeries(Class classe) {
        if (classe == String.class) {
            return new SeriesString();
        } else if (classe == Integer.class) {
            return new SeriesInteger();
        } else if (classe == Double.class) {
            return new SeriesDouble();
        } else {
            return null;
        }
    }

    public static Series createSeries(String name, Class classe) {
        if (classe == String.class) {
            return new SeriesString(name);
        } else if (classe == Integer.class) {
            return new SeriesInteger(name);
        } else if (classe == Double.class) {
            return new SeriesDouble(name);
        } else {
            return null;
        }
    }

    public static Series<String> createSeries(String[] data) {
        return new SeriesString(data);
    }

    public static Series<Double> createSeries(Double[] data) {
        return new SeriesDouble(data);
    }

    public static Series<Integer> createSeries(Integer[] data) {
        return new SeriesInteger(data);
    }

    public static Series<String> createSeries(String name, String[] data) {
        return new SeriesString(name, data);
    }

    public static Series<Integer> createSeries(String name, Integer[] data) {
        return new SeriesInteger(name, data);
    }

    public static Series<Double> createSeries(String name, Double[] data) {
        return new SeriesDouble(name, data);
    }
}
