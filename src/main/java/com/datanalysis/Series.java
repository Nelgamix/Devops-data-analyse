package com.datanalysis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Series<T> {
    private String name;
    private List<T> data;

    Series() {
        this("", null);
    }

    Series(T[] data) {
        this("", data);
    }

    Series(String name) {
        this(name, null);
    }

    Series(String name, T[] data) {
        this.name = name;
        this.data = data != null ? new ArrayList<>(Arrays.asList(data)) : new ArrayList<>();
    }

    int getSize() {
        return this.data.size();
    }

    T get(int i) {
        if (i >= 0 && i < data.size()) {
            return this.data.get(i);
        }

        return null;
    }

    void add(T d) {
        this.data.add(d);
    }

    void setName(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    List<T> getData() {
        return data;
    }

    T calculateMin() {
        if (this.getSize() == 0) {
            return null;
        }

        T min = this.getData().get(0);
        if (min instanceof Integer) {
            for (T d : this.getData()) {
                if ((Integer)min > (Integer)d) {
                    min = d;
                }
            }
        }

        return min;
    }

    T calculateMax() {
        if (this.getSize() == 0) {
            return null;
        }

        T max = this.getData().get(0);
        if (max instanceof Integer) {
            for (T d : this.getData()) {
                if ((Integer)max < (Integer)d) {
                    max = d;
                }
            }
        }

        return max;
    }

    Double calculateAvg() {
        if (this.getSize() == 0) {
            return null;
        }

        Double avg = 0d;
        if (this.getData().get(0) instanceof Integer) {
            for (T d : this.getData()) {
                avg += (Integer)d;
            }
        }

        return avg / this.getSize();
    }
}
