package com.datanalysis.series;

 class SeriesString extends Series<String> {
    SeriesString() {
        super();
        this.setDataType(String.class);
    }

    SeriesString(String[] data) {
        super(data);
        this.setDataType(String.class);
    }

    SeriesString(String name) {
        super(name);
        this.setDataType(String.class);
    }

    SeriesString(String name, String[] data) {
        super(name, data);
        this.setDataType(String.class);
    }

    @Override
    public String calculateMin() {
        return super.calculate((max, d) -> d.compareToIgnoreCase(max));
    }

    @Override
    public String calculateMax() {
        return super.calculate(String::compareToIgnoreCase);
    }
}
