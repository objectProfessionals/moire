package com.op.moire.fourstack;

import com.op.moire.Base;

import java.util.ArrayList;

public class Stack extends Base {

    static final int ALL = 6;
    static Stack stack = new Stack();

    public static void main(String[] args) throws Exception {
        stack.test();
    }

    private void test() {
        //1234567890ABCDE
        boolean[] blacks = {false, true, true, true, true,
                false, true, true, true, false,
                false, true, false, true, true};

        int off0 = 1;
        int on0 = off0+1;
        int off1 = on0;
        int on1 = off1+1;
        int off2 = on1;
        int on2 = off2+1;
        int off3 = on2;
        int on3 = off3+1;

        ArrayList<Values> offs = Values.createAllFilledCells(ALL, off0);
        ArrayList<Values> ons = Values.createAllFilledCells(ALL, on0);

        Cell cell1 = new Cell("1", blacks[0] ? ons : offs);
        Cell cell2 = new Cell("2", blacks[1] ? ons : offs);
        Cell cell4 = new Cell("4", blacks[3] ? ons : offs);
        Cell cell8 = new Cell("8", blacks[7] ? ons : offs);

        //Pairs
        ArrayList<Pair> pairs12 = Cell.getPairsByCombination(cell1, cell2, blacks[2] ? on1 : off1);
        ArrayList<Triplet> triplets124 = Cell.getTripletsByCombination(pairs12, cell4, blacks[6] ? on2 : off2);
        ArrayList<Quad> quad1248 = Cell.getQuadsByCombination(triplets124, cell8, blacks[14] ? on3 : off3);

        ArrayList<Pair> pairs48 = Cell.getPairsByCombination(cell4, cell8, blacks[11] ? on1 : off1);
        ArrayList<Triplet> triplets148 = Cell.getTripletsByCombination(pairs48, cell1, blacks[12] ? on2 : off2);
        ArrayList<Quad> quad1248b = Cell.getQuadsByCombination(triplets148, cell2, blacks[14] ? on3 : off3);

        ArrayList<Pair> pairs14 = Cell.getPairsByCombination(cell1, cell4, blacks[4] ? on1 : off1);
        ArrayList<Triplet> triplets142 = Cell.getTripletsByCombination(pairs14, cell2, blacks[12] ? on2 : off2);
        ArrayList<Quad> quad1248c = Cell.getQuadsByCombination(triplets142, cell8, blacks[14] ? on3 : off3);

        ArrayList<Pair> pairs18 = Cell.getPairsByCombination(cell1, cell8, blacks[8] ? on1 : off1);
        ArrayList<Triplet> triplets182 = Cell.getTripletsByCombination(pairs18, cell2, blacks[10] ? on2 : off2);
        ArrayList<Quad> quad1248d = Cell.getQuadsByCombination(triplets182, cell4, blacks[14] ? on3 : off3);

        ArrayList<Pair> pairs24 = Cell.getPairsByCombination(cell2, cell4, blacks[5] ? on1 : off1);
        ArrayList<Triplet> triplets241 = Cell.getTripletsByCombination(pairs24, cell1, blacks[6] ? on2 : off2);
        ArrayList<Quad> quad1248e = Cell.getQuadsByCombination(triplets241, cell8, blacks[14] ? on3 : off3);

        ArrayList<Pair> pairs28 = Cell.getPairsByCombination(cell2, cell8, blacks[9] ? on1 : off1);
        ArrayList<Triplet> triplets281 = Cell.getTripletsByCombination(pairs28, cell1, blacks[6] ? on2 : off2);
        ArrayList<Quad> quad1248f = Cell.getQuadsByCombination(triplets281, cell4, blacks[14] ? on3 : off3);

        ArrayList<Triplet> triplets284 = Cell.getTripletsByCombination(pairs28, cell4, blacks[13] ? on2 : off2);
        ArrayList<Quad> quad1248g = Cell.getQuadsByCombination(triplets284, cell1, blacks[14] ? on3 : off3);

        ArrayList<Quad> rem = new ArrayList<>();
        rem.addAll(quad1248);
        System.out.println(rem.size());
        rem.retainAll(quad1248b);
        System.out.println(rem.size());
        rem.retainAll(quad1248c);
        System.out.println(rem.size());
        rem.retainAll(quad1248d);
        System.out.println(rem.size());
        rem.retainAll(quad1248e);
        System.out.println(rem.size());
        rem.retainAll(quad1248f);
        System.out.println(rem.size());
        rem.retainAll(quad1248g);

//        //Triplets
//        ArrayList<Triplet> tris128 = Cell.getTripletsByCombination(pairs12, cell8, blacks[10] ? 6 : 5);
//        ArrayList<Triplet> tris248 = Cell.getTripletsByCombination(pairs24, cell8, blacks[13] ? 6 : 5);
//        ArrayList<Triplet> tris148 = Cell.getTripletsByCombination(pairs14, cell8, blacks[12] ? 6 : 5);
//
//
//        //Quads
//        ArrayList<Quad> quads1248 = Cell.getQuadsByCombination(cell1, cell2, cell4, cell8, blacks[14] ? 8 : 7);
//        cell1 = new Cell("1", getQuadsAsList(quads1248, 1));
//        cell2 = new Cell("2", getQuadsAsList(quads1248, 2));
//        cell4 = new Cell("4", getQuadsAsList(quads1248, 3));
//        cell8 = new Cell("8", getQuadsAsList(quads1248, 4));
//
//        printAll(cell1, cell2, cell4, cell8);
//
//
//
//        printAll(cell1, cell2, cell4, cell8);


        /*
            1+2 3 Z 4
            1+4 5 Z 4
            1+8 9 X 3
            2+4 6 X 3
            2+8 10 X 3
            4+8 12 Z 4

            1+2+4 7 Z 5
            1+2+8 11 X 4
            2+4+8 14 Z 5
            1+4+8 13 X 4

            1+2+4+815 Z ZZZZZX 5

         */
    }

