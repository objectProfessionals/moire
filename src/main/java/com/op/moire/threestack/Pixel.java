package com.op.moire.threestack;

import java.util.ArrayList;
import java.util.HashMap;

public class Pixel {

    public static void initPixelsRanges() {
        String[] singles = {"C", "M", "Y"};
        String[] choices = {"CC", "MM", "YY", "CM", "CY", "MC", "MY", "YC", "YM"};
        String[] order = {"Y","C","M","G","R","B","X",};
        double[] values = {1, 1.5, 2, 2.5, 3, 3.5, 4.5};
        HashMap<String, String> calculations = new HashMap<>();
        calculations.put("CC", "C");
        calculations.put("CM", "B");
        calculations.put("MC", "B");
        calculations.put("CY", "G");
        calculations.put("YC", "G");
        calculations.put("MM", "M");
        calculations.put("YY", "Y");
        calculations.put("YM", "R");
        calculations.put("MY", "R");

        calculations.put("RR", "R");
        calculations.put("GG", "G");
        calculations.put("BB", "B");

        calculations.put("RC", "X");
        calculations.put("RM", "R");
        calculations.put("RY", "R");

        calculations.put("GC", "G");
        calculations.put("GM", "X");
        calculations.put("GY", "G");

        calculations.put("BC", "B");
        calculations.put("BM", "B");
        calculations.put("BY", "X");

        calculations.put("RG", "X");
        calculations.put("GR", "X");
        calculations.put("RB", "X");
        calculations.put("BR", "X");
        calculations.put("GB", "X");
        calculations.put("BG", "X");
    }
}
