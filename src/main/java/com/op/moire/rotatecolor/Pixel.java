package com.op.moire.rotatecolor;

import java.util.Arrays;

public class Pixel {

    //static final int[] NUMS = {16, 12, 8, 4};
    //static final int[] NUMS = {9, 7, 5, 3};
    static final int[] NUMS = {4, 3, 2, 1};
    static final int NUM_PIXELS = NUMS[0];
    static final int NUM_BLACK_PIXELS = NUMS[1];
    static final int NUM_BLACKS_TOP = NUMS[2];
    static final int NUM_BLACKS_BOTTOM = NUMS[3];
    static final String[] COLS_BOTTOM = {"C", "M", "Y", "B"};
    static final String[] COLS_TOP = {"C", "M", "Y", "B"};
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

}
