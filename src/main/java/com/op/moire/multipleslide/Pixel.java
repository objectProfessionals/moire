package com.op.moire.multipleslide;

import java.util.Arrays;
import java.util.Random;

public class Pixel {

    //static final int[] NUMS = {16, 12, 8, 4};
    static final int[] NUMS = {9, 7, 5, 3};
    //static final int[] NUMS = {4, 3, 2, 1};
    static final int NUM_PIXELS = NUMS[0];
    static final int NUM_BLACK_PIXELS = NUMS[1];
    static final int NUM_BLACKS_TOP = NUMS[2];
    static final int NUM_BLACKS_BOTTOM = NUMS[3];
    String value;
    protected static Random random = new Random(1);
    protected int rndOff = (int) (random.nextDouble()*Pixel.NUM_PIXELS);


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

    boolean[] getValueAsBooleans(boolean randomise) {
        if (!randomise) {
            return getValueAsBooleans();
        }
        boolean[] newBs = new boolean[NUM_PIXELS];
        boolean[] bvs = getValueAsBooleans();
        for (int i=0; i< bvs.length; i++) {
            int ii = (i + rndOff) % NUM_PIXELS;
            newBs[ii] = bvs[i];
        }

        return newBs;

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
