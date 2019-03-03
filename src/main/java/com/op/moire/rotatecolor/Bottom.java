package com.op.moire.rotatecolor;

import java.util.ArrayList;

public class Bottom extends Pixel {


    public Bottom(String value) {
        super();
        this.value = value;
    }

    public static ArrayList<Bottom> createAllFilledBottoms() {
        String numBits = "%" + Pixel.NUM_PIXELS + "s";
        ArrayList<Bottom> bottoms = new ArrayList<>();
        int allVariations = (int) Math.pow(2, Pixel.NUM_PIXELS);
        int colVariations = Pixel.COLS_TOP.length;
        String numCols = "%" + colVariations + "s";
        //c m y b
        System.out.println("Tops...");
        for (int i = 0; i < allVariations; i++) {
            String bin = Integer.toBinaryString(i);
            String padded = String.format(numBits, bin).replaceAll(" ", "W").replaceAll("0", "W");
            long count = padded.chars().filter(ch -> ch == '1').count();
            if (count == NUM_BLACKS_TOP) {
                for (int j = 0; j < colVariations; j++) {
                    for (int k = 0; k < colVariations; k++) {
                        String newPadded = padded.replaceFirst("1", COLS_TOP[j]).replaceFirst("1", COLS_TOP[k]);
                        System.out.println(padded + ":" + newPadded);
                        Bottom bot = new Bottom(newPadded);
                        bottoms.add(bot);
                    }
                }
            }
        }
        return bottoms;
    }
    static ArrayList<Bottom> AcreateAllFilledBottoms() {
        String numBits = "%" + Pixel.NUM_PIXELS + "s";
        ArrayList<Bottom> bottoms = new ArrayList<>();
        int allVariations = (int) Math.pow(2, Pixel.NUM_PIXELS);
        int colVariations = Pixel.COLS_BOTTOM.length;
        String numCols = "%" + colVariations + "s";
        //c m y b
        System.out.println("Bottoms...");
        for (int i = 0; i < allVariations; i++) {
            String bin = Integer.toBinaryString(i);
            String padded = String.format(numBits, bin).replaceAll(" ", "W").replaceAll("0", "W");
            long count = padded.chars().filter(ch -> ch == '1').count();
            if (count == NUM_BLACKS_BOTTOM) {
                for (int j=0; j< colVariations; j++) {
                    String newPadded = padded.replaceAll("1", COLS_BOTTOM[j]);
                    System.out.println(padded +":"+newPadded);
                    Bottom bottom = new Bottom(newPadded);
                    bottoms.add(bottom);
                }
            }
        }
        return bottoms;
    }

    @Override
    public boolean equals(Object bottom) {
        return this.value.equals(((Bottom)bottom).value);
    }


}
