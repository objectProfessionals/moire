package com.op.moire.multiple;

public class HiddenWhitePixel extends HiddenPixel {
    public HiddenWhitePixel(int numBlacks, int w, int h) {
        this.nBlack = numBlacks;
        initAsBlack(false, w, h);
    }
}
