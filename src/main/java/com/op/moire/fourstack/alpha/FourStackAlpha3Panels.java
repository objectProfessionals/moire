package com.op.moire.fourstack.alpha;

import com.op.moire.Base;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.awt.Color.WHITE;

public class FourStackAlpha3Panels extends Base {


    private static FourStackAlpha3Panels fourStackAlpha = new FourStackAlpha3Panels();
    private String imagesDir = "testInv";
    private String imagesName = "testL";
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
    int opw = 100;
    int oph = 100;

    double[][] frs = new double[128][3];

    public static void main(String[] args) throws Exception {
        fourStackAlpha.doAll();
    }

    private void doAll() throws Exception {
        init();
        draw();

        saveLayered(opImage1, opImage2, null, null, "3");
        saveLayered(opImage1, opImage4, null, null, "5");
        saveLayered(opImage2, opImage4, null, null, "6");
        saveLayered(opImage1, opImage2, opImage4, null, "7");
        save();
    }

    private void draw() {

        for (int y = 0; y < iph; y++) {
            for (int x = 0; x < ipw; x++) {
                drawCell3(x, y);
            }

        }
    }

    private void drawCell3(int x, int y) {
        int off = 100;
        boolean b1 = new Color(ipImage1.getRGB(x, y)).getRed() < off;
        boolean b2 = new Color(ipImage2.getRGB(x, y)).getRed() < off;
        boolean b3 = new Color(ipImage3.getRGB(x, y)).getRed() < off;
        boolean b4 = new Color(ipImage4.getRGB(x, y)).getRed() < off;
        boolean b5 = new Color(ipImage5.getRGB(x, y)).getRed() < off;
        boolean b6 = new Color(ipImage6.getRGB(x, y)).getRed() < off;
        boolean b7 = new Color(ipImage7.getRGB(x, y)).getRed() < off;
        boolean[] bs = {b1, b2, b3, b4, b5, b6, b7};

        int c = 0;
        if (b1) c=c+1;
        if (b2) c=c+2;
        if (b3) c=c+4;
        if (b4) c=c+8;
        if (b5) c=c+16;
        if (b6) c=c+32;
        if (b7) c=c+64;


        setPixel(x, y, frs[c][0], op1);
        setPixel(x, y, frs[c][1], op2);
        setPixel(x, y, frs[c][2], op4);
    }

    private double getNum(boolean s1, boolean s2, boolean s3, boolean s4) {
        double c = 0;
        if (s1) c++;
        if (s2) c++;
        if (s3) c++;
        if (s4) c++;

        return c;
    }
    private void setPixel(int x, int y, double v, Graphics2D op) {
        float g = (float) (1 - v);
        System.out.println(g);
        Color col = new Color(g, g, g);
        op.setColor(col);
        op.fillRect(x, y, 1, 1);
    }

    private void save() {
        double dpi = 100;
        savePNGFile(opImage1, dir + op1src, dpi);
        savePNGFile(opImage2, dir + op2src, dpi);
        savePNGFile(opImage4, dir + op4src, dpi);
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
//        ipImage8 = ImageIO.read(new File(dir + ip8src));
//        ipImage9 = ImageIO.read(new File(dir + ip9src));
//        ipImage10 = ImageIO.read(new File(dir + ip10src));
//        ipImage11 = ImageIO.read(new File(dir + ip11src));
//        ipImage12 = ImageIO.read(new File(dir + ip12src));
//        ipImage13 = ImageIO.read(new File(dir + ip13src));
//        ipImage14 = ImageIO.read(new File(dir + ip14src));
//        ipImage15 = ImageIO.read(new File(dir + ip15src));

        ipw = ipImage1.getWidth();
        iph = ipImage1.getHeight();

        String numBits = "%" + 7 + "s";
        double tot = Math.pow(2, 7);
        for (double i = tot - 1; i > 0; i--) {
            String str = Integer.toBinaryString((int) i);
            String padded = String.format(numBits, str).replaceAll(" ", "0");

            boolean s7 = padded.substring(0, 1).equals("1");
            boolean s6 = padded.substring(1, 2).equals("1");
            boolean s5 = padded.substring(2, 3).equals("1");
            boolean s4 = padded.substring(3, 4).equals("1");
            boolean s3 = padded.substring(4, 5).equals("1");
            boolean s2 = padded.substring(5, 6).equals("1");
            boolean s1 = padded.substring(6, 7).equals("1");

            double numA = getNum(s7, s5, s3, s1);
            double numB = getNum(s7, s6, s4, s2);
            double numC = getNum(s7, s6, s5, s4);

            double frA = tot * (numA / 12);
            double frB = tot * (numB / 12);
            double frC = tot * (numC / 12);

//            double totFr = (frA + frB + frC);
//            frA = frA/totFr;
//            frB = frB/totFr;
//            frC = frC/totFr;
            frs[(int) i][0] = frA/128;
            frs[(int) i][1] = frB/128;
            frs[(int) i][2] = frC/128;
        }

    }

    private void saveLayered(BufferedImage opImageA, BufferedImage opImageB, BufferedImage opImageC, BufferedImage opImageD, String file) {
        BufferedImage opImage = createAlphaBufferedImage(opw, oph);
        Graphics2D op = (Graphics2D) opImage.getGraphics();
        op.setColor(WHITE);
        op.fillRect(0, 0, opw, oph);

        mixImageByGrey(op, opImageA, opImageB, opImageC, opImageD);
        savePNGFile(opImage, dir + imagesName + opSuff + file + opExt, 100);
    }

    private void mixImageByGrey(Graphics2D op, BufferedImage opImageA, BufferedImage opImageB, BufferedImage opImageC, BufferedImage opImageD) {
        for (int y = 0; y < oph; y++) {
            for (int x = 0; x < opw; x++) {
                double gA = 0;
                double gB = 0;
                double gC = 0;

                if (opImageA!=null) {
                    gA = 1 - ((double) (new Color(opImageA.getRGB(x, y)).getRed())) / 255.0;
                }
                if (opImageB!=null) {
                    gB = 1 - ((double) (new Color(opImageB.getRGB(x, y)).getRed())) / 255.0;
                }
                if (opImageC!=null) {
                    gC = 1 - ((double) (new Color(opImageC.getRGB(x, y)).getRed())) / 255.0;
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
