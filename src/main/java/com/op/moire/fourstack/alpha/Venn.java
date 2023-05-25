package com.op.moire.fourstack.alpha;

import com.op.moire.Base;

import java.text.DecimalFormat;
import java.util.HashMap;

import static java.lang.Math.abs;

public class Venn extends Base {

//    public static int ss[] = {20, 30};
//    public static int pp[] = {26, 34};
//    public static int tt[] = {42, 50};

//    public static int ss[] = {20, 30};
//    public static int pp[] = {24, 30};
//    public static int tt[] = {32, 38};

//    public static int ss[] = {20, 30};
//    public static int pp[] = {24, 30};
//    public static int tt[] = {32, 38};

    public static int ss[] = {20, 30};
    public static int pp[] = {30, 40};
    public static int tt[] = {40, 50};

    public int s[];
    public int p[];
    public int t[];

    DecimalFormat df = new DecimalFormat("#.00");

    HashMap<String, Integer[]> matches = new HashMap();

    public Venn(int[] ss, int[] pp, int[] tt) {
        super();
        this.s = ss;
        this.p = pp;
        this.t = tt;
    }

    public static void main(String[] args) {
        System.out.println("Started...");
        Venn venn = new Venn(ss, pp, tt);
        venn.getAll();
        System.out.println("Finished...");
    }

    public HashMap<String, Integer[]> getAll() {
        String numBits = "%7s";
        int allVariations = (int) Math.pow(2, 7);
        int mistakes = 0;

        for (int i = 0; i < allVariations; i++) {
            String bin = Integer.toBinaryString(i);
            String padded = String.format(numBits, bin).replaceAll(" ", "0");
            boolean a = padded.substring(6, 7).equals("1");
            boolean b = padded.substring(5, 6).equals("1");
            boolean ab = padded.substring(4, 5).equals("1");
            boolean c = padded.substring(3, 4).equals("1");
            boolean ac = padded.substring(2, 3).equals("1");
            boolean bc = padded.substring(1, 2).equals("1");
            boolean abc = padded.substring(0, 1).equals("1");

            boolean all[] = {a, b, ab, c, ac, bc, abc};
            int allN[] = {a ? s[1] : s[0],
                    b ? s[1] : s[0],
                    ab ? p[1] : p[0],
                    c ? s[1] : s[0],
                    ac ? p[1] : p[0],
                    bc ? p[1] : p[0],
                    abc ? t[1] : t[0]};
            mistakes = mistakes + checkForAll(padded, all, allN);
        }
        System.out.println("mistakes=" + mistakes);

        return matches;
    }

    private int checkForAll(String padded, boolean[] allB, int[] all) {
        double a = all[0];
        double b = all[1];
        double uab = all[2];
        double c = all[3];
        double uac = all[4];
        double ubc = all[5];
        double uabc = all[6];

        double total = tt[1];
        double pA = dp(a / total);
        double pB = dp(b / total);
        double pC = dp(c / total);
        double pAuB = dp(uab / total);
        double pAuC = dp(uac / total);
        double pBuC = dp(ubc / total);
        double pAuBuC = dp(uabc / total);

        double pAnB = dp(pA + pB - pAuB);
        double pAnC = dp(pA + pC - pAuC);
        double pBnC = dp(pB + pC - pBuC);

        double pAnBnC = dp(pAuBuC - pA - pB - pC
                + pAnB
                + pAnC
                + pBnC);

        int nAnBnC = (int) (pAnBnC * total);
        int nBnC = (int) (pBnC * total);
        int nAnC = (int) (pAnC * total);
        int nC = (int) (pC * total);
        int nAnB = (int) (pAnB * total);
        int nB = (int) (pB * total);
        int nA = (int) (pA * total);

        int noAnBnC = nAnBnC;
        int noAnB = abs(nAnB - nAnBnC);
        int noAnC = abs(nAnC - nAnBnC);
        int noBnC = abs(nBnC - nAnBnC);
        int noA = abs(nA - noAnB - noAnC - nAnBnC);
        int noB = abs(nB - noAnB - noBnC - nAnBnC);
        int noC = abs(nC - noAnC - noBnC - nAnBnC);


        Integer match[] = {noA, noB, noAnB, noC, noAnC, noBnC, noAnBnC};
        int r = 0;
        String bs = "";
        for (boolean bn : allB) {
            bs = bs + (bn ? "1" : "0");
        }

        matches.put(bs, match);

        double tot = noA + noB + noC + noAnB + noAnC + noBnC + noAnBnC;
        System.out.println(padded + ": A,B,C=" + allB[0] + "," + allB[1] + "," + allB[3]
                + ": A=" + noA + ", B=" + noB + ", C=" + noC
                + ", pAnB=" + noAnB + ", pAnC=" + noAnC + ", pBnC=" + noBnC + ", AnBnC=" + noAnBnC
                + " : tot=" + tot);


        return r;
    }

    private double dp(double v) {
        double value = Double.parseDouble(df.format(v));
        return (value);
    }

    private int val(int v) {
//        if (v<0) {
//            return 0;
//        }
        return v;
    }

}