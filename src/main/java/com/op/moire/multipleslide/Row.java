package com.op.moire.multipleslide;

public class Row {

    Top top;
    Bottom botn1;
    Bottom botn2;
    Bottom botn3;
    Bottom botn4;
    Bottom botn5;

    Row(Top b, Bottom t1, Bottom t2, Bottom t3) {
        this.top = b;
        this.botn1 = t1;
        this.botn2 = t2;
        this.botn3 = t3;
    }

    Row(Top b, Bottom t1, Bottom t2, Bottom t3, Bottom t4, Bottom t5) {
        this.top = b;
        this.botn1 = t1;
        this.botn2 = t2;
        this.botn3 = t3;
        this.botn4 = t4;
        this.botn5 = t5;
    }
}
