package com.op.moire.fourstack;

import java.util.ArrayList;

public class Cell {

    String name = "";
    ArrayList<Values> values = new ArrayList();

    Cell(String name, ArrayList<Values> values) {
        this.name = name;
        this.values = values;
    }

    public static ArrayList<Pair> getPairsByCombination(Cell cellA, Cell cellB, int numBlacks) {
        ArrayList<Values> valuesA = cellA.values;
        ArrayList<Values> valuesB = cellB.values;

        ArrayList<Pair> matchedPairs = new ArrayList<>();

        for (Values valueA : valuesA) {
            for (Values valueB : valuesB) {
                boolean doesCombine = doesCombine(valueA, valueB, numBlacks);
                if (doesCombine) {
                    Pair pair = new Pair(valueA, valueB);
                    if (!matchedPairs.contains(pair)) {
                        matchedPairs.add(pair);
                    }
                } else {
                    int i = 0;
                }
            }
        }

        return matchedPairs;
    }

    public static ArrayList<Triplet> getTripletsByCombination(ArrayList<Pair> pairs, Cell cell, int numBlacks) {
        ArrayList<Triplet> triplets = new ArrayList<>();
        for (Pair pair : pairs) {
            for (Values val : cell.values) {
                if (val.doesCombine(pair.merged(), numBlacks)) {
                    Triplet triplet = new Triplet(pair.values1, pair.values2, val);
                    if (!triplets.contains(triplet)) {
                        triplets.add(triplet);
                    }
                }
            }
        }

        return  triplets;
    }

    public static ArrayList<Quad> getQuadsByCombination(ArrayList<Triplet> triplets, Cell cell, int numBlacks) {
        ArrayList<Quad> all = new ArrayList<>();
        for (Triplet triplet : triplets) {
            for (Values val : cell.values) {
                if (val.doesCombine(triplet.merged(), numBlacks)) {
                    Quad quad = new Quad(triplet.values1, triplet.values2, triplet.values3, val);
                    if (!all.contains(quad)) {
                        all.add(quad);
                    }
                }
            }
        }

        return  all;
    }

    public static ArrayList<Triplet> getTripletsByCombination(Cell cellA, Cell cellB, Cell cellC, int numBlacks) {
        ArrayList<Values> valuesA = cellA.values;
        ArrayList<Values> valuesB = cellB.values;
        ArrayList<Values> valuesC = cellC.values;

        ArrayList<Triplet> matchedTriplets = new ArrayList<>();

        for (Values valueA : valuesA) {
            for (Values valueB : valuesB) {
                for (Values valueC : valuesC) {
                    boolean doesCombine = doesCombine(valueA, valueB, valueC, numBlacks);
                    if (doesCombine) {
                        Triplet triplet = new Triplet(valueA, valueB, valueC);
                        if (!matchedTriplets.contains(triplet)) {
                            matchedTriplets.add(triplet);
                        } else {
                            int i=0;
                        }
                    } else {
                        int i = 0;
                    }
                }
            }
        }

        return matchedTriplets;
    }

    public static ArrayList<Quad> getQuadsByCombination(Cell cellA, Cell cellB, Cell cellC, Cell cellD, int numBlacks) {
        ArrayList<Values> valuesA = cellA.values;
        ArrayList<Values> valuesB = cellB.values;
        ArrayList<Values> valuesC = cellC.values;
        ArrayList<Values> valuesD = cellD.values;

        ArrayList<Quad> matchedQuads = new ArrayList<>();

        for (Values valueA : valuesA) {
            for (Values valueB : valuesB) {
                for (Values valueC : valuesC) {
                    for (Values valueD : valuesD) {
                        boolean doesCombine = doesCombine(valueA, valueB, valueC, numBlacks);
                        if (doesCombine) {
                            Quad triplet = new Quad(valueA, valueB, valueC, valueD);
                            if (!matchedQuads.contains(triplet)) {
                                matchedQuads.add(triplet);
                            }
                        } else {
                            int i = 0;
                        }
                    }
                }
            }
        }

        return matchedQuads;
    }


    private static boolean doesCombine(Values valueA, Values valueB, int numToMatch) {
        int val = 0;
        for (int n = 0; n < Stack.ALL; n++) {
            String bn = valueA.value.substring(n, n + 1);
            String tn = valueB.value.substring(n, n + 1);
            if ("1".equals(bn) || "1".equals(tn)) {
                val = val + 1;
            }
        }
        return val == numToMatch;
    }

    private static boolean doesCombine(Values valueA, Values valueB, Values valueC, int numToMatch) {
        int val = 0;
        for (int n = 0; n < Stack.ALL; n++) {
            String vA = valueA.value.substring(n, n + 1);
            String vB = valueB.value.substring(n, n + 1);
            String vC = valueC.value.substring(n, n + 1);
            if ("1".equals(vA) || "1".equals(vB) || "1".equals(vC)) {
                val = val + 1;
            }
        }
        return val == numToMatch;
    }

    private static boolean doesCombine(Values valueA, Values valueB, Values valueC, Values valueD, int numToMatch) {
        int val = 0;
        for (int n = 0; n < Stack.ALL; n++) {
            String vA = valueA.value.substring(n, n + 1);
            String vB = valueB.value.substring(n, n + 1);
            String vC = valueC.value.substring(n, n + 1);
            String vD = valueD.value.substring(n, n + 1);
            if ("1".equals(vA) || "1".equals(vB) || "1".equals(vC) || "1".equals(vD)) {
                val = val + 1;
            }
        }
        return val == numToMatch;
    }
}
