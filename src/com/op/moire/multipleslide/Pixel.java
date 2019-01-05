package com.op.moire.multipleslide;

import java.util.Arrays;

public class Pixel {

    static final int NUM_PIXELS = 4;
    String value;

    boolean[] getValueAsBooleans() {
        boolean[] b = new boolean[NUM_PIXELS];
        Arrays.fill(b, false);
        for (int i=0; i<4; i++) {
            String v = value.substring(i, i+1);
            boolean bv = v.equals("1");
            b[i] = bv;
        }

        return b;
    }

    boolean isAllBlack(boolean checkBlack) {
        boolean[] blacks = getValueAsBooleans();
        for (boolean black : blacks) {
            if (black == checkBlack) {
                return false;
            }
        }

        return true;
    }
}
