package com.op.moire.fourstack.circles;

import com.op.moire.Base;
import com.op.moire.fourstack.sets.KMColorUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.awt.Color.*;

public class FourStackHalftone extends Base {


    private static FourStackHalftone fourStackCircles = new FourStackHalftone();
    private String imagesDir = "testSmall";
    private String imagesName = "test";
    private String dir = hostDir + "fourHT/" + imagesDir + "/";
    private String ipExt = ".png";
    private String opExt = ".png";
    private String opSuff = "_HT";

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

    Area area1;
    Area area2;
    Area area3;

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
    int opw = 1000;
    int oph = 1000;
    int d = 0;


    public static void main(String[] args) throws Exception {
        fourStackCircles.doAll();
    }

    private void doAll() throws Exception {
        init();

        initShapes();
        draw();

        saveLayered(opImage1, opImage2, null, null, "3");
//        saveLayered(opImage1, opImage4, null, null, "5");
//        saveLayered(opImage2, opImage4, null, null, "6");
//        saveLayered(opImage1, opImage2, opImage4, null, "7");
        save();
    }

    private void initShapes() {
        area1 = new Area(new Rectangle2D.Double(0, 0, 0, 0));
        area2 = new Area(new Rectangle2D.Double(0, 0, 0, 0));
        area3 = new Area(new Rectangle2D.Double(0, 0, 0, 0));
        for (int y = 0; y < iph; y++) {
            for (int x = 0; x < ipw; x++) {
                addArea(x, y);
            }

        }
    }

    private void addArea(int x, int y) {
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
        addArea(b1, area1, x, y);
        addArea(b2, area2, x, y);
        addArea(b3, area3, x, y);
    }

    private void addArea(boolean b1, Area area, int x, int y) {
        if (b1) {
            Shape rect = new Rectangle2D.Double(x * d, y * d, d, d);
            area.add(new Area(rect));
        }
    }

    private void draw() throws NoninvertibleTransformException {
        int d1 = d/10;
        int d2 = d/10;
        drawHT(op1, area1, area3, 0, d1, d2, 0, 30, CYAN, MAGENTA);

        drawHT(op2, area2, area3, 10, d1, d2, 15, 30, MAGENTA, CYAN);
    }

    private void drawHT(Graphics2D op, Area areaA, Area areaB, int offsetA, int dotA, int dotB, double angA, double angB, Color colA, Color colB) throws NoninvertibleTransformException {
        op.setColor(colA);

        int dd = d / 5;
        int offset = offsetA;

        int off = 100;
        Area aANB = new Area(areaA);
        aANB.subtract(areaB);
        op.setClip(aANB);
        AffineTransform rot = AffineTransform.getRotateInstance(Math.toRadians(angA), opw/2, oph / 2);
        op.setTransform(rot);
        for (int yy = 0; yy < oph; yy = yy + dd) {
            for (int xx = 0; xx < opw; xx = xx + dd) {
                op.fillOval(xx+offset, yy+offset, dotA, dotA);
            }
        }

        rot = AffineTransform.getRotateInstance(0, opw/2, oph / 2);
        op.setTransform(rot);
        op.setClip(null);
        Area aAIB = new Area(areaA);
        aAIB.intersect(areaB);
        op.setClip(aAIB);
        op.setColor(colA);

        rot = AffineTransform.getRotateInstance(Math.toRadians(angB), opw/2, oph / 2);
        op.setTransform(rot);

        offset = dotB/2;
        for (int yy = 0; yy < oph; yy = yy + dd) {
            for (int xx = 0; xx < opw; xx = xx + dd) {
                op.fillOval(xx+offset, yy+offset, dotB, dotB);
            }
        }

        rot = AffineTransform.getRotateInstance(0, opw/2, oph / 2);
        op.setTransform(rot);
        op.setClip(null);
        Area aBNA = new Area(areaB);
        aBNA.subtract(areaA);
        op.setClip(aBNA);
        op.setColor(colB);

        rot = AffineTransform.getRotateInstance(Math.toRadians(angB), opw/2, oph / 2);
        op.setTransform(rot);

        offset = dotB/2;
        for (int yy = 0; yy < oph; yy = yy + dd) {
            for (int xx = 0; xx < opw; xx = xx + dd) {
                op.fillOval(xx+offset, yy+offset, dotB, dotB);
            }
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

        d = opw / ipw;

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
        return KMColorUtils.mix(c1, c2);
    }

    private double getGrey(Color col) {

        return (col.getRed() + col.getGreen() + col.getBlue()) / (255.0 * 3.0);
    }

    private double getGrey(BufferedImage image, int x, int y) {
        Color col = new Color(image.getRGB(x, y));
        return (col.getRed() + col.getGreen() + col.getBlue()) / (255.0 * 3.0);
    }


}
