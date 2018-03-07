package com.op.moire.multiple;

public class HiddenBlackPixel extends HiddenPixel {
	public HiddenBlackPixel(int numBlacks, int w, int h) {
		this.nBlack = numBlacks;
		initAsBlack(true, w, h);
	}
}
