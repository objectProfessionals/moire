package com.op.moire.multiple;

public class SourceBlackPixel extends SourcePixel {

	public SourceBlackPixel(int numBlacks, int w, int h) {
		this.nBlack = numBlacks;
		initAsBlack(true, w, h);
	}

	@Override
	public SourceBlackPixel copy(int numBlacks) {
		SourceBlackPixel box = new SourceBlackPixel(numBlacks, this.w, this.h);
		box.subpixels = this.subpixels;
		return box;
	}
}
