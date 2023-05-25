
package com.op.moire.fourstack.cmykvalues;


import java.text.DecimalFormat;

public class Quad extends Pixel {

    Pixel p1;
    Pixel p2;
    Pixel p4;
    Pixel p8;

    Pair p12;
    Pair p14;
    Pair p18;
    Pair p24;
    Pair p28;
    Pair p48;

    Triple p124;
    Triple p128;
    Triple p148;
    Triple p248;


    Quad(Pixel s1, Pixel s2, Pixel s3, Pixel s4) {
        this.bracket = bracketMap.get("q");
        this.p1 = s1;
        this.p2 = s2;
        this.p4 = s3;
        this.p8 = s4;
        this.cell = getMergeString(p1, p2, p4, p8);
        this.value = calculateValue();

        calculatePairs();
        calculateTriples();
        calculateFitAll();
    }

    private double calculateFitAll() {
        double fit = calculateFit();
        fit = fit + p124.calculateFit();
        fit = fit + p128.calculateFit();
        fit = fit + p148.calculateFit();
        fit = fit + p248.calculateFit();

        fit = fit + p12.calculateFit();
        fit = fit + p14.calculateFit();
        fit = fit + p18.calculateFit();
        fit = fit + p24.calculateFit();
        fit = fit + p28.calculateFit();
        fit = fit + p48.calculateFit();

        fit = fit + p1.calculateFit();
        fit = fit + p2.calculateFit();
        fit = fit + p4.calculateFit();
        fit = fit + p8.calculateFit();

        this.fit = Double.parseDouble(df2.format(fit / 15.0));
        return fit;
    }

    Quad(Pixel s1, Pixel s2, Pixel s3, Pixel s4, boolean calc) {
        if (calc) {
        } else {
            this.p1 = s1;
            this.p2 = s2;
            this.p4 = s3;
            this.p8 = s4;
        }
    }

    private void calculatePairs() {
        p12 = new Pair(p1, p2);
        p14 = new Pair(p1, p4);
        p18 = new Pair(p1, p8);
        p24 = new Pair(p2, p4);
        p28 = new Pair(p2, p8);
        p48 = new Pair(p4, p8);
    }

    private void calculateTriples() {
        p124 = new Triple(p1, p2, p4);
        p128 = new Triple(p1, p2, p8);
        p148 = new Triple(p1, p4, p8);
        p248 = new Triple(p2, p4, p8);
    }

    @Override
    public boolean equals(Object o) {
        Quad p = (Quad) o;
        return this.cell.equals(p.cell);
    }

    @Override
    public String toString() {
        String str = "<"+p1.cell +":"
                + p2.cell +":"
                + p4.cell +":"
                + p8.cell +">,"
                +"["+this.fit+"],"
                + toBooleanString();
        return str;
    }

    public String toBooleanString() {
        return  p1.getBooleanValue() +","
                + p2.getBooleanValue() +","
                + p12.getBooleanValue() +","
                + p4.getBooleanValue() +","
                + p14.getBooleanValue() +","
                + p24.getBooleanValue() +","
                + p124.getBooleanValue() +","
                + p8.getBooleanValue() +","
                + p18.getBooleanValue() +","
                + p28.getBooleanValue() +","
                + p128.getBooleanValue() +","
                + p48.getBooleanValue() +","
                + p148.getBooleanValue() +","
                + p248.getBooleanValue() +","
                + this.getBooleanValue();
    }
}
