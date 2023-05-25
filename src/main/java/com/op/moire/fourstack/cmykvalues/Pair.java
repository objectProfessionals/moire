package com.op.moire.fourstack.cmykvalues;

public class Pair extends Pixel {

    Pixel s1;
    Pixel s2;

    Pair(Pixel s1, Pixel s2) {
        this.bracket = bracketMap.get("p");

        this.s1 = s1;
        this.s2 = s2;
        this.cell = getMergeString(s1, s2, null, null);
        this.value = calculateValue();
    }

    @Override
    public boolean equals(Object o) {
        Pair p = (Pair) o;
        return this.cell.equals(p.cell);
    }

}
