package com.op.moire.fourstack;

import java.util.ArrayList;

public class Calculate {
    static final Calculate calculate = new Calculate();
    static int all = 0;

    public static void main(String[] args) {
        System.out.println("Started...");
        calculate.test();
        System.out.println("Finished...");
    }

    private void test() {
        boolean[] blacks = {false,false,false,false,false,false,false,false,false,true,true,true,true,true,true
        };
        calculate(blacks);
    }

    Integer[] calculate(boolean[] blacks) {
        System.out.println();
        for (boolean bl : blacks) {
            System.out.print(bl+",");
        }
        System.out.println();

        int off1 = 3;
        int on1 = 4;

        int off2 = 5;
        int on2 = 6;
        int range2 = 1;

        int off3 = 5;
        int on3 = 8;
        int range3 = 2;
        int off4 = 5;
        int on4 = 7;
        all = 12;
        int range4 = 2;

        ArrayList<Integer> offs = getAllValues(off1);
        ArrayList<Integer> ons = getAllValues(on1);

        boolean allFalse = true;
        boolean allTrue = true;
        for (boolean black : blacks) {
            if (!black) {
                allTrue = false;
            }
            if (black) {
                allFalse = false;
            }
        }

        if (allTrue) {
            int t = (int) Math.pow(2, all);
            Integer[] allOffs = {t, t, t, t};
            return allOffs;
        }

        if (allFalse) {
            Integer[] allOffs = {0, 0, 0, 0};
            return allOffs;
        }

        System.out.println("offs:" + offs.size());
        System.out.println("ons:" + ons.size());

        ArrayList<Integer> vs1 = blacks[0] ? ons : offs;
        ArrayList<Integer> vs2 = blacks[1] ? ons : offs;
        ArrayList<Integer> vs4 = blacks[3] ? ons : offs;
        ArrayList<Integer> vs8 = blacks[7] ? ons : offs;

        int num12L = blacks[2] ? on2 : off2 - range2;
        int num12U = blacks[2] ? on2+range2 : off2;
        int num24L = blacks[5] ? on2 : off2-range2;
        int num24U = blacks[5] ? on2+range2 : off2;
        int num28L = blacks[9] ? on2 : off2-range2;
        int num28U = blacks[9] ? on2+range2 : off2;
        int num14L = blacks[4] ? on2 : off2-range2;
        int num14U = blacks[4] ? on2+range2 : off2;
        int num18L = blacks[8] ? on2 : off2-range2;
        int num18U = blacks[8] ? on2+range2 : off2;
        int num48L = blacks[11] ? on2 : off2-range2;
        int num48U = blacks[11] ? on2+range2 : off2;

        ArrayList<Integer[]> v1248P = getAllCombinedPairs(vs1, vs2, vs4, vs8,
                num12L, num12U, num24L, num24U, num28L, num28U, num14L, num14U, num18L, num18U, num48L, num48U);

        System.out.println("v1248 Pairs:" + v1248P.size());
        println(all, v1248P.get(0)[0]);
        println(all, v1248P.get(0)[1]);
        println(all, v1248P.get(0)[2]);
        println(all, v1248P.get(0)[3]);

        int num124L = blacks[6] ? on3 : off3 - range3;
        int num124U = blacks[6] ? on3 + range3 : off3;
        int num128L = blacks[10] ? on3 : off3 - range3;
        int num128U = blacks[10] ? on3 + range3 : off3;
        int num148L = blacks[12] ? on3 : off3 - range3;
        int num148U = blacks[12] ? on3 + range3 : off3;
        int num248L = blacks[13] ? on3 : off3 - range3;
        int num248U = blacks[13] ? on3 + range3 : off3;

        ArrayList<Integer[]> v1248T = getAllCombinedTriples(v1248P,
                num124L, num124U, num128L, num128U, num148L, num148U, num248L, num248U);
        System.out.println("v1248 Tris:" + v1248T.size());
        println(all, v1248T.get(0)[0]);
        println(all, v1248T.get(0)[1]);
        println(all, v1248T.get(0)[2]);
        println(all, v1248T.get(0)[3]);

        int num1248L = blacks[14] ? on4 : off4 - range4;
        int num1248U = blacks[14] ? on4 + range4 : off4;

        ArrayList<Integer[]> v1248 = getAllCombinedQuad(v1248T, num1248L, num1248U);
        System.out.println("v1248 Quads:" + v1248.size());

        Integer[] result = v1248.get(0);
        println(all, result[0]);
        println(all, result[1]);
        println(all, result[2]);
        println(all, result[3]);

//        int[] allNums = {num1, num2, num12, num4, num14,
//                num24, num124, num8, num18, num28,
//                num128, num48, num148, num248, num1248};
        //checkAll(v1248, allNums);

        return result;
    }

