package com.op.moire.fourstackold;

import java.util.ArrayList;


public class Triplet {
    Values values1;
    Values values2;
    Values values3;

    Triplet(Values values1, Values values2, Values values3) {
        this.values1 = values1;
        this.values2 = values2;
        this.values3 = values3;
    }

    @Override
    public boolean equals(Object object) {
        Values val1 = this.values1;
        Values val2 = this.values2;
        Values val3 = this.values3;

        Triplet pair = (Triplet) object;
        Values val1b = pair.values1;
        Values val2b = pair.values2;
        Values val3b = pair.values3;

        ArrayList<boolean[]> all = new ArrayList<>();
        all.add(val1.value);
        all.add(val2.value);
        all.add(val3.value);

        ArrayList<boolean[]> allb = new ArrayList<>();
        allb.add(val1b.value);
        allb.add(val2b.value);
        allb.add(val3b.value);

        return all.containsAll(allb);
    }

}
