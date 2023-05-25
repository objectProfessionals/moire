package com.op.moire.fourstack.cmykvalues;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.HashMap;

public class Pixel implements Comparable {

    static HashMap<String, String> colMap = new HashMap<>();
    static HashMap<String, Color> str2Col = new HashMap<>();
    static HashMap<String, Double> valsMap = new HashMap<>();
    static HashMap<String, Bracket> bracketMap = new HashMap<>();
    static HashMap<String, Double> fitMap = new HashMap<>();
    static int size = 4;
    String cell = "";
    double value = 0.0;
    Bracket bracket;
    double fit;
    static DecimalFormat df2 = new DecimalFormat("#.##");

    public Pixel() {

    }

    public Pixel(String padded, boolean calc) {
        if (calc) {
        } else {
            this.cell = padded;
        }
    }
    public Pixel(String padded) {
        if (colMap.isEmpty()) {
            setup();
        }
        this.bracket = bracketMap.get("s");
        this.cell = padded;
        this.value = calculateValue();
    }

    protected double calculateValue() {
        double val = 0.0;
        for (int i = 0; i < cell.length(); i++) {
            String c = cell.substring(i, i + 1);
            val = val + valsMap.get(c);
        }
        return val;
    }

    protected double calculateFit() {
        if (value < bracket.mid) {
            fit = 1 - (value / bracket.mid);
        } else {
            fit = (value - bracket.mid) / bracket.mid;
        }
        fit = Double.valueOf(df2.format(fit));
        return fit;
    }

    protected String getMergeString(Pixel p1, Pixel p2, Pixel p3, Pixel p4) {
        String s1 = p1 != null && p1.cell != null ? p1.cell : null;
        String s2 = p2 != null && p2.cell != null ? p2.cell : null;
        String s3 = p3 != null && p3.cell != null ? p3.cell : null;
        String s4 = p4 != null && p4.cell != null ? p4.cell : null;

        String m = "";
        for (int i = 0; i < s1.length(); i++) {
            String c1 = s1.substring(i, i + 1);
            String c2 = s2.substring(i, i + 1);
            String m12 = colMap.get(c1 + c2);
            String mFinal = m12;
            if (s3 != null) {
                String c3 = s3.substring(i, i + 1);
                String m123 = colMap.get(m12 + c3);
                mFinal = m123;
                if (s4 != null) {
                    String c4 = s4.substring(i, i + 1);
                    mFinal = colMap.get(m123 + c4);
                }
            }
            m = m + mFinal;
        }

        return m;
    }

    protected Boolean getBooleanValue() {
        if (this.value <= bracket.mid) {
            return false;
        } else if (this.value >= bracket.mid) {
            return true;
        }
        return null;
    }

    public static void setup() {
        if (colMap.isEmpty()) {
            colMap.put("oo", "o");

            colMap.put("co", "c");
            colMap.put("mo", "m");
            colMap.put("yo", "y");
            colMap.put("oc", "c");
            colMap.put("om", "m");
            colMap.put("oy", "y");
            colMap.put("or", "r");
            colMap.put("ro", "r");
            colMap.put("og", "g");
            colMap.put("go", "g");
            colMap.put("ob", "b");
            colMap.put("bo", "b");
            colMap.put("ox", "x");
            colMap.put("xo", "x");

            colMap.put("cc", "c");
            colMap.put("mm", "m");
            colMap.put("yy", "y");

            colMap.put("cm", "b");
            colMap.put("mc", "b");
            colMap.put("cy", "g");
            colMap.put("yc", "g");
            colMap.put("my", "r");
            colMap.put("ym", "r");

            colMap.put("cr", "x");
            colMap.put("rc", "x");
            colMap.put("mr", "r");
            colMap.put("rm", "r");
            colMap.put("yr", "y");
            colMap.put("ry", "y");

            colMap.put("cg", "g");
            colMap.put("gc", "g");
            colMap.put("mg", "x");
            colMap.put("gm", "x");
            colMap.put("yg", "g");
            colMap.put("gy", "g");

            colMap.put("cb", "b");
            colMap.put("bc", "b");
            colMap.put("mb", "b");
            colMap.put("bm", "b");
            colMap.put("yb", "x");
            colMap.put("by", "x");

            colMap.put("cx", "x");
            colMap.put("xc", "x");
            colMap.put("mx", "x");
            colMap.put("xm", "x");
            colMap.put("yx", "x");
            colMap.put("xy", "x");

            colMap.put("rr", "r");
            colMap.put("gg", "g");
            colMap.put("bb", "b");

            colMap.put("rx", "x");
            colMap.put("xr", "x");
            colMap.put("gx", "x");
            colMap.put("xg", "x");
            colMap.put("bx", "x");
            colMap.put("xb", "x");

            colMap.put("rg", "x");
            colMap.put("gr", "x");
            colMap.put("rb", "x");
            colMap.put("br", "x");
            colMap.put("gb", "x");
            colMap.put("bg", "x");

            colMap.put("xx", "x");

            valsMap.put("o", 0.0);
            valsMap.put("y", 0.3);
            valsMap.put("c", 0.4);
            valsMap.put("m", 0.6);
            valsMap.put("g", 0.7);
            valsMap.put("r", 0.9);
            valsMap.put("b", 1.0);
            valsMap.put("x", 1.6);

            Bracket bracketS = new Bracket(0.2, 0.8, 0.5 * 4 * 0.65);
            Bracket bracketP = new Bracket(0.2, 0.8, 0.5 * 4 * 1.2);
            Bracket bracketT = new Bracket(0.2, 0.8, 0.5 * 4 * 1.75);
            Bracket bracketQ = new Bracket(0.2, 0.8, 0.5 * 4 * 2.0);
            bracketMap.put("s", bracketS);
            bracketMap.put("p", bracketP);
            bracketMap.put("t", bracketT);
            bracketMap.put("q", bracketQ);

            fitMap.put("s", 0.5);

            str2Col.put("o", Color.WHITE);
            str2Col.put("c", Color.CYAN);
            str2Col.put("m", Color.MAGENTA);
            str2Col.put("y", Color.YELLOW);
            str2Col.put("x", Color.BLACK);

        }
    }

    static protected Color getColor(String key) {
        if (str2Col.isEmpty()) {
            setup();
        }
        return str2Col.get(key);
    }

    @Override
    public int compareTo(Object o) {
        Pixel other = (Pixel) o;

        return (int) (this.value * 100) - (int) (other.value * 100);
    }

    @Override
    public String toString() {
        return cell;
    }
}


