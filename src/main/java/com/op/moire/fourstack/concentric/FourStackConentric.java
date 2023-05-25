package com.op.moire.fourstack.concentric;

import com.op.moire.Base;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.awt.Color.*;

public class FourStackConentric extends Base {


    private static FourStackConentric fourStackConentric = new FourStackConentric();
    private String imagesDir = "concentric2";
    private String imagesName = "test";
    private String dir = hostDir + "fourStack/" + imagesDir + "/";
    private String ipExt = ".png";
    private String opExt = ".png";
    private String opSuff = "_CONC";

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

    int f = 1;
    int ff = 880;
    int ipw = 10 * f;
    int iph = 10 * f;
    int opw = ff * f;
    int oph = ff * f;


    public static void main(String[] args) throws Exception {
        fourStackConentric.doAll();
    }

    private void doAll() throws Exception {
        init();
        drawCircles();
        save();
    }

    private void drawCircles() {

        for (int y = 0; y < iph; y++) {
            for (int x = 0; x < ipw; x++) {
                drawCircles(x, y);
                //drawLines(x, y);
            }
        }

        saveLayered(opImage1, opImage2, null, null, "3");
//        saveLayered(opImage1, opImage4, null, null, "5");
//        saveLayered(opImage1, opImage2, opImage4, null, "7");
//        saveLayered(opImage1, opImage8, null, null, "9");
//        saveLayered(opImage1, opImage2, opImage4, opImage8, "15");
    }

        /*
        1248356790ABCDEF
        CMYKCMYKCMYKCMYK

        CMYCMYCMYCM
        MYCMYCMYCMY
        YXMYCMYCMYC

        30,50,00,700,90,00,B00,,00,D00,000,F000,1111111,0,0,0
        03,00,60,070,00,A0,0B0,00,000,E00,0F00,0,2222222,0,0
        00,05,06,007,,00,00,000,C0,0D0,0E0,00F0,0,0,4444444,0
        00,0,00,00,000,09,0A,00B,0C,00D,00E,000F,0,0,0,8888888
         */

    private void drawLines(int x, int y) {
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

        int th = 4;
        drawLine(op1, x, y, b1, 0, -1, CYAN, th);
        drawLine(op2, x, y, b2, th, -1, MAGENTA, th);
        drawLine(op4, x, y, b4, th * 2, -1, YELLOW, th);
        drawLine(op8, x, y, b8, th * 3, -1, CYAN, th);

        int offY = 2 * th;
        th = 3;
        drawLine(op1, x, y, b3, offY, 0, MAGENTA, th);
        drawLine(op2, x, y, b3, offY, th, YELLOW, th);

//        offY = 6;
//        drawLine(op1, x, y, b5, offY, 0, CYAN, th);
//        drawLine(op4, x, y, b5, offY, 1, MAGENTA, th);
//
//        offY = 8;
//        drawLine(op2, x, y, b6, offY, 0, MAGENTA, th);
//        drawLine(op4, x, y, b6, offY, 1, CYAN, th);
//
//        offY = 10;
//        drawLine(op1, x, y, b7, offY, 0, CYAN, th);
//        drawLine(op2, x, y, b7, offY, 2, MAGENTA, th);
//        drawLine(op4, x, y, b7, offY, 1, YELLOW, th);
//
//        offY = 13;
//        th = 2;
//        drawLine(op1, x, y, b9, offY, 0, CYAN, th);
//        drawLine(op8, x, y, b9, offY, 1, MAGENTA, th);
//
//        offY = 15;
//        drawLine(op2, x, y, b10, offY, 0, YELLOW, th);
//        drawLine(op8, x, y, b10, offY, 1, CYAN, th);
//
//        offY = 17;
//        drawLine(op1, x, y, b11, offY, 0, MAGENTA, th);
//        drawLine(op2, x, y, b11, offY, 1, YELLOW, th);
//        drawLine(op8, x, y, b11, offY, 2, CYAN, th);
//
//        offY = 20;
//        drawLine(op4, x, y, b12, offY, 0, CYAN, th);
//        drawLine(op8, x, y, b12, offY, 1, MAGENTA, th);
//
//        offY = 22;
//        drawLine(op1, x, y, b13, offY, 0, CYAN, th);
//        drawLine(op4, x, y, b13, offY, 1, MAGENTA, th);
//        drawLine(op8, x, y, b13, offY, 2, YELLOW, th);
//
//        offY = 25;
//        drawLine(op2, x, y, b14, offY, 0, MAGENTA, th);
//        drawLine(op4, x, y, b14, offY, 1, YELLOW, th);
//        drawLine(op8, x, y, b14, offY, 2, CYAN, th);
//
//        offY = 28;
//        th = 1;
//        drawLine(op1, x, y, b15, offY, 0, YELLOW, th);
//        drawLine(op2, x, y, b15, offY, 1, MAGENTA, th);
//        drawLine(op4, x, y, b15, offY, 2, CYAN, th);
//        drawLine(op8, x, y, b15, offY, 3, MAGENTA, th);
    }

