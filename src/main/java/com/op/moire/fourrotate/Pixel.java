package com.op.moire.fourrotate;

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

    boolean[] getValueAsBooleans(double rot) {
        boolean[] vals = getValueAsBooleans();
        if (rot == 0) {
            return vals;
        } else if (rot == 90) {
            boolean[] vals2 = {vals[6],vals[3],vals[0],vals[7],vals[4],vals[1],vals[8],vals[5],vals[2]};
            return vals2;
        } else if (rot == 180) {
            boolean[] vals2 = {vals[8],vals[7],vals[6],vals[5],vals[4],vals[3],vals[2],vals[1],vals[0]};
            return vals2;
        } else {
            boolean[] vals2 = {vals[2],vals[5],vals[8],vals[1],vals[4],vals[7],vals[0],vals[3],vals[6]};
            return vals2;
        }
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
