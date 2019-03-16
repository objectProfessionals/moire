package com.op.moire.rotate;

import java.awt.*;
import java.util.ArrayList;

public class Bottom extends Pixel {


    public Bottom(String value, Color[] cols) {
        super();
        this.value = value;
        this.cols = cols;
    }

    static ArrayList<Bottom> createAllFilledBottoms() {
        String numBits = "%" + Pixel.NUM_PIXELS + "s";
        ArrayList<Bottom> bottoms = new ArrayList<>();
        int allVariations = (int) Math.pow(2, Pixel.NUM_PIXELS);
        for (int i = 0; i < allVariations; i++) {
            String bin = Integer.toBinaryString(i);
            String padded = String.format(numBits, bin).replaceAll(" ", "0");
            long count = padded.chars().filter(ch -> ch == '1').count();
            if (count == NUM_BLACKS_BOTTOM) {
                Bottom bottom = new Bottom(padded, null);
                bottoms.add(bottom);
            }
        }
        return bottoms;
    }

    public boolean equals(Bottom bottom) {
        return this.value.equals(bottom.value);
    }
}
