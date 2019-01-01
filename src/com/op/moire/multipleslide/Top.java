package com.op.moire.multipleslide;

import java.util.ArrayList;

public class Top extends Pixel {

    ArrayList<Bottom> bottoms;
    boolean[] blacks = {false, false, false, false};

    Top(String value, ArrayList<Bottom> bottoms) {
        super();
        this.value = value;
        this.bottoms = bottoms;
        initBlacks();
    }

    private void initBlacks() {
        int i = 0;
        for (Bottom bottom : bottoms) {
            blacks[i] = isBlack(bottom);
            i++;
        }
    }

    private boolean isBlack(Bottom bottom) {
        int val = 0;
        for (int n = 0; n < 4; n++) {
            String bn = this.value.substring(n, n + 1);
            String tn = bottom.value.substring(n, n + 1);
            if ("1".equals(bn) || "1".equals(tn)) {
                val = val + 1;
            }
        }
        return val > 2;
    }


    public int[] matchBlacksRnd(boolean black1, boolean black2, boolean black3, int i2, int i3) {
        if (blacks[i2] != black1 || blacks[i3] != black2) {
            int[] pos = {-1, -1, -1};
            return pos;
        }
        int count = 2;
        int[] matchInds = {i2, i3, -1};
        boolean[] toMatch = {black1, black2, black3};
        boolean[] allblacks = {blacks[0], blacks[1], blacks[2], blacks[3], blacks[0], blacks[1], blacks[2], blacks[3]};
        while (count < 3) {
            int rnd = (int) (Math.random() * 8);
            if (matchInds[0] == rnd || matchInds[1] == rnd) {
                continue;
            }
            if (matchInds[1] == rnd % (blacks.length)) {
                continue;
            }
            if (allblacks[rnd] == toMatch[count]) {
                matchInds[count] = rnd % (blacks.length);
                count++;
            }
        }

        return matchInds;
    }

    public int[] matchBlacksRndFirst(boolean black1, boolean black2, boolean black3) {
        int count = 0;
        int[] matchInds = {-1, -1, -1};
        boolean[] toMatch = {black1, black2, black3};
        boolean[] allblacks = {blacks[0], blacks[1], blacks[2], blacks[3], blacks[0], blacks[1], blacks[2], blacks[3]};
        while (count < 3) {
            int rnd = (int) (Math.random() * 8);
            if (matchInds[0] == rnd || matchInds[1] == rnd) {
                continue;
            }
            if (matchInds[1] == rnd % (blacks.length)) {
                continue;
            }
            if (allblacks[rnd] == toMatch[count]) {
                matchInds[count] = rnd % (blacks.length);
                count++;
            }
        }
        return matchInds;
    }

    public int[] matchBlacks(boolean black1, boolean black2, boolean black3, int i2, int i3) {
        if (blacks[i2] != black1 || blacks[i3] != black2) {
            int[] pos = {-1, -1, -1};
            return pos;
        }
        int[] pos = {i2, i3, -1};
        int i = 0;
        if (black1 == black2 && black1 == black3 && black2 == black3) {
            pos[2] = i2;
        } else {
            for (boolean bl : blacks) {
                if (i != i2 && i != i3 && bl == black3) {
                    pos[2] = i;
                    break;
                }
                i++;
            }
        }

        return pos;
    }

    public int[] matchBlacks(boolean black1, boolean black2, boolean black3) {
        int ind = 0;
        int count = 0;
        int[] matchInds = {-1, -1, -1};
        boolean[] toMatch = {black1, black2, black3};
        for (boolean bl : blacks) {
            if (count < 3 && bl == toMatch[count]) {
                matchInds[count] = ind;
                ind++;
                count++;
                continue;
            }

            ind++;
        }


        if (matchInds[2] == -1) {
            matchInds[2] = matchInds[0];
        }
        return matchInds;
    }
}
