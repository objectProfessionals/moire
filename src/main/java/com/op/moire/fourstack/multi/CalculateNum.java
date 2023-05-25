package com.op.moire.fourstack.multi;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class CalculateNum {
    static final CalculateNum calculate = new CalculateNum();
    HashMap<String, String> pairB = new HashMap<>();
    HashMap<String, String> tripleB = new HashMap<>();
    HashMap<String, String> quadB = new HashMap<>();
    HashMap<String, String> pairW = new HashMap<>();
    HashMap<String, Color> str2Col = new HashMap();

    public static void main(String[] args) {
        System.out.println("Started...");
        calculate.test();
        System.out.println("Finished...");
    }

    private void test() {
        boolean[] blacks = {false,false,false,false,false,false,true,false,false,false,false,false,false,false,false};
        calculate(blacks);
    }

    public String getAllParms() {
        HashMap<String, Integer> parms = new HashMap<>();
//        parms.put("all", all);
//        parms.put("offSingle", offSingle);
//        parms.put("onSingle", onSingle);
//        parms.put("offTriple", offTriple);
//        parms.put("onTriple", onTriple);
//        parms.put("offQuad", offQuad);
//        parms.put("onQuad", onQuad);
        String ret = "";
        for (String key : parms.keySet()) {
            ret = ret + "," + key + (parms.get(key));
        }
        return ret;
    }


    ArrayList<String[]> calculate(boolean[] blacks) {
        if(pairB.isEmpty()) {
            setup();
        }
        HashMap<String, Boolean> key2val = new HashMap<>();
        key2val.put("A", true);
        key2val.put("B", true);
        key2val.put("C", true);
        key2val.put("D", true);
        key2val.put("a", blacks[0]);
        key2val.put("b", blacks[1]);
        key2val.put("c", blacks[3]);
        key2val.put("d", blacks[7]);
        key2val.put("e", blacks[2]);
        key2val.put("f", blacks[4]);
        key2val.put("g", blacks[8]);
        key2val.put("h", blacks[5]);
        key2val.put("i", blacks[9]);
        key2val.put("j", blacks[11]);
        key2val.put("1", blacks[6] || blacks[10]);
        key2val.put("2", blacks[12] || blacks[13]);
        key2val.put("o", blacks[14]);

        ArrayList<String[]> ret = new ArrayList<>();
        //aaebbAAfBBg1o2hDDiCCddjcc
        //aaebb
        //AAfBB
        //g1o2h
        //DDiCC
        //ddjcc
        String val1 = getAll("aae  AAf  g1o2           ", key2val, "A");
        String val2 = getAll("  ebb   BB 1o2h  i       ", key2val, "B");
        String val4 = getAll("       f   1o2h  iCC  jcc", key2val, "C");
        String val8 = getAll("          g1o2 DDi  ddj  ", key2val, "D");

        String[] vals = {val1, val2, val4, val8};
        ret.add(vals);

        System.out.println("val1="+val1);
        System.out.println("val2="+val2);
        System.out.println("val4="+val4);
        System.out.println("val8="+val8);
        return ret;
    }

    private String getAll(String match, HashMap<String, Boolean> key2val, String mainLetter) {
        String ret = "";
        for (int i=0; i< match.length(); i++) {
            String m = match.substring(i, i+1);
            String let = mainLetter;
            if (m.equals("1")) {
                if (mainLetter.equals("C") || mainLetter.equals("D")) {
                    let = mainLetter;
                } else {
                    let = mainLetter;
                }
            }
            if (m.equals("2")) {
                if (mainLetter.equals("A") || mainLetter.equals("B")) {
                    let = mainLetter;
                } else {
                    let = mainLetter;
                }
            }
            boolean keyVal = false;
            if (key2val.get(m) != null) {
                keyVal = key2val.get(m);
            }
            ret = ret + (keyVal ? let: "0");
        }

        return ret;
    }

    private void setup() {
        pairB.put("AB", "E");
        pairB.put("AC", "F");
        pairB.put("AD", "G");
        pairB.put("BC", "H");
        pairB.put("BD", "I");
        pairB.put("CD", "J");

        tripleB.put("EC", "K");
        tripleB.put("ED", "L");
        tripleB.put("FD", "M");
        tripleB.put("HD", "N");

        quadB.put("KD", "O");
        quadB.put("LC", "O");
        quadB.put("MB", "O");
        quadB.put("NA", "O");

        pairW.put("A0", "A");
        pairW.put("0A", "A");
        pairW.put("B0", "B");
        pairW.put("0B", "B");
        pairW.put("C0", "C");
        pairW.put("0C", "C");
        pairW.put("D0", "D");
        pairW.put("0D", "D");

        double alpha = 0.75;
        str2Col.put("0", getAlpha(Color.WHITE, 0));

        str2Col.put("A", getAlpha(new Color(0.1f, 0.1f, 0.1f), alpha));
        str2Col.put("B", getAlpha(new Color(0.1f, 0.1f, 0.1f), alpha));
        str2Col.put("C", getAlpha(new Color(0.1f, 0.1f, 0.1f), alpha));
        str2Col.put("D", getAlpha(new Color(0.1f, 0.1f, 0.1f), alpha));

        str2Col.put("F", getAlpha(Color.BLUE, 1));
        str2Col.put("G", getAlpha(Color.MAGENTA, 1));
        str2Col.put("H", getAlpha(Color.YELLOW, 1));
        str2Col.put("I", getAlpha(Color.CYAN.darker(), 1));
        str2Col.put("J", getAlpha(Color.CYAN.darker(), 1));
        str2Col.put("K", getAlpha(Color.CYAN.darker(), 1));
        str2Col.put("L", getAlpha(Color.CYAN.darker(), 1));
        str2Col.put("M", getAlpha(Color.CYAN.darker(), 1));
        str2Col.put("N", getAlpha(Color.CYAN.darker(), 1));
        str2Col.put("O", getAlpha(Color.CYAN.darker(), 1));
    }

    private Color getAlpha(Color col, double d) {
        int al = (int) (d * 255);
        return new Color(col.getRed(), col.getGreen(), col.getBlue(), al);
    }


}
