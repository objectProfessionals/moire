package com.op.moire.multipleslide;

import java.util.ArrayList;

public class Top extends Pixel {

    static final int NUM_BLACKS = 2;
    ArrayList<Bottom> bottoms;
    ArrayList<Boolean> blacks = new ArrayList();

    Top(String value, ArrayList<Bottom> bottoms) {
        super();
        this.value = value;
        this.bottoms = bottoms;
        initBlacks();
    }

    static ArrayList<Top> setupBlacks(ArrayList<Bottom> bottoms) {
        String numBits = "%"+ Pixel.NUM_PIXELS+"s";
        ArrayList<Top> tops = new ArrayList<>();
        int allVariations = (int) Math.pow(2, Pixel.NUM_PIXELS);
        for (int i = 0; i < allVariations; i++) {
            String bin = Integer.toBinaryString(i);
            String padded = String.format(numBits, bin).replaceAll(" ", "0");
            long count = padded.chars().filter(ch -> ch == '1').count();
            if (count == NUM_BLACKS) {
                Top top = new Top(padded, bottoms);
                tops.add(top);
            }
        }
        return tops;
    }



    private void initBlacks() {
        int i = 0;
        for (Bottom bottom : bottoms) {
            blacks.add(isBlack(bottom));
            i++;
        }
    }

    private boolean isBlack(Bottom bottom) {
        int val = 0;
        for (int n = 0; n < NUM_PIXELS; n++) {
            String bn = this.value.substring(n, n + 1);
            String tn = bottom.value.substring(n, n + 1);
            if ("1".equals(bn) || "1".equals(tn)) {
                val = val + 1;
            }
        }
        return val > 2;
    }


    public int[] matchBlacksRnd(boolean black1, boolean black2, boolean black3, int i2, int i3) {
        if (blacks.get(i2) != black1 || blacks.get(i3) != black2) {
            int[] pos = {-1, -1, -1};
            return pos;
        }
        int count = 2;
        int[] matchInds = {i2, i3, -1};
        boolean[] toMatch = {black1, black2, black3};
        ArrayList<Boolean> allblacks = new ArrayList<>();
        allblacks.addAll(blacks);
        allblacks.addAll(blacks);
        while (count < NUM_PIXELS-1) {
            int rnd = (int) (Math.random() * (NUM_PIXELS*2));
            if (matchInds[0] == rnd || matchInds[1] == rnd) {
                continue;
            }
            if (matchInds[1] == rnd % (blacks.size())) {
                continue;
            }
            if (allblacks.get(rnd) == toMatch[count]) {
                matchInds[count] = rnd % (blacks.size());
                count++;
            }
        }

        return matchInds;
    }

    public int[] matchBlacksRndFirst(boolean black1, boolean black2, boolean black3) {
        int count = 0;
        int[] matchInds = {-1, -1, -1};
        boolean[] toMatch = {black1, black2, black3};
        ArrayList<Boolean> allblacks = new ArrayList<>();
        allblacks.addAll(blacks);
        allblacks.addAll(blacks);
        while (count < NUM_PIXELS-1) {
            int rnd = (int) (Math.random()  * (NUM_PIXELS*2));
            if (matchInds[0] == rnd || matchInds[1] == rnd) {
                continue;
            }
            if (matchInds[1] == rnd % (blacks.size())) {
                continue;
            }
            if (allblacks.get(rnd) == toMatch[count]) {
                matchInds[count] = rnd % (blacks.size());
                count++;
            }
        }
        return matchInds;
    }

    public int[] matchBlacks(boolean black1, boolean black2, boolean black3, int i2, int i3) {
        if (blacks.get(i2) != black1 || blacks.get(i3) != black2) {
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
