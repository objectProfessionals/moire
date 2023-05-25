package com.op.moire.cmykstack;

import com.op.moire.Base;
import com.op.moire.fourstack.sets.KMColorUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static java.awt.Color.*;

public class RadomStack extends Base {

    static RadomStack stack = new RadomStack();

    //    int[] t = {3, 1};
//    int[] p = {6, 4};
//    int[] s = {15, 10};
    int[] t = {4, 1};
    int[] p = {9, 3};
    int[] s = {15, 10};
    private boolean rnd = true;
    //private String imagesDir = "moved";
    private String imagesDir = "medium";
    //private String imagesDir = "small";
    private String imagesName = "test";
    private String outputImagesName = "random";
    private String dir = hostDir + "cmystack/" + imagesDir + "/";
    private String ipExt = ".png";
    private String opExt = ".png";
    private String opSuff = "_OUT";
    private String ip1src = imagesName + "1" + ipExt;
    private String ip2src = imagesName + "2" + ipExt;
    private String ip3src = imagesName + "3" + ipExt;
    private String ip4src = imagesName + "4" + ipExt;
    private String ip5src = imagesName + "5" + ipExt;
    private String ip6src = imagesName + "6" + ipExt;
    private String ip7src = imagesName + "7" + ipExt;

    private String op1src = outputImagesName + opSuff + "1" + opExt;
    private String op2src = outputImagesName + opSuff + "2" + opExt;
    private String op4src = outputImagesName + opSuff + "4" + opExt;

    BufferedImage ipImage1;
    BufferedImage ipImage2;
    BufferedImage ipImage3;
    BufferedImage ipImage4;
    BufferedImage ipImage5;
    BufferedImage ipImage6;
    BufferedImage ipImage7;

    BufferedImage opImage1;
    BufferedImage opImage2;
    BufferedImage opImage4;
    Graphics2D op1;
    Graphics2D op2;
    Graphics2D op4;
    int ipw = 0;
    int iph = 0;
    int opw = 0;
    int oph = 0;
    int pixelWidth = 1;
    int opFactor = pixelWidth * 5;
    int iFactor = 10;
    HashMap<String, String> calculations = new HashMap<>();
    HashMap<String, Color> key2color = new HashMap<>();
    HashMap<Color, String> color2Key = new HashMap<>();
    int maxEnd = 0;
    Random random = new Random(0);
    HashMap<String, Integer[]> rndCoords = new HashMap<>();

    public static void main(String[] args) throws Exception {
        stack.doAll();
    }

    private void doAll() throws IOException {
        initImages();
        initCoords();

        drawAll();

        saveAsImages();

        saveLayered(opImage1, opImage2, null, "3");
//        saveLayered(opImage1, opImage4, null, "5");
//        saveLayered(opImage2, opImage4, null, "6");
//        saveLayered(opImage1, opImage2, opImage4, "7");

    }

    private void initCoords() {
        for (int y = 0; y < iph; y++) {
            for (int x = 0; x < ipw; x++) {
                for (int i = 0; i < iFactor; i++) {
                    int rndX = (int) (random.nextDouble() * (double) opFactor);
                    int rndY = (int) (random.nextDouble() * (double) opFactor);
                    int xx = x * opFactor + rndX;
                    int yy = y * opFactor + rndY;
                    Integer[] xy = {xx, yy};
                    rndCoords.put(x+","+y+","+i, xy);
                }
            }
        }
    }

    private void drawAll() {
        for (int y = 0; y < iph; y++) {
            for (int x = 0; x < ipw; x++) {
                System.out.println("x,y:" + x + "," + y);
                int off = 100;
                boolean b1 = new Color(ipImage1.getRGB(x, y)).getRed() < off;
                boolean b2 = new Color(ipImage2.getRGB(x, y)).getRed() < off;
                boolean b3 = new Color(ipImage3.getRGB(x, y)).getRed() < off;
                boolean b4 = new Color(ipImage4.getRGB(x, y)).getRed() < off;
                boolean b5 = new Color(ipImage5.getRGB(x, y)).getRed() < off;
                boolean b6 = new Color(ipImage6.getRGB(x, y)).getRed() < off;
                boolean b7 = new Color(ipImage7.getRGB(x, y)).getRed() < off;

                Boolean[] blacks = {b1, b2, b3, b4, b5, b6, b7};
                drawCell(x, y, blacks);
            }
        }
        System.out.println("maxEnd=" + maxEnd);

    }

