package com.op.moire.multiple;

import java.util.ArrayList;

public abstract class Pixel {

    protected int w = -1;
    protected int h = -1;
    protected int n = -1;

    protected String subpixels = "";
    protected int nBlack = -1;

    protected void initSubpixels(boolean black, int w, int h) {
        this.w = w;
        this.h = h;
        this.n = w * h;
        this.subpixels = "";
        for (int i = 0; i < n; i++) {
            this.subpixels = subpixels + (black ? "b" : "w");
        }
    }

    protected void initAsBlack(boolean black, int w, int h) {
        initSubpixels(true, w, h);
        if (black) {
            while (getNumberOfBlacks() > nBlack) {
                changeRandomBlackToWhite();
            }
        } else {
            int blacksNeeded = nBlack;
            while (getNumberOfBlacks() > blacksNeeded) {
                changeRandomBlackToWhite();
            }
        }
    }

    public int getNumberOfBlacks() {
        int num = 0;
        for (int i = 0; i < subpixels.length(); i++) {
            String sub = subpixels.substring(i, i + 1);
            if (sub.equals("b")) {
                num++;
            }
        }
        return num;
    }

    protected void changeRandomBlackToWhite() {
        int pos = (int) (Math.random() * n);
        changeBlackToWhite(pos);
    }

    protected void changeBlackToWhite(int pos) {
        String newSubs = subpixels.substring(0, pos) + "w"
                + subpixels.substring(pos + 1);
        subpixels = newSubs;
    }

    protected void changeRandomWhiteToBlack() {
        int pos = (int) (Math.random() * n);
        changeWhiteToBlack(pos);
    }

    protected void changeWhiteToBlack(int pos) {
        String sub = subpixels.substring(pos, pos + 1);
        boolean white = "w".equals(sub);
        if (white) {
            String newSubs = subpixels.substring(0, pos) + "b"
                    + subpixels.substring(pos + 1);
            subpixels = newSubs;
        }
    }

    protected String get(int pos) {
        return subpixels.substring(pos, pos + 1);
    }

    public ArrayList<Integer> getWhitePositions() {
        ArrayList<Integer> pos = new ArrayList<Integer>();

        for (int i = 0; i < subpixels.length(); i++) {
            String sub = subpixels.substring(i, i + 1);
            if (sub.equals("w")) {
                pos.add(i);
            }
        }

        return pos;
    }

}
