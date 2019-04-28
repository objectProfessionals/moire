package com.op.moire.fourstack;

public class Pair {

    String s1 = "";
    String s2 = "";
    String mer = "";

    Pair(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    @Override
    public boolean equals(Object o) {
        Pair p = (Pair) o;
        return this.mer.equals(p.mer);
    }

    public String merged() {
        String m = "";
        for (int i = 0; i < s1.length(); i++) {
            String c1 = s1.substring(i, i + 1);
            String c2 = s2.substring(i, i + 1);
            m = m + merge(c1, c2);
        }

        mer = m;
        return m;
    }
    private String merge(String c1, String c2) {
        if (isEither(c1, c2, "C", "C")) {
            return "C";
        }
        if (isEither(c1, c2, "M", "M")) {
            return "M";
        }
        if (isEither(c1, c2, "Y", "Y")) {
            return "Y";
        }
        if (isEither(c1, c2, "C", "M")) {
            return "B";
        }
        if (isEither(c1, c2, "C", "Y")) {
            return "G";
        }
        if (isEither(c1, c2, "M", "Y")) {
            return "R";
        }
        if (isEither(c1, c2, "0", "C")) {
            return "C";
        }
        if (isEither(c1, c2, "0", "M")) {
            return "M";
        }
        if (isEither(c1, c2, "0", "Y")) {
            return "Y";
        }
        if (isEither(c1, c2, "0", "X")) {
            return "X";
        }
        if (isEither(c1, c2, "C", "X")) {
            return "X";
        }
        if (isEither(c1, c2, "M", "X")) {
            return "X";
        }
        if (isEither(c1, c2, "Y", "X")) {
            return "X";
        }

        return "0";
    }

    private boolean isEither(String s1, String s2, String c1, String c2) {
        return (s1.equals(c1) && s2.equals(c2)) || (s1.equals(c2) && s2.equals(c1));
    }

    public boolean succesMerge(int off, int on, char co) {
        String m = merged();
        char[] chars = m.toCharArray();
        int nOff = 0;
        int nOn = 0;
        for (char c : chars) {
            if (c == co) {
                nOn++;
            } else {
                nOff++;
            }
        }
        return (nOn == on && nOff >= off);
    }

    public boolean succesMerge(int on, char co) {
        String m = merged();
        char[] chars = m.toCharArray();
        int nOff = 0;
        int nOn = 0;
        for (char c : chars) {
            if (c == co) {
                nOn++;
            } else {
                nOff++;
            }
        }
        return (nOn == on);
    }
}
