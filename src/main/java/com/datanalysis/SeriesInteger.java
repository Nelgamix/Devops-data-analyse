package com.datanalysis;

class SeriesInteger extends Series<Integer> {
    SeriesInteger() {
        super();
    }

    SeriesInteger(Integer[] data) {
        super(data);
    }

    SeriesInteger(String name) {
        super(name);
    }

    SeriesInteger(String name, Integer[] data) {
        super(name, data);
    }

    @Override
    Integer calculateMin() {
        if (this.getSize() == 0) {
            return null;
        }

        Integer min = this.getData().get(0);

        for (Integer d : this.getData()) {
            if (min > d) {
                min = d;
            }
        }

        return min;
    }

    @Override
    Integer calculateMax() {
        if (this.getSize() == 0) {
            return null;
        }

        Integer max = this.getData().get(0);

        for (Integer d : this.getData()) {
            if (max < d) {
                max = d;
            }
        }

        return max;
    }

    @Override
    Double calculateAvg() {
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
