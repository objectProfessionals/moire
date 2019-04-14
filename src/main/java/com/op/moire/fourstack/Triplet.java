package com.op.moire.fourstack;

import java.util.ArrayList;

import static com.op.moire.fourstack.Stack.ALL;

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

        ArrayList<String> all = new ArrayList<>();
        all.add(val1.value);
        all.add(val2.value);
        all.add(val3.value);

        ArrayList<String> allb = new ArrayList<>();
        allb.add(val1b.value);
        allb.add(val2b.value);
        allb.add(val3b.value);

        return all.containsAll(allb);
    }

    String merged() {
        String val = "";
        for (int n = 0; n < ALL; n++) {
            String bn = values1.value.substring(n, n + 1);
            String tn = values2.value.substring(n, n + 1);
            String vn = values3.value.substring(n, n + 1);
            if ("1".equals(bn) || "1".equals(tn) || "1".equals(vn)) {
                val = val + "1";
            } else {
                val = val + "0";
            }
        }
        return val;
   }

}
