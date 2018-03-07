package com.op.moire.multiple;

public class SourceWhitePixel extends SourcePixel {

	public SourceWhitePixel(int numBlacks, int w, int h) {
		this.nBlack = numBlacks;
		initAsBlack(false, w, h);
	}

	@Override
	public SourceWhitePixel copy(int numBlacks) {
		SourceWhitePixel box = new SourceWhitePixel(numBlacks, this.w, this.h);
		box.subpixels = this.subpixels;
		return box;
	}
}
