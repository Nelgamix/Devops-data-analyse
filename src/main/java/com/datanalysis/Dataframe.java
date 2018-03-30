package com.datanalysis;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dataframe {
    private List<Series> series;

    public Dataframe() {
        this.series = new ArrayList<>();
    }

    public Dataframe(String filename) {
        this.series = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filename))) {
            String[] line;
            if ((line = reader.readNext()) != null) {
                for (int i = 0; i < line.length; i++) {
                    Series<String> s = new Series<>();
                    s.add(line[i]);
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

    public Dataframe(Series ...datacolumns) {
        this.series = new ArrayList<>();
        this.series.addAll(Arrays.asList(datacolumns));
    }

    public void addSeries(Series s) {
        this.series.add(s);
    }

    public void printAll() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < this.getSize(); i++) {
            for (Series d : this.series) {
                builder.append(String.format("%8s", String.valueOf(d.get(i))));
            }
            builder.append("\n");
        }

        System.out.print(builder.toString());
    }

    public void printFirstLines(int n) {
        if (n >= this.getSize() || n <= 0) {
            return;
        }

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < n; i++) {
            for (Series d : this.series) {
                builder.append(String.format("%8s", String.valueOf(d.get(i))));
            }
            builder.append("\n");
        }

        System.out.print(builder.toString());
    }

    public void printLastLines(int n) {
        int t = this.getSize();
        if (n >= t || n <= 0) {
            return;
        }

        StringBuilder builder = new StringBuilder();

        for (int i = n; i < t - n; i++) {
            for (Series d : this.series) {
                builder.append(String.format("%8s", String.valueOf(d.get(i))));
            }
            builder.append("\n");
        }

        System.out.print(builder.toString());
    }

    public Dataframe selectLines(int min, int max) {
        if (min < 0 || min >= max || max > this.getSize()) {
            return null;
        }

        Dataframe df = new Dataframe();

        // Add series to new Dataframe
        for (int i = 0; i < this.getSeries().size(); i++) {
            df.addSeries(new Series());
        }

        // Copy data
        for (int i = min; i < max; i++) {
            for (int j = 0; j < this.getSeries().size(); j++) {
                df.getSeries().get(j).add(this.getSeries().get(j).get(i));
            }
        }

        return df;
    }

    public Dataframe selectSeries(String[] names) {
        if (names.length < 1) {
            return null;
        }

        Dataframe df = new Dataframe();

        this.getSeries().forEach(s -> {
            for (String name : names) {
                if (s.getName().equals(name)) {
                    Series ns = new Series(name);
                    s.getData().forEach(ns::add);
                    df.addSeries(ns);

                    break;
                }
            }
        });

        return df;
    }

    public List<Series> getSeries() {
        return series;
    }

    public int getSize() {
        int t = 0;
        for (Series s : this.getSeries()) {
            if (t < s.getSize()) {
                t = s.getSize();
            }
        }

        return t;
    }
}
