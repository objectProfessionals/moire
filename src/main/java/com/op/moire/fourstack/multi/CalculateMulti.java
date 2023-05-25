package com.op.moire.fourstack.multi;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class CalculateMulti {
    static final CalculateMulti calculate = new CalculateMulti();
    HashMap<String, Color> str2Col = new HashMap();
    static int all = 9;
    int offSingle = 3;
    int onSingle = 4;
    int offPair = 2;
    int onPair = 3;
    int offTriple = 1;
    int onTriple = 2;
    int offQuad = 0;
    int onQuad = 1;

    HashMap<String, String> pairB = new HashMap<>();
    HashMap<String, String> tripleB = new HashMap<>();
    HashMap<String, String> quadB = new HashMap<>();
    HashMap<String, String> pairW = new HashMap<>();
    public static void main(String[] args) {
        System.out.println("Started...");
        calculate.test();
        System.out.println("Finished...");
    }

    public String getAllParms() {
        HashMap<String, Integer> parms = new HashMap<>();
        parms.put("all", all);
        parms.put("offSingle", offSingle);
        parms.put("onSingle", onSingle);
        parms.put("offTriple", offTriple);
        parms.put("onTriple", onTriple);
        parms.put("offQuad", offQuad);
        parms.put("onQuad", onQuad);
        String ret = "";
        for (String key : parms.keySet()) {
            ret = ret + "," + key + (parms.get(key));
        }
        return ret;
    }

    private void test() {
        boolean[] blacks = {false,false,false,false,false,false,false,false,false,true,true,true,true,true,true};
        //boolean[] blacks = {false, false, false, false, false, false, false, false, false, true, true, true, true, true, true};
        //boolean[] blacks = {false, true, true, true, false, false, true, true, true, true, false, true, true, true, false};
        //boolean[] blacks = {false, true, false, true, false, false, true, true, true, true, false, true, true, true, true};
        //boolean[] blacks = {false, false, false, false, false, false, false, false, false, true, true, true, true, true, true};
        //boolean[] blacks = {false, true, false, true, false, true, false, true, false, true, false, true, false, true, false};
        //boolean[] blacks = {false, false, false, false, false, false, false, false, false, true, true, true, true, true, true};
        //boolean[] blacks = {false,false,false,false,false,false,true,false,false,false,false,false,false,false,false};
        calculate(blacks);
    }

    ArrayList<String[]> calculate(boolean[] blacks) {
        setup();

        String blString = "";
        for (boolean bl : blacks) {
            blString = blString + bl + ",";
        }
        System.out.println(blString);

        if (blString.equals("false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,")) {
            ArrayList<String[]> vs = new ArrayList<>();
            String wh = "000000";
            String[] whs = {wh, wh, wh, wh};
            vs.add(whs);
            return vs;
        }


        ArrayList<String> v1s = getAllCorrectAStrings(blacks[0], offSingle, onSingle, "A");
        ArrayList<String> v2s = getAllCorrectAStrings(blacks[1], offSingle, onSingle, "B");
        ArrayList<String> v4s = getAllCorrectAStrings(blacks[3], offSingle, onSingle, "C");
        ArrayList<String> v8s = getAllCorrectAStrings(blacks[7], offSingle, onSingle, "D");
        System.out.println(v1s.size() + ":" + v2s.size() + ":" + v4s.size() + ":" + v8s.size() + ":");

        String[] vs = filterAll(v1s, v2s, v4s, v8s, blacks);

        System.out.println(vs[0]);
        System.out.println(vs[1]);
        System.out.println(vs[2]);
        System.out.println(vs[3]);

        ArrayList<String[]> vs2 = new ArrayList<>();
        vs2.add(vs);
        return vs2;
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

        str2Col.put("0", getAlpha(Color.WHITE, 0));
        str2Col.put("A", getAlpha(Color.CYAN, 0.25));
        str2Col.put("B", getAlpha(Color.MAGENTA, 0.25));
        str2Col.put("C", getAlpha(Color.YELLOW, 0.25));
        str2Col.put("D", getAlpha(Color.CYAN.darker(), 0.25));

        str2Col.put("F", getAlpha(Color.BLUE, 0.25));
        str2Col.put("G", getAlpha(Color.MAGENTA, 0.25));
        str2Col.put("H", getAlpha(Color.YELLOW, 0.25));
        str2Col.put("I", getAlpha(Color.CYAN.darker(), 0.25));
        str2Col.put("J", getAlpha(Color.CYAN.darker(), 0.25));
        str2Col.put("K", getAlpha(Color.CYAN.darker(), 0.25));
        str2Col.put("L", getAlpha(Color.CYAN.darker(), 0.25));
        str2Col.put("M", getAlpha(Color.CYAN.darker(), 0.25));
        str2Col.put("N", getAlpha(Color.CYAN.darker(), 0.25));
        str2Col.put("O", getAlpha(Color.CYAN.darker(), 0.25));
    }

    private Color getAlpha(Color col, double d) {
        int al = (int) (d * 255);
        return new Color(col.getRed(), col.getGreen(), col.getBlue(), al);
    }

    private String[] filterAll(ArrayList<String> v1s, ArrayList<String> v2s, ArrayList<String> v4s, ArrayList<String> v8s, boolean[] blacks) {
        for (String v1 : v1s) {
            for (String v2 : v2s) {
                for (String v4 : v4s) {
                    for (String v8 : v8s) {
                        String m1248 = mergeQuad(v1, v2, v4, v8, blacks[14]);
                        if (m1248 != null) {
                            String m124 = mergeTriples(v1, v2, v4, blacks[6]);
                            if (m124 != null) {
                                String m128 = mergeTriples(v1, v2, v8, blacks[10]);
                                if (m128 != null) {
                                    String m248 = mergeTriples(v2, v4, v8, blacks[13]);
                                    if (m248 != null) {
                                        String m148 = mergeTriples(v1, v4, v8, blacks[12]);
                                        if (m148 != null) {

                                            String m12 = mergePairs(v1, v2, blacks[2]);
                                            if (m12 != null) {
                                                String m14 = mergePairs(v1, v4, blacks[4]);
                                                if (m14 != null) {
                                                    String m18 = mergePairs(v1, v2, blacks[8]);
                                                    if (m18 != null) {
                                                        String m24 = mergePairs(v2, v4, blacks[5]);
                                                        if (m24 != null) {
                                                            String m28 = mergePairs(v2, v8, blacks[9]);
                                                            if (m28 != null) {
                                                                String m48 = mergePairs(v4, v8, blacks[11]);
                                                                if (m48 != null) {
                                                                    String[] arr = {v1, v2, v4, v8};
                                                                    return arr;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }
        return null;
    }

    private String[] filterAll2(ArrayList<String> v1s, ArrayList<String> v2s, ArrayList<String> v4s, ArrayList<String> v8s, boolean[] blacks) {
        String[] filtered = {null, null, null, null};

        ArrayList<Pair> m12 = mergePairs(v1s, v2s, blacks[2]);
//        v1s = getAllPair(m12, 1);
//        v2s = getAllPair(m12, 2);
        ArrayList<Pair> m14 = mergePairs(v1s, v4s, blacks[4]);
//        v1s = getAllPair(m14, 1);
//        v4s = getAllPair(m14, 2);
        ArrayList<Pair> m18 = mergePairs(v1s, v8s, blacks[8]);
//        v1s = getAllPair(m18, 1);
//        v8s = getAllPair(m18, 2);
        ArrayList<Pair> m24 = mergePairs(v2s, v4s, blacks[5]);
//        v2s = getAllPair(m24, 1);
//        v4s = getAllPair(m24, 2);
        ArrayList<Pair> m28 = mergePairs(v2s, v8s, blacks[9]);
//        v2s = getAllPair(m28, 1);
//        v8s = getAllPair(m28, 2);

        v1s = getAllSingles(m12, m14, m18, 1, 1, 1);

        ArrayList<Triple> m124 = mergeTriples(v1s, v2s, v4s, blacks[6]);
        v1s = getAllTriple(m124, 1);
        v2s = getAllTriple(m124, 2);
        v4s = getAllTriple(m124, 3);

        ArrayList<Triple> m128 = mergeTriples(v1s, v2s, v8s, blacks[10]);
        v1s = getAllTriple(m128, 1);
        v2s = getAllTriple(m128, 2);
        v8s = getAllTriple(m128, 3);

        ArrayList<Triple> m148 = mergeTriples(v1s, v4s, v8s, blacks[12]);
        v1s = getAllTriple(m148, 1);
        v4s = getAllTriple(m148, 2);
        v8s = getAllTriple(m148, 3);

        ArrayList<Triple> m248 = mergeTriples(v2s, v4s, v8s, blacks[13]);
        v2s = getAllTriple(m248, 1);
        v4s = getAllTriple(m248, 2);
        v8s = getAllTriple(m248, 3);


        ArrayList<Quad> m1248 = mergeQuads(v1s, v2s, v4s, v8s, blacks[13]);
//        v1s = getAllQuad(m1248, 1);
//        v2s = getAllQuad(m1248, 2);
//        v4s = getAllQuad(m1248, 3);
//        v8s = getAllQuad(m1248, 4);

        filtered[0] = m1248.get(0).va;
        filtered[1] = m1248.get(0).vb;
        filtered[2] = m1248.get(0).vc;
        filtered[3] = m1248.get(0).vd;
        return filtered;
    }

    private ArrayList<String> getAllSingles(ArrayList<Pair> mAB, ArrayList<Pair> mAC, ArrayList<Pair> mAD, int a, int b, int c) {
        ArrayList<String> all = new ArrayList<>();
        for (Pair pairAB : mAB) {
            String toMatchAB = a == 1 ? pairAB.va : pairAB.vb;
            for (Pair pairAC : mAC) {
                String toMatchAC = b == 1 ? pairAC.va : pairAC.vb;
                for (Pair pairAD : mAD) {
                    String toMatchAD = c == 1 ? pairAD.va : pairAD.vb;
                    if (toMatchAB.equals(toMatchAC) && toMatchAB.equals(toMatchAD) && toMatchAC.equals(toMatchAD)) {
                        if (!all.contains(pairAB.va)) {
                            all.add(pairAB.va);
                        }
                    }
                }
            }

        }

        return all;
    }

    private ArrayList<String> getAllPair(ArrayList<Pair> m12, int i) {
        ArrayList<String> all = new ArrayList<>();
        for (Pair pair : m12) {
            if (i == 1) {
                all.add(pair.va);
            }
            if (i == 2) {
                all.add(pair.vb);
            }
        }
        return all;
    }

    private ArrayList<String> getAllTriple(ArrayList<Triple> tabc, int i) {
        ArrayList<String> all = new ArrayList<>();
        for (Triple triple : tabc) {
            if (i == 1) {
                if (!all.contains(triple.va)) {
                    all.add(triple.va);
                }
            }
            if (i == 2) {
                if (!all.contains(triple.vb)) {
                    all.add(triple.vb);
                }
            }
            if (i == 3) {
                if (!all.contains(triple.vc)) {
                    all.add(triple.vc);
                }
            }
        }
        return all;
    }

    private ArrayList<String> getAllQuad(ArrayList<Quad> qabcd, int i) {
        ArrayList<String> all = new ArrayList<>();
        for (Quad quad : qabcd) {
            if (i == 1) {
                if (!all.contains(quad.va)) {
                    all.add(quad.va);
                }
            }
            if (i == 2) {
                if (!all.contains(quad.vb)) {
                    all.add(quad.vb);
                }
            }
            if (i == 3) {
                if (!all.contains(quad.vc)) {
                    all.add(quad.vc);
                }
            }
            if (i == 4) {
                if (!all.contains(quad.vd)) {
                    all.add(quad.vd);
                }
            }
        }
        return all;
    }

    private ArrayList<Pair> mergePairs(ArrayList<String> v1s, ArrayList<String> v2s, boolean black) {
        ArrayList<Pair> merged = new ArrayList<>();
        for (String v1 : v1s) {
            for (String v2 : v2s) {
                Pair newPair = new Pair(v1, v2);
                newPair.m = mergePairs(v1, v2, black);
                if (newPair.m != null) {
                    if (!merged.contains(newPair)) {
                        merged.add(newPair);
                    }
                }
            }

        }

        return merged;
    }

    private ArrayList<Triple> mergeTriples(ArrayList<String> v1s, ArrayList<String> v2s, ArrayList<String> v3s, boolean black) {
        ArrayList<Triple> merged = new ArrayList<>();
        for (String v1 : v1s) {
            for (String v2 : v2s) {
                for (String v3 : v3s) {
                    Triple triple = new Triple(v1, v2, v3);
                    triple.m = mergeTriples(v1, v2, v3, black);
                    if (triple.m != null) {
                        merged.add(triple);
                    }
                }

            }
        }

        return merged;
    }

    private ArrayList<Quad> mergeQuads(ArrayList<String> v1s, ArrayList<String> v2s, ArrayList<String> v3s, ArrayList<String> v4s, boolean black) {
        ArrayList<Quad> merged = new ArrayList<>();
        for (String v1 : v1s) {
            for (String v2 : v2s) {
                for (String v3 : v3s) {
                    for (String v4 : v4s) {
                        Quad quad = new Quad(v1, v2, v3, v4);
                        quad.m = mergeQuad(v1, v2, v3, v4, black);
                        if (quad.m != null) {
                            merged.add(quad);
                        }
                    }
                }

            }
        }

        return merged;
    }

    private String mergePairs(String v1, String v2, boolean black) {
        String merged = "";
        int cb = 0;
        for (int i = 0; i < v1.length(); i++) {
            String c1 = v1.substring(i, i + 1);
            String c2 = v2.substring(i, i + 1);
            String resB = pairB.get(c1 + c2);
            if (resB != null) {
                cb++;
                merged = merged + resB;
            } else {
                String resW = pairW.get(c1 + c2);
                if (resW != null) {
                    merged = merged + resW;
                } else {
                    merged = merged + "0";
                }
            }
        }

        boolean match = (black && cb == onPair) || (!black && cb == offPair);
        if (match) {
            return merged;
        } else {
            return null;
        }
    }

    private String mergeTriples(String v1, String v2, String v3, boolean black) {
        String merged = "";
        int cb = 0;
        for (int i = 0; i < v1.length(); i++) {
            String c1 = v1.substring(i, i + 1);
            String c2 = v2.substring(i, i + 1);
            String c3 = v3.substring(i, i + 1);
            String res12 = pairB.get(c1 + c2);
            if (res12 != null) {
                String res123 = tripleB.get(res12 + c3);
                if (res123 != null) {
                    cb++;
                    merged = merged + res123;
                } else {
                    merged = merged + res12;
                }
            } else {
                String resW = pairW.get(c1 + c2);
                if (resW != null) {
                    merged = merged + resW;
                } else {
                    merged = merged + "0";
                }
            }
        }

        boolean match = (black && cb == onTriple) || (!black && cb == offTriple);
        if (match) {
            return merged;
        } else {
            return null;
        }
    }

    private String mergeQuad(String v1, String v2, String v3, String v4, boolean black) {
        String merged = "";
        int cb = 0;
        for (int i = 0; i < v1.length(); i++) {
            String c1 = v1.substring(i, i + 1);
            String c2 = v2.substring(i, i + 1);
            String c3 = v3.substring(i, i + 1);
            String c4 = v4.substring(i, i + 1);
            String res12 = pairB.get(c1 + c2);
            if (res12 != null) {
                String res123 = tripleB.get(res12 + c3);
                if (res123 != null) {
                    String res1234 = quadB.get(res123 + c4);
                    if (res1234 != null) {
                        cb++;
                        merged = merged + res1234;
                    } else {
                        merged = merged + res123;
                    }
                } else {
                    merged = merged + res12;
                }
            } else {
                String resW = pairW.get(c1 + c2);
                if (resW != null) {
                    merged = merged + resW;
                } else {
                    merged = merged + "0";
                }
            }
        }

        boolean match = (black && cb == onQuad) || (!black && cb == offQuad);
        if (match) {
            return merged;
        } else {
            return null;
        }
    }

    private ArrayList<String> getAllCorrectAStrings(boolean black, int off, int on, String value) {
        ArrayList<String> vs = new ArrayList<>();
        ArrayList<Integer> ints1 = getCorrectIntegers(black ? on : off);
        for (int i : ints1) {
            String bin = String.format("%" + all + "s", Integer.toString(i, 2)).replaceAll(" ", "0").replaceAll("1", value);
            vs.add(bin);
        }

        return vs;
    }

    private ArrayList<Integer> getCorrectIntegers(int correct) {
        ArrayList<Integer> correct1s = new ArrayList<>();
        int total = (int) (Math.pow(2, all));
        for (int i = 0; i < total; i++) {
            if (hasCorrectNumber1s(correct, i)) {
                correct1s.add(i);
            }
        }
        return correct1s;
    }

    private boolean hasCorrectNumber1s(int num, int ord) {
        long i = Integer.toString(ord, 2).chars().filter(s -> s == '1').count();
        return i == num;
    }

}
