package com.op.moire.multipleslide;

import com.op.moire.Base;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ThreeSlide extends Base {
    private static final ThreeSlide threeSlide = new ThreeSlide();
    private String imagesDir = "faces";
    private String imagesName = "facesB";
    //    private String imagesDir = "ILU";
//    private String imagesName = "ILU";
//    private String imagesDir = "line";
//    private String imagesName = "line";
    private String dir = hostDir + "threeSlide/" + imagesDir + "/";
    private String ip1src = imagesName + "1.png";
    private String ip2src = imagesName + "2.png";
    private String ip3src = imagesName + "3.png";
    private String opBsrc = imagesName + "B.png";
    private String opTsrc = imagesName + "T.png";

    double dpi = 5*25.4;
    int ww = -1;
    int hh = -1;
    int www = -1;
    int hhh = -1;
    int border = -1;

    BufferedImage ipImage1;
    BufferedImage ipImage2;
    BufferedImage ipImage3;
    BufferedImage opImageB;
    BufferedImage opImageT;
    Graphics2D opGB;
    Graphics2D opGT;

    ArrayList<Top> tops = new ArrayList<>();
    ArrayList<Bottom> bottoms = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        threeSlide.doAll();
    }

    private void doAll() throws IOException {
        setup();
        initImages();


        for (int y = 2; y < hh - 2; y++) {
            Pair lastPair = null;
            for (int x = 2; x < ww - 2; x++) {
                Pair pair = null;
                if (x == 2) {
                    pair = writeDiagonalFirst(x, y);
                } else {
                    pair = writeDiagonal(x, y, lastPair);
                }
                lastPair = pair;
            }
        }
        initMarkers();
        saveImages();
    }

    private void initMarkers() {
        opGB.setColor(Color.RED);
        opGB.fillRect(border, border, 2, 2);
        opGB.fillRect((www + border) / 2, border, 2, 2);
        opGB.fillRect(www + border - 2, border, 2, 2);
        opGB.fillRect(border, hhh + border - 2, 2, 2);
        opGB.fillRect((www + border) / 2, hhh + border - 2, 2, 2);
        opGB.fillRect(www + border - 2, hhh + border - 2, 2, 2);

        opGT.setColor(Color.RED);
        opGT.fillRect(border, border, 2, 2);
        opGT.fillRect(((www + border) / 2) - 2, border, 2, 2);
        opGT.fillRect(www + border - 6, border, 2, 2);
        opGT.fillRect(border, hhh + border - 2, 2, 2);
        opGT.fillRect(((www + border) / 2) - 2, hhh + border - 2, 2, 2);
        opGT.fillRect(www + border - 6, hhh + border - 2, 2, 2);
    }

    private Pair writeDiagonal(int x, int y, Pair lastPair) {
        boolean a1 = -1 != ipImage1.getRGB(x, y);
        boolean a2 = -1 != ipImage2.getRGB(x, y);
        boolean a3 = -1 != ipImage3.getRGB(x, y);
        Pair pair = matchBottom(a1, a2, a3, bottoms.indexOf(lastPair.topn2), bottoms.indexOf(lastPair.topn3));
        if (pair != null) {
            setPixels(pair, x, y);

        }
        return pair;
    }

    private void saveImages() {
        savePNGFile(opImageB, dir + opBsrc, dpi);
        savePNGFile(opImageT, dir + opTsrc, dpi);
    }

    private Pair writeDiagonalFirst(int x, int y) {
        boolean a1 = -1 != ipImage1.getRGB(x, y);
        boolean a2 = -1 != ipImage2.getRGB(x, y);
        boolean a3 = -1 != ipImage3.getRGB(x, y);
        Pair pair = matchBottomFirst(a1, a2, a3);
        if (pair != null) {
            setPixelsFirst(pair, x, y);

        }
        return pair;
    }

    private void setPixelsFirst(Pair pair, int x, int y) {

        boolean[] tb = pair.topn1.getValueAsBooleans();
        fill(opGB, tb, 2 * x, 2 * y);

        boolean[] tb2 = pair.topn2.getValueAsBooleans();
        fill(opGB, tb2, 2 * (x + 1), 2 * y);

        boolean[] tt = pair.top.getValueAsBooleans();
        fill(opGT, tt, 2 * x, 2 * y);

    }

    private void setPixels(Pair pair, int x, int y) {

        boolean[] tb3 = pair.topn2.getValueAsBooleans();
        fill(opGB, tb3, 2 * (x + 1), 2 * y);

        boolean[] tt = pair.top.getValueAsBooleans();
        fill(opGT, tt, 2 * x, 2 * y);

    }

    private void fill(Graphics2D opG, boolean[] tb, int x, int y) {
        if (tb[0]) {
            opG.fillRect(x + border, y + border, 1, 1);
        }
        if (tb[1]) {
            opG.fillRect(x + border + 1, y + border, 1, 1);
        }
        if (tb[2]) {
            opG.fillRect(x + border, y + border + 1, 1, 1);
        }
        if (tb[3]) {
            opG.fillRect(x + border + 1, y + border + 1, 1, 1);
        }
    }

    private Pair matchBottom(boolean a1, boolean a2, boolean a3, int i2, int i3) {
        //Collections.shuffle(tops);
        for (Top top : tops) {
            //int[] matches = top.matchBlacks(a1, a2, a3, i2, i3);
            int[] matches = top.matchBlacksRnd(a1, a2, a3, i2, i3);
            if (isMatched(matches)) {
                print(top, matches);
                if (matches[1] == matches[2]) {
                    break;
                }
                return new Pair(top, bottoms.get(matches[0]), bottoms.get(matches[1]), bottoms.get(matches[2]));
            }

        }
        return null;
    }

    private Pair matchBottomFirst(boolean a1, boolean a2, boolean a3) {
        //Collections.shuffle(tops);
        for (Top top : tops) {
            //int[] matches = top.matchBlacks(a1, a2, a3);
            int[] matches = top.matchBlacksRndFirst(a1, a2, a3);
            if (isMatched(matches)) {
                print(top, matches);
                if (matches[1] == matches[2]) {
                    break;
                }
                return new Pair(top, bottoms.get(matches[0]), bottoms.get(matches[1]), bottoms.get(matches[2]));
            }

        }
        return null;
    }

    private void print(Top b, int[] matches) {
        System.out.print(b.value + ":");
        String blacks = "";
        for (int m : matches) {
            System.out.print("" + m + ",");
            blacks = blacks + (b.blacks[m] ? "B" : "W");
        }
        System.out.print(":" + blacks);
        System.out.println("");
    }

    private boolean isMatched(int[] matches) {
        for (int m : matches) {
            if (m == -1) {
                return false;
            }
        }
        return true;
    }

    private void initImages() throws IOException {
        System.out.println("Reading...");
        ipImage1 = ImageIO.read(new File(dir + ip1src));
        ipImage2 = ImageIO.read(new File(dir + ip2src));
        ipImage3 = ImageIO.read(new File(dir + ip3src));
        ww = ipImage1.getWidth();
        hh = ipImage1.getHeight();
        www = ww * 2;
        hhh = hh * 2;

        border = (int) ((double) (www) * 0.05);

        System.out.println("Creating...");
        opImageB = createAlphaBufferedImage(www + 2 * border, hhh + 2 * border);
        opGB = (Graphics2D) opImageB.getGraphics();
        opGB.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        opGB.setColor(Color.BLACK);

        opImageT = createAlphaBufferedImage(www + 2 * border, hhh + 2 * border);
        opGT = (Graphics2D) opImageT.getGraphics();
        opGT.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        opGT.setColor(Color.BLACK);
    }

    private void setup() {
        Bottom t1 = new Bottom("1000");
        Bottom t2 = new Bottom("0100");
        Bottom t3 = new Bottom("0010");
        Bottom t4 = new Bottom("0001");

        bottoms = new ArrayList();
        bottoms.add(t1);
        bottoms.add(t2);
        bottoms.add(t3);
        bottoms.add(t4);

        Top b1 = new Top("1100", bottoms);
        Top b2 = new Top("0110", bottoms);
        Top b3 = new Top("0011", bottoms);
        Top b4 = new Top("1001", bottoms);
        Top b5 = new Top("1010", bottoms);
        Top b6 = new Top("0101", bottoms);

        tops = new ArrayList();
        tops.add(b1);
        tops.add(b2);
        tops.add(b3);
        tops.add(b4);
        tops.add(b5);
        tops.add(b6);

    }

}
