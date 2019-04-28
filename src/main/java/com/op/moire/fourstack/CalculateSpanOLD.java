package com.op.moire.fourstack;

public class CalculateSpanOLD {
    static final CalculateSpanOLD calculate = new CalculateSpanOLD();
    static int all = 0;

    public static void main(String[] args) {
        System.out.println("Started...");
        calculate.test();
        System.out.println("Finished...");
    }

    private void test() {
        boolean[] blacks = {false, false, false, false, false, false, false, false, false, true, true, true, true, true, true
        };
        calculate(blacks);
    }

    Value[] calculate(boolean[] blacks) {
        Value v1 = new Value("X10357090BD0F");
        Value v2 = new Value("6X230700AB0EF");
        Value v4 = new Value("6CX4570000DEF");
        Value v8 = new Value("0C000X89ABDEF");

        replaceWith1s(blacks[0], v1, "1");
        replaceWith1s(blacks[1], v2, "2");
        replaceWith1s(blacks[3], v4, "4");
        replaceWith1s(blacks[7], v8, "8");

        Value[] vs = {v1, v2, v4, v8};
        replaceWith1s(blacks[2], vs, "3");
        replaceWith1s(blacks[4], vs, "5");
        replaceWith1s(blacks[5], vs, "6");
        replaceWith1s(blacks[6], vs, "7");
        replaceWith1s(blacks[8], vs, "9");
        replaceWith1s(blacks[9], vs, "A");
        replaceWith1s(blacks[10], vs, "B");
        replaceWith1s(blacks[11], vs, "C");
        replaceWith1s(blacks[12], vs, "D");
        replaceWith1s(blacks[13], vs, "E");
        replaceWith1s(blacks[14], vs, "F");

        replaceWith1s(true, vs, "X");

        return vs;
    }

    private void replaceWith1s(boolean b, Value v1, String s) {
        if (b) {
            v1.v = v1.v.replaceAll(s, "1");
        } else {
            v1.v = v1.v.replaceAll(s, "0");
        }
    }

    private void replaceWith1s(boolean b, Value[] vs, String s) {
        for (Value v : vs) {
            if (b) {
                v.v = v.v.replaceAll(s, "1");
            } else {
                v.v = v.v.replaceAll(s, "0");
            }
        }
    }

}
