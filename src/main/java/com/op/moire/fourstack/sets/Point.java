package com.op.moire.fourstack.sets;

import java.awt.*;

public class Point {
    int x;
    int y;
    Color color;

    Point (int x, int y) {
        this.x = x;
        this.y = y;
    }

    Point (int x, int y, Color c) {
        this.x = x;
        this.y = y;
        this.color = c;
    }

    @Override
    public boolean equals(Object o) {
        Point p = (Point)o;
        return (p.x == this.x) && (p.y == this.y);
    }
}
