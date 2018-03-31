package com.datanalysis;

class SeriesFactory {
    static Series createSeries(Class classe) {
        if (classe == String.class) {
            return new SeriesString();
        } else if (classe == Integer.class) {
            return new SeriesInteger();
        } else {
            return null;
        }
    }

    static Series createSeries(String name, Class classe) {
        if (classe == String.class) {
            return new SeriesString(name);
        } else if (classe == Integer.class) {
            return new SeriesInteger(name);
        } else {
            return null;
        }
    }

    static Series<String> createSeries(String[] data) {
        return new SeriesString(data);
    }

    static Series<Integer> createSeries(Integer[] data) {
        return new SeriesInteger(data);
    }

    static Series<String> createSeries(String name, String[] data) {
        return new SeriesString(name, data);
    }

    static Series<Integer> createSeries(String name, Integer[] data) {
        return new SeriesInteger(name, data);
    }
}