    private void drawCell(int x, int y, Boolean[] blacks) {
        for (int i=0; i< iFactor; i++) {
            int xx = rndCoords.get(x+","+y+","+i)[0];
            int yy = rndCoords.get(x+","+y+","+i)[1];
            Color[] colors = getColors(blacks[0], blacks[1], blacks[2]);
            op1.setColor(colors[0]);
            paint(op1, xx, yy);
            op2.setColor(colors[1]);
            paint(op2, xx, yy);

//            if(blacks[0]) {
//                if(blacks[1]) {
//                    if (blacks[2]) {
//                        op1.setColor(CYAN);
//                        paint(op1, xx, yy);
//                        op2.setColor(MAGENTA);
//                        paint(op2, xx, yy);
//                    } else {
//                        op1.setColor(getPreferredColor(CYAN));
//                        paint(op1, xx, yy);
//                        op2.setColor(getPreferredColor(MAGENTA));
//                        paint(op2, xx, yy);
//                    }
//                } else {
//                    if (blacks[2]) {
//                        op1.setColor(CYAN);
//                        paint(op1, xx, yy);
//                        op2.setColor(getPreferredColor(MAGENTA));
//                        paint(op2, xx, yy);
//                    } else {
//                        op1.setColor(CYAN);
//                        paint(op1, xx, yy);
//                        op2.setColor(getOtherColor(MAGENTA));
//                        paint(op2, xx, yy);
//                    }
//                }
//            } else {
//                if(blacks[1]) {
//                    if (blacks[2]) {
//                        op1.setColor(getOtherColor(CYAN));
//                        paint(op1, xx, yy);
//                        op2.setColor(MAGENTA);
//                        paint(op2, xx, yy);
//                    } else {
//                        op1.setColor(getOtherColor(CYAN));
//                        paint(op1, xx, yy);
//                        op2.setColor(MAGENTA);
//                        paint(op2, xx, yy);
//                    }
//                } else {
//                    if (blacks[2]) {
//                        op1.setColor(getOtherColor(CYAN));
//                        paint(op1, xx, yy);
//                        op2.setColor(getPreferredColor(MAGENTA));
//                        paint(op2, xx, yy);
//                    } else {
//                        op1.setColor(getOtherColor(CYAN));
//                        paint(op1, xx, yy);
//                        op2.setColor(getOtherColor(MAGENTA));
//                        paint(op2, xx, yy);
//                    }
//                }
//            }
        }
    }

    private Color[] getColors(Boolean black1, Boolean black2, Boolean black3) {
        int i = 0;
        if (black1) {
            i = i + 1;
        }
        if (black2) {
            i = i + 2;
        }
        if (black3) {
            i = i + 4;
        }

        Color[][] all = {{getRandomColor(), getRandomColor()},
                {CYAN, getOtherColor(MAGENTA)},
                {getOtherColor(CYAN), MAGENTA},
                {getPreferredColor(CYAN, 4), getPreferredColor(MAGENTA, 5)},
                {getPreferredColor(CYAN, 5), getPreferredColor(MAGENTA, 4)},
                {getPreferredColor(CYAN, 10), getPreferredColor(MAGENTA, 8)},
                {getPreferredColor(CYAN, 8), getPreferredColor(MAGENTA, 10)},
                {CYAN, MAGENTA}};
        return all[i];
    }

    private void paint(Graphics2D op, int xx, int yy) {
        op.fillRect(xx, yy, pixelWidth, pixelWidth);
    }

    private Color getPreferredColor(Color preferred, int weighting) {
        ArrayList<Color> toUse = new ArrayList<>();
        toUse.add(WHITE);
        toUse.add(CYAN);
        toUse.add(MAGENTA);
        toUse.add(YELLOW);
        for (int i=0; i<weighting; i++) {
            toUse.add(preferred);
        }
        int r = (int)(random.nextDouble()*((double)toUse.size()));
        return toUse.get(r);
    }

    private Color getOtherColor(Color dontUse) {
        ArrayList<Color> toUse = new ArrayList<>();
        toUse.add(WHITE);
        toUse.add(CYAN);
        toUse.add(MAGENTA);
        toUse.add(YELLOW);
        toUse.add(WHITE);
        toUse.add(CYAN);
        toUse.add(MAGENTA);
        toUse.add(YELLOW);
        toUse.add(WHITE);
        toUse.add(CYAN);
        toUse.add(MAGENTA);
        toUse.add(YELLOW);
        toUse.add(WHITE);
        toUse.add(CYAN);
        toUse.add(MAGENTA);
        toUse.add(YELLOW);
        toUse.remove(dontUse);
        int r = (int)(random.nextDouble()*((double)toUse.size()));
        return toUse.get(r);
    }

    private Color getRandomColor() {
        ArrayList<Color> toUse = new ArrayList<>();
        toUse.add(WHITE);
        toUse.add(CYAN);
        toUse.add(MAGENTA);
        toUse.add(YELLOW);
        int r = (int)(random.nextDouble()*((double)toUse.size()));
        return toUse.get(r);
    }

    private void saveLayered(BufferedImage opImageA, BufferedImage opImageB, BufferedImage opImageC, String file) {
        BufferedImage opImage = createAlphaBufferedImage(opw, oph);
        Graphics2D op = (Graphics2D) opImage.getGraphics();

        //mixImageByColors(op, opImageA, opImageB, opImageC);
        mixImageByCMY(op, opImageA, opImageB, opImageC);
        savePNGFile(opImage, dir + outputImagesName + opSuff + file + opExt, 100);
    }

