package com.niraj;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TrendRecog {
    private double TotalInputs_N = 0;
    private double WindowSize_K = 0;
    private List<Double> CostArray, WindowResults;
    private String path;

    public TrendRecog(String path) {
        this.path = path;
        this.CostArray = new ArrayList<>();
        this.WindowResults = new ArrayList<>();
    }

    /**
     * Generates Trend value for each of the window sized sub-sequence.
     */
    private void generatePattern() {
        double length = CostArray.size();
        for (Integer i = 0; i <= length - WindowSize_K; i++) {
            double Trend = 0;
            List<Double> tempList = CostArray.subList(i, i + (int) WindowSize_K);
            for (Integer j = 0; j < WindowSize_K; j++) {
                int k = 2;
                while (j + k < WindowSize_K + 1) {
                    List<Double> smallWindow = tempList.subList(j, j + k);
                    if (increasingSubsequence(smallWindow, 0, 1))
                        Trend++;
                    if (decreasingSubsequence(smallWindow, 0, 1))
                        Trend--;
                    k++;
                }

            }
            WindowResults.add(Trend);
        }
    }

    /**
     * Recursive function which checks if the sublist has a inecreasing sub-sequence of elements
     *
     * @param sublist List<Double> list to be checked
     * @param a       int starting index
     * @param b       int ending index
     * @return true if list has increasing sub-sequence
     */
    private boolean increasingSubsequence(List<Double> sublist, int a, int b) {
        if (sublist.size() <= b)
            return true;
        if (sublist.get(a) < sublist.get(b)) {
            return increasingSubsequence(sublist, b, b + 1);
        } else {
            return false;
        }
    }

    /**
     * Recursive function which checks if the sublist has a decreasing sub-sequence of elements
     *
     * @param sublist List<Double> list to be checked
     * @param a       int starting index
     * @param b       int ending index
     * @return true if list has decreasing sub-sequence
     */
    private boolean decreasingSubsequence(List<Double> sublist, int a, int b) {
        if (sublist.size() <= b)
            return true;
        if (sublist.get(a) > sublist.get(b)) {
            return decreasingSubsequence(sublist, b, b + 1);
        }
        return false;
    }

    /** Parses a text file and retrieves CostArray, Windowsize and Number of inputs
     * Format:
     * <pre>
     *     10 50
     *     100000 20000 300000
     * </pre>
     * @throws IOException
     */
    private void parseFile() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(this.path)));
        int contentLength = content.length();
        if (contentLength <= 0) {
            System.out.println("Empty Input");
            return;
        }
        String[] split = content.split("\\s");
        StringBuilder sb = new StringBuilder();
        for (String aSplit : split) {
            if (!aSplit.isEmpty()) {
                double temp = Double.parseDouble(aSplit);
                CostArray.add(temp);
            }
        }
        this.TotalInputs_N = CostArray.get(0);
        CostArray.remove(0);
        this.WindowSize_K = CostArray.get(0);
        CostArray.remove(0);
    }

    /** Displays List of type Double.
     *
     * @param listVals: List<Double>
     */
    private void displayDoubleLists(List<Double> listVals) {
        for (double d : listVals) {
            System.out.println(d);
        }
    }

    /**
     * @throws IOException
     */
    public void beginAnalysis() throws IOException {
        parseFile();
        if (WindowSize_K <= 1 || TotalInputs_N <= 1 || WindowSize_K >= TotalInputs_N || TotalInputs_N >= 20000) {
            System.out.println("Invalid input");
            return;
        }
        generatePattern();
        displayDoubleLists(WindowResults);
    }

}
