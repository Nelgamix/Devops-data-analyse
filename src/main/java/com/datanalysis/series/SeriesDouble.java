package com.datanalysis.series;

import java.util.Comparator;

public class SeriesDouble extends Series<Double> {
    SeriesDouble() {
        super();
        this.setDataType(Double.class);
    }

    SeriesDouble(Double[] data) {
        super(data);
        this.setDataType(Double.class);
    }

    SeriesDouble(String name) {
        super(name);
        this.setDataType(Double.class);
    }

    SeriesDouble(String name, Double[] data) {
        super(name, data);
        this.setDataType(Double.class);
    }

    @Override
    public Double calculateMin() {
        return super.calculate((min, d) -> d < min ? -1 : 1);
    }

    @Override
    public Double calculateMax() {
        return super.calculate(Comparator.comparingDouble(max -> (Double) max));
    }

    @Override
    public Double calculateAvg() {
        if (this.getSize() == 0) {
            return null;
        }

        Double avg = 0d;

        for (Double d : this.getData()) {
            avg += d;
        }

        return avg / this.getSize();
    }
}
