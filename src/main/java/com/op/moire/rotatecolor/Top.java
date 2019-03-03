package com.op.moire.rotatecolor;

import java.util.ArrayList;
import java.util.HashMap;

public class Top extends Pixel {

    ArrayList<Bottom> bottoms;

    Top(String value, ArrayList<Bottom> bottoms) {
        super();
        this.value = value;
        this.bottoms = bottoms;
    }

    public static ArrayList<Top> createAllFilledTops(ArrayList<Bottom> bottoms) {
        String numBits = "%" + Pixel.NUM_PIXELS + "s";
        ArrayList<Top> tops = new ArrayList<>();
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
                        Top top = new Top(newPadded, bottoms);
                        tops.add(top);
                    }
                }
            }
        }
        return tops;
    }

    private boolean isCombinedBlack(Bottom bottom, double blackNum) {
        double value = 0;
        HashMap<String, Double> hm = new HashMap<>();
        hm.put("W", 0.0);
        hm.put("Y", 0.25);
        hm.put("C", 0.25);
        hm.put("M", 0.75);
        hm.put("B", 1.0);
        for (int n = 0; n < NUM_PIXELS; n++) {
            String bn = this.value.substring(n, n + 1);
            String tn = bottom.value.substring(n, n + 1);
            double valB = hm.get(bn);
            double valT = hm.get(tn);
            value = value + valB + valT;
        }

        //System.out.println("t,b,v:"+this.value+","+bottom.value+","+value);
        return value >= blackNum;
    }

    private boolean AisCombinedBlack(Bottom bottom, double blackNum) {
        double value = 0;
        for (int n = 0; n < NUM_PIXELS; n++) {
            String bn = this.value.substring(n, n + 1);
            String tn = bottom.value.substring(n, n + 1);
            double val = 0;
            if ("C".equals(bn) && "C".equals(tn)) {
                val = 0;
            } else if ("C".equals(bn) && "M".equals(tn)) {
                val = 1;
            } else if ("C".equals(bn) && "Y".equals(tn)) {
                val = 0.5;
            } else if ("C".equals(bn) && "W".equals(tn)) {
                val = 0;
            } else if ("C".equals(bn) && "B".equals(tn)) {
                val = 1;

            } else if ("M".equals(bn) && "C".equals(tn)) {
                val = 0.5;
            } else if ("M".equals(bn) && "M".equals(tn)) {
                val = 1;
            } else if ("M".equals(bn) && "Y".equals(tn)) {
                val = 1;
            } else if ("M".equals(bn) && "W".equals(tn)) {
                val = 0.5;
            } else if ("M".equals(bn) && "B".equals(tn)) {
                val = 1;

            } else if ("Y".equals(bn) && "C".equals(tn)) {
                val = 0;
            } else if ("Y".equals(bn) && "M".equals(tn)) {
                val = 0.5;
            } else if ("Y".equals(bn) && "Y".equals(tn)) {
                val = 0;
            } else if ("Y".equals(bn) && "W".equals(tn)) {
                val = 0;
            } else if ("Y".equals(bn) && "B".equals(tn)) {
                val = 1;

            } else if ("B".equals(bn) && "C".equals(tn)) {
                val = 1;
            } else if ("B".equals(bn) && "M".equals(tn)) {
                val = 1;
            } else if ("B".equals(bn) && "Y".equals(tn)) {
                val = 1;
            } else if ("B".equals(bn) && "W".equals(tn)) {
                val = 1;
            } else if ("B".equals(bn) && "B".equals(tn)) {
                val = 1;

            } else if ("W".equals(bn) && "C".equals(tn)) {
                val = 0;
            } else if ("W".equals(bn) && "M".equals(tn)) {
                val = 1;
            } else if ("W".equals(bn) && "Y".equals(tn)) {
                val = 0;
            } else if ("W".equals(bn) && "W".equals(tn)) {
                val = 0;
            } else if ("W".equals(bn) && "B".equals(tn)) {
                val = 1;
            }
            value = value + val;
        }

        if (value >= blackNum) {
            return true;
        }
        return false;
    }

    public boolean hasCorrectBottomCombination(Bottom bot, boolean value, double blackNum) {
        boolean isBlack = isCombinedBlack(bot, blackNum);
        return (value && isBlack) || (!value && !isBlack);
    }

    Top rotate(double rot) {
        String[] vals = getValueAsStrings();
        if (rot % 360 == 0) {
            return new Top(this.value, bottoms);
        } else if (rot % 360 == 90) {
            String[] vals2 = {vals[2], vals[0], vals[3], vals[1]};
            String rotVal = String.join("", vals2);
            return new Top(rotVal, bottoms);
        } else if (rot % 360 == 180) {
            String[] vals2 = {vals[3], vals[2], vals[1], vals[0]};
            String rotVal = String.join("", vals2);
            return new Top(rotVal, bottoms);
        } else {
            String[] vals2 = {vals[1], vals[3], vals[0], vals[2]};
            String rotVal = String.join("", vals2);
            return new Top(rotVal, bottoms);
        }
    }

    @Override
    public boolean equals(Object top) {
        return this.value.equals(((Top) top).value);
    }


}
