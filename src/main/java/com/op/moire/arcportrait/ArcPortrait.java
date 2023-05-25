package com.op.moire.arcportrait;

import com.op.moire.Base;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;

public class ArcPortrait extends Base {


    private static ArcPortrait arcPortrait = new ArcPortrait();
    private String imagesName = "Virga";
    private String dir = hostDir + "arcPortrait/";
    private String ipExt = ".png";
    private String opExt = ".png";
    private String opSuff = "_ARC";

    private String op1src = imagesName + opSuff + opExt;

    private String ip1src = imagesName + ipExt;

    BufferedImage ipImage1;
    BufferedImage opImage1;
    Graphics2D op1;

    int ipw = 1000;
    int iph = 1000;
    int opw = 1000;
    int oph = 1000;
    int d = 100;


    public static void main(String[] args) throws Exception {
        arcPortrait.doAll();
    }

    private void doAll() throws Exception {
        init();
        draw();
        save();
    }

    private void draw() {

        for (int y = 0; y <= iph; y = y + d) {
            for (int x = 0; x <= ipw; x = x + d) {
                int cx = x + (d / 2);
                int cy = y + (d / 2);
                draw(cx, cy);
            }
        }
    }

    private void draw(int cx, int cy) {
        int r1 = 10;
        int r2 = 500;
        int rd = 10;
        for (int r = r1; r < r2; r = r + rd) {
            double fit = circleFit(cx, cy, r);
        }
    }

    private double circleFit(int cx, int cy, double r) {
        int rr = (int) r;
        double ad = 5;

        float g1 = 1;
        int arcSt = 0;
        int arcEn = 0;
        HashMap<Integer, Integer> st2en = new HashMap();
        for (double a = 0; a < 360; a = a + ad) {
            double rad = Math.toRadians(a);
            int x = (int) (cx + r * Math.cos(rad));
            int y = (int) (cy + r * Math.sin(rad));
            float g = (float) getGrey(ipImage1, x, y);
            if (g1<0.1 && g < 0.1) {
                arcEn = (int)a;
            } else {
                if (arcSt != arcEn) {
                    st2en.put(arcSt, arcEn);
                }
                arcSt = (int) a;
                arcEn = (int) a;
            }

            g1 = g;
        }

        for (Integer st : st2en.keySet()) {
            int en = st2en.get(st);
            op1.setColor(BLACK);
            op1.drawArc(cx-rr, cy-rr, rr*2, rr*2, st, en-st);
        }
        return 0;
    }

    private double getGrey(BufferedImage image, int x, int y) {
        if (x < 0 || x >= ipw || y < 0 || y >= iph) {
            return 1;
        }
        Color col = new Color(image.getRGB(x, y));
        return (col.getRed() + col.getGreen() + col.getBlue()) / (255.0 * 3.0);
    }

    private void save() {
        double dpi = 100;
        savePNGFile(opImage1, dir + op1src, dpi);
    }

    private void init() throws IOException, FontFormatException {
        opImage1 = createBufferedImage(opw, oph);
        op1 = (Graphics2D) opImage1.getGraphics();
        op1.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        op1.setColor(WHITE);
        op1.fillRect(0, 0, opw, oph);

        ipImage1 = ImageIO.read(new File(dir + ip1src));

        ipw = ipImage1.getWidth();
        iph = ipImage1.getHeight();
    }
}
