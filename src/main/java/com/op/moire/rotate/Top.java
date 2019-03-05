package com.op.moire.rotate;

import java.util.ArrayList;

public class Top extends Pixel {

    ArrayList<Boolean> blacks = new ArrayList();

    Top(String value, ArrayList<Bottom> bottoms) {
        super();
        this.value = value;
        initBlacks(bottoms);
    }

    Top(String value) {
        super();
        this.value = value;
    }

    static ArrayList<Top> createAllFilledTops(ArrayList<Bottom> bottoms) {
        String numBits = "%" + Pixel.NUM_PIXELS + "s";
        ArrayList<Top> tops = new ArrayList<>();
        int allVariations = (int) Math.pow(2, Pixel.NUM_PIXELS);
        for (int i = 0; i < allVariations; i++) {
            String bin = Integer.toBinaryString(i);
            String padded = String.format(numBits, bin).replaceAll(" ", "0");
            long count = padded.chars().filter(ch -> ch == '1').count();
            if (count == NUM_BLACKS_TOP) {
                Top top = new Top(padded, bottoms);
                tops.add(top);
            }
        }
        return tops;
    }

    public boolean rotateEquals(double rot, Top otherTop) {
        String[] vals = getValueAsStrings();
        boolean three = NUM_PIXELS == 9;
        String rotVal = "";
        if (rot%360 == 0) {
            return this.value.equals(otherTop.value);
        } else if (rot%360 == 90) {
            if (three) {
                String[] vals2 = {vals[6], vals[3], vals[0], vals[7], vals[4], vals[1], vals[8], vals[5], vals[2]};
                rotVal = String.join("", vals2);
            } else {
                String[] vals2 = {vals[12], vals[8], vals[4], vals[0], vals[13], vals[9], vals[5], vals[1], vals[14], vals[10], vals[6], vals[2], vals[15], vals[11], vals[7], vals[3]};
                rotVal = String.join("", vals2);
            }
        } else if (rot%360 == 180) {
            if (three) {
                String[] vals2 = {vals[8], vals[7], vals[6], vals[5], vals[4], vals[3], vals[2], vals[1], vals[0]};
                rotVal = String.join("", vals2);
            } else {
                String[] vals2 = {vals[15], vals[14], vals[13], vals[12], vals[11], vals[10], vals[9], vals[8], vals[7], vals[6], vals[5], vals[4], vals[3], vals[2], vals[1], vals[0]};
                rotVal = String.join("", vals2);
            }
        } else {
            if (three) {
                String[] vals2 = {vals[2], vals[5], vals[8], vals[1], vals[4], vals[7], vals[0], vals[3], vals[6]};
                rotVal = String.join("", vals2);
            } else {
                String[] vals2 = {vals[3], vals[7], vals[11], vals[15], vals[2], vals[6], vals[10], vals[14], vals[1], vals[5], vals[9], vals[13], vals[0], vals[4], vals[8], vals[12]};
                rotVal = String.join("", vals2);
            }
        }
        return rotVal.equals(otherTop.value);
    }

    Top rotate2(double rot) {
        String[] vals = getValueAsStrings();
        String rotVal = this.value;
        if (rot%360 == 0) {

        } else if (rot%360 == 90) {
            String[] vals2 = {vals[2], vals[0], vals[3], vals[1]};
            rotVal = String.join("", vals2);
        } else if (rot%360 == 180) {
            String[] vals2 = {vals[3], vals[2], vals[1], vals[0]};
            rotVal = String.join("", vals2);
        } else {
            String[] vals2 = {vals[1], vals[3], vals[0], vals[1]};
            rotVal = String.join("", vals2);
        }
        return new Top(rotVal);
    }

    private void initBlacks(ArrayList<Bottom> bottoms) {
        for (Bottom bot : bottoms) {
            this.blacks.add(this.isMatch(bot, Pixel.NUM_BLACK_PIXELS));
        }
    }

    private boolean isMatch(Bottom bottom, int numToMatch) {
        int val = 0;
        for (int n = 0; n < Pixel.NUM_PIXELS; n++) {
            String bn = this.value.substring(n, n + 1);
            String tn = bottom.value.substring(n, n + 1);
            if ("1".equals(bn) || "1".equals(tn)) {
                val = val + 1;
            }
        }
        return val == numToMatch;

    }

    public boolean isMatchWithBottomBlackOrWhite(Bottom bottom, Boolean blackOrWhite) {
//        boolean w2 = isMatch(bottom, NUM_BLACKS_TOP-1);
//        boolean w3 = isMatch(bottom, NUM_BLACKS_TOP-2);
        if (blackOrWhite) {
            return isMatch(bottom, NUM_BLACK_PIXELS);
        } else {
            return isMatch(bottom, NUM_BLACKS_TOP);
        }
    }

    public boolean equals(Top top) {
        return (this.value.equalsIgnoreCase(top.value));
    }

}
