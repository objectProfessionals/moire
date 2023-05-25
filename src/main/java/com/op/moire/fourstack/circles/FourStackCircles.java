package com.op.moire.fourstack.circles;

import com.op.moire.Base;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FourStackCircles extends Base {


    private static FourStackCircles fourStackCircles = new FourStackCircles();
    private String imagesDir = "circles2";
    private String imagesName = "test";
    private String dir = hostDir + "fourStack/" + imagesDir + "/";
    private String ipExt = ".png";
    private String opExt = ".png";
    private String opSuff = "_CIR";

    private String op1src = imagesName + opSuff + "1" + opExt;
    private String op2src = imagesName + opSuff + "2" + opExt;
    private String op4src = imagesName + opSuff + "4" + opExt;
    private String op8src = imagesName + opSuff + "8" + opExt;

    private String ip1src = imagesName + "1" + ipExt;
    private String ip2src = imagesName + "2" + ipExt;
    private String ip3src = imagesName + "3" + ipExt;
    private String ip4src = imagesName + "4" + ipExt;
    private String ip5src = imagesName + "5" + ipExt;
    private String ip6src = imagesName + "6" + ipExt;
    private String ip7src = imagesName + "7" + ipExt;
    private String ip8src = imagesName + "8" + ipExt;
    private String ip9src = imagesName + "9" + ipExt;
    private String ip10src = imagesName + "10" + ipExt;
    private String ip11src = imagesName + "11" + ipExt;
    private String ip12src = imagesName + "12" + ipExt;
    private String ip13src = imagesName + "13" + ipExt;
    private String ip14src = imagesName + "14" + ipExt;
    private String ip15src = imagesName + "15" + ipExt;

    BufferedImage ipImage1;
    BufferedImage ipImage2;
    BufferedImage ipImage3;
    BufferedImage ipImage4;
    BufferedImage ipImage5;
    BufferedImage ipImage6;
    BufferedImage ipImage7;
    BufferedImage ipImage8;
    BufferedImage ipImage9;
    BufferedImage ipImage10;
    BufferedImage ipImage11;
    BufferedImage ipImage12;
    BufferedImage ipImage13;
    BufferedImage ipImage14;
    BufferedImage ipImage15;

    BufferedImage opImage1;
    BufferedImage opImage2;
    BufferedImage opImage4;
    BufferedImage opImage8;
    Graphics2D op1;
    Graphics2D op2;
    Graphics2D op4;
    Graphics2D op8;

    int ipw = 0;
    int iph = 0;
    int opw = 2000;
    int oph = 2000;


    public static void main(String[] args) throws Exception {
        fourStackCircles.doAll();
    }

    private void doAll() throws Exception {
        init();
        draw();
        save();
    }

    private void draw() {

        for (int y = 0; y < iph; y++) {
            for (int x = 0; x < ipw; x++) {
                draw(x, y);
            }
        }
    }

    private void draw(int x, int y) {
        int d = opw / ipw;
        int off = 100;
        boolean b1 = new Color(ipImage1.getRGB(x, y)).getRed() < off;
        boolean b2 = new Color(ipImage2.getRGB(x, y)).getRed() < off;
        boolean b3 = new Color(ipImage3.getRGB(x, y)).getRed() < off;
        boolean b4 = new Color(ipImage4.getRGB(x, y)).getRed() < off;
        boolean b5 = new Color(ipImage5.getRGB(x, y)).getRed() < off;
        boolean b6 = new Color(ipImage6.getRGB(x, y)).getRed() < off;
        boolean b7 = new Color(ipImage7.getRGB(x, y)).getRed() < off;
        boolean b8 = new Color(ipImage8.getRGB(x, y)).getRed() < off;
        boolean b9 = new Color(ipImage9.getRGB(x, y)).getRed() < off;
        boolean b10 = new Color(ipImage10.getRGB(x, y)).getRed() < off;
        boolean b11 = new Color(ipImage11.getRGB(x, y)).getRed() < off;
        boolean b12 = new Color(ipImage12.getRGB(x, y)).getRed() < off;
        boolean b13 = new Color(ipImage13.getRGB(x, y)).getRed() < off;
        boolean b14 = new Color(ipImage14.getRGB(x, y)).getRed() < off;
        boolean b15 = new Color(ipImage15.getRGB(x, y)).getRed() < off;

        double rF = 20.0;
        int r = (int) (((double) d) / rF);

        float str = r;

        BasicStroke stroke = new BasicStroke(str);
        op1.setColor(Color.BLACK);
        op1.setStroke(stroke);
        op2.setColor(Color.BLACK);
        op2.setStroke(stroke);
        op4.setColor(Color.BLACK);
        op4.setStroke(stroke);
        op8.setColor(Color.BLACK);
        op8.setStroke(stroke);

        int arcOff = 180;
        int arcOn = 270;

        int arcOffT = 270/3;
        int arcOnT = 360/3;

        int arcOffQ = 270/4;
        int arcOnQ = 360/4;

        int cx = d / 2 + (x * d);
        int cy = d / 2 + (y * d);

        int r01 = r * 15;
        int r02 = r * 14;
        int r04 = r * 13;
        int r08 = r * 12;
        int r1 = r * 11;
        int r2 = r * 10;
        int r3 = r * 9;
        int r5 = r * 8;
        int r6 = r * 7;
        int r8 = r * 6;

        int r4 = r * 5;
        int r7 = r * 4;
        int r9 = r * 3;
        int r10 = r * 2;
        int r11 = r * 1;

        //drawCircle(op1, 0, b1 ? arcOn : arcOff, cx, cy, r01);
        drawCircle(op1, 0, b1 ? arcOn : arcOff, cx, cy, r1);
        drawCircle(op1, 0, b1 ? arcOn : arcOff, cx, cy, r2);
        drawCircle(op1, 0, b1 ? arcOn : arcOff, cx, cy, r5);
        drawCircle(op1, 0, b7 ? arcOnT : arcOffT, cx, cy, r4);
        drawCircle(op1, 0, b11 ? arcOnT : arcOffT, cx, cy, r7);
        drawCircle(op1, 0, b13 ? arcOnT : arcOffT, cx, cy, r9);
        drawCircle(op1, 0, b15 ? arcOnQ : arcOffQ, cx, cy, r11);

//        drawCircle(op2, 0, b2 ? arcOn : arcOff, cx, cy, r02);
        drawCircle(op2, getStartPair(b1, b2, b3), b2 ? arcOn : arcOff, cx, cy, r1);
        drawCircle(op2, 0, b2 ? arcOn : arcOff, cx, cy, r3);
        drawCircle(op2, b7 ? arcOnT : arcOffT, b7 ? arcOnT : arcOffT, cx, cy, r4);
        drawCircle(op2, 0, b2 ? arcOn : arcOff, cx, cy, r6);
        drawCircle(op2, b11 ? arcOnT : arcOffT, b11 ? arcOnT : arcOffT, cx, cy, r7);
        drawCircle(op2, 0, b14 ? arcOnT : arcOffT, cx, cy, r10);
        drawCircle(op2, b15 ? arcOnQ : arcOffQ, b15 ? arcOnQ : arcOffQ, cx, cy, r11);
//
//        drawCircle(op4, 0, b4 ? arcOn : arcOff, cx, cy, r04);
        drawCircle(op4, getStartPair(b1, b4, b5), b4 ? arcOn : arcOff, cx, cy, r2);
        drawCircle(op4, getStartPair(b2, b4, b6), b4 ? arcOn : arcOff, cx, cy, r3);
        drawCircle(op4, b7 ? arcOnT*2 : arcOffT*2, b7 ? arcOnT : arcOffT, cx, cy, r4);
        drawCircle(op4, 0, b4 ? arcOn : arcOff, cx, cy, r8);
        drawCircle(op4, b13 ? arcOnT : arcOffT, b13 ? arcOnT : arcOffT, cx, cy, r9);
        drawCircle(op4, b14 ? arcOnT : arcOffT, b14 ? arcOnT : arcOffT, cx, cy, r10);
        drawCircle(op4, b15 ? arcOnQ*2 : arcOffQ*2, b15 ? arcOnQ : arcOffQ, cx, cy, r11);
//
//        drawCircle(op8, 0, b8 ? arcOn : arcOff, cx, cy, r08);
        drawCircle(op8, getStartPair(b1, b8, b9), b8 ? arcOn : arcOff, cx, cy, r5);
        drawCircle(op8, b10 ? 90 : 0, b8 ? arcOn : arcOff, cx, cy, r6);
        drawCircle(op8, b11 ? arcOnT*2 : arcOffT*2, b11 ? arcOnT : arcOffT, cx, cy, r7);
        drawCircle(op8, getStartPair(b4, b8, b12), b8 ? arcOn : arcOff, cx, cy, r8);
        drawCircle(op8, b13 ? arcOnT*2 : arcOffT*2, b13 ? arcOnT : arcOffT, cx, cy, r9);
        drawCircle(op8, b14 ? arcOnT*2 : arcOffT*2, b14 ? arcOnT : arcOffT, cx, cy, r10);
        drawCircle(op8, b15 ? arcOnQ*3 : arcOffQ*3, b15 ? arcOnQ : arcOffQ, cx, cy, r11);
    }

    private int getStartPair(boolean b1, boolean b2, boolean b3) {
        return b1 ? (b2 ? (b3 ? 90 : 0) : (b3 ? 180 : 0)) : (b2 ? (b3 ? 90 : 0) : (b3 ? 180 : 90));
    }

    private void drawCircle(Graphics2D op1, int st1, int arc1, int cx, int cy, int r1) {
        Circle c1 = new Circle(cx, cy, r1, st1, arc1);
        op1.draw(c1.getArc());
    }

    private Color getAlpha(Color col, double d) {
        int al = (int) (d * 255);
        return new Color(col.getRed(), col.getGreen(), col.getBlue(), al);
    }


    private void save() {
        double dpi = 100;
        savePNGFile(opImage1, dir + op1src, dpi);
        savePNGFile(opImage2, dir + op2src, dpi);
        savePNGFile(opImage4, dir + op4src, dpi);
        savePNGFile(opImage8, dir + op8src, dpi);
    }

    private void init() throws IOException, FontFormatException {
        opImage1 = createAlphaBufferedImage(opw, oph);
        op1 = (Graphics2D) opImage1.getGraphics();
        op1.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);

        opImage2 = createAlphaBufferedImage(opw, oph);
        op2 = (Graphics2D) opImage2.getGraphics();
        op2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);

        opImage4 = createAlphaBufferedImage(opw, oph);
        op4 = (Graphics2D) opImage4.getGraphics();
        op4.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);

        opImage8 = createAlphaBufferedImage(opw, oph);
        op8 = (Graphics2D) opImage8.getGraphics();
        op8.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);

        ipImage1 = ImageIO.read(new File(dir + ip1src));
        ipImage2 = ImageIO.read(new File(dir + ip2src));
        ipImage3 = ImageIO.read(new File(dir + ip3src));
        ipImage4 = ImageIO.read(new File(dir + ip4src));
        ipImage5 = ImageIO.read(new File(dir + ip5src));
        ipImage6 = ImageIO.read(new File(dir + ip6src));
        ipImage7 = ImageIO.read(new File(dir + ip7src));
        ipImage8 = ImageIO.read(new File(dir + ip8src));
        ipImage9 = ImageIO.read(new File(dir + ip9src));
        ipImage10 = ImageIO.read(new File(dir + ip10src));
        ipImage11 = ImageIO.read(new File(dir + ip11src));
        ipImage12 = ImageIO.read(new File(dir + ip12src));
        ipImage13 = ImageIO.read(new File(dir + ip13src));
        ipImage14 = ImageIO.read(new File(dir + ip14src));
        ipImage15 = ImageIO.read(new File(dir + ip15src));

        ipw = ipImage1.getWidth();
        iph = ipImage1.getHeight();
    }
}