    private ArrayList<Integer[]> getAllCombinedPairs(ArrayList<Integer> vs1, ArrayList<Integer> vs2, ArrayList<Integer> vs4, ArrayList<Integer> vs8,
                                                     int num12L, int num12U,
                                                     int num24L, int num24U,
                                                     int num28L,int num28U,
                                                     int num14L,int num14U,
                                                     int num18L,int num18U,
                                                     int num48L, int num48U) {
        ArrayList<Integer[]> passing = new ArrayList<>();
        int i = 0;
        for (Integer v1 : vs1) {
            System.out.println(i + "/" + vs1.size());
            i++;
            for (Integer v2 : vs2) {
                if (hasGTLNumber1s(num12L, num12U, v1 | v2)) {
                    for (Integer v4 : vs4) {
                        if (hasGTLNumber1s(num24L, num24U, v2 | v4)) {
                            for (Integer v8 : vs8) {
                                if (hasGTLNumber1s(num48L, num48U, v4 | v8)) {
                                    if (hasGTLNumber1s(num18L, num18U, v1 | v8)
                                            && hasGTLNumber1s(num14L, num14U, v1 | v4)
                                            && hasGTLNumber1s(num28L, num28U, v2 | v8)
                                            ) {
                                        Integer[] ints = {v1, v2, v4, v8};
                                        passing.add(ints);
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }

        return passing;
    }

    private ArrayList<Integer[]> getAllCombinedTriples(ArrayList<Integer[]> inputs,
                                                       int num124L, int num124U,
                                                       int num128L, int num128U,
                                                       int num148L, int num148U,
                                                       int num248L, int num248U) {

        ArrayList<Integer[]> passing = new ArrayList<>();
        for (Integer[] ints : inputs) {
            int v1 = ints[0];
            int v2 = ints[1];
            int v4 = ints[2];
            int v8 = ints[3];

            if (hasGTLNumber1s(num124L, num124U, v1 | v2 | v4)) {
                if (hasGTLNumber1s(num128L, num128U, v1 | v2 | v8)) {
                    if (hasGTLNumber1s(num148L, num148U, v1 | v4 | v8)) {
                        if (hasGTLNumber1s(num248L, num248U, v2 | v4 | v8)) {
                            Integer[] arr = {v1, v2, v4, v8};
                            passing.add(arr);
                        }
                    }

                }

            }

        }

        return passing;
    }

    private ArrayList<Integer[]> getAllCombinedQuad(ArrayList<Integer[]> inputs,
                                                    int num1248L, int num1248U) {

        ArrayList<Integer[]> passing = new ArrayList<>();
        for (Integer[] ints : inputs) {
            int v1 = ints[0];
            int v2 = ints[1];
            int v4 = ints[2];
            int v8 = ints[3];

            if (hasGTLNumber1s(num1248L, num1248U, v1 | v2 | v4 | v8)) {
                Integer[] arr = {v1, v2, v4, v8};
                passing.add(arr);
            }
        }

        return passing;
    }

    private boolean hasCorrectNumber1s(int num, int ord) {
        return Integer.toString(ord, 2).chars().filter(s -> s == '1').count() == num;
    }

    private boolean hasGTLNumber1s(int numL, int numU, int ord) {
        long i = Integer.toString(ord, 2).chars().filter(s -> s == '1').count();
        return i >= numL && i <= numU;
    }

    private ArrayList<Integer> getAllValues(int num) {
        ArrayList<Integer> ints = new ArrayList<>();
        int total = (int) Math.pow(2, all);
        for (int i = 0; i < total; i++) {
            String bin = Integer.toString(i, 2);
            long count = bin.chars().filter(s -> s == '1').count();
            if (count == num) {
                ints.add((int) i);
            }
        }

        return ints;
    }

    private void checkAll(Integer[] v1248, int[] allNums) {
        int i = 1;
        for (int num : allNums) {
            check(v1248, i, num);
            i++;
        }
    }

    private void check(Integer[] v1248, int i, int num) throws RuntimeException {
        String n = String.format("%4s", Integer.toString(i, 2)).replaceAll(" ", "0");
        Integer v1 = 0;
        Integer v2 = 0;
        Integer v3 = 0;
        Integer v4 = 0;
        if (n.substring(0, 1).equals("1")) {
            v4 = v1248[3];
        }
        if (n.substring(1, 2).equals("1")) {
            v3 = v1248[2];
        }
        if (n.substring(2, 3).equals("1")) {
            v2 = v1248[1];
        }
        if (n.substring(3, 4).equals("1")) {
            v1 = v1248[0];
        }

        int c = v1 | v2 | v3 | v4;
        if (Integer.toString(c, 2).chars().filter(s -> s == '1').count() != num) {
            System.out.println(i + " not passing check");
            throw new RuntimeException(i + " not passing check");
        }


    }

    private void println(int all, Integer i) {
        String n = String.format("%" + all + "s", Integer.toString(i, 2)).replaceAll(" ", "0");
        System.out.println(n);
    }

    private ArrayList<Integer> filter(ArrayList<Integer[]> v1248, int pos) {
        ArrayList<Integer> filtered = new ArrayList<>();
        for (Integer[] ints : v1248) {
            filtered.add(ints[pos]);
        }

        return filtered;
    }


//    private ArrayList<Integer[]> getAllCombinedPairs(ArrayList<Integer> vs1, ArrayList<Integer> vs2, ArrayList<Integer> vs4, ArrayList<Integer> vs8,
//                                                     int num12, int num24, int num28, int num14, int num18, int num48) {
//        ArrayList<Integer[]> passing = new ArrayList<>();
//        int i = 0;
//        for (Integer v1 : vs1) {
//            System.out.println(i + "/" + vs1.size());
//            i++;
//            for (Integer v2 : vs2) {
//                if (hasCorrectNumber1s(num12, v1 | v2)) {
//                    for (Integer v4 : vs4) {
//                        if (hasCorrectNumber1s(num24, v2 | v4)) {
//                            for (Integer v8 : vs8) {
//                                if (hasCorrectNumber1s(num48, v4 | v8)) {
//                                    if (hasCorrectNumber1s(num18, v1 | v8)
//                                            && hasCorrectNumber1s(num14, v1 | v4)
//                                            && hasCorrectNumber1s(num28, v2 | v8)
//                                            ) {
//                                        Integer[] ints = {v1, v2, v4, v8};
//                                        passing.add(ints);
//                                    }
//
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        return passing;
//    }
}
