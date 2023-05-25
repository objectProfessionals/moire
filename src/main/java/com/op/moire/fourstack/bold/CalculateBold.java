package com.op.moire.fourstack.bold;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class CalculateBold {

    public HashMap<String, Color> str2Col = new HashMap();

    void setup() {
        str2Col.put("0", getAlpha(Color.WHITE, 0));
        str2Col.put("X", getAlpha(Color.BLACK, 0.5));
        str2Col.put("1", getAlpha(Color.BLACK, 0.5));
        str2Col.put("2", getAlpha(Color.BLACK, 0.5));
        str2Col.put("3", getAlpha(Color.BLACK, 0.5));
        str2Col.put("4", getAlpha(Color.BLACK, 0.25));
        str2Col.put("5", getAlpha(Color.BLACK, 1));
        str2Col.put("6", getAlpha(Color.BLACK, 1));
        str2Col.put("C", getAlpha(Color.CYAN, 0.5));
        str2Col.put("M", getAlpha(Color.MAGENTA, 0.5));
        str2Col.put("Y", getAlpha(Color.YELLOW, 0.5));
        str2Col.put("R", getAlpha(Color.RED, 0.5));
        str2Col.put("G", getAlpha(Color.GREEN, 0.5));
        str2Col.put("B", getAlpha(Color.BLUE, 0.5));

        str2Col.put("x", getAlpha(Color.BLACK, 0.25));
        str2Col.put("c", getAlpha(Color.CYAN, 0.25));
        str2Col.put("m", getAlpha(Color.MAGENTA, 0.25));
        str2Col.put("y", getAlpha(Color.YELLOW, 0.25));
    }

    private Color getAlpha(Color col, double d) {
        int al = (int) (d * 255);
        return new Color(col.getRed(), col.getGreen(), col.getBlue(), al);
    }


    public ArrayList<String[]> calculate(boolean[] blacks) {
        ArrayList<String[]> ret = new ArrayList<>();

        int r1 = 8;
        String c11 = "x";
        String c21 = (blacks[1] ? "1" : "0");
        String c41 = "x";
        String c81 = "x";

        int r2 = 8;
        String c12 = (blacks[0] ? "1" : "0");
        String c22 = "x";
        String c42 = "x";
        String c82 = "x";

        int r3_4 = 6;
        String c13_4 = "x0";
        String c23_4 = (blacks[2] ? "0" : "3") + (blacks[2] ? "3" : "0");
        String c43_4 = "xx";
        String c83_4 = "xx";

        int r5_6 = 8;
        String c1_56 = "00";
        String c2_56 = "00";
        String c4_56 = (blacks[3] ? "4" : "0") + (blacks[3] ? "4" : "0");
        String c8_56 = "xx";

        int r7_8 = 8;
        String c1_78 = "50";
        String c2_78 = "00";
        String c4_78 = (blacks[4] ? "0" : "5") + (blacks[4] ? "5" : "0");
        String c8_78 = "xx";

        int r9_10 = 8;
        String c1_910 = "00";
        String c2_910 = "60";
        String c4_910 = (blacks[5] ? "0" : "6") + (blacks[5] ? "6" : "0");
        String c8_910 = "xx";

        int r11_13 = 8;
        String c1_1113 = "CMY";
        String c2_1113 = "CMY";
        String c4_1113 = (blacks[6] ? "Y" : "0") + (blacks[6] ? "C" : "0") + (blacks[6] ? "M" : "0");
        String c8_1113 = "xxx";

        String v1 = r(c11, r2)
                + r(c12, r2)
                + r(c13_4, r3_4)
                + r(c1_56, r5_6)
                + r(c1_78, r7_8)
                + r(c1_910, r9_10)
                + r(c1_1113, r11_13)
                + "x000MYC00CMY000cmy";
        String v2 = r(c21, r1)
                + r(c22, r2)
                + r(c23_4, r3_4)
                + r(c2_56, r5_6)
                + r(c2_78, r7_8)
                + r(c2_910, r9_10)
                + r(c2_1113, r11_13)
                + "000x0CMY00000CMYmyc";
        String v4 = r(c41, r1)
                + r(c42, r2)
                + r(c43_4, r3_4)
                + r(c4_56, r5_6)
                + r(c4_78, r7_8)
                + r(c4_910, r9_10)
                + r(c4_1113, r11_13)
                + "00000000x0MYCMYCycm";
        String v8 = r(c81, r1)
                + r(c82, r2)
                + r(c83_4, r3_4)
                + r(c8_56, r5_6)
                + r(c8_78, r7_8)
                + r(c8_910, r9_10)
                + r(c8_1113, r11_13)
                + (blacks[7] ? "1" : "0")
                + (blacks[8] ? "1" : "0") + (blacks[8] ? "1" : "0")
                + (blacks[9] ? "1" : "0") + (blacks[9] ? "1" : "0")
                + (blacks[10] ? "Y" : "0") + (blacks[10] ? "C" : "0") + (blacks[10] ? "M" : "0")
                + (blacks[11] ? "0" : "1") + (blacks[9] ? "1" : "0")
                + (blacks[12] ? "Y" : "0") + (blacks[12] ? "C" : "0") + (blacks[12] ? "M" : "0")
                + (blacks[13] ? "Y" : "0") + (blacks[13] ? "C" : "0") + (blacks[13] ? "M" : "0")
                + (blacks[14] ? "1" : "0") + (blacks[14] ? "1" : "0") + (blacks[14] ? "1" : "0");
        String[] arr = {v1, v2, v4, v8};
        System.out.println(v1.length() + ":" + v2.length() + ":" + v4.length() + ":" + v8.length());
        ret.add(arr);
        return ret;
    }

    String r(String s, int num) {
        return new String(new char[num]).replace("\0", s);
    }
}
