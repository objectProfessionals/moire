package com.op.moire.multipleslide;

public class Row {

    Top top;
    Bottom botn1;
    Bottom botn2;
    Bottom botn3;

    Row(Top b, Bottom t1, Bottom t2, Bottom t3) {
        this.top = b;
        this.botn1 = t1;
        this.botn2 = t2;
        this.botn3 = t3;
    }
}
