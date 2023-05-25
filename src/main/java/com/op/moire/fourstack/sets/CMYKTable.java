package com.op.moire.fourstack.sets;

import com.op.moire.Base;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CMYKTable extends Base {
    private static final CMYKTable cmykTable = new CMYKTable();

    private String imagesDir = "sets";
    private String dir = hostDir + "fourStack/" + imagesDir + "/";
    private String op1src = "table.png";
    private BufferedImage opImage;
    private Graphics2D op;
    private int opw = 730;
    private int oph = opw;

    public static void main(String[] args) throws Exception {
        cmykTable.doAll();
    }

    private void doAll() {
        init();

        drawBars();
        //drawAll();
        save();
    }

    private void drawBars() {
        double tot = 255;
        double d = tot / 8;
        int x = 0;
        int y = 0;
        int bar = 10;

        for (double xr = 0; xr <= tot; xr = xr + d) {
            for (double xg = 0; xg <= tot; xg = xg + d) {
                for (double xb = 0; xb <= tot; xb = xb + d) {

                    Color h = new Color((int) xr, (int) xg, (int) xb);
                    op.setColor(h);
                    op.fillRect(x+bar, 0, 1, bar);
                    op.fillRect(0, x+bar, bar, 1);
                    x = x + 1;
                }
            }
        }

        for (int yy = bar; yy < oph; yy++) {
            for (int xx = bar; xx < opw; xx++) {
                int colh = opImage.getRGB(xx, bar/2);
                int colv = opImage.getRGB(bar/2, yy);
                Color h = new Color(colh);
                Color v = new Color(colv);
                Color res = KMColorUtils.mix(v, h);
                op.setColor(res);
                op.fillRect(xx, yy, 1, 1);
            }
        }
    }

    private void drawTable() {
        int tot = 256;
        int d = tot / 8;

        int x = 0;
        int y = 0;
        for (int yr = 0; yr < tot; yr = yr + d) {
            for (int yg = 0; yg < tot; yg = yg + d) {
                for (int yb = 0; yb < tot; yb = yb + d) {
                    Color v = new Color(yr, yg, yb);
                    x = 0;

                    for (int xr = 0; xr < tot; xr = xr + d) {
                        for (int xg = 0; xg < tot; xg = xg + d) {
                            for (int xb = 0; xb < tot; xb = xb + d) {
                                x = x + 1;

                                Color h = new Color(xr, xg, xb);
                                Color res = KMColorUtils.mix(v, h);
                                op.setColor(res);
                                op.fillRect(x, y, 1, 1);
                            }
                        }
                    }
                    y = y + 1;
                }
            }
        }
    }


    private void init() {
        opImage = createAlphaBufferedImage(opw, oph);
        op = (Graphics2D) opImage.getGraphics();
        op.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        op.setColor(Color.WHITE);
        op.fillRect(0, 0, opw, oph);

    }

    private void save() {
        double dpi = 100;
        savePNGFile(opImage, dir + op1src, dpi);
    }


}
