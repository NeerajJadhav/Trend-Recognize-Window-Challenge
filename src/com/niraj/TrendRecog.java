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

    private void generatePattern() {
        double length = CostArray.size();
        for (Integer i = 0; i <= length - WindowSize_K; i++) {
            List<Double> tempList = CostArray.subList(i,i+ (int)WindowSize_K);
            WindowResults.add(Trend);
            Trend = 1;
        }
    }

    private int increasingSubsequence(List<Double> sublist){
        int Trend = 0;
        for(int j = 0; j < WindowSize_K-1; j++){
            double current = sublist.get(j);
            double next = sublist.get(j+1);
            if (current<next){
                Trend++;
            }else{
                Trend--;
            }
        }
        return Trend;
    }

    /**
     * @throws IOException
     */
    private void parseFile() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(this.path)));
        int contentLength = content.length();
        StringBuilder stringNumeric = new StringBuilder();
        for (int i = 0; i < contentLength; i++) {
            char charstr = content.charAt(i);
            if (isWhiteSpace(charstr)) {
                if (stringNumeric.length() > 0) {
                    double temp = Double.parseDouble(stringNumeric.toString());
                    CostArray.add(temp);
                }
                stringNumeric = new StringBuilder();
            } else {
                stringNumeric.append(charstr);
            }
        }
        this.TotalInputs_N = CostArray.get(0);
        this.WindowSize_K = CostArray.get(1);
        CostArray.remove(0);
        CostArray.remove(0);
    }

    /**
     * @param c
     * @return
     */
    private boolean isWhiteSpace(char c) {
        switch (c) {
            case '\n':
            case '\t':
            case ' ':
            case '\r':
                return true;

            default:
                return false;
        }
    }

    /**
     * @param listVals
     */
    private void displayDoubleLists(List<Double> listVals) {
        for (double d : listVals){
            System.out.println(d);
        }
    }

    /**
     * @throws IOException
     */
    public void beginAnalysis() throws IOException {
        parseFile();
        generatePattern();
        displayDoubleLists(WindowResults);
    }

}
