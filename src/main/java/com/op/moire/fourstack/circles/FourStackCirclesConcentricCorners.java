package com.op.moire.fourstack.circles;

import com.op.moire.Base;
import com.op.moire.fourstack.sets.KMColorUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.awt.BasicStroke.CAP_BUTT;
import static java.awt.BasicStroke.JOIN_MITER;
import static java.awt.Color.*;

public class FourStackCirclesConcentricCorners extends Base {


    private static FourStackCirclesConcentricCorners fourStackCircles = new FourStackCirclesConcentricCorners();
    private String imagesDir = "testLarge";
    private String imagesName = "testL";
    private String dir = hostDir + "fourCircle/" + imagesDir + "/";
    private String ipExt = ".png";
    private String opExt = ".png";
    private String opSuff = "_CCORN";

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
    int opw = 1000;
    int oph = 1000;


    public static void main(String[] args) throws Exception {
        fourStackCircles.doAll();
    }

    private void doAll() throws Exception {
        init();
        draw();

        saveLayered(opImage1, opImage2, null, null, "3");
//        saveLayered(opImage1, opImage4, null, null, "5");
//        saveLayered(opImage2, opImage4, null, null, "6");
//        saveLayered(opImage1, opImage2, opImage4, null, "7");
        save();
    }

    private void draw() {

        int rd = 4;
        for (int r = rd; r < ipw * 2; r = r + rd) {
            draw(r);
        }
    }

    private void draw(int r) {

        int ad = 5;
        for (int a = 0; a < 360; a = a + ad) {
            drawCircle(r, a);
        }
    }

    private void drawCircle(int r, int a) {
        drawArc(op1, 0, 0, -1, -1, r, a, 0, YELLOW);
        drawArc(op2, 1, 0, 0, -1, r, a, 1, CYAN);
    }

    private void drawArc(Graphics2D op, int cx1, int cy1, int tlx, int tly, int r, int a, int bInd, Color col) {
        double rads = Math.toRadians(a);
        int x = cx1*ipw + (int) (r * Math.cos(rads));
        int y = cy1*iph + (int) (r * Math.sin(rads));

        boolean b1 = getColor(ipImage1, x, y);
        boolean b2 = getColor(ipImage2, x, y);
        boolean b3 = getColor(ipImage3, x, y);
        boolean[] bs = {b1, b2, b3};

        int rr = r * 10;

        boolean b = bs[bInd];
        if (b) {
            BasicStroke stroke = new BasicStroke(10, CAP_BUTT, JOIN_MITER);
            op.setColor(col);
            op.setStroke(stroke);
            op.drawArc(tlx * rr, tly * rr, rr * 2, rr * 2, 360 - a, 5);
        }
    }

    private boolean getColor(BufferedImage ipImage, int x, int y) {
        if (x < 0 || x >= ipImage.getWidth() || y < 0 || y >= ipImage.getHeight()) {
            return false;
        }

        int off = 100;
        return new Color(ipImage.getRGB(x, y)).getRed() < off;
    }

    private Color getAlpha(Color col, double d) {
        int al = (int) (d * 255);
        return new Color(col.getRed(), col.getGreen(), col.getBlue(), al);
    }


    private void save() {
        double dpi = 100;
        savePNGFile(opImage1, dir + op1src, dpi);
        savePNGFile(opImage2, dir + op2src, dpi);
//        savePNGFile(opImage4, dir + op4src, dpi);
//        savePNGFile(opImage8, dir + op8src, dpi);
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
                Color colA = new Color(opImageA.getRGB(x, y));
                Color colB = new Color(opImageB.getRGB(x, y));
                Color res = KMColorUtils.mix(colA, colB);
                Color res3 = res;
                if (opImageC != null) {
                    Color colC = new Color(opImageC.getRGB(x, y));
                    Color res2 = KMColorUtils.mix(res, colC);
                    if (opImageD != null) {
                        Color colD = new Color(opImageD.getRGB(x, y));
                        res3 = KMColorUtils.mix(res2, colD);
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

    private double getGrey(Color col) {

        return (col.getRed() + col.getGreen() + col.getBlue()) / (255.0 * 3.0);
    }

    private double getGrey(BufferedImage image, int x, int y) {
        Color col = new Color(image.getRGB(x, y));
        return (col.getRed() + col.getGreen() + col.getBlue()) / (255.0 * 3.0);
    }


}