    private void mixImageByColors(Graphics2D op, BufferedImage opImageA, BufferedImage opImageB, BufferedImage opImageC) {
        for (int y = 0; y < oph; y++) {
            for (int x = 0; x < opw; x++) {
                Color colA = new Color(opImageA.getRGB(x, y));
                Color colB = new Color(opImageB.getRGB(x, y));
                Color res = KMColorUtils.mix(colA, colB);
                Color res2 = res;
                if (opImageC != null) {
                    Color colC = new Color(opImageC.getRGB(x, y));
                    res2 = KMColorUtils.mix(res, colC);
                }
                op.setColor(res2);
                op.fillRect(x, y, 1, 1);
            }

        }
    }

    private void mixImageByCMY(Graphics2D op, BufferedImage opImageA, BufferedImage opImageB, BufferedImage opImageC) {
        for (int y = 0; y < oph; y++) {
            for (int x = 0; x < opw; x++) {
                Color colA = new Color(opImageA.getRGB(x, y));
                Color colB = new Color(opImageB.getRGB(x, y));
                Color res = getMixColor(colA, colB);
                Color res2 = res;
                if (opImageC != null) {
                    Color colC = new Color(opImageC.getRGB(x, y));
                    res2 = getMixColor(res, colC);
                }
                op.setColor(res2);
                op.fillRect(x, y, 1, 1);
            }

        }
    }

    private Color getMixColor(Color c1, Color c2) {

        return key2color.get(calculations.get(color2Key.get(c1) + color2Key.get(c2)));
    }


    ArrayList forLoopRange(int from, int limit) {
        ArrayList numbers = new ArrayList<Integer>(limit);
        for (int to = from + limit; from < to; ++from) {
            numbers.add(from);
        }
        return numbers;
    }

    private void saveAsImages() {
        double dpi = 100;
        savePNGFile(opImage1, dir + op1src, dpi);
        savePNGFile(opImage2, dir + op2src, dpi);
        savePNGFile(opImage4, dir + op4src, dpi);
    }

    private void initImages() throws IOException {
        System.out.println("Reading...");
        ipImage1 = ImageIO.read(new File(dir + ip1src));
        ipImage2 = ImageIO.read(new File(dir + ip2src));
        ipImage3 = ImageIO.read(new File(dir + ip3src));
        ipImage4 = ImageIO.read(new File(dir + ip4src));
        ipImage5 = ImageIO.read(new File(dir + ip5src));
        ipImage6 = ImageIO.read(new File(dir + ip6src));
        ipImage7 = ImageIO.read(new File(dir + ip7src));

        ipw = ipImage1.getWidth();
        iph = ipImage1.getHeight();
        opw = ipw * opFactor;
        oph = iph * opFactor;
        System.out.println("Creating...");

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

        calculations.put("CC", "C");
        calculations.put("CM", "B");
        calculations.put("MC", "B");
        calculations.put("CY", "G");
        calculations.put("YC", "G");
        calculations.put("MM", "M");
        calculations.put("YY", "Y");
        calculations.put("YM", "R");
        calculations.put("MY", "R");

        calculations.put("RR", "R");
        calculations.put("GG", "G");
        calculations.put("BB", "B");

        calculations.put("RC", "X");
        calculations.put("RM", "R");
        calculations.put("RY", "R");

        calculations.put("GC", "G");
        calculations.put("GM", "X");
        calculations.put("GY", "G");

        calculations.put("BC", "B");
        calculations.put("BM", "B");
        calculations.put("BY", "X");

        calculations.put("RG", "X");
        calculations.put("GR", "X");
        calculations.put("RB", "X");
        calculations.put("BR", "X");
        calculations.put("GB", "X");
        calculations.put("BG", "X");

        calculations.put("WW", "W");
        calculations.put("WW", "W");
        calculations.put("CW", "C");
        calculations.put("WC", "C");
        calculations.put("YW", "Y");
        calculations.put("WY", "Y");
        calculations.put("MW", "M");
        calculations.put("WM", "M");
        calculations.put("RW", "R");
        calculations.put("WR", "R");
        calculations.put("GW", "G");
        calculations.put("WG", "G");
        calculations.put("BW", "B");
        calculations.put("WB", "B");
        calculations.put("XW", "X");
        calculations.put("WX", "X");

        key2color.put("W", WHITE);
        key2color.put("C", CYAN);
        key2color.put("Y", YELLOW);
        key2color.put("M", MAGENTA);
        key2color.put("R", RED);
        key2color.put("G", GREEN);
        key2color.put("B", BLUE);
        key2color.put("X", BLACK);

        color2Key.put(WHITE, "W");
        color2Key.put(CYAN, "C");
        color2Key.put(YELLOW, "Y");
        color2Key.put(MAGENTA, "M");
        color2Key.put(RED, "R");
        color2Key.put(GREEN, "G");
        color2Key.put(BLUE, "B");
        color2Key.put(BLACK, "X");
    }


}
