package com.op.moire.fourstack;

import java.util.ArrayList;

public class Quad {
    Values values1;
    Values values2;
    Values values3;
    Values values4;

    Quad(Values values1, Values values2, Values values3, Values values4) {
        this.values1 = values1;
        this.values2 = values2;
        this.values3 = values3;
        this.values4 = values4;
    }

    @Override
    public boolean equals(Object object) {
        Values val1 = this.values1;
        Values val2 = this.values2;
        Values val3 = this.values3;
        Values val4 = this.values4;

        Quad quad = (Quad) object;
        Values val1b = quad.values1;
        Values val2b = quad.values2;
        Values val3b = quad.values3;
        Values val4b = quad.values4;

        ArrayList<String> all = new ArrayList<>();
        all.add(val1.value);
        all.add(val2.value);
        all.add(val3.value);
        all.add(val4.value);

        ArrayList<String> allb = new ArrayList<>();
        allb.add(val1b.value);
        allb.add(val2b.value);
        allb.add(val3b.value);
        allb.add(val4b.value);

        return all.containsAll(allb);
    }
}
