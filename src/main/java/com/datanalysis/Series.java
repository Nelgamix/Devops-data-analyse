package com.datanalysis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Series<T> {
    private String name;
    private List<T> data;

    public Series() {
        this.data = new ArrayList<>();
    }

    public Series(T[] data) {
        this("", data);
    }

    public Series(String name) {
        this(name, null);
    }

    public Series(String name, T[] data) {
        this.name = name;
        this.data = data != null ? new ArrayList<>(Arrays.asList(data)) : new ArrayList<>();
    }

    public int getSize() {
        return this.data.size();
    }

    public T get(int i) {
        if (i < data.size()) {
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

    public String getName() {
        return name;
    }

    public List<T> getData() {
        return data;
    }
}
