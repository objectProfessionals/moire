package com.op.moire.multipleslide;

import com.op.moire.Base;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ThreeSlide extends Base {
    private static final ThreeSlide threeSlide = new ThreeSlide();
    private String imagesDir = "faces";
    private String imagesName = "facesC";
    //    private String imagesDir = "scene";
//    private String imagesName = "scene";
    //    private String imagesDir = "line";
//    private String imagesName = "line";
    private String dir = hostDir + "threeSlide/" + imagesDir + "/";
    private String ip1src = imagesName + "1.png";
    private String ip2src = imagesName + "2.png";
    private String ip3src = imagesName + "3.png";
    private String opBsrc = imagesName + "B.png";
    private String opTsrc = imagesName + "T.png";

    double dpi = 5 * 25.4;
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
            Row lastRow = null;
            for (int x = 2; x < ww - 2; x++) {
                Row row = null;
                if (x == 2) {
                    row = writeDiagonalFirst(x, y);
                } else {
                    row = writeDiagonal(x, y, lastRow);
                }
                lastRow = row;
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

    private Row writeDiagonal(int x, int y, Row lastRow) {
        boolean a1 = -1 != ipImage1.getRGB(x, y);
        boolean a2 = -1 != ipImage2.getRGB(x, y);
        boolean a3 = -1 != ipImage3.getRGB(x, y);

        Row row = matchBottom(a1, a2, a3, bottoms.indexOf(lastRow.botn2), bottoms.indexOf(lastRow.botn3));
        if (row != null) {
            setPixels(row, x, y);

        }
        return row;
    }

    private void saveImages() {
        savePNGFile(opImageB, dir + opBsrc, dpi);
        savePNGFile(opImageT, dir + opTsrc, dpi);
    }

    private Row writeDiagonalFirst(int x, int y) {
        boolean a1 = -1 != ipImage1.getRGB(x, y);
        boolean a2 = -1 != ipImage2.getRGB(x, y);
        boolean a3 = -1 != ipImage3.getRGB(x, y);
        Row row = matchBottomFirst(a1, a2, a3);
        if (row != null) {
            setPixelsFirst(row, x, y);

        }
        return row;
    }

    private void setPixelsFirst(Row row, int x, int y) {

        boolean[] tb = row.botn1.getValueAsBooleans();
        fill(opGB, tb, 2 * x, 2 * y);

        boolean[] tb2 = row.botn2.getValueAsBooleans();
        fill(opGB, tb2, 2 * (x + 1), 2 * y);

        boolean[] tt = row.top.getValueAsBooleans();
        fill(opGT, tt, 2 * x, 2 * y);

    }

    private void setPixels(Row row, int x, int y) {

        boolean[] tb3 = row.botn2.getValueAsBooleans();
        fill(opGB, tb3, 2 * (x + 1), 2 * y);

        boolean[] tt = row.top.getValueAsBooleans();
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

    private boolean bwreset = false;
    private Row matchBottom(boolean a1, boolean a2, boolean a3, int i2, int i3) {
        Collections.shuffle(tops);
        for (Top top : tops) {
            //int[] matches = top.matchBlacks(a1, a2, a3, i2, i3);
            int[] matches = top.matchBlacksRnd(a1, a2, a3, i2, i3);
            if (isMatched(matches)) {
                Boolean allBlackOrWhite = print(top, matches);
                if (matches[1] == matches[2]) {
                    break;
                }
                Row row = new Row(top, bottoms.get(matches[0]), bottoms.get(matches[1]), bottoms.get(matches[2]));
                return row;
            }

        }
        return null;
    }

    private Row matchBottomFirst(boolean a1, boolean a2, boolean a3) {
        Collections.shuffle(tops);
        for (Top top : tops) {
            //int[] matches = top.matchBlacks(a1, a2, a3);
            int[] matches = top.matchBlacksRndFirst(a1, a2, a3);
            if (isMatched(matches)) {
                print(top, matches);
                if (matches[1] == matches[2]) {
                    break;
                }
                return new Row(top, bottoms.get(matches[0]), bottoms.get(matches[1]), bottoms.get(matches[2]));
            }

        }
        return null;
    }

    private Boolean print(Top top, int[] matches) {
        System.out.print(top.value + ":");
        String blacks = "";
        for (int m : matches) {
            System.out.print("" + m + ",");
            blacks = blacks + (top.blacks.get(m) ? "B" : "W");
        }
        System.out.print(":" + blacks);
        System.out.println("");
        Boolean ret = null;
        if ("WWW".equals(blacks)) {
            ret = Boolean.FALSE;
        } else if ("WWW".equals(blacks)) {
            ret = Boolean.TRUE;
        }

        return ret;
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
        bottoms = Bottom.setupBlacks();
        tops = Top.setupBlacks(bottoms);

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
