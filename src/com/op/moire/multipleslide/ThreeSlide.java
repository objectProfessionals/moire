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
    private String imagesName = "facesB";
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
    private boolean doShuffle = false;

    double dpi = 5 * 25.4;
    int ww = -1;
    int hh = -1;
    int www = -1;
    int hhh = -1;
    int border = -1;
    int pixelWidth = -1;

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

    private Row writeDiagonal(int x, int y, Row lastRow) {
        boolean a1 = isBlack(ipImage1, x, y);
        boolean a2 = isBlack(ipImage2, x, y);
        boolean a3 = isBlack(ipImage3, x, y);

        Row row = matchBottom(a1, a2, a3, bottoms.indexOf(lastRow.botn2), bottoms.indexOf(lastRow.botn3));
        if (row != null) {
            setPixels(row, x, y);
        } else {
            System.out.println("row is null at x,y:"+x+ ","+y);
        }
        return row;
    }

    private Row writeDiagonalFirst(int x, int y) {
        boolean a1 = isBlack(ipImage1, x, y);
        boolean a2 = isBlack(ipImage2, x, y);
        boolean a3 = isBlack(ipImage3, x, y);

        Row row = matchBottomFirst(a1, a2, a3);
        if (row != null) {
            setPixelsFirst(row, x, y);

        }
        return row;
    }

    private void setPixelsFirst(Row row, int x, int y) {
        float avgGrey = (float) ((getGrey(ipImage1, x, y) + getGrey(ipImage2, x, y) + getGrey(ipImage3, x, y)) / 3.0);

        boolean[] tb = row.botn1.getValueAsBooleans();
        fill(opGB, tb, pixelWidth * x, pixelWidth * y, avgGrey);

        boolean[] tb2 = row.botn2.getValueAsBooleans();
        fill(opGB, tb2, pixelWidth * (x + 1), pixelWidth * y, avgGrey);

        boolean[] tt = row.top.getValueAsBooleans();
        fill(opGT, tt, pixelWidth * x, pixelWidth * y, avgGrey);

    }

    private void setPixels(Row row, int x, int y) {
        float avgGrey = (float) ((getGrey(ipImage1, x, y) + getGrey(ipImage2, x, y) + getGrey(ipImage3, x, y)) / 3.0);

        boolean[] tb3 = row.botn2.getValueAsBooleans();
        fill(opGB, tb3, pixelWidth * (x + 1), pixelWidth * y, avgGrey);

        boolean[] tt = row.top.getValueAsBooleans();
        fill(opGT, tt, pixelWidth * x, pixelWidth * y, avgGrey);

    }

    private void fill(Graphics2D opG, boolean[] tb, int xOff, int yOff, float greyValue) {
        int c = 0;
        float ff = 0.25f;
        opG.setColor(new Color(0f, 0f, 0f, 1 - (ff * greyValue)));
        for (int y = 0; y < pixelWidth; y++) {
            for (int x = 0; x < pixelWidth; x++) {
                if (tb[c]) {
                    opG.fillRect(xOff + x + border, yOff + y + border, 1, 1);
                }
                c++;
            }
        }
    }

    private Row matchBottom(boolean a1, boolean a2, boolean a3, int i2, int i3) {
        shuffle(tops);
        System.out.println("a1,a2,a3 i2,i3="+a1+","+a2+","+a3+","+i2+","+i3);
        for (Top top : tops) {
            //int[] matches = top.matchBlacks(a1, a2, a3, i2, i3);
            int[] matches = top.matchBlacksRnd(a1, a2, a3, i2, i3);
            if (isMatched(matches)) {
                Boolean allBlackOrWhite = printMatched(top, matches);
                if (matches[1] == matches[2]) {
                    break;
                }
                Row row = new Row(top, bottoms.get(matches[0]), bottoms.get(matches[1]), bottoms.get(matches[2]));
                return row;
            }

        }
        return null; //matchBottom(a1, a2, a3, i2, i3);
    }

    private Row matchBottomFirst(boolean a1, boolean a2, boolean a3) {
        shuffle(tops);
        for (Top top : tops) {
            //int[] matches = top.matchBlacks(a1, a2, a3);
            int[] matches = top.matchBlacksRndFirst(a1, a2, a3);
            if (isMatched(matches)) {
                printMatched(top, matches);
                if (matches[1] == matches[2]) {
                    break;
                }
                return new Row(top, bottoms.get(matches[0]), bottoms.get(matches[1]), bottoms.get(matches[2]));
            }

        }
        return null;
    }

    private void shuffle(ArrayList<Top> tops) {
        if (doShuffle) {
            Collections.shuffle(tops);
        }
    }

    private Boolean printMatched(Top top, int[] matches) {
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
        www = ww * pixelWidth;
        hhh = hh * pixelWidth;

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
        pixelWidth = (int) Math.sqrt(Pixel.NUM_PIXELS);
    }

    private boolean isBlack(BufferedImage image, int x, int y) {
        return getGrey(image, x, y) < 0.5;
        //return -1 != image.getRGB(x, y);
    }

    private double getGrey(BufferedImage image, int x, int y) {
        Color col = new Color(image.getRGB(x, y));
        return (col.getRed() + col.getGreen() + col.getBlue()) / (255.0 * 3.0);
    }

    private void saveImages() {
        savePNGFile(opImageB, dir + opBsrc, dpi);
        savePNGFile(opImageT, dir + opTsrc, dpi);
    }

    private void initMarkers() {
        opGB.setColor(Color.RED);
        opGB.fillRect(border, border, pixelWidth, pixelWidth);
        opGB.fillRect((www + border) / 2, border, pixelWidth, pixelWidth);
        opGB.fillRect(www + border - pixelWidth, border, pixelWidth, pixelWidth);
        opGB.fillRect(border, hhh + border - pixelWidth, pixelWidth, pixelWidth);
        opGB.fillRect((www + border) / 2, hhh + border - pixelWidth, pixelWidth, pixelWidth);
        opGB.fillRect(www + border - pixelWidth, hhh + border - pixelWidth, pixelWidth, pixelWidth);

        opGT.setColor(Color.RED);
        opGT.fillRect(border, border, pixelWidth, pixelWidth);
        opGT.fillRect(((www + border) / 2) - pixelWidth, border, pixelWidth, pixelWidth);
        opGT.fillRect(www + border - (3 * pixelWidth), border, pixelWidth, pixelWidth);
        opGT.fillRect(border, hhh + border - pixelWidth, pixelWidth, pixelWidth);
        opGT.fillRect(((www + border) / 2) - pixelWidth, hhh + border - pixelWidth, pixelWidth, pixelWidth);
        opGT.fillRect(www + border - (3 * pixelWidth), hhh + border - pixelWidth, pixelWidth, pixelWidth);
    }

}
