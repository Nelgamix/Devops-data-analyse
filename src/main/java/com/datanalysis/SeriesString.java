package com.datanalysis;

 class SeriesString extends Series<String> {
    SeriesString() {
        super();
    }

    SeriesString(String[] data) {
        super(data);
    }

    SeriesString(String name) {
        super(name);
    }

    SeriesString(String name, String[] data) {
        super(name, data);
    }

    @Override
    String calculateMin() {
        if (this.getSize() == 0) {
            return null;
        }

        String min = this.getData().get(0);

        for (String d : this.getData()) {
            if (d.compareToIgnoreCase(min) < 0) {
                min = d;
            }
        }

        return min;
    }

    @Override
    String calculateMax() {
        if (this.getSize() == 0) {
            return null;
        }

        String max = this.getData().get(0);

        for (String d : this.getData()) {
            if (d.compareToIgnoreCase(max) > 0) {
                max = d;
            }
        }

        return max;
    }
}
