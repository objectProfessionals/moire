package com.op.moire.multipleslide;

import java.util.ArrayList;

public class Bottom extends Pixel {


    public Bottom(String value) {
        super();
        this.value = value;
    }

    static ArrayList<Bottom> setupBlacks() {
        String numBits = "%"+ Pixel.NUM_PIXELS+"s";
        ArrayList<Bottom> bottoms = new ArrayList<>();
        int allVariations = (int) Math.pow(2, Pixel.NUM_PIXELS);
        for (int i = 0; i < allVariations; i++) {
            String bin = Integer.toBinaryString(i);
            String padded = String.format(numBits, bin).replaceAll(" ", "0");
            long count = padded.chars().filter(ch -> ch == '1').count();
            if (count == NUM_BLACKS_BOTTOM) {
                Bottom bottom = new Bottom(padded);
                bottoms.add(bottom);
            }
        }
        return bottoms;
    }

}
