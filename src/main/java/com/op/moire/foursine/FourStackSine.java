package com.op.moire.foursine;

import com.op.moire.Base;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;

public class FourStackSine extends Base {


    private static FourStackSine fourStackSine = new FourStackSine();
    private String imagesDir = "testMed";
    private String imagesName = "test";
    private String dir = hostDir + "fourSine/" + imagesDir + "/";
    private String ipExt = ".png";
    private String opExt = ".png";
    private String opSuff = "_SINE";

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

    int ipw = 50;
    int iph = 50;
    int opw = 1000;
    int oph = 1000;
    int pixel = opw / ipw;


    public static void main(String[] args) throws Exception {
        fourStackSine.doAll();
    }

    private void doAll() throws Exception {
        init();
        draw();

        saveLayered(opImage1, opImage2, null, null, "3");
        saveLayered(opImage1, opImage4, null, null, "5");
        saveLayered(opImage2, opImage4, null, null, "6");
        //saveLayered(opImage1, opImage2, opImage4, null, "7");
        save();
    }

    private void draw() {

        for (int y = 0; y < ipw; y++) {
            for (int x = 0; x < ipw; x++) {
                draw(x, y);
            }

        }
    }

    private void draw(int x, int y) {
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


        drawSine(op1, x, y, b1, b3, 1, 1, 15, BLACK);
        drawSine(op2, x, y, b2, b3, 1, 1, -15, BLACK);

        drawSine(op1, x, y, b1, b5, 1, 1.25, -10, BLACK);
        drawSine(op4, x, y, b4, b5, 1, 1.25, 10, BLACK);

        drawSine(op2, x, y, b2, b6, 1, 1.25, -15, BLACK);
        drawSine(op4, x, y, b4, b6, 1, 1.25, 15, BLACK);
    }

    private void drawSine(Graphics2D op, int x, int y, boolean b, boolean p, double ampF, double strF, double off, Color col) {
        float str = 1f;
        double angD = 15;
        double amp = 1;
        double offAng = 0;
        if (b) {
            str = str * (float) strF;
            offAng = offAng + off;
        }
        if (p) {
            str = str * (float) strF;
            amp = ampF;
            offAng = offAng + off;
        }
        op.setStroke(new BasicStroke(str));
        op.setColor(col);
        int xx = x * pixel;
        int yy = y * pixel + (pixel / 2);
        double xxx = xx;
        double yyy = yy;
        for (double a = 0; a <= 360; a = a + angD) {
            double r = Math.toRadians(a + offAng);
            double xxx2 = xx + ((double) pixel) * (a / 360.0);
            double yyy2 = yy + (amp * ((double) pixel / 2.0) * Math.sin(r));
            op.drawLine((int) xxx, (int) yyy, (int) xxx2, (int) yyy2);

            xxx = xxx2;
            yyy = yyy2;
        }
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
        op1.setColor(WHITE);
        op1.fillRect(0, 0, opw, oph);

        opImage2 = createAlphaBufferedImage(opw, oph);
        op2 = (Graphics2D) opImage2.getGraphics();
        op2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        op2.setColor(WHITE);
        op2.fillRect(0, 0, opw, oph);

        opImage4 = createAlphaBufferedImage(opw, oph);
        op4 = (Graphics2D) opImage4.getGraphics();
        op4.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        op4.setColor(WHITE);
        op4.fillRect(0, 0, opw, oph);

        opImage8 = createAlphaBufferedImage(opw, oph);
        op8 = (Graphics2D) opImage8.getGraphics();
        op8.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        op8.setColor(WHITE);
        op8.fillRect(0, 0, opw, oph);

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

    private void saveLayered(BufferedImage opImageA, BufferedImage opImageB, BufferedImage opImageC, BufferedImage opImageD, String file) {
        BufferedImage opImage = createAlphaBufferedImage(opw, oph);
        Graphics2D op = (Graphics2D) opImage.getGraphics();
        op.setColor(WHITE);
        op.fillRect(0, 0, opw, oph);

        mixImage(op, opImageA, opImageB, opImageC, opImageD);
        savePNGFile(opImage, dir + imagesName + opSuff + file + opExt, 100);
    }

    private void mixImage(Graphics2D op, BufferedImage opImageA, BufferedImage opImageB, BufferedImage opImageC, BufferedImage opImageD) {
        for (int y = 0; y < oph; y++) {
            for (int x = 0; x < opw; x++) {
                int rgbA = opImageA.getRGB(x, y);
                int rgbB = opImageB.getRGB(x, y);
                Color colA = new Color(rgbA);
                Color colB = new Color(rgbB);
//                if (rgbA!=0) {
//                    System.out.println(x+","+y + ":"+rgbA);
//                }
                Color res = mix(colA, colB);
                Color res3 = res;
                if (opImageC != null) {
                    Color colC = new Color(opImageC.getRGB(x, y));
                    Color res2 = mix(res, colC);
                    if (opImageD != null) {
                        Color colD = new Color(opImageD.getRGB(x, y));
                        res3 = mix(res2, colD);
                    } else {
                        res3 = res2;
                    }
                } else {
                    res3 = res;
                }
                op.setColor(res3);
                op.fillRect(x, y, 1, 1);
            }

        }
    }

    private Color mix(Color c1, Color c2) {
        if (c1.equals(BLACK) || c2.equals(BLACK)) {
            return BLACK;
        }
        return WHITE;
//        float d = (float)((getGrey(c1) + getGrey(c2))/2.0);
//        return new Color(d, d, d);
    }

    private double getGrey(Color col) {

        return (col.getRed() + col.getGreen() + col.getBlue()) / (255.0 * 3.0);
    }

    private double getGrey(BufferedImage image, int x, int y) {
        Color col = new Color(image.getRGB(x, y));
        return (col.getRed() + col.getGreen() + col.getBlue()) / (255.0 * 3.0);
    }


}
