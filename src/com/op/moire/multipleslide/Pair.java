package com.op.moire.multipleslide;

public class Pair {

    Top top;
    Bottom topn1;
    Bottom topn2;
    Bottom topn3;

    Pair(Top b, Bottom t1, Bottom t2, Bottom t3) {
        this.top = b;
        this.topn1 = t1;
        this.topn2 = t2;
        this.topn3 = t3;
    }
}
