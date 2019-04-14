package com.op.moire.fourstack;

import java.util.ArrayList;

import static com.op.moire.fourstack.Stack.ALL;

public class Values {

    String value = "";
    Values(String value) {
        this.value = value;
    }

    static ArrayList<Values> createAllFilledCells(int numPixels, int numBlack) {
        String numBits = "%" + numPixels + "s";
        ArrayList<Values> tops = new ArrayList<>();
        int allVariations = (int) Math.pow(2, numPixels);
        for (int i = 0; i < allVariations; i++) {
            String bin = Integer.toBinaryString(i);
            String padded = String.format(numBits, bin).replaceAll(" ", "0");
            long count = padded.chars().filter(ch -> ch == '1').count();
            if (count == numBlack) {
                Values top = new Values(padded);
                tops.add(top);
            }
        }
        return tops;
    }

    boolean doesCombine(String value1, int numMatches) {
        int val = 0;
        for (int n = 0; n < ALL; n++) {
            String bn = value.substring(n, n + 1);
            String tn = value1.substring(n, n + 1);
            if ("1".equals(bn) || "1".equals(tn)) {
                val = val + 1;
            } else {
                val = val + 0;
            }
        }
        return val == numMatches;

    }

}
