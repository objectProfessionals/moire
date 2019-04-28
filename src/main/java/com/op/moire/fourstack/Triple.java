package com.op.moire.fourstack;

public class Triple {

    String s1 = "";
    String s2 = "";
    String s3 = "";
    String mer = "";

    Triple(String s1, String s2, String s3) {
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
    }

    @Override
    public boolean equals(Object o) {
        Triple p = (Triple) o;
        return this.mer.equals(p.mer);
    }

    public String merged() {
        String m = "";
        for (int i = 0; i < s1.length(); i++) {
            String c1 = s1.substring(i, i + 1);
            String c2 = s2.substring(i, i + 1);
            String c3 = s3.substring(i, i + 1);
            m = m + merge(c1, c2, c3);
        }

        mer = m;
        return m;
    }
    private String merge(String c1, String c2, String c3) {

        if (isEither(c1, c2, c3, "C", "C", "C")) {
            return "C";
        }
        if (isEither(c1, c2, c3, "C", "C", "M")) {
            return "B";
        }
        if (isEither(c1, c2, c3, "C", "M", "M")) {
            return "B";
        }
        if (isEither(c1, c2, c3, "C", "C", "Y")) {
            return "G";
        }
        if (isEither(c1, c2, c3, "C", "C", "Y")) {
            return "G";
        }
        if (isEither(c1, c2, c3, "C", "Y", "Y")) {
            return "G";
        }

        if (isEither(c1, c2, c3, "M", "M", "M")) {
            return "M";
        }
        if (isEither(c1, c2, c3, "M", "M", "C")) {
            return "B";
        }
        if (isEither(c1, c2, c3, "M", "C", "C")) {
            return "B";
        }
        if (isEither(c1, c2, c3, "M", "M", "Y")) {
            return "R";
        }
        if (isEither(c1, c2, c3, "M", "Y", "Y")) {
            return "R";
        }

        if (isEither(c1, c2, c3, "Y", "Y", "Y")) {
            return "Y";
        }

        if (isEither(c1, c2, c3, "Y", "C", "M")) {
            return "X";
        }
        return "0";
    }

    private boolean isEither(String s1, String s2, String s3, String c1, String c2, String c3) {
        return ((s1.equals(c1) && s2.equals(c2) && s3.equals(c3)) ||
                (s1.equals(c2) && s2.equals(c3) && s3.equals(c1)) ||
                (s1.equals(c3) && s2.equals(c1) && s3.equals(c2)) ||
                (s1.equals(c1) && s2.equals(c3) && s3.equals(c2)) ||
                (s1.equals(c2) && s2.equals(c1) && s3.equals(c3)) ||
                (s1.equals(c3) && s2.equals(c2) && s3.equals(c1)));

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
}
