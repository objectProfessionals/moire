package com.op.moire.sliding;

import com.op.moire.Base;

import java.util.ArrayList;

public class Sliding extends Base {

    private static Sliding sliding;
    private String[] bottoms = {"", "", "", ""};
    private String[] ones = {"1000", "0100", "0010", "0001"};
    private String[] twos = {"1100", "0110", "0011", "1001", "1010", "0101"};
    private String[] threes = {"0111", "1011", "1101", "1110"};

    String p1 = "bbbwb";
    String p2 = "wwbbw";
    String p3 = "bbwwb";
    String ps[] = {p1, p2, p3};


    public static void main(String[] args) throws Exception {
        sliding = new Sliding();
        sliding.setup();
        sliding.draw();
    }

    private void draw() {
        ArrayList<String> failsb = new ArrayList();
        ArrayList<String> failsc = new ArrayList();
        ArrayList<String> failst = new ArrayList();
        int pos = 2;

        String[] btbcbb = drawForPos(pos, failsb, failsc, failst, false, null, null);
        String[] btbcbb2 = drawForPos(3, failsb, failsc, failst, false, btbcbb[0], btbcbb[1]);
        while (btbcbb2 == null) {
            btbcbb2 = drawForPos(3, failsb, failsc, failst, false, btbcbb[0], btbcbb[1]);
        }

    }

    private String[] drawForPos(int pos, ArrayList<String> failsb, ArrayList<String> failsc, ArrayList<String> failst, boolean b, String bcLast, String bbLast) {
        while (!b) {
            String bc = chooseOne(failsc, null, true);
            boolean p22Value = valueOf(2, pos);
            String p22 = chooseMore(bc, p22Value);
            boolean p13Value = valueOf(1, pos + 1);
            String bt = chooseOne(failst, p22, p13Value);
            boolean p31Value = valueOf(3, pos - 1);
            String bb = chooseOne(failsb, p22, p31Value);
            //String b3 = chooseOne(p22, p31Value);

            boolean v12 = isValid(bt, p22, p13Value);
            if (!v12) {
                failst.add(bt);
                System.out.println("failst=" + failst);
                return null;
            }
            boolean v22 = isValid(bc, p22, p22Value);
            if (!v22) {
                failsc.add(bc);
                System.out.println("failsc=" + failsc);
                return null;
            }
            boolean v32 = isValid(bb, p22, p31Value);
            if (!v32) {
                failsb.add(bb);
                System.out.println("failsb=" + failsb);
                return null;
            }

            System.out.println("b" + (pos + 1) + ",b" + (pos) + ",b" + (pos - 1) + "=" + bt + "," + bc + "," + bb);
            System.out.println("p" + pos + "=" + p22);
            pos++;
            String[] ret = {bt, bc, bb};
            return ret;
        }
        return null;
    }

    private String chooseOne(ArrayList<String> fails) {
        for (String one : ones) {
            boolean anyFail = false;
            for (String fail : fails) {
                if (one.equals(fail)) {
                    anyFail = true;
                }
            }
            if (!anyFail) {
                return one;
            }
        }
        return null;
    }

    private boolean isValid(String b, String p, boolean pValue) {
        String m = merge(b, p);
        int mVal = numberTrue(m);
        return ((pValue && (mVal == 3)) || (!pValue && (mVal == 2)));
    }

    private String chooseOne(ArrayList<String> fails, String p, boolean isBlack) {
        for (String one : ones) {
            if (fails.contains(one)) {
                continue;
            }
            int n = numberTrueByOr(one, p);
            if (isBlack && n == 3) {
                return one;
            } else if (!isBlack && n == 2) {
                return one;
            }
        }
        return null;
    }

    private String chooseMore(String b, boolean isBlack) {
        if (isBlack) {
            return choose2sWith1sThatMake3s(b);
        } else {
            return choose2sWith1sThatMake2s(b);
        }
    }

    private String choose2sWith1sThatMake2s(String one) {
        for (String two : twos) {
            if (numberTrueByOr(one, two) == 2) {
                return two;
            }
        }
        return null;
    }

    private String choose2sWith1sThatMake3s(String one) {
        for (String two : twos) {
            if (numberTrueByOr(one, two) == 3) {
                return two;
            }
        }
        return null;
    }

    private int numberTrueByOr(String one, String two) {
        String merged = merge(one, two);
        return numberTrue(merged);
    }

    private int numberTrue(String merged) {
        int c = 0;
        for (int i = 0; i < merged.length(); i++) {
            String m = merged.substring(i, i + 1);
            if ("1".equals(m)) {
                c++;
            }
        }
        return c;
    }

    private String merge(String one, String two) {
        String merged = "";
        for (int i = 0; i < one.length(); i++) {
            String t = two.substring(i, i + 1);
            String o = one.substring(i, i + 1);
            if ("1".equals(o) || "1".equals(t)) {
                merged = merged + "1";
            } else {
                merged = merged + "0";
            }
        }

        return merged;
    }

    private boolean valueOf(int y, int x) {
        return ps[y - 1].substring(x - 1, x).equals("b");
    }

    private String chooseOne() {
        return ones[0];
    }

    private void setup() {
    }

}
