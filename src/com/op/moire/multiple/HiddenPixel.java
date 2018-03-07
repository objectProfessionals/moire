package com.op.moire.multiple;

public abstract class HiddenPixel extends Pixel {
	public void print() {
		System.out.println(this.subpixels);
	}

	@Override
	public String toString() {
		return this.subpixels;
	}
}
