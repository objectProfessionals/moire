package com.op.moire.fourstackold;

import java.util.ArrayList;

public class Values {

    //boolean[] value = {false, false, false, false, false, false, false, false}; //8
    //boolean[] value = {false, false, false, false, false, false, false, false, false}; //9
    //boolean[] value = {false, false, false, false, false, false, false, false, false, false}; //10
    boolean[] value = new boolean[Stack.ALL];

    Values(Boolean[] value) {
        for (int i = 0; i < value.length; i++) {
            this.value[i] = value[i];
        }
    }

    static ArrayList<Values> createAllFilledCells(int numBlack) {
        int numPixels = Stack.ALL;
        String numBits = "%" + numPixels + "s";
        ArrayList<Values> tops = new ArrayList<>();
        int allVariations = (int) Math.pow(2, numPixels);
        for (int i = 0; i < allVariations; i++) {
            String bin = Integer.toBinaryString(i);
            String padded = String.format(numBits, bin).replaceAll(" ", "0");
            long count = padded.chars().filter(ch -> ch == '1').count();
            if (count == numBlack) {
                Boolean[] b = new Boolean[numPixels];
                for (int n = 0; n < padded.length(); n++) {
                    String p = padded.substring(n, n + 1);
                    b[n] = ("1".equals(p));
                }
                Values top = new Values(b);
                tops.add(top);
            }
        }
        return tops;
    }

    boolean hasNum(int numMatches) {
        int val = 0;
        for (int n = 0; n < value.length; n++) {
            if (value[n]) {
                val = val + 1;
            }
        }
        return val == numMatches;

    }

    Values merge(Values values2) {
        Boolean[] val = new Boolean[Stack.ALL];
        for (int n = 0; n < this.value.length; n++) {
            if (this.value[n] || values2.value[n]) {
                val[n] = true;
            } else {
                val[n] = false;
            }
        }
        return new Values(val);
    }

    @Override
    public String toString() {
        String val = "";
        for (int n = 0; n < this.value.length; n++) {
            if (this.value[n]) {
                val = val + "1";
            } else {
                val = val + "0";
            }
        }
        return val;
    }
}
