package com.op.moire.fourstack.cmyk;

public class Quad extends Pixel {

    String s1 = "";
    String s2 = "";
    String s3 = "";
    String s4 = "";
    String mer = "";

    Quad(String s1, String s2, String s3, String s4) {
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.s4 = s4;
    }

    @Override
    public boolean equals(Object o) {
        Quad p = (Quad) o;
        return this.mer.equals(p.mer);
    }

    public boolean checkMerged(int numBlacks) {
        if (darks.isEmpty()) {
            darks.add("M");
            darks.add("R");
            darks.add("G");
            darks.add("B");
            darks.add("X");
        }

        return super.checkMerged(numBlacks, s1, s2, s3, s4);
    }
}


