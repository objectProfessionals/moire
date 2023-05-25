package com.op.moire.fourstack.cmykvalues;

public class Triple extends Pixel {

    Pixel s1;
    Pixel s2;
    Pixel s3;

    Triple(Pixel s1, Pixel s2, Pixel s3) {
        this.bracket = bracketMap.get("t");
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.cell = getMergeString(s1, s2, s3, null);
        this.value = calculateValue();
    }

    @Override
    public boolean equals(Object o) {
        Triple p = (Triple) o;
        return this.cell.equals(p.cell);
    }

}