    private void drawLine(Graphics2D opg, int x, int y, boolean b, int offY, int off, Color col, int th) {
        int d = opw / ipw;
        int cx = d / 2 + (x * d);
        int cy = d / 2 + (y * d);
        int xs = cx - d / 2;
        int xe = cx + d / 2;
        int yy = offY + cy - d / 2;
        if (b && off == -1) {
            opg.setColor(getAlpha(col, 0.5));
            for (int i = 0; i < th; i++) {
                opg.drawLine(xs, yy + i, xe, yy + i);
            }
        } else if (off != -1) {
            if (!b) {
                yy = yy + off;
            }
            opg.setColor(getAlpha(col, 0.5));
            for (int i = 0; i < th; i++) {
                opg.drawLine(xs, yy + i, xe, yy + i);
            }
        }

    }

    private void saveLayered(BufferedImage opImageA, BufferedImage opImageB, BufferedImage opImageC, BufferedImage opImageD, String file) {
        BufferedImage opImage = createAlphaBufferedImage(opw, oph);
        Graphics2D op = (Graphics2D) opImage.getGraphics();
        op.setColor(WHITE);
        op.fillRect(0, 0, opw, oph);
        if (opImageA != null) {
            op.drawImage(opImageA, null, null);
        }
        if (opImageB != null) {
            op.drawImage(opImageB, null, null);
        }
        if (opImageC != null) {
            op.drawImage(opImageC, null, null);
        }
        if (opImageD != null) {
            op.drawImage(opImageD, null, null);
        }
        savePNGFile(opImage, dir + imagesName + opSuff + file + opExt, 100);
    }

