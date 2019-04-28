package com.op.moire.fourstackold;

import java.util.ArrayList;

public class Quad {
    Values values1;
    Values values2;
    Values values4;
    Values values8;

    Quad(Values values1, Values values2, Values values4, Values values8) {
        this.values1 = values1;
        this.values2 = values2;
        this.values4 = values4;
        this.values8 = values8;
    }

    @Override
    public boolean equals(Object object) {
        Values val1 = this.values1;
        Values val2 = this.values2;
        Values val3 = this.values4;
        Values val4 = this.values8;

        Quad quad = (Quad) object;
        Values val1b = quad.values1;
        Values val2b = quad.values2;
        Values val3b = quad.values4;
        Values val4b = quad.values8;

        ArrayList<boolean[]> all = new ArrayList<>();
        all.add(val1.value);
        all.add(val2.value);
        all.add(val3.value);
        all.add(val4.value);

        ArrayList<boolean[]> allb = new ArrayList<>();
        allb.add(val1b.value);
        allb.add(val2b.value);
        allb.add(val3b.value);
        allb.add(val4b.value);

        return all.containsAll(allb);
    }

    @Override
    public String toString() {
        return values1.toString() +":"+values2.toString()+":"+values4.toString() +":"+ values8.toString();
    }
}
