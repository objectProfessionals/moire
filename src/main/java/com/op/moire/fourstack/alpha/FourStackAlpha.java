package com.op.moire.fourstack.alpha;

import com.op.moire.Base;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FourStackAlpha extends Base {


    private static FourStackAlpha fourStackAlpha = new FourStackAlpha();
    private String imagesDir = "testMed";
    private String imagesName = "test";
    private String dir = hostDir + "fourCircle/" + imagesDir + "/";
    private String ipExt = ".png";
    private String opExt = ".png";
    private String opSuff = "_ALPHA";

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
    int opw = ipw * 4;
    int oph = iph * 4;

    public static void main(String[] args) throws Exception {
        fourStackAlpha.doAll();
    }

    private void doAll() throws Exception {
        init();
        draw();

        saveLayered(opImage1, opImage2, null, null, "3");
        saveLayered(opImage1, opImage4, null, null, "5");
        //saveLayered(opImage2, opImage4, null, null, "15");
        saveLayered(opImage1, opImage2, opImage4, null, "7");
        save();
    }

    private void draw() {

        for (int y = 0; y < iph; y++) {
            for (int x = 0; x < ipw; x++) {
                drawCell(x, y);
            }

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

        double sOn = 0.75;
        double pOn = 0.5;
        double tOn = 0.2;
        double qOn = 0.1;

//        if (!b1 && !b2 && !b3 && !b4 && !b5 && !b6 && !b7 && !b8 && !b9 && !b10 && !b11 && !b12 && !b13 && !b14 && !b15) {
//            setPixel(x * 4, y * 4, sOn, op1);
//            setPixel(x * 4, y * 4, sOn, op2);
//            setPixel(x * 4, y * 4, sOn, op4);
//            setPixel(x * 4, y * 4, sOn, op8);
//        }
        if (b1) {
            setPixel(x * 4, y * 4, sOn, op1);
        }
        if (b2) {
            setPixel(x * 4 + 1, y * 4, sOn, op2);
        }
        if (b3) {
            setPixel(x * 4 + 2, y * 4, pOn, op1);
            setPixel(x * 4 + 2, y * 4, pOn, op2);
        }
        if (b4) {
            setPixel(x * 4 + 3, y * 4, sOn, op4);
        }
        if (b5) {
            setPixel(x * 4, y * 4 + 1, pOn, op1);
            setPixel(x * 4, y * 4 + 1, pOn, op4);
        }
        if (b6) {
            setPixel(x * 4 + 1, y * 4 + 1, pOn, op2);
            setPixel(x * 4 + 1, y * 4 + 1, pOn, op4);
        }
        if (b7) {
            setPixel(x * 4 + 2, y * 4 + 1, tOn, op1);
            setPixel(x * 4 + 2, y * 4 + 1, tOn, op2);
            setPixel(x * 4 + 2, y * 4 + 1, tOn, op4);
        }
        if (b8) {
            setPixel(x * 4 + 3, y * 4 + 1, sOn, op8);
        }
        if (b9) {
            setPixel(x * 4, y * 4 + 2, pOn, op1);
            setPixel(x * 4, y * 4 + 2, pOn, op8);
        }
        if (b10) {
            setPixel(x * 4 + 1, y * 4 + 2, pOn, op2);
            setPixel(x * 4 + 1, y * 4 + 2, pOn, op8);
        }
        if (b11) {
            setPixel(x * 4 + 2, y * 4 + 2, tOn, op1);
            setPixel(x * 4 + 2, y * 4 + 2, tOn, op2);
            setPixel(x * 4 + 2, y * 4 + 2, tOn, op8);
        }
        if (b12) {
            setPixel(x * 4 + 3, y * 4 + 2, pOn, op4);
            setPixel(x * 4 + 3, y * 4 + 2, pOn, op8);
        }
        if (b13) {
            setPixel(x * 4, y * 4 + 3, tOn, op1);
            setPixel(x * 4, y * 4 + 3, tOn, op4);
            setPixel(x * 4, y * 4 + 3, tOn, op8);
        }
        if (b14) {
            setPixel(x * 4 + 1, y * 4 + 3, tOn, op2);
            setPixel(x * 4 + 1, y * 4 + 3, tOn, op4);
            setPixel(x * 4 + 1, y * 4 + 3, tOn, op8);
        }
        if (b15) {
            setPixel(x * 4 + 2, y * 4 + 3, qOn, op1);
            setPixel(x * 4 + 2, y * 4 + 3, qOn, op2);
            setPixel(x * 4 + 2, y * 4 + 3, qOn, op4);
            setPixel(x * 4 + 2, y * 4 + 3, qOn, op8);
        }

    }

    private void setPixel(int x, int y, double v, Graphics2D op) {
        float g = (float) (v);
        Color col = new Color(0, 0, 0, g);
        op.setColor(col);
        op.fillRect(x, y, 1, 1);
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
        opw = ipw * 4;
        oph = iph * 4;

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

    }

    private void saveLayered(BufferedImage opImageA, BufferedImage opImageB, BufferedImage opImageC, BufferedImage opImageD, String file) {
        BufferedImage opImage = createAlphaBufferedImage(opw, oph);
        Graphics2D op = (Graphics2D) opImage.getGraphics();
        //op.setColor(WHITE);
        //op.fillRect(0, 0, opw, oph);

        mixImageByGrey(op, opImageA, opImageB, opImageC, opImageD);
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
}
