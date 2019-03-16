package com.op.moire.rotate;

import com.op.moire.Base;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Rotate extends Base {
    private static final Rotate fourRotate = new Rotate();
    private String imagesDir = "sanVir3";
    private String imagesName = "sv";
    private String dir = hostDir + "fourRotate/" + imagesDir + "/";
    private String ipExt = ".jpg";
    private String opExt = ".png";
    private String ip1src = imagesName + "1" + ipExt;
    private String ip2src = imagesName + "2" + ipExt;
    private String ip3src = imagesName + "3" + ipExt;
    private String ip4src = imagesName + "4" + ipExt;
    private String opBsrc = imagesName + "B" + opExt;
    private String opTsrc = imagesName + "T" + opExt;
    private String opBTsrc = imagesName + "BT.png";
    private boolean saveOnOneImage = false;
    private Color[] mainCol = {Color.BLACK, Color.BLUE, Color.RED.darker(), Color.GREEN.darker()};
    private Color[] bgCol = {Color.WHITE, Color.CYAN, Color.MAGENTA.brighter().brighter(), Color.YELLOW.brighter()};

    int ipw = -1;
    int iph = -1;
    int opw = -1;
    int oph = -1;
    int border = -1;

    int opFactor = 20;
    int ipStep = 1;
    int opStep = opFactor/(ipStep*2);
    double dpi = 254;

    BufferedImage ipImage1;
    BufferedImage ipImage2;
    BufferedImage ipImage3;
    BufferedImage ipImage4;
    BufferedImage opImageB;
    BufferedImage opImageT;
    Graphics2D opGB;
    Graphics2D opGT;

    ArrayList<Bottom> bottoms = null;
    ArrayList<Top> tops = null;
    private double bwThresh = 0.5;

    public static void main(String[] args) throws Exception {
        fourRotate.doAll();
    }

    private void doAll() throws IOException {
        initImages();
//        bottoms = Bottom.createAllFilledBottoms();
//        tops = Top.createAllFilledTops(bottoms);
        //System.out.println("bottoms:" + bottoms.size() + " tops:" + tops.size());
        int x = (ipw / 2) - 1;
        int y = (iph / 2) - 1;

        //1,2,3,4
        printAllCorners(x, y, 1, 0, 1, 1, 0, 1);

        int step = ipStep * 2;
        for (int j = step + ipStep; j < ipw; j = j + step) {
            int mid = (int) (j / step);
            for (int i = 0; i < j; i = i + ipStep) {
                int x1 = x + i - mid;
                int y1 = y - mid;
                int w1 = j - i;
                int h1 = i;
                int w2 = j - (step * i);
                int h2 = j;
                int w3 = -i;
                int h3 = j - i;
                printAllCorners(x1, y1, w1, h1, w2, h2, w3, h3);
            }
        }

        saveImages();
    }

    private void printAllCorners(int x, int y, int w1, int h1, int w2, int h2, int w3, int h3) {
        //opGT.setColor(new Color((float)Math.random(),(float) Math.random(), (float)Math.random()));
        int x1 = x;
        int y1 = y;

        int x2 = x + w1;
        int y2 = y + h1;

        int x3 = x + w2;
        int y3 = y + h2;

        int x4 = x + w3;
        int y4 = y + h3;


        int rnd = (int) (Math.random() * 4);
        //rnd = 0;
        //System.out.println("rnd="+rnd);
        ArrayList<Top> finalTops = getAllTops(rnd);
        ArrayList<Bottom> finalBots = getAllBottoms(rnd, x1, y1, x2, y2, x3, y3, x4, y4);

        setTopPixels(opGT, finalTops.get(0), x1, y1, rnd);
        setTopPixels(opGT, finalTops.get(1), x2, y2, rnd);
        setTopPixels(opGT, finalTops.get(2), x3, y3, rnd);
        setTopPixels(opGT, finalTops.get(3), x4, y4, rnd);

        setBottomPixels(opGB, finalBots.get(0), x1, y1, rnd);
        setBottomPixels(opGB, finalBots.get(1), x2, y2, rnd);
        setBottomPixels(opGB, finalBots.get(2), x3, y3, rnd);
        setBottomPixels(opGB, finalBots.get(3), x4, y4, rnd);

        System.out.println("printed x,y: " + x1 + "," + y1 +":"+ x2 + "," + y2 +":"+ x3 + "," + y3 +":"+ x4 + "," + y4 +":");
    }

    private ArrayList<Bottom> getAllBottoms(int rnd, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        Boolean[] blacksNeededA = getBlacks(ipImage1, x1, y1, x2, y2, x3, y3, x4, y4);
        Boolean[] blacksNeededB = getBlacks(ipImage2, x1, y1, x2, y2, x3, y3, x4, y4);
        Boolean[] blacksNeededC = getBlacks(ipImage3, x1, y1, x2, y2, x3, y3, x4, y4);
        Boolean[] blacksNeededD = getBlacks(ipImage4, x1, y1, x2, y2, x3, y3, x4, y4);

        boolean[] blacks0 = {blacksNeededA[0], blacksNeededB[0], blacksNeededC[0], blacksNeededD[0]};
        boolean[] blacks1 = {blacksNeededA[1], blacksNeededB[1], blacksNeededC[1], blacksNeededD[1]};
        boolean[] blacks2 = {blacksNeededA[2], blacksNeededB[2], blacksNeededC[2], blacksNeededD[2]};
        boolean[] blacks3 = {blacksNeededA[3], blacksNeededB[3], blacksNeededC[3], blacksNeededD[3]};

        ArrayList<Bottom> bots = new ArrayList();

        Color[] colsA = getColors(ipImage1, x1, y1, x2, y2, x3, y3, x4, y4);
        Color[] colsB = getColors(ipImage2, x1, y1, x2, y2, x3, y3, x4, y4);
        Color[] colsC = getColors(ipImage3, x1, y1, x2, y2, x3, y3, x4, y4);
        Color[] colsD = getColors(ipImage4, x1, y1, x2, y2, x3, y3, x4, y4);

        Color[] cols0 = {colsA[0], colsB[0], colsC[0], colsD[0]};
        Color[] cols1 = {colsA[1], colsB[1], colsC[1], colsD[1]};
        Color[] cols2 = {colsA[2], colsB[2], colsC[2], colsD[2]};
        Color[] cols3 = {colsA[3], colsB[3], colsC[3], colsD[3]};

        bots.add(getBottom(rnd, blacks0, cols0));
        bots.add(getBottom(rnd, blacks1, cols1));
        bots.add(getBottom(rnd, blacks2, cols2));
        bots.add(getBottom(rnd, blacks3, cols3));

        return bots;
    }

    private Bottom getBottom(int rnd, boolean[] blacksNeeded, Color[] cols) {

        int i0 = (0 + rnd) % 4;
        int i1 = (1 + rnd) % 4;
        int i2 = (2 + rnd) % 4;
        int i3 = (3 + rnd) % 4;

        String val = blacksNeeded[0] ? "1" : "0";
        val = val + (blacksNeeded[1] ? "1" : "0");
        val = val + (blacksNeeded[3] ? "1" : "0");
        val = val + (blacksNeeded[2] ? "1" : "0");

        Color[] colen = {Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK};
        Top top = new Top(val, colen);
        int ang = (rnd * 90);
        top = top.rotate2(ang);

        return new Bottom(top.value, colen);
    }

    private ArrayList<Top> getAllTops(int rnd) {
        ArrayList<Top> all = new ArrayList();
        String[] vals = {"1", "1", "1", "1"};
        vals[rnd] = "0";
        String val = vals[0] + vals[1] + vals[3] + vals[2];
        Color[] cols = {Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK};
        Top top0 = new Top(val, cols);
        Top top1 = new Top(val, cols);
        Top top2 = new Top(val, cols);
        Top top3 = new Top(val, cols);

        all.add(top0);
        all.add(top1);
        all.add(top3);
        all.add(top2);

        return all;
    }

    private Boolean[] getBlacks(BufferedImage image, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        Boolean[] blacksNeeded = {false, false, false, false};
        blacksNeeded[0] = isBlack(image, x1, y1);
        blacksNeeded[1] = isBlack(image, x2, y2);
        blacksNeeded[2] = isBlack(image, x3, y3);
        blacksNeeded[3] = isBlack(image, x4, y4);
        return blacksNeeded;
    }

    private Color[] getColors(BufferedImage image, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        Color[] blacksNeeded = {null, null, null, null};
        blacksNeeded[0] = getColor(image, x1, y1);
        blacksNeeded[1] = getColor(image, x2, y2);
        blacksNeeded[2] = getColor(image, x3, y3);
        blacksNeeded[3] = getColor(image, x4, y4);
        return blacksNeeded;
    }

    private boolean isBlack(BufferedImage image, int x, int y) {
        return getGrey(image, x, y) < bwThresh;
        //return -1 != image.getRGB(x, y);
    }

    private double getGrey(BufferedImage image, int x, int y) {
        System.out.println("x,y:"+x+","+y);
        Color col = new Color(image.getRGB(x, y));
        return (col.getRed() + col.getGreen() + col.getBlue()) / (255.0 * 3.0);
    }

    private Color getColor(BufferedImage image, int x, int y) {
        return new Color(image.getRGB(x, y));
    }

    private void saveImages() {
        if (saveOnOneImage) {
            saveAsOneImage();
        } else {
            saveAsTwoImages();
        }
    }

    private void initImages() throws IOException {
        System.out.println("Reading...");
        ipImage1 = ImageIO.read(new File(dir + ip1src));
        ipImage2 = ImageIO.read(new File(dir + ip2src));
        ipImage3 = ImageIO.read(new File(dir + ip3src));
        ipImage4 = ImageIO.read(new File(dir + ip4src));
        ipw = ipImage1.getWidth();
        iph = ipImage1.getHeight();
        opw = ipw * opFactor;
        oph = iph * opFactor;

        border = (int) ((double) (opw) * 0.05);
        border = 0;

        double tot = opw + 2 * border;


        System.out.println("Creating...");
        opImageB = createAlphaBufferedImage(opw + 2 * border, oph + 2 * border);
        opGB = (Graphics2D) opImageB.getGraphics();
        opGB.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        opGB.setColor(Color.WHITE);
        opGB.fillRect(0, 0, opw, oph);
        opGB.setColor(Color.BLACK);

        opImageT = createAlphaBufferedImage(opw + 2 * border, oph + 2 * border);
        opGT = (Graphics2D) opImageT.getGraphics();
        opGT.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        opGT.setColor(Color.BLACK);
    }

    private void saveAsOneImage() {
        BufferedImage opImageC = createAlphaBufferedImage(1 + 2 * (opw + 2 * border), oph + 2 * border);
        Graphics2D opG = (Graphics2D) (opImageC.getGraphics());
        opG.drawImage(opImageB, null, null);
        opG.setColor(Color.RED);
        opG.fillRect((opw + 2 * border), 0, 1, oph + 2 * border);
        opG.drawImage(opImageT, AffineTransform.getTranslateInstance(1 + opw + 2 * border, 0), null);

        savePNGFile(opImageC, dir + opBTsrc, dpi);
    }

    private void saveAsTwoImages() {
        savePNGFile(opImageB, dir + opBsrc, dpi);
        savePNGFile(opImageT, dir + opTsrc, dpi);
    }

    public static double calculateSD(double numArray[]) {
        double sum = 0.0, standardDeviation = 0.0;
        int length = numArray.length;

        for (double num : numArray) {
            sum += num;
        }

        double mean = sum / length;

        for (double num : numArray) {
            standardDeviation += Math.pow(num - mean, 2);
        }

        return Math.sqrt(standardDeviation / length);
    }

    private void setBottomPixels(Graphics2D opG, Bottom bottom, int x1, int y1, int rnd) {
        int xOff = opFactor * x1;
        int yOff = opFactor * y1;

        boolean[] vals = bottom.getValueAsBooleans();

        int[] is = {0, 1, 2, 3};
        int rot = rnd * 90;
        if (rot%360 == 0) {
            is[0] = 0;
            is[1] = 1;
            is[2] = 2;
            is[3] = 3;
        } else if (rot%360 == 90) {
            is[0] = 2;
            is[1] = 0;
            is[2] = 3;
            is[3] = 1;
        } else if (rot%360 == 180) {
            is[0] = 3;
            is[1] = 2;
            is[2] = 1;
            is[3] = 0;
        } else {
            is[0] = 1;
            is[1] = 3;
            is[2] = 0;
            is[3] = 2;
        }

        fillBotPixel(opG, xOff + 0, yOff + 0, vals[0], mainCol[is[0]], bgCol[is[0]]);
        fillBotPixel(opG, xOff + opStep, yOff + 0, vals[1], mainCol[is[1]], bgCol[is[1]]);
        fillBotPixel(opG, xOff + opStep, yOff + opStep, vals[3], mainCol[is[3]], bgCol[is[3]]);
        fillBotPixel(opG, xOff + 0, yOff + opStep, vals[2], mainCol[is[2]], bgCol[is[2]]);
    }

    private void fillBotPixel(Graphics2D opG, int x, int y, boolean val, Color main, Color bg) {
        if (val) {
            opG.setColor(main);
            opG.fillRect(x, y, opStep, opStep);
        } else {
            opG.setColor(bg);
            opG.fillRect(x, y, opStep, opStep);
        }
    }

    private void setTopPixels(Graphics2D opGT, Top top, int x1, int y1, int rnd) {
        Color[] colst = {Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK};
        fillTop(opGT, top.getValueAsBooleans(), opFactor * x1, opFactor * y1, rnd, colst[0]);
    }

    private void fillTop(Graphics2D opG, boolean[] tb, int xOff, int yOff, int rnd, Color col) {
        int c = 0;

        fillTopPixel(opG, xOff + 0, yOff + 0, tb[0]);
        fillTopPixel(opG, xOff + opStep, yOff + 0, tb[1]);
        fillTopPixel(opG, xOff + opStep, yOff + opStep, tb[3]);
        fillTopPixel(opG, xOff + 0, yOff + opStep, tb[2]);
    }

    private void fillTopPixel(Graphics2D opG, int x, int y, boolean b) {
        if (b) {
            opG.setColor(Color.BLACK);
            opG.fillRect(x, y, opStep, opStep);
        }
    }
}