    private void printAll(Cell cell1, Cell cell2, Cell cell4, Cell cell8) {
        System.out.println(cell1.values.size());
        System.out.println(cell2.values.size());
        System.out.println(cell4.values.size());
        System.out.println(cell8.values.size());
    }

    private ArrayList<Values> getPairsAsList(ArrayList<Pair> pairs, int pos) {
        ArrayList<Values> vals = new ArrayList();
        for (Pair pair : pairs) {
            if (pos == 1) {
                if (!vals.contains(pair.values1)) {
                    vals.add(pair.values1);
                }
            } else {
                if (!vals.contains(pair.values2)) {
                    vals.add(pair.values2);
                }
            }
        }

        return vals;
    }

    private ArrayList<Values> getTripletsAsList(ArrayList<Triplet> trips, int pos) {
        ArrayList<Values> vals = new ArrayList();
        for (Triplet triplet : trips) {
            if (pos == 1) {
                if (!vals.contains(triplet.values1)) {
                    vals.add(triplet.values1);
                }
            } else if (pos == 2) {
                if (!vals.contains(triplet.values2)) {
                    vals.add(triplet.values2);
                }
            } else {
                if (!vals.contains(triplet.values3)) {
                    vals.add(triplet.values3);
                }
            }
        }

        return vals;
    }

    private ArrayList<Values> getQuadsAsList(ArrayList<Quad> quads, int pos) {
        ArrayList<Values> vals = new ArrayList();
        for (Quad quad : quads) {
            if (pos == 1) {
                if (!vals.contains(quad.values1)) {
                    vals.add(quad.values1);
                }
            } else if (pos == 2) {
                if (!vals.contains(quad.values2)) {
                    vals.add(quad.values2);
                }
            } else if (pos == 3) {
                if (!vals.contains(quad.values3)) {
                    vals.add(quad.values3);
                }
            } else {
                if (!vals.contains(quad.values4)) {
                    vals.add(quad.values4);
                }
            }
        }

        return vals;
    }

}
