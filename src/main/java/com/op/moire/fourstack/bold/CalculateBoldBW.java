package com.op.moire.fourstack.bold;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class CalculateBoldBW {

    public HashMap<String, Color> str2Col = new HashMap();

    void setup() {
        str2Col.put("0", getAlpha(Color.WHITE, 0));
        str2Col.put("X", getAlpha(Color.BLACK, 0.5));
        str2Col.put("x", getAlpha(Color.BLACK, 0.25));
        str2Col.put("1", getAlpha(Color.BLACK, 1));
        str2Col.put("2", getAlpha(Color.BLACK, 1));
        str2Col.put("3", getAlpha(Color.BLACK, 0.1));
        str2Col.put("4", getAlpha(Color.BLACK, 1));
        str2Col.put("5", getAlpha(Color.BLACK, 0.5));
        str2Col.put("6", getAlpha(Color.BLACK, 0.5));
        str2Col.put("7", getAlpha(Color.BLACK, 0.25));
        str2Col.put("8", getAlpha(Color.BLACK, 1));
        str2Col.put("9", getAlpha(Color.BLACK, 0.5));
        str2Col.put("A", getAlpha(Color.BLACK, 0.5));
        str2Col.put("B", getAlpha(Color.BLACK, 0.25));
        str2Col.put("C", getAlpha(Color.BLACK, 0.5));
        str2Col.put("D", getAlpha(Color.BLACK, 0.25));
        str2Col.put("E", getAlpha(Color.BLACK, 0.25));
        str2Col.put("F", getAlpha(Color.BLACK, 0.1));

        str2Col.put("Y", getAlpha(Color.CYAN, 0.5));
        str2Col.put("M", getAlpha(Color.MAGENTA, 0.5));
        str2Col.put("Y", getAlpha(Color.YELLOW, 0.5));
        str2Col.put("R", getAlpha(Color.RED, 0.5));
        str2Col.put("N", getAlpha(Color.GREEN, 0.5));
        str2Col.put("L", getAlpha(Color.BLUE, 0.5));

        str2Col.put("y", getAlpha(Color.CYAN, 0.25));
        str2Col.put("m", getAlpha(Color.MAGENTA, 0.25));
        str2Col.put("y", getAlpha(Color.YELLOW, 0.25));
        str2Col.put("r", getAlpha(Color.RED, 0.25));
        str2Col.put("n", getAlpha(Color.GREEN, 0.25));
        str2Col.put("l", getAlpha(Color.BLUE, 0.25));
    }

    private Color getAlpha(Color col, double d) {
        int al = (int) (d * 255);
        return new Color(col.getRed(), col.getGreen(), col.getBlue(), al);
    }


    public ArrayList<String[]> calculate(boolean[] blacks) {
        ArrayList<String[]> ret = new ArrayList<>();

        int r1a = 12;
        String c11a = "x";
        String c21a = (blacks[1] ? "1" : "0");
        String c41a = "X";
        String c81a = "X";

        int r1b = 12;
        String c11b = (blacks[0] ? "1" : "0");
        String c21b = "X";
        String c41b = "X";
        String c81b = "X";

        int r3 = 10;
        String c13 = "x0";
        String c23 = (blacks[2] ? "0" : "3") + (blacks[2] ? "3" : "0");
        String c43 = "XX";
        String c83 = "XX";

        int r4 = 12;
        String c14 = "0";
        String c24 = "0";
        String c44 = (blacks[3] ? "4" : "0");
        String c84 = "X";

        int r5 = 8;
        String c15 = "x0";
        String c25 = "00";
        String c45 = (blacks[4] ? "0" : "5") + (blacks[4] ? "5" : "0");
        String c85 = "XX";

        int r6 = 8;
        String c16 = "00";
        String c26 = "x0";
        String c46 = (blacks[5] ? "0" : "6") + (blacks[5] ? "6" : "0");
        String c86 = "XX";

        int r7 = 8;
        String c17 = "00";
        String c27 = "00";
        String c47 = (blacks[6] ? "0" : "7") + (blacks[6] ? "7" : "0");
        String c87 = "xx";

        int r8 = 24;
        String c18 = "0";
        String c28 = "0";
        String c48 = "0";
        String c88 = (blacks[7] ? "0" : "8");

        int r9 = 8;
        String c19 = "x0";
        String c29 = "00";
        String c49 = "00";
        String c89 = (blacks[8] ? "0" : "9") + (blacks[8] ? "9" : "0");

        int rA = 8;
        String c1A = "00";
        String c2A = "x0";
        String c4A = "00";
        String c8A = (blacks[9] ? "0" : "A") + (blacks[9] ? "A" : "0");

        int rB = 4;
        String c1B = "x000";
        String c2B = "00x0";
        String c4B = "0000";
        String c8B = (blacks[10] ? "0" : "B") + (blacks[10] ? "B" : "0") + (blacks[10] ? "0" : "B") + (blacks[10] ? "B" : "0");

        int rC = 8;
        String c1C = "00";
        String c2C = "00";
        String c4C = "x0";
        String c8C = (blacks[11] ? "0" : "C") + (blacks[11] ? "C" : "0");

        int rD = 2;
        String c1D = "x000";
        String c2D = "0000";
        String c4D = "00x0";
        String c8D = (blacks[12] ? "0" : "D") + (blacks[12] ? "D" : "0") + (blacks[12] ? "0" : "D") + (blacks[12] ? "D" : "0");

        int rE = 2;
        String c1E = "0000";
        String c2E = "x000";
        String c4E = "00x0";
        String c8E = (blacks[13] ? "0" : "E") + (blacks[13] ? "E" : "0") + (blacks[13] ? "0" : "E") + (blacks[13] ? "E" : "0");

        int rF = 2;
        String c1F = "x00000";
        String c2F = "00x000";
        String c4F = "0000x0";
        String c8F = (blacks[14] ? "0" : "F") + (blacks[14] ? "F" : "0") + (blacks[14] ? "0" : "F") + (blacks[14] ? "F" : "0") + (blacks[14] ? "0" : "F") + (blacks[14] ? "F" : "0");

        String v1 = r(c11a, r1a)
                + r(c11b, r1b)
                + r(c13, r3)
                + r(c14, r4)
                + r(c15, r5)
                + r(c16, r6)
                + r(c17, r7)
                + r(c18, r8)
                + r(c19, r9)
                + r(c1A, rA)
                + r(c1B, rB)
                + r(c1C, rC)
                + r(c1D, rD)
                + r(c1E, rE)
                + r(c1F, rF);

        String v2 = r(c21a, r1a)
                + r(c21b, r1b)
                + r(c23, r3)
                + r(c24, r4)
                + r(c25, r5)
                + r(c26, r6)
                + r(c27, r7)
                + r(c28, r8)
                + r(c29, r9)
                + r(c2A, rA)
                + r(c2B, rB)
                + r(c2C, rC)
                + r(c2D, rD)
                + r(c2E, rE)
                + r(c2F, rF);

        String v4 = r(c41a, r1a)
                + r(c41b, r1b)
                + r(c43, r3)
                + r(c44, r4)
                + r(c45, r5)
                + r(c46, r6)
                + r(c47, r7)
                + r(c48, r8)
                + r(c49, r9)
                + r(c4A, rA)
                + r(c4B, rB)
                + r(c4C, rC)
                + r(c4D, rD)
                + r(c4E, rE)
                + r(c4F, rF);

        String v8 = r(c81a, r1a)
                + r(c81b, r1b)
                + r(c83, r3)
                + r(c84, r4)
                + r(c85, r5)
                + r(c86, r6)
                + r(c87, r7)
                + r(c88, r8)
                + r(c89, r9)
                + r(c8A, rA)
                + r(c8B, rB)
                + r(c8C, rC)
                + r(c8D, rD)
                + r(c8E, rE)
                + r(c8F, rF);

        String[] arr = {v1, v2, v4, v8};
        System.out.println(v1.length() + ":" + v2.length() + ":" + v4.length() + ":" + v8.length());
        ret.add(arr);
        return ret;
    }

    String r(String s, int num) {
        return new String(new char[num]).replace("\0", s);
    }
}
