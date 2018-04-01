package com.datanalysis;

import com.datanalysis.series.Series;
import com.datanalysis.series.SeriesFactory;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class DataFrame {
    private static final Integer PRINT_DEFAULT = 5;
    private List<Series> series;

    DataFrame() {
        this.series = new ArrayList<>();
    }

    DataFrame(String filename) {
        this.series = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filename))) {
            String[] line;
            if ((line = reader.readNext()) != null) {
                for (String aLine : line) {
                    Series s = SeriesFactory.createSeries(String.class);
                    s.add(aLine);
                    this.addSeries(s);
                }

                while ((line = reader.readNext()) != null) {
                    for (int i = 0; i < this.series.size(); i++) {
                        this.series.get(i).add(line[i]);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    DataFrame(Series ...datacolumns) {
        this.series = new ArrayList<>();
        this.series.addAll(Arrays.asList(datacolumns));
    }

    void addSeries(Series s) {
        this.series.add(s);
    }

    void printAll() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < this.getSize(); i++) {
            for (Series d : this.series) {
                builder.append(String.format("%8s", String.valueOf(d.get(i))));
            }
            builder.append("\n");
        }

        System.out.print(builder.toString());
    }

    void printFirstLines() {
        this.printFirstLines(DataFrame.PRINT_DEFAULT);
    }

    void printLastLines() {
        this.printLastLines(DataFrame.PRINT_DEFAULT);
    }

    void printFirstLines(int n) {
        int t = this.getSize();
        if (n <= 0) {
            return;
        }

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < n && i < t; i++) {
            for (Series d : this.series) {
                builder.append(String.format("%8s", String.valueOf(d.get(i))));
            }
            builder.append("\n");
        }

        System.out.print(builder.toString());
    }

    void printLastLines(int n) {
        int t = this.getSize();
        if (n <= 0) {
            return;
        }

        StringBuilder builder = new StringBuilder();

        for (int i = Math.max(0, t - n); i < t; i++) {
            for (Series d : this.series) {
                builder.append(String.format("%8s", String.valueOf(d.get(i))));
            }
            builder.append("\n");
        }

        System.out.print(builder.toString());
    }

    DataFrame selectLines(int min, int max) {
        if (min < 0 || min >= max || max > this.getSize()) {
            return null;
        }

        DataFrame df = new DataFrame();

        // Add series to new DataFrame
        for (int i = 0; i < this.getSeries().size(); i++) {
            df.addSeries(SeriesFactory.createSeries(this.getSeries().get(i).getDataType()));
        }

        // Copy data
        for (int i = min; i < max; i++) {
            for (int j = 0; j < this.getSeries().size(); j++) {
                df.getSeries().get(j).add(this.getSeries().get(j).get(i));
            }
        }

        return df;
    }

    DataFrame selectSeries(String[] names) {
        if (names.length < 1) {
            return null;
        }

        DataFrame df = new DataFrame();

        this.getSeries().forEach(s -> {
            for (String name : names) {
                if (s.getName().equals(name)) {
                    Series ns = SeriesFactory.createSeries(name, s.getDataType());
                    s.getData().forEach(ns::add);
                    df.addSeries(ns);

                    break;
                }
            }
        });

        return df.getSeries().size() > 0 ? df : null;
    }

    List<Series> getSeries() {
        return series;
    }

    int getSize() {
        int t = 0;
        for (Series s : this.getSeries()) {
            if (t < s.getSize()) {
                t = s.getSize();
            }
        }

        return t;
    }
}
