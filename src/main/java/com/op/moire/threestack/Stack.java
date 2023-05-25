package com.op.moire.threestack;

import com.op.moire.Base;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Stack extends Base {

    static Stack stack = new Stack();
    static int ALL = 2;

    public static void main(String[] args) throws Exception {
        stack.test();
    }

    private void test() {
        //1234567
        boolean[] blacks = {false, true, false, true, false, true, false};


        String[] singles = {"C", "M", "Y"};
        String[] choices = {"CC", "MM", "YY", "CM", "CY", "MC", "MY", "YC", "YM"};

        String[] order = {"Y", "C", "M", "G", "R", "B", "X",};
        double[] values = {1, 1.5, 2, 2.5, 3, 3.5, 4.5};
        HashMap<String, Double> singleToValue = new HashMap<>();
        for (int i = 0; i < order.length; i++) {
            singleToValue.put(order[i], values[i]);
        }

        HashMap<String, String> calculations = new HashMap<>();
        calculations.put("CC", "C");
        calculations.put("CM", "B");
        calculations.put("MC", "B");
        calculations.put("CY", "G");
        calculations.put("YC", "G");
        calculations.put("MM", "M");
        calculations.put("YY", "Y");
        calculations.put("YM", "R");
        calculations.put("MY", "R");

        calculations.put("RR", "R");
        calculations.put("GG", "G");
        calculations.put("BB", "B");

        calculations.put("RC", "X");
        calculations.put("RM", "R");
        calculations.put("RY", "R");

        calculations.put("GC", "G");
        calculations.put("GM", "X");
        calculations.put("GY", "G");

        calculations.put("BC", "B");
        calculations.put("BM", "B");
        calculations.put("BY", "X");

        calculations.put("RG", "X");
        calculations.put("GR", "X");
        calculations.put("RB", "X");
        calculations.put("BR", "X");
        calculations.put("GB", "X");
        calculations.put("BG", "X");


        HashMap<String, Double> stackedValuesP = new HashMap<>();
        HashMap<String, Double> stackedValuesT = new HashMap<>();
        for (String singleC : singles) {
            for (String singleB : singles) {
                for (String singleA : singles) {

                    String singleABC = singleA + singleB + singleC;

                    String resultAB = calculations.get(singleA + singleB);
                    String resultAC = calculations.get(singleA + singleC);
                    String resultBC = calculations.get(singleB + singleC);

                    double stackValuePAB = singleToValue.get(resultAB);
                    stackedValuesP.put(singleA + singleB, stackValuePAB);
                    double stackValuePAC = singleToValue.get(resultAC);
                    stackedValuesP.put(singleA + singleC, stackValuePAC);
                    double stackValuePBC = singleToValue.get(resultBC);
                    stackedValuesP.put(singleB + singleC, stackValuePBC);

                    String resultABC = calculations.get(resultAB + singleC);
                    double stackValueT = singleToValue.get(resultABC);
                    stackedValuesT.put(singleABC, stackValueT);
                }
            }
        }
        //System.out.println(stackedValuesT);

        double[][] allValuesP = new double[choices.length][choices.length];
        double[][][] allValuesT = new double[choices.length][choices.length][choices.length];
        HashMap<String, Double> combinations = new HashMap<>();
        for (int c = 0; c < choices.length; c++) {
            for (int b = 0; b < choices.length; b++) {
                for (int a = 0; a < choices.length; a++) {
                    String chC0 = choices[c].substring(0, 1);
                    String chB0 = choices[b].substring(0, 1);
                    String chA0 = choices[a].substring(0, 1);

                    String chC1 = choices[c].substring(1, 2);
                    String chB1 = choices[b].substring(1, 2);
                    String chA1 = choices[a].substring(1, 2);

                    combinations.put(chA0 + chA1, singleToValue.get(chA0) + singleToValue.get(chA1));
                    combinations.put(chB0 + chB1, singleToValue.get(chB0) + singleToValue.get(chB1));
                    combinations.put(chC0 + chC1, singleToValue.get(chC0) + singleToValue.get(chC1));

                    double vPAB0 = stackedValuesP.get(chA0 + chB0);
                    double vPAC0 = stackedValuesP.get(chA0 + chC0);
                    double vPBC0 = stackedValuesP.get(chB0 + chC0);
                    double vPAB1 = stackedValuesP.get(chA1 + chB1);
                    double vPAC1 = stackedValuesP.get(chA1 + chC1);
                    double vPBC1 = stackedValuesP.get(chB1 + chC1);
                    allValuesP[a][b] = vPAB0 + vPAB1;
                    allValuesP[a][c] = vPAC0 + vPAC1;
                    allValuesP[b][c] = vPBC0 + vPBC1;
                    combinations.put(choices[a] + "+" + choices[b], allValuesP[a][b]);
                    combinations.put(choices[a] + "+" + choices[c], allValuesP[a][c]);
                    combinations.put(choices[b] + "+" + choices[c], allValuesP[b][c]);

                    double vT0 = stackedValuesT.get(chA0 + chB0 + chC0);
                    double vT1 = stackedValuesT.get(chA1 + chB1 + chC1);

                    allValuesT[a][b][c] = vT0 + vT1;
                    combinations.put(choices[a] + "+" + choices[b] + "+" + choices[c], allValuesT[a][b][c]);

                }
            }

        }
        //System.out.println(combinations);
        Map<String, Double> sortedByCount = combinations.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));


        Map<String, Double> sortedByCountS = sortedByCount.entrySet()
                .stream()
                .filter(key -> key.getKey().length() == 2)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        Map<String, Double> sortedByCountP = sortedByCount.entrySet()
                .stream()
                .filter(key -> key.getKey().length() == 5)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        Map<String, Double> sortedByCountT = sortedByCount.entrySet()
                .stream()
                .filter(key -> key.getKey().length() == 8)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

//        System.out.println(sortedByCountS);
//        System.out.println(sortedByCountP);
//        System.out.println(sortedByCountT);

        HashMap<String, Double> b7 = getEnd(blacks[6], sortedByCountT, 5.5, 6.5);
        HashMap<String, Double> b6 = getEnd(blacks[5], sortedByCountP, 4.5, 5.5);
        HashMap<String, Double> b5 = getEnd(blacks[4], sortedByCountP, 4.5, 5.5);
        HashMap<String, Double> b3 = getEnd(blacks[2], sortedByCountP, 4.5, 5.5);
        HashMap<String, Double> b4 = getEnd(blacks[3], sortedByCountS, 2.5, 3.5);
        HashMap<String, Double> b2 = getEnd(blacks[1], sortedByCountS, 2.5, 3.5);
        HashMap<String, Double> b1 = getEnd(blacks[0], sortedByCountS, 2.5, 3.5);

        System.out.println(b7);
        System.out.println(b6);
        System.out.println(b5);
        System.out.println(b4);
        System.out.println(b3);
        System.out.println(b2);
        System.out.println(b1);

        //filterPairsByCombincations(b1, b2, b3);
    }

    private HashMap<String, Double> getEnd(boolean black, Map<String, Double> sortedByCount, double min, double max) {
        HashMap<String,Double> ret = new HashMap<>();
        for (String key : sortedByCount.keySet()) {
            double val = sortedByCount.get(key);
            if ((!black && val <= min) || (black && val >= max)) {
                ret.put(key, val);
            }
        }

        return ret;
    }
}
