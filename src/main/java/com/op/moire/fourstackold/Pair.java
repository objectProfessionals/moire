package com.op.moire.fourstackold;

public class Pair {
    Values values1;
    Values values2;

    Pair(Values values1, Values values2) {
        this.values1 = values1;
        this.values2 = values2;
    }

    @Override
    public boolean equals(Object object) {
        Values val1 = this.values1;
        Values val2 = this.values2;

        Pair pair = (Pair) object;
        Values val1b = pair.values1;
        Values val2b = pair.values2;

        return val1.value.equals(val1b.value) && val2.value.equals(val2b.value)
                || val1.value.equals(val2b.value) && val2.value.equals(val1b.value);
    }

}
