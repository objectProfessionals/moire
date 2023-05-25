package com.op.moire.fourstack.alpha;

import com.op.moire.Base;
import com.op.moire.fourstack.sets.KMColorUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import static java.awt.Color.*;

public class FourStackAlphaCirlces extends Base {


    private static FourStackAlphaCirlces fourStackAlpha = new FourStackAlphaCirlces();
    private String imagesDir = "testLarge";
    private String imagesName = "test";
    private String dir = hostDir + "fourCircle/" + imagesDir + "/";
    private String ipExt = ".png";
    private String opExt = ".png";
    private String opSuff = "_ALPHAC";

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

    BufferedImage ipImageMask;
    Graphics2D ipMask;
    private String opMasksrc = imagesName + opSuff + "_MASK" + opExt;

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
    int scale = 10;
    int opw = ipw * scale;
    int oph = iph * scale;

    Random random = new Random(1);

    public static void main(String[] args) throws Exception {
        fourStackAlpha.doAll();
    }

    private void doAll() throws Exception {
        init();
        draw();

        save();
        saveLayered(opImage1, opImage2, null, null, "3");
        //saveLayered(opImage1, opImage4, null, null, "5");
        //saveLayered(opImage2, opImage4, null, null, "15");
        saveLayered(opImage1, opImage2, opImage4, null, "7");
    }

    private void draw() {

        int max = 10000;
        for (int i = 0; i < max; i++) {
            int x = (int) (((double)ipw) * random.nextDouble());
            int y = (int) (((double)iph) * random.nextDouble());
            drawCell(x, y);
        }
    }

    private void drawCell(int x, int y) {
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
        boolean[] bs = {b1, b2, b3, b4, b5, b6, b7,
                b8, b9, b10, b11, b12, b13, b14, b15};


        int sL = 10;
        int sS = 5;
        boolean overlap = b7;

        int r1 = (b1 ? sL : sS);
        int r2 = (b2 ? sL : sS);
        int r4 = (b4 ? sL : sS);
        int rMax = Math.max(r1, r2);
        rMax = Math.max(rMax, r4);
        if (overlap) {
            if (!radiusInMask(x, y, rMax)) {
                setCircle(YELLOW, x, y, r1, op1);
                setCircle(CYAN, x, y, r2, op2);
                setCircle(MAGENTA, x, y, r4, op4);

                ipMask.fillOval(x * scale - rMax, y * scale - rMax, rMax * 2, rMax * 2);
            }
        } else {
            if (!radiusInMask(x, y, rMax)) {
                setArc(YELLOW, x, y, r1, op1, 0, 120);
                ipMask.fillArc(x * scale - r1, y * scale - r1, r1 * 2, r1 * 2, 0, 120);
                setArc(CYAN, x, y, r2, op2, 120, 120);
                ipMask.fillArc(x * scale - r2, y * scale - r2, r2 * 2, r2 * 2, 120, 120);
                setArc(MAGENTA, x, y, r4, op4, 240, 120);
                ipMask.fillArc(x * scale - r4, y * scale - r4, r4 * 2, r4 * 2, 240, 120);
            }
        }

    }

    private void setCircle(Color col, int x, int y, int r, Graphics2D op) {
        int xx = x * scale;
        int yy = y * scale;

        op.setColor(col);
        op.fillOval(xx - r, yy - r, r * 2, r * 2);

    }

    private void setArc(Color col, int x, int y, int r, Graphics2D op, int st, int ext) {
        int xx = x * scale;
        int yy = y * scale;

        op.setColor(col);
        op.fillArc(xx - r, yy - r, r * 2, r * 2, st, ext);

    }

    private boolean radiusInMask(int x, int y, int r) {
        int aInc = 15;
        int rInc = 2;
        for (int i = 1; i <= r; i = i + rInc) {
            for (int a = 0; a < 360; a = a + aInc) {
                double rads = Math.toRadians(a);
                int xx = x * scale + (int) (Math.cos(rads) * i);
                int yy = y * scale + (int) (Math.sin(rads) * i);
                if (xx < 0 || xx >= opw || yy < 0 || yy >= oph) {
                    continue;
                }
                Color mCol = new Color(ipImageMask.getRGB(xx, yy));
                if (mCol.getRed() < 100) {
                    return true;
                }
            }
        }


        return false;
    }

    private void save() {
        double dpi = 100;
        savePNGFile(opImage1, dir + op1src, dpi);
        savePNGFile(opImage2, dir + op2src, dpi);
        savePNGFile(opImage4, dir + op4src, dpi);
//        savePNGFile(opImage8, dir + op8src, dpi);
        savePNGFile(ipImageMask, dir + opMasksrc, dpi);
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
        opw = ipw * scale;
        oph = iph * scale;

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

        ipImageMask = createBufferedImage(opw, oph);
        ipMask = (Graphics2D) ipImageMask.getGraphics();
        ipMask.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        ipMask.setColor(WHITE);
        ipMask.fillRect(0, 0, opw, oph);
        ipMask.setColor(BLACK);
    }

    private void saveLayered(BufferedImage opImageA, BufferedImage opImageB, BufferedImage opImageC, BufferedImage opImageD, String file) {
        BufferedImage opImage = createAlphaBufferedImage(opw, oph);
        Graphics2D op = (Graphics2D) opImage.getGraphics();

        //mixImageByGrey(op, opImageA, opImageB, opImageC, opImageD);
        mixImageByColors(op, opImageA, opImageB, opImageC, opImageD);
        savePNGFile(opImage, dir + imagesName + opSuff + file + opExt, 100);
    }

    private void mixImageByGrey(Graphics2D op, BufferedImage opImageA, BufferedImage opImageB, BufferedImage opImageC, BufferedImage opImageD) {
        for (int y = 0; y < oph; y++) {
            for (int x = 0; x < opw; x++) {
                int rgbA = opImageA.getRGB(x, y);
                int gA = (rgbA >> 24) & 0xff;
                int rgbB = opImageB.getRGB(x, y);
                int gB = (rgbB >> 24) & 0xff;
                int gC = 0;
                if (opImageC != null) {
                    int rgbC = opImageC.getRGB(x, y);
                    gC = (rgbC >> 24) & 0xff;
                }

                float tot = (float) (gA + gB + gC);
                if (tot > 1) {
                    tot = 1;
                }
                Color col = new Color(0, 0, 0, tot);
                op.setColor(col);
                op.fillRect(x, y, 1, 1);
            }

        }
    }

    private void mixImageByColors(Graphics2D op, BufferedImage opImageA, BufferedImage opImageB, BufferedImage opImageC, BufferedImage opImageD) {
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
}
