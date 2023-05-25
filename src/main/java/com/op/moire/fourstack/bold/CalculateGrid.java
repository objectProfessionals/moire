package com.op.moire.fourstack.bold;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class CalculateGrid {

    public HashMap<String, Color> str2Col = new HashMap();
    public HashMap<String, Double> cell2Weight = new HashMap();
    public HashMap<Integer, Integer[]> val2pos = new HashMap();
    public int ps[] = {
            1, 1, 1, 1, 2, 2, 2, 2,
            1, 1, 3, 3, 3, 3, 2, 2,
            1, 9, 15, 11, 7, 15, 6, 2,
            1, 9, 11, 5, 10, 7, 6, 2,
            8, 9, 13, 10, 5, 14, 6, 4,
            8, 9, 15, 13, 14, 15, 6, 4,
            8, 8, 12, 12, 12, 12, 4, 4,
            8, 8, 8, 8, 4, 4, 4, 4
    };

    void setup() {
        cell2Weight.put("S", 1.0);
        cell2Weight.put("P", 1.0);
        cell2Weight.put("T", 1.0);
        cell2Weight.put("Q", 1.0);

        str2Col.put("00", getAlpha(Color.WHITE, 0));

        str2Col.put("01", getAlpha(Color.BLACK, cell2Weight.get("S")));
        str2Col.put("02", getAlpha(Color.BLACK, cell2Weight.get("S")));
        str2Col.put("04", getAlpha(Color.BLACK, cell2Weight.get("S")));
        str2Col.put("08", getAlpha(Color.BLACK, cell2Weight.get("S")));

        str2Col.put("03", getAlpha(Color.BLACK, cell2Weight.get("P")));
        str2Col.put("05", getAlpha(Color.BLACK, cell2Weight.get("P")));
        str2Col.put("06", getAlpha(Color.BLACK, cell2Weight.get("P")));
        str2Col.put("09", getAlpha(Color.BLACK, cell2Weight.get("P")));
        str2Col.put("10", getAlpha(Color.BLACK, cell2Weight.get("P")));
        str2Col.put("12", getAlpha(Color.BLACK, cell2Weight.get("P")));

        str2Col.put("07", getAlpha(Color.BLACK, cell2Weight.get("T")));
        str2Col.put("11", getAlpha(Color.BLACK, cell2Weight.get("T")));
        str2Col.put("13", getAlpha(Color.BLACK, cell2Weight.get("T")));
        str2Col.put("14", getAlpha(Color.BLACK, cell2Weight.get("T")));

        str2Col.put("15", getAlpha(Color.BLACK, cell2Weight.get("Q")));

        Integer[] arr1 = {0, 1, 6};
        val2pos.put(1, arr1);
        Integer[] arr2 = {4, 5, 11};
        val2pos.put(2, arr2);
        Integer[] arr4 = {29, 34, 35};
        val2pos.put(4, arr4);
        Integer[] arr8 = {24, 30, 31};
        val2pos.put(8, arr8);

        Integer[] arr3 = {2, 3};
        val2pos.put(3, arr3);
        Integer[] arr5 = {14, 21};
        val2pos.put(5, arr5);
        Integer[] arr6 = {17, 23};
        val2pos.put(6, arr6);
        Integer[] arr9 = {12, 18};
        val2pos.put(9, arr9);
        Integer[] arr10 = {15, 20};
        val2pos.put(10, arr10);
        Integer[] arr12 = {32, 33};
        val2pos.put(12, arr12);

        Integer[] arr7 = {9, 16};
        val2pos.put(7, arr7);
        Integer[] arr11 = {8, 13};
        val2pos.put(11, arr11);
        Integer[] arr13 = {19, 26};
        val2pos.put(13, arr13);
        Integer[] arr14 = {22, 27};
        val2pos.put(14, arr14);

        Integer[] arr15 = {7, 10, 25, 28};
        val2pos.put(15, arr15);
    }

    private Color getAlpha(Color col, double d) {
        int al = (int) (d * 255);
        return new Color(col.getRed(), col.getGreen(), col.getBlue(), al);
    }


    public ArrayList<String[]> calculate(boolean[] blacks) {
        ArrayList<String[]> ret = new ArrayList<>();
        String v1 = get(1, true, blacks);
        String v2 = get(2, false, blacks);
        String v4 = get(4, false, blacks);
        String v8 = get(8, false, blacks);
//        String v2 = get(p2s, blacks);

        String[] arr = {v1, v2, v4, v8};
        System.out.println(v1.length() + ":" + v2.length() + ":" + v4.length() + ":" + v8.length());
        ret.add(arr);
        return ret;
    }

    String get(int check, boolean type, boolean[] blacks) {
        String numBits = "%2s";
        String ret = "";
        int ind = 0;
        for (int p : ps) {
            if (checkType(p, check)) {
                boolean black = blacks[p - 1];
                String vv = String.format(numBits, String.valueOf(p)).replaceAll(" ", "0");
                if (black) {
                    ret = ret + vv;
                } else {
                    ret = ret + "00";
                }
            } else {
                ret = ret + "00";
            }
            ind++;
        }
        return ret;
    }

    private boolean checkType(int p, int check) {
        if (check == 8 && (p == 8 || p == 9 || p == 10 || p == 11 || p == 12 || p == 13 || p == 14 || p == 15)) {
            return true;
        } else if (check == 4 && (p == 4 || p == 5 || p == 6 || p == 7 || p == 12 || p == 13 || p == 14 || p == 15)) {
            return true;
        } else if (check == 1 && (p == 1 || p == 3 || p == 5 || p == 7 || p == 9 || p == 11 || p == 13 || p == 15)) {
            return true;
        } else if (check == 2 && (p == 2 || p == 3 || p == 6 || p == 7 || p == 10 || p == 11 || p == 14 || p == 15)) {
            return true;
        }

        return false;
    }

    String get(int[] ps, boolean[] blacks) {
        String numBits = "%2s";
        String vStr = "000000000000000000000000000000000000000000000000000000000000000000000000";
        for (int value : val2pos.keySet()) {
            for (int p1 : ps) {
                if (p1 == value - 1) {
                    Integer[] arr = val2pos.get(value);
                    for (int i : arr) {
                        String bef = i == 0 ? "" : vStr.substring(0, i * 2);
                        String aft = vStr.substring(i * 2 + 2);
                        String vv = String.format(numBits, String.valueOf(value)).replaceAll(" ", "0");
                        String val = blacks[p1] ? vv : "00";
                        vStr = bef + val + aft;
                    }
                }
            }
        }
        return vStr;
    }
}
