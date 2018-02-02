package com.niraj;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        TrendRecog newTrend = new TrendRecog("src\\resources\\Trend.txt");
        try {
            newTrend.beginAnalysis();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
