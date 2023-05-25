package com.op.moire.cmykstack;

import com.op.moire.Base;
import com.op.moire.fourstack.sets.KMColorUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import static java.awt.Color.*;

public class Stack extends Base {

    static Stack stack = new Stack();

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

    private String op1src = imagesName + opSuff + "1" + opExt;
    private String op2src = imagesName + opSuff + "2" + opExt;
    private String op4src = imagesName + opSuff + "4" + opExt;

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
    int pixelWidth = 4;
    int opFactor = pixelWidth * 7;
    HashMap<String, String> calculations = new HashMap<>();
    HashMap<String, Color> colors = new HashMap<>();
    HashMap<Color, String> colors2 = new HashMap<>();
    int maxEnd = 0;

    public static void main(String[] args) throws Exception {
        stack.doAll();
    }

    private void doAll() throws IOException {
        initImages();

        drawAll();

        saveAsImages();

        saveLayered(opImage1, opImage2, null, "3");
        saveLayered(opImage1, opImage4, null, "5");
        saveLayered(opImage2, opImage4, null, "6");
        saveLayered(opImage1, opImage2, opImage4, "7");

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

    private void drawCell(int px, int py, Boolean[] blacks) {
        //f : t
        int sa = blacks[0] ? s[0] : s[1];
        int sb = blacks[1] ? s[0] : s[1];
        int sc = blacks[3] ? s[0] : s[1];

        int pab = blacks[2] ? p[0] : p[1];
        int pac = blacks[4] ? p[0] : p[1];
        int pbc = blacks[5] ? p[0] : p[1];

        int z = blacks[6] ? t[0] : t[1];

        int w = pab - z;
        int x = pac - z;
        int y = pbc - z;

        int q = sa - w - x - z;
        int r = sb - w - y - z;
        int s = sc - x - z - y;

        int count = 0;
        int end = z;
        int max = (opFactor * 2) * (opFactor * 2);
        boolean[] out1 = new boolean[max];
        boolean[] out2 = new boolean[max];
        boolean[] out4 = new boolean[max];
        for (; count < end; count++) {
            out1[count] = true;
            out2[count] = true;
            out4[count] = true;
        }

        end = end + w;
        for (; count < end; count++) {
            out1[count] = true;
            out2[count] = true;
        }

        end = end + x;
        for (; count < end; count++) {
            out1[count] = true;
            out4[count] = true;
        }

        end = end + y;
        for (; count < end; count++) {
            out2[count] = true;
            out4[count] = true;
        }

        end = end + q;
        for (; count < end; count++) {
            out1[count] = true;
        }

        end = end + r;
        for (; count < end; count++) {
            out2[count] = true;
        }

        end = end + s;
        for (; count < end; count++) {
            out4[count] = true;
        }

        if (end > maxEnd) {
            maxEnd = end;
        }
        ArrayList<Integer> rndOrder = forLoopRange(0, end);
        if (rnd) {
            Collections.shuffle(rndOrder);
        }

        fillRects(out1, op1, rndOrder, px, py);
        fillRects(out2, op2, rndOrder, px, py);
        fillRects(out4, op4, rndOrder, px, py);
    }

    private void fillRects(boolean[] out, Graphics2D op, ArrayList<Integer> order, int px, int py) {
        int xoff = 0;
        int yoff = 0;
        int sq = opFactor;
        for (int rnd : order) {
            int x1 = (px * sq) + xoff;
            int y1 = (py * sq) + yoff;
            if (out[rnd]) {
                op.fillRect(x1, y1, pixelWidth, pixelWidth);
            } else {
                //
            }
            xoff = xoff + pixelWidth;
            if (xoff == sq) {
                xoff = 0;
                yoff = yoff + pixelWidth;
            }
        }
    }

    private void saveLayered(BufferedImage opImageA, BufferedImage opImageB, BufferedImage opImageC, String file) {
        BufferedImage opImage = createAlphaBufferedImage(opw, oph);
        Graphics2D op = (Graphics2D) opImage.getGraphics();

        //mixImageByColors(op, opImageA, opImageB, opImageC);
        mixImageByCMY(op, opImageA, opImageB, opImageC);
        savePNGFile(opImage, dir + imagesName + opSuff + file + opExt, 100);
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

        return colors.get(calculations.get(colors2.get(c1) + colors2.get(c2)));
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
        op1.setColor(Color.YELLOW);

        opImage2 = createAlphaBufferedImage(opw, oph);
        op2 = (Graphics2D) opImage2.getGraphics();
        op2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        op2.setColor(WHITE);
        op2.fillRect(0, 0, opw, oph);
        op2.setColor(CYAN);

        opImage4 = createAlphaBufferedImage(opw, oph);
        op4 = (Graphics2D) opImage4.getGraphics();
        op4.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        op4.setColor(WHITE);
        op4.fillRect(0, 0, opw, oph);
        op4.setColor(Color.MAGENTA);

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

        colors.put("W", WHITE);
        colors.put("C", CYAN);
        colors.put("Y", YELLOW);
        colors.put("M", MAGENTA);
        colors.put("R", RED);
        colors.put("G", GREEN);
        colors.put("B", BLUE);
        colors.put("X", BLACK);

        colors2.put(WHITE, "W");
        colors2.put(CYAN, "C");
        colors2.put(YELLOW, "Y");
        colors2.put(MAGENTA, "M");
        colors2.put(RED, "R");
        colors2.put(GREEN, "G");
        colors2.put(BLUE, "B");
        colors2.put(BLACK, "X");
    }


}
