package com.niraj;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        TrendRecog newTrend = new TrendRecog("D:\\Development\\Web\\Trend.txt");
        try {
            newTrend.beginAnalysis();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
