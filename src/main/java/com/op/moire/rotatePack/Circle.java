package com.op.moire.rotatePack;

import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

class Circle {
    double cx, cy, r;
    Color col = null;
    double startAng = 0;
    int dp = 0;
    float grey = 0;

    Circle(double x, double y, double r, double startAng, double g) {
        this.cx = x;
        this.cy = y;
        this.r = r;
        this.startAng = startAng;
        grey = (float)g;
    }

    @Override
    public boolean equals(Object o2) {
        Circle c2 = (Circle) o2;
        return eq(cx, c2.cx) && eq(cy, c2.cy) && eq(r, c2.r);
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
