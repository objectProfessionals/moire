package com.op.moire.fourstack.circles;

import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;

public class Circle {
    int startAng = 0;
    int arc = 0;
    int cx = 0;
    int cy = 0;
    int r = 0;

    public Circle(int cx, int cy, int r, int startAng, int arc) {
        this.cx = cx;
        this.cy = cy;
        this.r = r;
        this.startAng = startAng;
        this.arc = arc;
    }

    public Arc2D.Double getArc() {
        return new Arc2D.Double(cx-r, cy-r, r*2, r*2, startAng, arc, Arc2D.OPEN);
    }
}
