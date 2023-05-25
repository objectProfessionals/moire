package com.op.moire.fourstack.alpha;

import com.op.moire.Base;

import java.text.DecimalFormat;
import java.util.HashMap;

public class CalculateVenn extends Base {

    public static int ss[] = {8, 12};
    public static int pp[] = {12, 16};
    public static int tt[] = {16, 24};

    public int s[];
    public int p[];
    public int t[];

    DecimalFormat df = new DecimalFormat("#.00");

    HashMap<String, Integer[]> matches = new HashMap();

    public CalculateVenn(int[] ss, int[] pp, int[] tt) {
        super();
        this.s = ss;
        this.p = pp;
        this.t = tt;
    }

    public static void main(String[] args) {
        System.out.println("Started...");
        CalculateVenn venn = new CalculateVenn(ss, pp, tt);
        HashMap<String, Integer[]> all = venn.getAll();
        System.out.println("Finished...");
    }

    protected HashMap<String, Integer[]> getAll() {
        HashMap<String, Integer[]> all = tryAll();
        boolean incT0 = true;
        boolean incT1 = true;
        while (all == null) {
            all = tryAll();
            if (all == null) {
                if (incT0) {
                    t[0] = t[0] + 1;
                    incT0 = false;
                    incT1 = true;
                } else if (incT1) {
                    t[1] = t[1] + 2;
                    incT0 = true;
                    incT1 = false;
                }
            }
        }


        matches = all;
        return matches;
    }

    public HashMap<String, Integer[]> tryAll() {
        matches = new HashMap();

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
            if (mistakes > 0) {
                System.out.println("mistakes=" + mistakes);
                return null;
            }

        }

        return matches;
    }

    private int checkForAll(String padded, boolean[] allB, int[] all) {
        int nA = all[0];
        int nB = all[1];
        int nAuB = all[2];
        int nC = all[3];
        int nAuC = all[4];
        int nBuC = all[5];
        int nAuBuC = all[6];

        int total = t[1];
        int nAnB = nA + nB - nAuB;
        int nAnC = nA + nC - nAuC;
        int nBnC = nB + nC - nBuC;

        if (nAnB < 0 || nAnC < 0 || nBnC < 0) {
            return 1;
        }

        int nAnBnC = nAuBuC - nA - nB - nC
                + nAnB + nAnC + nBnC;

        if (nAnBnC < 0 || nAnBnC > nAnB || nAnBnC > nAnC || nAnBnC > nBnC) {
            return 1;
        }

        System.out.println(padded + "t0/1=" + t[0] + "/" + t[1]
                + ": A=" + nA + ", B=" + nB + ", C=" + nC
                + ", pAnB=" + nAnB + ", pAnC=" + nAnC + ", pBnC=" + nBnC + ", AnBnC=" + nAnBnC);

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


        return 0;
    }

    int abs(int a) {
        return a;
    }
}