package com.op.moire.fourstack.multi;

public class Pair {
    String va;
    String vb;
    String m;

    Pair(String va, String vb) {
        this.va = va;
        this.vb = vb;
    }

    @Override
    public boolean equals(Object o) {
        Pair p = (Pair) o;
        return (this.m.equals(p.m));
    }
}
