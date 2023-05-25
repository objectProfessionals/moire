package com.op.moire.fourstack.cmyk;

import java.util.ArrayList;
import java.util.HashMap;

public class Pixel {

    ArrayList<String> darks = new ArrayList<>();
    static HashMap<String, String> colMap = new HashMap<>();

    public boolean checkMerged(int num, String s1, String s2, String s3, String s4) {
        int c = 0;

        for (int i = 0; i < s1.length(); i++) {
            String c1 = s1.substring(i, i + 1);
            String c2 = s2.substring(i, i + 1);
            String m12 = mergeWith(c1, c2);
            String mFinal = m12;
            if (s3 != null) {
                String c3 = s3.substring(i, i + 1);
                String m123 = mergeWith(m12, c3);
                mFinal = m123;
                if (s4 != null) {
                    String c4 = s4.substring(i, i + 1);
                    mFinal = mergeWith(m123, c4);
                }
            }
            if (darks.contains(mFinal)) {
                c++;
            }
        }

        return c == num;
    }

    private String mergeWith(String s1, String s2) {
        if (colMap.isEmpty()) {
            colMap.put("00", "0");
            colMap.put("C0", "C");
            colMap.put("M0", "M");
            colMap.put("Y0", "Y");
            colMap.put("0C", "C");
            colMap.put("0M", "M");
            colMap.put("0Y", "Y");

            colMap.put("0R", "R");
            colMap.put("0G", "G");
            colMap.put("0B", "B");
            colMap.put("R0", "R");
            colMap.put("G0", "G");
            colMap.put("B0", "B");

            colMap.put("CC", "C");
            colMap.put("CY", "G");
            colMap.put("CM", "B");
            colMap.put("YY", "Y");
            colMap.put("YC", "G");
            colMap.put("YM", "R");
            colMap.put("MM", "M");
            colMap.put("MC", "B");
            colMap.put("MY", "R");
            colMap.put("CR", "X");
            colMap.put("RC", "X");
            colMap.put("CG", "X");
            colMap.put("GC", "X");
            colMap.put("CB", "X");
            colMap.put("BC", "X");
            colMap.put("MR", "X");
            colMap.put("RR", "X");
            colMap.put("MG", "X");
            colMap.put("GM", "X");
            colMap.put("MB", "X");
            colMap.put("BM", "X");
            colMap.put("YR", "X");
            colMap.put("RY", "X");
            colMap.put("YG", "X");
            colMap.put("GY", "X");
            colMap.put("YB", "X");
            colMap.put("BY", "X");

            colMap.put("CX", "X");
            colMap.put("MX", "X");
            colMap.put("YX", "X");
            colMap.put("XC", "X");
            colMap.put("XM", "X");
            colMap.put("XY", "X");
            colMap.put("XR", "X");
            colMap.put("XG", "X");
            colMap.put("XB", "X");
            colMap.put("RX", "X");
            colMap.put("GX", "X");
            colMap.put("BX", "X");
        }

        String res = colMap.get(s1 + s2);
        return res;
    }


}
