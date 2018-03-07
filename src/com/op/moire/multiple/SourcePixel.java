package com.op.moire.multiple;

public abstract class SourcePixel extends Pixel {
	public abstract SourcePixel copy(int numBlacks);

	public void resetAllWhite() {
		initSubpixels(false, this.w, this.h);
	}

	public void print() {
		System.out.println(this.subpixels);
	}

	@Override
	public String toString() {
		return this.subpixels;
	}
}