    private void drawCircles(int x, int y) {
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

        double rF = 88.0;
        int r = (int) (((double) d) / rF);

        int cx = d / 2 + (x * d);
        int cy = d / 2 + (y * d);

        Color[] colsOther = {WHITE, CYAN, MAGENTA, YELLOW, LIGHT_GRAY};
        Color[] cols = {CYAN, MAGENTA, YELLOW, LIGHT_GRAY};

        int[] rs = {};
        int rA = r * 44;
        int rB = r * 40;
        int rC = r * 36;
        int rD = r * 32;
        int r3 = r * 28;
        int r5 = r * 26;
        int r6 = r * 24;
        int r7 = r * 22;
        int r9 = r * 19;
        int r10 = r * 17;
        int r11 = r * 15;
        int r12 = r * 12;
        int r13 = r * 10;
        int r14 = r * 7;
        int r15 = r * 4;

        int len = (int) (((double) r3) * 0.5);
        int[] xs1 = {cx - r3, cx - r3 + len, cx - r3};
        int[] ys1 = {cy - r3, cy - r3, cy - r3 + len};
        int[] xs2 = {cx + r3 - len, cx + r3, cx + r3};
        int[] ys2 = {cy - r3, cy - r3, cy - r3 + len};
        int[] xs4 = {cx + r3, cx + r3, cx + r3 - len};
        int[] ys4 = {cy + r3 - len, cy + r3, cy + r3};
        int[] xs8 = {cx - r3, cx - r3 + len, cx - r3};
        int[] ys8 = {cy + r3 - len, cy + r3, cy + r3};

//        drawTriangle(op1, cx, cy, xs1, ys1, b1, cols[0]);
//        drawTriangle(op2, cx, cy, xs2, ys2, b2, cols[1]);
//        drawTriangle(op4, cx, cy, xs4, ys4, b4, cols[2]);
//        drawTriangle(op8, cx, cy, xs8, ys8, b8, cols[3]);

        drawCircle(op1, cx, cy, rA, 4, b1, cols[0], 4);
        drawCircle(op2, cx, cy, rB, 4, b2, cols[1], 4);
        drawCircle(op4, cx, cy, rC, 4, b4, cols[2], 4);
        drawCircle(op8, cx, cy, rD, 4, b8, cols[3], 4);

        int i = 0;
        drawCircle(op1, cx, cy, r3, 0, b3, cols[0], 3);
        drawCircle(op2, cx, cy, r3, 3, b3, cols[1], 3);

//        drawCircle(op1, cx, cy, r5, 0, b5, cols[0], 1);
//        drawCircle(op4, cx, cy, r5, 1, b5, cols[2], 1);
//
//        drawCircle(op2, cx, cy, r6, 0, b6, cols[1], 1);
//        drawCircle(op4, cx, cy, r6, 1, b6, cols[2], 1);
//
//        drawCircle(op1, cx, cy, r7, 0, b7, cols[0], 1);
//        drawCircle(op2, cx, cy, r7, 1, b7, cols[1], 1);
//        drawCircle(op4, cx, cy, r7, 2, b7, cols[2], 1);
//
//        drawCircle(op1, cx, cy, r9, 0, b9, cols[0], 1);
//        drawCircle(op8, cx, cy, r9, 1, b9, cols[3], 1);
//
//        drawCircle(op2, cx, cy, r10, 0, b10, cols[1], 1);
//        drawCircle(op8, cx, cy, r10, 1, b10, cols[3], 1);
//
//        drawCircle(op1, cx, cy, r11, 0, b11, cols[0], 1);
//        drawCircle(op2, cx, cy, r11, 1, b11, cols[1], 1);
//        drawCircle(op8, cx, cy, r11, 2, b11, cols[3], 1);
//
//        drawCircle(op4, cx, cy, r12, 0, b12, cols[2], 1);
//        drawCircle(op8, cx, cy, r12, 1, b12, cols[3], 1);
//
//        drawCircle(op1, cx, cy, r13, 0, b13, cols[0], 1);
//        drawCircle(op4, cx, cy, r13, 1, b13, cols[2], 1);
//        drawCircle(op8, cx, cy, r13, 2, b13, cols[3], 1);
//
//        drawCircle(op2, cx, cy, r14, 0, b14, cols[1], 1);
//        drawCircle(op4, cx, cy, r14, 1, b14, cols[2], 1);
//        drawCircle(op8, cx, cy, r14, 2, b14, cols[3], 1);
//
//        drawCircle(op1, cx, cy, r15, 0, b15, cols[0], 1);
//        drawCircle(op2, cx, cy, r15, 1, b15, cols[1], 1);
//        drawCircle(op4, cx, cy, r15, 2, b15, cols[2], 1);
//        drawCircle(op8, cx, cy, r15, 3, b15, cols[3], 1);
    }

    private void drawTriangle(Graphics2D op, int cx, int cy, int[] xs, int[] ys, boolean b, Color col) {
        op.setColor(getAlpha(col, 0.5));
        if (!b) {
            //op.setColor(BLACK);
        }
        //op.setStroke(new BasicStroke((float) 2));
        //op.drawPolygon(xs, ys, 3);
        if (b) {
            op.fillPolygon(xs, ys, 3);
        }
    }

    private void drawCircle(Graphics2D op, int cx, int cy, int r, int off, boolean b, Color col, int str) {
        op.setStroke(new BasicStroke((float) str));

        op.setColor(getAlpha(col, 0.5));
        int rr = r;
        if (!b) {
            rr = rr - off - str/2;
        }
        op.drawOval(cx - rr, cy - rr, (rr) * 2, (rr) * 2);
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
