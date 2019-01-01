package com.op.moire.multipleslide;

public class Pixel {

    String value;

    boolean[] getValueAsBooleans() {
        boolean[] b = {false, false, false, false};
        for (int i=0; i<4; i++) {
            String v = value.substring(i, i+1);
            boolean bv = v.equals("1");
            b[i] = bv;
        }

        return b;
    }

}
