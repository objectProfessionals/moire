package com.op.moire.fourstack.cmyk;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class CalculateCMYK {
    static final CalculateCMYK calculate = new CalculateCMYK();
    HashMap<String, Color> str2Col = new HashMap();
    static int all = 6;
    int allPainted = 2;
    int offSingle = 2;
    int onSingle = 3;
    int offPair = 2;
    int onPair = 3;
    int offTriple = 2;
    int onTriple = 3;
    int offQuad = 2;
    int onQuad = 3;

    public String getAllParms() {
        HashMap<String, Integer> parms = new HashMap<>();
        parms.put("all", all);
        parms.put("allPainted", allPainted);
        parms.put("offSingle", offSingle);
        parms.put("onSingle", onSingle);
        parms.put("offTriple", offTriple);
        parms.put("onTriple", onTriple);
        parms.put("offQuad", offQuad);
        parms.put("onQuad", onQuad);
        String ret = "";
        for (String key : parms.keySet()) {
            ret = ret +","+key+(parms.get(key));
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println("Started...");
        calculate.test();
        System.out.println("Finished...");
    }

    private void test() {
        //boolean[] blacks = {false,false,false,false,false,false,false,false,false,true,true,true,true,true,true};
        //boolean[] blacks = {false, false, false, false, false, false, false, false, false, true, true, true, true, true, true};
        //boolean[] blacks = {false, true, true, true, false, false, true, true, true, true, false, true, true, true, false};
        //boolean[] blacks = {false, true, false, true, false, false, true, true, true, true, false, true, true, true, true};
        boolean[] blacks = {false,false,false,false,false,false,false,false,false,true,true,true,true,true,true};
        calculate(blacks);
    }

    ArrayList<String[]> calculate(boolean[] blacks) {
        setup();

        String blString = "";
        for (boolean bl : blacks) {
            blString = blString + bl + ",";
        }
        System.out.println(blString);

        ArrayList<String[]> vs = new ArrayList<>();

        if (blString.equals("false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,")) {
            String wh = "000000";
            String[] whs = {wh, wh, wh, wh};
            vs.add(whs);
            return vs;
        }

        int num12 = blacks[2] ? onPair : offPair;
        int num14 = blacks[4] ? onPair : offPair;
        int num18 = blacks[8] ? onPair : offPair;
        int num24 = blacks[5] ? onPair : offPair;
        int num28 = blacks[9] ? onPair : offPair;
        int num48 = blacks[11] ? onPair : offPair;

        ArrayList<String> v1s = getAllCorrectAStrings(blacks[0], offSingle, onSingle);
        ArrayList<String> v2s = getAllCorrectAStrings(blacks[1], offSingle, onSingle);
        v1s = filterPairs(v1s, v2s, num12);
        ArrayList<String> v4s = getAllCorrectAStrings(blacks[3], offSingle, onSingle);
        v1s = filterPairs(v1s, v4s, num14);
        v2s = filterPairs(v2s, v4s, num24);
        ArrayList<String> v8s = getAllCorrectAStrings(blacks[7], offSingle, onSingle);
        v1s = filterPairs(v1s, v8s, num18);
        v2s = filterPairs(v2s, v8s, num28);
        v4s = filterPairs(v4s, v8s, num48);

        System.out.println(v1s.size() + ":" + v2s.size() + ":" + v4s.size() + ":" + v8s.size() + ":");


        ArrayList<String[]> filtered = filterPairs(v1s, v2s, v4s, v8s, blacks);

        System.out.println(filtered.get(0)[0]);
        System.out.println(filtered.get(0)[1]);
        System.out.println(filtered.get(0)[2]);
        System.out.println(filtered.get(0)[3]);


        String[] arrv1 = {v1s.get(0), v1s.get(1), v1s.get(2), v1s.get(3)};
        vs.add(arrv1);
        return vs;
    }

    private ArrayList<String> filterPairs(ArrayList<String> vA, ArrayList<String> vB, int numAB) {
        ArrayList<String> filtered = new ArrayList<>();
        for (String va : vA) {
            for (String vb : vB) {
                if (hasCorrectPair(numAB, va, vb)) {
                    if (!filtered.contains(va)) {
                        filtered.add(va);
                    }
                }
            }
        }
        return filtered;
    }

    private ArrayList<String[]> filterPairs(ArrayList<String> v1s, ArrayList<String> v2s, ArrayList<String> v4s, ArrayList<String> v8s, boolean[] blacks) {
        ArrayList<String[]> filtered = new ArrayList<>();

        int num12 = blacks[2] ? onPair : offPair;
        int num14 = blacks[4] ? onPair : offPair;
        int num18 = blacks[8] ? onPair : offPair;
        int num24 = blacks[5] ? onPair : offPair;
        int num28 = blacks[9] ? onPair : offPair;
        int num48 = blacks[11] ? onPair : offPair;

        int num124 = blacks[6] ? onTriple : offTriple;
        int num128 = blacks[10] ? onTriple : offTriple;
        int num248 = blacks[13] ? onTriple : offTriple;
        int num148 = blacks[12] ? onTriple : offTriple;

        int num1248 = blacks[14] ? onQuad : offQuad;

//        long t1 = System.currentTimeMillis();
//        System.out.println("tALL=");
//        for (String v1 : v1s) {
//            for (String v2 : v2s) {
//                for (String v4 : v4s) {
//                    for (String v8 : v8s) {
//                        if (hasCorrectQuad(num1248, v1, v2, v4, v8)) {
//
//                        }
//                    }
//                }
//            }
//        }
//        System.out.println("tALL=" + (System.currentTimeMillis() - t1)/1000.0);
        //240x480x480x480 clean = 26542080000 = 106s => 1/4 tn / sec, 1 Quad check=>3mins

        for (String v1 : v1s) {
            for (String v2 : v2s) {
                if (hasCorrectPair(num12, v1, v2)) {
                    for (String v4 : v4s) {
                        if (hasCorrectPair(num14, v1, v4)) {
                            if (hasCorrectPair(num24, v2, v4)) {
                                for (String v8 : v8s) {
                                    if (hasCorrectQuad(num1248, v1, v2, v4, v8)) {

                                        if (hasCorrectTriplet(num128, v1, v2, v8)) {
                                            if (hasCorrectTriplet(num124, v1, v2, v4)) {
                                                if (hasCorrectTriplet(num248, v2, v4, v8)) {
                                                    if (hasCorrectTriplet(num148, v1, v4, v8)) {

                                                        if (hasCorrectPair(num18, v1, v8)) {
                                                            if (hasCorrectPair(num28, v2, v8)) {
                                                                if (hasCorrectPair(num48-1, v4, v8)) {
                                                                    System.out.println(v1 + ":" + v2 + ":" + v4 + ":" + v8);
                                                                    String[] arr = {v1, v2, v4, v8};
                                                                    filtered.add(arr);
                                                                    return filtered;
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

        return filtered;
    }

    private boolean hasCorrectPair(int numBlacks, String v1, String v2) {
        Pair pair = new Pair(v1, v2);
        return pair.checkMerged(numBlacks);
    }

    private boolean hasCorrectTriplet(int numBlacks, String v1, String v2, String v3) {
        Triple trip = new Triple(v1, v2, v3);
        return trip.checkMerged(numBlacks);
    }

    private boolean hasCorrectQuad(int numBlacks, String v1, String v2, String v3, String v4) {
        Quad quad = new Quad(v1, v2, v3, v4);
        return quad.checkMerged(numBlacks);
    }

    private ArrayList<String> getAllCorrectAStrings(boolean black, int off, int on) {
        ArrayList<String> vs = new ArrayList<>();
        ArrayList<Integer> ints1 = getCorrectIntegers(allPainted);
        for (int i : ints1) {
            String bin = String.format("%" + all + "s", Integer.toString(i, 2)).replaceAll(" ", "0");
            vs.add(bin);
        }

        ArrayList<String> allcombs = getAllCombinations(black, off, on);

        ArrayList<String> fs = new ArrayList<>();
        for (String rep : allcombs) {
            for (String f : vs) {
                String res1 = new String(f).replaceFirst("1", rep.substring(0, 1));
                String res2 = res1.replaceFirst("1", rep.substring(1, 2));
                if (allPainted >= 3) {
                    String res3 = res2.replaceFirst("1", rep.substring(2, 3));
                    if (allPainted == 4) {
                        String res4 = res3.replaceFirst("1", rep.substring(3, 4));
                        fs.add(res4);
                    } else {
                        fs.add(res3);
                    }
                } else {
                    fs.add(res2);
                }
            }
        }

        return fs;
    }

    private ArrayList<String> getAllCombinations(boolean black, int off, int on) {
        String[] arr1 = {"C", "Y", "M"};
        String[] arr2 = {"C", "Y", "M", "M"};
        if (black) {
            return getAllCombinations(black, arr2, off, on);
        } else {
            return getAllCombinations(black, arr1, off, on);
        }
    }

    private ArrayList<String> getAllCombinations(boolean black, String[] arr, int off, int on) {
        ArrayList<String> allCombs = new ArrayList<>();

        int num = arr.length;
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                if (allPainted >= 3) {
                    for (int k = 0; k < num; k++) {
                        if (allPainted >= 4) {
                            for (int l = 0; l < num; l++) {
                                String all = arr[i] + arr[j] + arr[k] + arr[l];
                                long c = all.chars().filter(s -> s == 'M').count();
                                if (black && c == on) {
                                    allCombs.add(all);
                                } else if (!black && c == off) {
                                    allCombs.add(all);
                                }
                            }
                        } else {
                            String all = arr[i] + arr[j] + arr[k];
                            long c = all.chars().filter(s -> s == 'M').count();
                            if (black && c == on) {
                                allCombs.add(all);
                            } else if (!black && c == off) {
                                allCombs.add(all);
                            }
                        }
                    }
                } else {
                    String all = arr[i] + arr[j];
                    long c = all.chars().filter(s -> s == 'M').count();
                    if (black && c == on) {
                        allCombs.add(all);
                    } else if (!black && c == off) {
                        allCombs.add(all);
                    }
                }
            }
        }

        return allCombs;
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

    private void setup() {
        str2Col.put("C", getAlpha(Color.CYAN));
        str2Col.put("M", getAlpha(Color.MAGENTA));
        str2Col.put("Y", getAlpha(Color.YELLOW));
        str2Col.put("0", getAlpha(Color.WHITE));
//        str2Col.put("R", Color.RED);
//        str2Col.put("B", Color.BLUE);
//        str2Col.put("G", Color.GREEN);
//        str2Col.put("X", Color.BLACK);

    }

    private Color getAlpha(Color col) {
        int al = 125;
        return new Color(col.getRed(), col.getGreen(), col.getBlue(), al);
    }


}
