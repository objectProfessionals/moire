package com.op.moire.rotate;

import com.op.moire.Base;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Rotate extends Base {
    private static final Rotate fourRotate = new Rotate();
    private String imagesDir = "test3";
    private String imagesName = "test";
    private String dir = hostDir + "fourRotate/" + imagesDir + "/";
    private String ip1src = imagesName + "1.png";
    private String ip2src = imagesName + "2.png";
    private String ip3src = imagesName + "3.png";
    private String ip4src = imagesName + "4.png";
    private String opBsrc = imagesName + "B.png";
    private String opTsrc = imagesName + "T.png";
    private String opBTsrc = imagesName + "BT.png";
    private boolean saveOnOneImage = false;

    double dpi = 100;
    int ww = -1;
    int hh = -1;
    int www = -1;
    int hhh = -1;
    int border = -1;
    int pixelWidth = -1;

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

    public static void main(String[] args) throws Exception {
        fourRotate.doAll();
    }

    private void doAll() throws IOException {
        setup();
        initImages();
        bottoms = Bottom.createAllFilledBottoms();
        tops = Top.createAllFilledTops(bottoms);
        System.out.println("bottoms:" + bottoms.size() + " tops:" + tops.size());
        int x = (ww / 2) - 1;
        int y = (hh / 2) - 1;

        //1,2,3,4
        printAllCorners(x, y, 1, 0, 1, 1, 0, 1);

        for (int j = 3; j < ww; j = j + 2) {
            int mid = (int) (j / 2);
            for (int i = 0; i < j; i++) {
                int x1 = x + i - mid;
                int y1 = y - (int) (j / 2);
                int w1 = j - i;
                int h1 = i;
                int w2 = j - (2 * i);
                int h2 = j;
                int w3 = -i;
                int h3 = j - i;
                printAllCorners(x1, y1, w1, h1, w2, h2, w3, h3);
            }
        }

        //5,6,7,(5)
//        printAllCorners(x - 1, y - 1, 3, 0, 3, 3, 0, 3);
//        printAllCorners(x, y - 1, 2, 1, 1, 3, -1, 2);
//        printAllCorners(x + 1, y - 1, 1, 2, -1, 3, -2, 1);

        //8,9,10,11,12,(8)
//        printAllCorners(x - 2, y - 2, 5, 0, 5, 5, 0, 5);
//        printAllCorners(x - 1, y - 2, 4, 1, 3, 5, -1, 4);
//        printAllCorners(   x,     y - 2, 3, 2, 1, 5, -2, 3);
//        printAllCorners(x + 1, y - 2, 2, 3, -1, 5, -3, 2);
//        printAllCorners(x + 2, y - 2, 1, 4, -3, 5, -4, 1);

        //8,9,10,11,12,(8)
//        printAllCorners(x - 3, y - 3, 7, 0, 7, 7, 0, 7);
//        printAllCorners(x - 2, y - 3, 6, 1, 5, 7, -1, 6);
//        printAllCorners(x - 1, y - 3, 5, 2, 3, 7, -2, 5);
//        printAllCorners(x - 0, y - 3, 4, 3, 1, 7, -3, 4);
//        printAllCorners(x + 1, y - 3, 3, 4, -1, 7, -4, 3);
//        printAllCorners(x + 2, y - 3, 2, 5, -3, 7, -5, 2);
//        printAllCorners(x + 3, y - 3, 1, 6, -5, 7, -6, 1);

        saveImages();
    }

    private void printAllCorners(int x, int y, int w1, int h1, int w2, int h2, int w3, int h3) {
        int x1 = x;
        int y1 = y;

        int x2 = x + w1;
        int y2 = y + h1;

        int x3 = x + w2;
        int y3 = y + h2;

        int x4 = x + w3;
        int y4 = y + h3;


        ArrayList<Top> finalTops = new ArrayList();
        ArrayList<Bottom> finalBots = new ArrayList();

        finalTops = new ArrayList();
        finalBots = new ArrayList();

        Corner[][] corners = getCorners(x1, y1, x2, y2, x3, y3, x4, y4);
        System.out.println("corners found:" + x1 + ":" + y1);

        for (int j = 0; j < 4; j++) {

            int k = (j + 3) % 4;
            int l = (j + 2) % 4;
            int m = (j + 1) % 4;
            Corner cornA = corners[0][j];
            Corner cornB = corners[1][k];
            Corner cornC = corners[2][l];
            Corner cornD = corners[3][m];
            filterMathingTopBotsToCornerA(cornA, cornB, cornC, cornD);

            HashMap<Top, ArrayList<Bottom>> top2BotsForA = corners[0][j].top2Bots;
            Iterator<Top> it = top2BotsForA.keySet().iterator();
            if (top2BotsForA.isEmpty()) {
                System.out.println("none found");
            }
            boolean tryAgain = true;
            int i = 0;
            while (tryAgain) {
                //Top topA = getRandom(it);
                Top topA = it.next();
                ArrayList<Bottom> allBots = getBottoms(top2BotsForA, topA);
                Bottom botA = allBots.get(allBots.size() - 1);
                finalTops.add(topA);
                finalBots.add(botA);
                tryAgain = false;
            }
        }

        setPixels(opGT, finalTops.get(0), x1, y1);
        setPixels(opGB, finalBots.get(0), x1, y1);
        setPixels(opGT, finalTops.get(1), x2, y2);
        setPixels(opGB, finalBots.get(1), x2, y2);
        setPixels(opGT, finalTops.get(2), x3, y3);
        setPixels(opGB, finalBots.get(2), x3, y3);
        setPixels(opGT, finalTops.get(3), x4, y4);
        setPixels(opGB, finalBots.get(3), x4, y4);

    }

    private void filterMathingTopBotsToCornerA(Corner cornerA, Corner cornerB, Corner cornerC, Corner cornerD) {

        cornerA.filterMatchingTopBots(cornerB, 90, -1);
        cornerA.filterMatchingTopBots(cornerC, 180, -2);
        cornerA.filterMatchingTopBots(cornerD, 270, -3);
    }

    private ArrayList<Bottom> getBottoms(HashMap<Top, ArrayList<Bottom>> top2BotsForA, Top topA) {
        return top2BotsForA.get(topA);
    }

    private Top getRandom(Iterator<Top> it) {
        ArrayList<Top> allTops = new ArrayList<>();
        while (it.hasNext()) {
            allTops.add(it.next());
        }

        int i = (int) (Math.random() * allTops.size());
        return allTops.get(i);
    }

    private Corner[][] getCorners(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        Boolean[] blacksNeededA = getBlacks(ipImage1, x1, y1, x2, y2, x3, y3, x4, y4);
        Boolean[] blacksNeededB = getBlacks(ipImage2, x1, y1, x2, y2, x3, y3, x4, y4);
        Boolean[] blacksNeededC = getBlacks(ipImage3, x1, y1, x2, y2, x3, y3, x4, y4);
        Boolean[] blacksNeededD = getBlacks(ipImage4, x1, y1, x2, y2, x3, y3, x4, y4);

        Corner[][] corners = getCornersFromBlacks(blacksNeededA, blacksNeededB, blacksNeededC, blacksNeededD);
        return corners;
    }

    private Corner[][] getCornersFromBlacks(Boolean[] blacksNeededA, Boolean[] blacksNeededB, Boolean[] blacksNeededC, Boolean[] blacksNeededD) {
        Corner[] cornersA = initNewCorners();
        Corner[] cornersB = initNewCorners();
        Corner[] cornersC = initNewCorners();
        Corner[] cornersD = initNewCorners();


        for (Top top : tops) {
            int b = 0;
            for (Bottom bot : bottoms) {
                matchTopBots(blacksNeededA, cornersA, top, bot, b, 0);
                matchTopBots(blacksNeededB, cornersB, top, bot, b, -90);
                matchTopBots(blacksNeededC, cornersC, top, bot, b, -180);
                matchTopBots(blacksNeededD, cornersD, top, bot, b,-270);
                b++;
            }
        }
        Corner[][] corners = {cornersA, cornersB, cornersC, cornersD};

        return corners;
    }

    private void matchTopBots(Boolean[] blacksNeeded, Corner[] corners, Top top,
                              Bottom bot, int b, double rot) {

        ArrayList<Bottom> matchedBots0 = new ArrayList<>();
        ArrayList<Bottom> matchedBots1 = new ArrayList<>();
        ArrayList<Bottom> matchedBots2 = new ArrayList<>();
        ArrayList<Bottom> matchedBots3 = new ArrayList<>();

        matchTopBots(blacksNeeded[0], corners[0], top, matchedBots0, b, bot, rot);
        matchTopBots(blacksNeeded[1], corners[1], top, matchedBots1, b, bot, rot);
        matchTopBots(blacksNeeded[2], corners[2], top, matchedBots2, b, bot, rot);
        matchTopBots(blacksNeeded[3], corners[3], top, matchedBots3, b, bot, rot);
    }

    private Corner[] initNewCorners() {
        HashMap<Top, ArrayList<Bottom>> topBotsA0 = new HashMap<>();
        Corner cornerA0 = new Corner(topBotsA0);
        HashMap<Top, ArrayList<Bottom>> topBotsA1 = new HashMap<>();
        Corner cornerA1 = new Corner(topBotsA1);
        HashMap<Top, ArrayList<Bottom>> topBotsA2 = new HashMap<>();
        Corner cornerA2 = new Corner(topBotsA2);
        HashMap<Top, ArrayList<Bottom>> topBotsA3 = new HashMap<>();
        Corner cornerA3 = new Corner(topBotsA3);

        Corner[] corners = {cornerA0, cornerA1, cornerA2, cornerA3};

        return corners;
    }

    private Boolean[] getBlacks(BufferedImage image, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        Boolean[] blacksNeeded = {false, false, false, false};
        blacksNeeded[0] = isBlack(image, x1, y1);
        blacksNeeded[1] = isBlack(image, x2, y2);
        blacksNeeded[2] = isBlack(image, x3, y3);
        blacksNeeded[3] = isBlack(image, x4, y4);
        return blacksNeeded;
    }

    private void matchTopBots(Boolean val, Corner corner, Top top, ArrayList<Bottom> matchedBots, int b, Bottom bot, double rot) {
        //boolean match = top.blacks.get(b).equals(val);
        boolean match = top.isMatchWithBottomBlackOrWhite(bot, val);
        if (match) {
            if (getBottoms(corner.top2Bots, top) == null) {
                corner.top2Bots.put(top, matchedBots);
            }
            matchedBots.add(bot);
        }
    }


    private void setPixels(Graphics2D opGT, Pixel pixel, int x, int y) {
        boolean[] tt = pixel.getValueAsBooleans();
        float avgGrey = (float) ((getGrey(ipImage1, x, y) + getGrey(ipImage2, x, y) + getGrey(ipImage3, x, y) + getGrey(ipImage4, x, y)) / 4.0);
        fill(opGT, tt, pixelWidth * x, pixelWidth * y, 0);
    }

    private void fill(Graphics2D opG, boolean[] tb, int xOff, int yOff, float greyValue) {
        int c = 0;
        float ff = 0.5f;

        opG.setColor(new Color(0f, 0f, 0f, 1 - (ff * greyValue)));
        for (int y = 0; y < pixelWidth; y++) {
            for (int x = 0; x < pixelWidth; x++) {
                if (tb[c]) {
                    opG.fillRect(xOff + x, yOff + y, 1, 1);
                }
                c++;
            }
        }
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
        ww = ipImage1.getWidth();
        hh = ipImage1.getHeight();
        www = ww * pixelWidth;
        hhh = hh * pixelWidth;

        border = (int) ((double) (www) * 0.05);
        border = 0;

        double tot = www + 2 * border;

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
        pixelWidth = (int) Math.sqrt(Pixel.NUM_PIXELS);
    }

    private void saveAsOneImage() {
        BufferedImage opImageC = createAlphaBufferedImage(1 + 2 * (www + 2 * border), hhh + 2 * border);
        Graphics2D opG = (Graphics2D) (opImageC.getGraphics());
        opG.drawImage(opImageB, null, null);
        opG.setColor(Color.RED);
        opG.fillRect((www + 2 * border), 0, 1, hhh + 2 * border);
        opG.drawImage(opImageT, AffineTransform.getTranslateInstance(1 + www + 2 * border, 0), null);

        savePNGFile(opImageC, dir + opBTsrc, dpi);
    }

    private void saveAsTwoImages() {
        savePNGFile(opImageB, dir + opBsrc, dpi);
        savePNGFile(opImageT, dir + opTsrc, dpi);
    }

}
