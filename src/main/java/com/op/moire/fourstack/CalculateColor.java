package com.op.moire.fourstack;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class CalculateColor {
    static final CalculateColor calculate = new CalculateColor();
    int all = 9;
    int off = 2;
    int on = 3;
    int off8 = 2;
    int on8 = 3;
    int offPair = 1;
    int onPair = 2;
    int offTriple = 1;
    int onTriple = 2;
    HashMap<String, Color> str2Col = new HashMap();

    public static void main(String[] args) {
        System.out.println("Started...");
        calculate.test();
        System.out.println("Finished...");
    }

    private void test() {
        boolean[] blacks = {false,false,false,false,false,false,false,false,false,true,true,true,true,true,true};
        //boolean[] blacks = {false, false, false, false, false, false, false, false, false, true, true, true, true, true, true};
        //boolean[] blacks = {false, true, true, true, false, false, true, true, true, true, false, true, true, true, false};
        //boolean[] blacks = {false, true, true, false, true, false, true, true, true, true, false, true, true, false, true};
        //boolean[] blacks = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
        //boolean[] blacks = {false, true, false, false, false, false, true, true, true, false, false, true, false, false, false};
        //boolean[] blacks = {false,false,false,false,true,false,true,false,false,false,false,false,false,false,false};
        //boolean[] blacks = {false, true, true, false, true, false, true, true, true, true, false, true, true, false, true};
        //boolean[] blacks = {true,true,true,true,false,false,false,false,false,false,true,false,false,true,false};
        //boolean[] blacks = {false,false,false,false,false,false,true,false,false,false,false,false,false,false,false};
        calculate(blacks);
    }

    ArrayList<String[]> calculate(boolean[] blacks) {
        setup();
        System.out.println();
        for (boolean bl : blacks) {
            System.out.print(bl + ",");
        }
        System.out.println();

        ArrayList<String> v1s = getCorrectStrings(blacks[0], "C", off, on);
        ArrayList<String> v2s = getCorrectStrings(blacks[1], "M", off, on);
        ArrayList<String> v4s = getCorrectStrings(blacks[3], "Y", off, on);
        ArrayList<String> v8s = getCorrectStrings(blacks[3], "X", off, on);
        //ArrayList<String> v8s = getAllCorrectStrings(blacks[7], "CMY", off8, on8);

//        printAllln(v1s);
//        printAllln(v2s);
//        printAllln(v4s);
//        printAllln(v8s);

        //        printAllln(v8s);
        ArrayList<String[]> filtered = getAll(v1s, v2s, v4s, v8s, blacks);

        System.out.println(filtered.size());

        return filtered;
    }

    private void setup() {
        str2Col.put("C", getAlpha(Color.BLACK));
        str2Col.put("M", getAlpha(Color.BLACK));
        str2Col.put("Y", getAlpha(Color.BLACK));
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

    private ArrayList<String[]> getAll(ArrayList<String> v1s, ArrayList<String> v2s, ArrayList<String> v4s, ArrayList<String> v8s, boolean[] blacks) {
        int num12 = blacks[2] ? onPair : offPair;
        int num24 = blacks[5] ? onPair : offPair;
        int num14 = blacks[4] ? onPair : offPair;
        int num28 = blacks[9] ? onPair : offPair;
        int num18 = blacks[8] ? onPair : offPair;
        int num48 = blacks[11] ? onPair : offPair;
        int num124 = blacks[6] ? onTriple : offTriple;
        int num128 = blacks[10] ? onTriple : offTriple;
        int num248 = blacks[13] ? onTriple : offTriple;
        int num148 = blacks[12] ? onTriple : offTriple;


        ArrayList<String[]> filtered = new ArrayList<>();
        int i = 0;
        for (String v1 : v1s) {
            for (String v2 : v2s) {
                if (hasCorrectPair(num12, v1, v2, 'B')) {
                    for (String v4 : v4s) {
                        if (hasCorrectPair(num24, v2, v4, 'R')) {
                            if (hasCorrectPair(num14, v1, v4, 'G')) {
                                if (hasCorrectTriple(num124, v1, v2, v4)) {
                                    for (String v8 : v8s) {
                                        if (hasCorrectTriple(num128, v1, v2, v8)) {
                                            if (hasCorrectTriple(num248, v2, v4, v8)) {
                                                if (hasCorrectTriple(num148, v1, v4, v8)) {
                                                    //if (hasAllCorrectPairs(num18, num28, num48, v1, v2, v4, v8)) {
                                                    String[] arr = {v1, v2, v4, v8};
                                                    filtered.add(arr);
                                                }
                                            }

                                        }
                                    }

                                }
                            }
                        }

                    }
//                        if (hasCorrectPair(num24, v2 , v4)) {
//
//                            filtered.add(v1);
//                            filtered.add(v2);
//                            filtered.add(v4);
//                        }
//
//                    }
                }
            }
            i++;
        }

        return filtered;
    }

    private boolean hasAllCorrectPairs(int num18, int num28, int num48, String v1, String v2, String v4, String v8) {
        String[] arr = {"RGB", "RBG", "GRB", "GBR", "BRG", "BGR"};
        String[] arr2 = {"R", "G", "B", "X"};
        for (String a : arr) {
            char a1 = a.charAt(0);
            char a2 = a.charAt(1);
            char a3 = a.charAt(2);
            if (hasCorrectPair(num18, v1, v8, a1)) {
                if (hasCorrectPair(num28, v2, v8, a2)) {
                    if (hasCorrectPair(num48, v4, v8, a3)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean hasCorrectPair(int on, String v1, String v2, char co) {
        Pair pair = new Pair(v1, v2);
        return pair.succesMerge(on, co);
    }

    private boolean hasCorrectTriple(int on, String v1, String v2, String v3) {
        Triple triple = new Triple(v1, v2, v3);
        boolean sX = triple.succesMerge(on, 'X', 'B');
        boolean sB = triple.succesMerge(on, 'B', 'R');
        boolean sR = triple.succesMerge(on, 'R', 'G');
        boolean sG = triple.succesMerge(on, 'G', 'B');
        return sX || sB || sR || sG;
    }

    private ArrayList<String> getCorrectStrings(boolean black, String c, int off, int on) {
        ArrayList<String> vs = new ArrayList<>();
        int vNum = black ? on : off;
        ArrayList<Integer> ints1 = getCorrectIntegers(vNum);
        for (int i : ints1) {
            String repC = replace1s(i, c);
            vs.add(repC);
        }
        return vs;
    }

    private ArrayList<String> getAllCorrectStrings(boolean black, String c, int off, int on) {
        ArrayList<String> vs = new ArrayList<>();
        int vNum = black ? on : off;
        ArrayList<Integer> ints1 = getCorrectIntegers(vNum);
        for (int i : ints1) {
            String bin = String.format("%" + all + "s", Integer.toString(i, 2)).replaceAll(" ", "0");
            vs.add(bin);
        }

        ArrayList<String> allcombs = getAllCombinations(vNum);

        ArrayList<String> fs = new ArrayList<>();
        for (String rep : allcombs) {
            for (String f : vs) {
                String res1 = new String(f).replaceFirst("1", rep.substring(0, 1));
                if (vNum >= 2) {
                    String res2 = res1.replaceFirst("1", rep.substring(1, 2));
                    if (vNum >= 3) {
                        String res3 = res2.replaceFirst("1", rep.substring(2, 3));
                        if (vNum == 4) {
                            String res4 = res3.replaceFirst("1", rep.substring(3, 4));
                            fs.add(res4);
                        } else {
                            fs.add(res3);
                        }
                    } else {
                        fs.add(res2);
                    }
                } else {
                    fs.add(res1);
                }
            }
        }

        return fs;
    }

    private ArrayList<String> getAllCombinations(int depth) {
        ArrayList<String> allCombs = new ArrayList<>();
        String[] arr = {"C", "M", "Y"};
        if (depth == 1) {
            for (int i = 0; i < arr.length; i++) {
                String all = arr[i];
                allCombs.add(all);
            }
        } else if (depth == 2) {
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr.length; j++) {
                    String all = arr[i] + arr[j];
                    allCombs.add(all);
                }
            }
        } else if (depth == 3) {
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr.length; j++) {
                    for (int k = 0; k < arr.length; k++) {
                        String all = arr[i] + arr[j] + arr[k];
                        allCombs.add(all);
                    }
                }
            }
        } else if (depth == 4) {
            for (int h = 0; h < arr.length; h++) {
                for (int i = 0; i < arr.length; i++) {
                    for (int j = 0; j < arr.length; j++) {
                        for (int k = 0; k < arr.length; k++) {
                            String all = arr[h] + arr[i] + arr[j] + arr[k];
                            allCombs.add(all);
                        }
                    }
                }
            }
        }

        return allCombs;
    }

    private void printAllMerged(ArrayList<Pair> st) {
        for (Pair p : st) {
            System.out.println(p.s1 + ":" + p.s2 + "=" + p.merged());
        }
        System.out.println();
    }

    private void printAll(ArrayList<String[]> st) {
        System.out.println();
        for (String[] s : st) {
            System.out.println(s[0] + ":" + s[1] + ":" + s[2] + ":" + s[3]);
        }
        System.out.println();
    }


    private String replace1s(Integer i, String replace) {
        String n = String.format("%" + all + "s", Integer.toString(i, 2)).replaceAll(" ", "0");
        return n.replaceAll("1", replace);
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

    private void println(Integer i) {
        String n = String.format("%" + all + "s", Integer.toString(i, 2)).replaceAll(" ", "0");
        System.out.println(n);
    }

    private void printAllln(ArrayList<String> vs) {
        for (String v : vs) {
            System.out.println(v);
        }
    }

}
