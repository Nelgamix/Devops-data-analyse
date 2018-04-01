package com.datanalysis.series;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Series<T> {
    private String name;
    private List<T> data;
    private Class dataType;

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

    public int getSize() {
        return this.data.size();
    }

    public T get(int i) {
        if (i >= 0 && i < data.size()) {
            return this.data.get(i);
        }

        return null;
    }

    public void add(T d) {
        this.data.add(d);
    }

    public void setName(String name) {
        this.name = name;
    }

    void setDataType(Class dataType) {
        this.dataType = dataType;
    }

    public Class getDataType() {
        return dataType;
    }

    public String getName() {
        return name;
    }

    public List<T> getData() {
        return data;
    }

    T calculate(Comparator<T> c) {
        if (this.getSize() < 1) {
            return null;
        }

        T min = this.getData().get(0);
        for (T d : this.getData()) {
            if (c.compare(min, d) < 0) {
                min = d;
            }
        }

        return min;
    }

    public T calculateMin() {
        return null;
    }

    public T calculateMax() {
        return null;
    }

    public Double calculateAvg() {
        return null;
    }
}
