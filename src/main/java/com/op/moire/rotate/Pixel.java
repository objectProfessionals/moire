package com.op.moire.rotate;

import java.util.Arrays;

public class Pixel {

    //static final int[] NUMS = {16, 12, 8, 4};
    static final int[] NUMS = {9, 7, 5, 3};
    //static final int[] NUMS = {4, 3, 2, 1};
    static final int NUM_PIXELS = NUMS[0];
    static final int NUM_BLACK_PIXELS = NUMS[1];
    static final int NUM_BLACKS_TOP = NUMS[2];
    static final int NUM_BLACKS_BOTTOM = NUMS[3];

    String value;

    String[] getValueAsStrings() {
        String[] b = new String[NUM_PIXELS];
        Arrays.fill(b, "0");
        for (int i = 0; i < NUM_PIXELS; i++) {
            String v = value.substring(i, i + 1);
            b[i] = v;
        }

        return b;
    }

    boolean[] getValueAsBooleans() {
        boolean[] b = new boolean[NUM_PIXELS];
        Arrays.fill(b, false);
        for (int i = 0; i < NUM_PIXELS; i++) {
            String v = value.substring(i, i + 1);
            boolean bv = v.equals("1");
            b[i] = bv;
        }

        return b;
    }

}
