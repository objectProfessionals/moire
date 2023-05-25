package com.op.moire.rotatePack;

import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

class Square {
    int cx, cy, d;
    Color col = null;
    int startAng = 0;
    int dp = 0;
    float grey = 0;

    Square(int x, int y, int r, int startAng, double g) {
        this.cx = x;
        this.cy = y;
        this.d = r;
        this.startAng = startAng;
        grey = (float)g;
    }

    @Override
    public boolean equals(Object o2) {
        Square c2 = (Square) o2;
        return eq(cx, c2.cx) && eq(cy, c2.cy) && eq(d, c2.d);
    }

    private boolean eq(double d1, double d2) {
        return round(d1) == round(d2);
    }

    private double dp(double d) {
        return round(d);
    }

    private double round(double value) {
        if (dp < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(dp, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
