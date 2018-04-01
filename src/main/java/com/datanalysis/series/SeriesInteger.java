package com.datanalysis.series;

import java.util.Comparator;

class SeriesInteger extends Series<Integer> {
    SeriesInteger() {
        super();
        this.setDataType(Integer.class);
    }

    SeriesInteger(Integer[] data) {
        super(data);
        this.setDataType(Integer.class);
    }

    SeriesInteger(String name) {
        super(name);
        this.setDataType(Integer.class);
    }

    SeriesInteger(String name, Integer[] data) {
        super(name, data);
        this.setDataType(Integer.class);
    }

    @Override
    public Integer calculateMin() {
        return super.calculate((min, d) -> d - min);
    }

    @Override
    public Integer calculateMax() {
        return super.calculate(Comparator.comparingInt(max -> max));
    }

    @Override
    public Double calculateAvg() {
        if (this.getSize() == 0) {
            return null;
        }

        Double avg = 0d;

        for (Integer d : this.getData()) {
            avg += d;
        }

        return avg / this.getSize();
    }
}
