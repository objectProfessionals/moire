package com.op.moire.multipleslide;

import java.util.ArrayList;
import java.util.Arrays;

public class Top extends Pixel {

    ArrayList<Bottom> bottoms;
    ArrayList<Boolean> blacks = new ArrayList();

    Top(String value, ArrayList<Bottom> bottoms) {
        super();
        this.value = value;
        this.bottoms = bottoms;
        initBlacks();
    }

    static ArrayList<Top> setupBlacks(ArrayList<Bottom> bottoms) {
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


    private void initBlacks() {
        int i = 0;
        for (Bottom bottom : bottoms) {
            blacks.add(isCombinedBlack(bottom));
            i++;
        }
    }

    private boolean isCombinedBlack(Bottom bottom) {
        int val = 0;
        for (int n = 0; n < Pixel.NUM_PIXELS; n++) {
            String bn = this.value.substring(n, n + 1);
            String tn = bottom.value.substring(n, n + 1);
            if ("1".equals(bn) || "1".equals(tn)) {
                val = val + 1;
            }
        }
        return val >= Pixel.NUM_BLACK_PIXELS;
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
        while (count < 3) {
            int rnd = (int) (Math.random() * ((blacks.size()) * 2));
            if (matchInds[0] == rnd % (blacks.size())) {
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
        while (count < 3) {
            int rnd = (int) (Math.random() * ((blacks.size()) * 2));
            if (matchInds[0] == rnd || matchInds[1] == rnd) {
                continue;
            }
            if (matchInds[0] == rnd % (blacks.size())) {
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

    public int[] matchBlacksRnd(Boolean[] blacksToMatch, Integer[] in, int minMissed) {
        int missed = blacks.get(in[0]) != blacksToMatch[0] ? 1 : 0;
        missed = missed + (blacks.get(in[1]) != blacksToMatch[1] ? 1 : 0);
        missed = missed + (blacks.get(in[2]) != blacksToMatch[2] ? 1 : 0);
        missed = missed + (blacks.get(in[3]) != blacksToMatch[3] ? 1 : 0);

        boolean[] toMatch = new boolean[blacksToMatch.length];
        for (int c=0; c<blacksToMatch.length; c++) {
            toMatch[c] = blacksToMatch[c];
        }

        ArrayList<Boolean> allblacks = new ArrayList<>();
        allblacks.addAll(blacks);
        allblacks.addAll(blacks);
        int[] matchInds = new int[blacksToMatch.length];

        for (int c =0; c<in.length; c++) {
            matchInds[c] = in[c];
        }

        if ( minMissed == 1) {
            if (allblacks.get(0) == toMatch[4]) {
                matchInds[4] = matchInds[0];
                return matchInds;
            }
            if (allblacks.get(1) == toMatch[4]) {
                matchInds[4] = matchInds[1];
                return matchInds;
            }
            if (allblacks.get(2) == toMatch[4]) {
                matchInds[4] = matchInds[2];
                return matchInds;
            }
        }
        if ( missed > minMissed) {
            int[] pos = new int[blacksToMatch.length];
            Arrays.fill(pos, -1);

            return pos;
        }
        int count = 4;
        //int[] matchInds = {i2, i3, -1};

        while (count < blacksToMatch.length) {
            int rnd = (int) (Math.random() * ((blacks.size()) * 2));
            if (matchInds[0] == rnd % (blacks.size())) {
                continue;
            }
            if (matchInds[1] == rnd % (blacks.size())) {
                continue;
            }
            if (matchInds[2] == rnd % (blacks.size())) {
                continue;
            }
            if (matchInds[3] == rnd % (blacks.size())) {
                continue;
            }
            if (allblacks.get(rnd) == toMatch[count]) {
                matchInds[count] = rnd % (blacks.size());
                count++;
            }
        }

        return matchInds;
    }

    public int[] matchBlacksRndFirst(Boolean[] blacksToMatch) {
        int count = 0;
        //int[] matchInds = {-1, -1, -1};
        int[] matchInds = new int[blacksToMatch.length];
        Arrays.fill(matchInds, -1);

        boolean[] toMatch = new boolean[blacksToMatch.length];
        Arrays.fill(toMatch, false);
        ArrayList<Boolean> allblacks = new ArrayList<>();
        allblacks.addAll(blacks);
        allblacks.addAll(blacks);
        while (count < 5) {
            int rnd = (int) (Math.random() * ((blacks.size()) * 2));
            if (matchInds[0] == rnd || matchInds[1] == rnd) {
                continue;
            }
            if (matchInds[0] == rnd % (blacks.size())) {
                continue;
            }
            if (matchInds[1] == rnd % (blacks.size())) {
                continue;
            }
            if (matchInds[2] == rnd % (blacks.size())) {
                continue;
            }
            if (matchInds[3] == rnd % (blacks.size())) {
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
