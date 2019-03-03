package com.op.moire.rotatecolor;

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
import java.util.LinkedHashMap;

public class RotateColor extends Base {
    private static final RotateColor fourRotateColor = new RotateColor();
    private String imagesDir = "test";
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
        fourRotateColor.doAll();
    }

    private void doAll() throws IOException {
        setup();
        initImages();
        bottoms = Bottom.createAllFilledBottoms();
        tops = Top.createAllFilledTops(bottoms);
        System.out.println("bottoms:" + bottoms.size() + " tops:" + tops.size());
        int x = (ww / 2) - 1;
        int y = (hh / 2) - 1;

        //testPrint();
        //1,2,3,4
        boolean printAll = true;

        printAllCorners(x, y, 1, 0, 1, 1, 0, 1);

        if (printAll) {
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

//    private void testPrint() {
//        int x = (ww / 2) - 1;
//        int y = (hh / 2) - 1;
//
//        testPrintAllCorners(x, y);
//
//    }

    private void printAllCorners(int x, int y, int w1, int h1, int w2, int h2, int w3, int h3) {
        boolean[] a = {true, false, true, false};
        boolean[] b = {false, false, true, true};
        boolean[] c = {true, true, true, false};
        boolean[] d = {true, false, false, false};

        System.out.println("x,y:" + x + "," + y);
        int x1 = x;
        int y1 = y;

        int x2 = x + w1;
        int y2 = y + h1;

        int x3 = x + w2;
        int y3 = y + h2;

        int x4 = x + w3;
        int y4 = y + h3;

        a[0] = isBlack(ipImage1, x1, y1);
        a[1] = isBlack(ipImage1, x2, y2);
        a[2] = isBlack(ipImage1, x3, y3);
        a[3] = isBlack(ipImage1, x4, y4);

        b[0] = isBlack(ipImage2, x1, y1);
        b[1] = isBlack(ipImage2, x2, y2);
        b[2] = isBlack(ipImage2, x3, y3);
        b[3] = isBlack(ipImage2, x4, y4);

        c[0] = isBlack(ipImage3, x1, y1);
        c[1] = isBlack(ipImage3, x2, y2);
        c[2] = isBlack(ipImage3, x3, y3);
        c[3] = isBlack(ipImage3, x4, y4);

        d[0] = isBlack(ipImage4, x1, y1);
        d[1] = isBlack(ipImage4, x2, y2);
        d[2] = isBlack(ipImage4, x3, y3);
        d[3] = isBlack(ipImage4, x4, y4);

        double v = 1.5;
        ArrayList<LinkedHashMap<Top, ArrayList<Bottom>>> possibleTops = getAllPossibleTopsBots(a, v);

        ArrayList<LinkedHashMap<Top, ArrayList<Bottom>>> filteredTops90 = iterateFilter(b, 1, 90, possibleTops, v);

        ArrayList<LinkedHashMap<Top, ArrayList<Bottom>>> filteredTops180 = iterateFilter(b, 2, 180, filteredTops90, v);

        ArrayList<LinkedHashMap<Top, ArrayList<Bottom>>> filteredTops270 = iterateFilter(b, 3, 270, filteredTops180, v);

        ArrayList<LinkedHashMap<Top, Bottom>> firstMatching = getFirstMatching(filteredTops270);
        printAllTopBot(firstMatching);
        setPixels(opGT, firstMatching.get(0).keySet().iterator().next(), x1, y1);
        setPixels(opGT, firstMatching.get(1).keySet().iterator().next(), x2, y2);
        setPixels(opGT, firstMatching.get(2).keySet().iterator().next(), x3, y3);
        setPixels(opGT, firstMatching.get(3).keySet().iterator().next(), x4, y4);

        setPixels(opGB, firstMatching.get(0).get(firstMatching.get(0).keySet().iterator().next()), x1, y1);
        setPixels(opGB, firstMatching.get(1).get(firstMatching.get(1).keySet().iterator().next()), x2, y2);
        setPixels(opGB, firstMatching.get(2).get(firstMatching.get(2).keySet().iterator().next()), x3, y3);
        setPixels(opGB, firstMatching.get(3).get(firstMatching.get(3).keySet().iterator().next()), x4, y4);
    }

    private ArrayList<LinkedHashMap<Top,ArrayList<Bottom>>> iterateFilter(boolean[] vals, int valOff, int ang,
                                                                          ArrayList<LinkedHashMap<Top, ArrayList<Bottom>>> possibleTops, double v) {
        double vv = v;
        double vd = 0.1;
        ArrayList<LinkedHashMap<Top, ArrayList<Bottom>>> filteredTops = null;
        int size = 0;
        while (size == 0 && vv > 0) {
            size = 0;
            filteredTops = filterAllPossibleTopsBotsFor(vals, valOff, ang, possibleTops, vv);
            Iterator<LinkedHashMap<Top, ArrayList<Bottom>>> it = filteredTops.iterator();
            while (it.hasNext()) {
                LinkedHashMap<Top, ArrayList<Bottom>> map = it.next();
                if (map.keySet().size() ==0 ) {
                    size = 0;
                    break;
                } else {
                    size++;
                }
            }
            vv = vv - vd;
        }
        return filteredTops;
    }

    private void printAllTopBot(ArrayList<LinkedHashMap<Top, Bottom>> firstMatching) {
        System.out.println("");
        int i = 0;
        for (HashMap<Top, Bottom> topBot : firstMatching) {
            Top top = topBot.keySet().iterator().next();
            Bottom bot = topBot.get(top);
            System.out.println("T(" + i + "):" + top.value);
            System.out.println("B(" + i + "):" + bot.value);
            System.out.println("");
            i++;
        }
    }

    private ArrayList<LinkedHashMap<Top, Bottom>> getFirstMatching(ArrayList<LinkedHashMap<Top, ArrayList<Bottom>>> filteredTops270) {
        ArrayList<Bottom> matchedBots = new ArrayList();
        LinkedHashMap<Top, ArrayList<Bottom>> mapPerValue0 = filteredTops270.get(0);
        LinkedHashMap<Top, ArrayList<Bottom>> mapPerValue1 = filteredTops270.get(1);
        LinkedHashMap<Top, ArrayList<Bottom>> mapPerValue2 = filteredTops270.get(2);
        LinkedHashMap<Top, ArrayList<Bottom>> mapPerValue3 = filteredTops270.get(3);
        ArrayList<Bottom> allBots0 = getAllBottoms(mapPerValue0);
        ArrayList<Bottom> allBots1 = getAllBottoms(mapPerValue1);
        ArrayList<Bottom> allBots2 = getAllBottoms(mapPerValue2);
        ArrayList<Bottom> allBots3 = getAllBottoms(mapPerValue3);

        allBots0.addAll(allBots1);
        allBots0.addAll(allBots2);
        allBots0.addAll(allBots3);

        ArrayList<LinkedHashMap<Top, Bottom>> finished = new ArrayList<>();
        int i = 0;
        for (LinkedHashMap<Top, ArrayList<Bottom>> mapPerValue : filteredTops270) {
            if (!mapPerValue.entrySet().isEmpty()) {
                Bottom chosenBot = null;
                for (Top top : mapPerValue.keySet()) {
                    for (int j = 0; j < allBots0.size(); j++) {
                        if (mapPerValue.get(top).contains(allBots0.get(j))) {
                            chosenBot = allBots0.get(j);
                            break;
                        }
                    }
                    LinkedHashMap<Top, Bottom> map = new LinkedHashMap<>();
                    map.put(top, chosenBot);
                    finished.add(map);
                    break;
                }

            }
            i++;
        }

        return finished;
    }

    private ArrayList<Bottom> addAllBots(ArrayList<Bottom> allBots0, ArrayList<Bottom> allBots1, ArrayList<Bottom> allBots2, ArrayList<Bottom> allBots3) {
        ArrayList<Bottom> all = new ArrayList<>();
        for (Bottom bot : allBots0) {
            if (!all.contains(bot)) {
                all.add(bot);
            }
        }

        return all;
    }

    private ArrayList<Bottom> getAllBottoms(HashMap<Top, ArrayList<Bottom>> mapPerValue) {
        ArrayList<Bottom> bots = new ArrayList<>();
        for (Top top : mapPerValue.keySet()) {
            for (Bottom bot : mapPerValue.get(top)) {
                if (!bots.contains(bot)) {
                    bots.add(bot);
                }
            }
        }
        return bots;
    }

    private ArrayList<LinkedHashMap<Top, ArrayList<Bottom>>> filterAllPossibleTopsBotsFor(boolean[] cornerValues, int valOffset,
                                                                                          double ang, ArrayList<LinkedHashMap<Top, ArrayList<Bottom>>> possibleTops, double blackNum) {
        ArrayList<LinkedHashMap<Top, ArrayList<Bottom>>> filteredCorners = new ArrayList();
        filteredCorners.add(new LinkedHashMap<>());
        filteredCorners.add(new LinkedHashMap<>());
        filteredCorners.add(new LinkedHashMap<>());
        filteredCorners.add(new LinkedHashMap<>());
        int valInd = 0;
        for (boolean value : cornerValues) {
            LinkedHashMap<Top, ArrayList<Bottom>> newCombs = new LinkedHashMap<>();
            int ind = (valInd + (4 - valOffset)) % 4;
            LinkedHashMap<Top, ArrayList<Bottom>> combs = possibleTops.get(ind);
            for (Top top : combs.keySet()) {
                for (Bottom bot : combs.get(top)) {
                    Top rot = top.rotate(ang);
                    if (rot.hasCorrectBottomCombination(bot, value, blackNum)) {
                        if (newCombs.get(top) == null) {
                            newCombs.put(top, new ArrayList());
                        }
                        if (!newCombs.get(top).contains(bot)) {
                            newCombs.get(top).add(bot);
                        }
                    }
                }
            }

            filteredCorners.remove(ind);
            filteredCorners.add(ind, newCombs);
            valInd++;
        }

        return filteredCorners;

    }

    private ArrayList<LinkedHashMap<Top, ArrayList<Bottom>>> getAllPossibleTopsBots(boolean[] values, double blackNum) {
        ArrayList<LinkedHashMap<Top, ArrayList<Bottom>>> possibleCorners = new ArrayList();
        for (boolean value : values) {
            LinkedHashMap<Top, ArrayList<Bottom>> combs = new LinkedHashMap<>();
            for (Top top : tops) {
                for (Bottom bot : bottoms) {
                    if (top.hasCorrectBottomCombination(bot, value, 3)) {
                        if (combs.get(top) == null) {
                            combs.put(top, new ArrayList());
                        }
                        if (!combs.get(top).contains(bot)) {
                            combs.get(top).add(bot);
                        }
                    }
                }
            }

            possibleCorners.add(combs);
        }

        return possibleCorners;
    }

    private void setPixels(Graphics2D opGT, Pixel pixel, int x, int y) {
        float avgGrey = (float) ((getGrey(ipImage1, x, y) + getGrey(ipImage2, x, y) + getGrey(ipImage3, x, y) + getGrey(ipImage4, x, y)) / 4.0);
        fill(opGT, pixel.value, pixelWidth * x, pixelWidth * y, 0);
    }

    private void fill(Graphics2D opG, String value, int xOff, int yOff, float greyValue) {
        int c = 0;
        float ff = 0.5f;

        opG.setColor(new Color(0f, 0f, 0f, 1 - (ff * greyValue)));
        int alpha = 127;
        for (int y = 0; y < pixelWidth; y++) {
            for (int x = 0; x < pixelWidth; x++) {
                Color col = Color.WHITE;
                String val = value.substring(c, c + 1);
                if (val.equals("C")) {
                    col = Color.CYAN;
                } else if (val.equals("M")) {
                    col = Color.MAGENTA;
                } else if (val.equals("Y")) {
                    col = Color.YELLOW;
                } else if (val.equals("W")) {
                    col = Color.WHITE;
                } else if (val.equals("B")) {
                    col = Color.BLACK;
                } else {
                    throw new RuntimeException("CMYWB");
                }
                Color newCol = new Color(col.getRed(), col.getGreen(), col.getBlue(), alpha);
                opG.setColor(newCol);
                opG.fillRect(xOff + x, yOff + y, 1, 1);
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
