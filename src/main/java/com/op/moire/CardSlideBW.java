package com.op.moire;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class CardSlideBW extends Base {

    private static final String ipFileName1 = "ccMessage1";
    private static final String ipFileName2 = "ccMessage2";
    // private static final String ipFileName1 = "sanjayBW";
    // private static final String ipFileName2 = "virginijaBW";
    private static final String opBottom = "slideBottomBW";
    private static final String opTop = "slideTopBW";
    private static final String dir = "misc/cards/";
    private static final String outFileExt = ".png";

    private int subpixels = 2;
    private int xOffFractionOf = 5;
    private int xOff = -1;
    private int width = -1;
    private int height = -1;
    private int ww = -1;
    private int hh = -1;
    private boolean addBorder = false;
    private int border = -1;
    private double dpi = 101.6;
    private double mm2in = 25.4;
    private HashMap<String, Color> map;

    private BufferedImage opImageBottom;
    private Graphics2D opGBottom;
    private BufferedImage opImageTop;
    private Graphics2D opGTop;

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        CardSlideBW cp = new CardSlideBW();
        cp.setupDimensions();
        cp.setupOutputImages();
        cp.draw();
        cp.saveImages();
    }

    private void setupDimensions() throws IOException {
        String src = hostDir + dir;
        BufferedImage bi1 = ImageIO.read(new File(src + ipFileName1
                + outFileExt));
        width = bi1.getWidth();
        height = bi1.getHeight();
        xOff = width / xOffFractionOf;
        ww = (width * subpixels + subpixels * xOff);
        hh = height * subpixels;

        double bmm = 20;
        border = (int) (dpi * bmm / mm2in);
    }

    private void draw() throws IOException {
        String src = hostDir + dir;
        BufferedImage bi1 = ImageIO.read(new File(src + ipFileName1
                + outFileExt));
        BufferedImage bi2 = ImageIO.read(new File(src + ipFileName2
                + outFileExt));

        ArrayList<ArrayList<Color>> lastBotColumn = new ArrayList<ArrayList<Color>>();
        ArrayList<ArrayList<Color>> nextBotColumn = new ArrayList<ArrayList<Color>>();
        for (int x = 0; x < width + xOff; x++) {
            for (int y = 0; y < height; y++) {
                if (x < xOff) {
                    ArrayList<Color> white = createRandomWhites();
                    int rgb1 = bi1.getRGB(x, y);
                    boolean firstBlack = rgb1 != -1;
                    paintSubpixel(opGBottom, x, y, white);
                    ArrayList<Color> invColors = getInverted(firstBlack, white);
                    paintSubpixel(opGTop, x, y, invColors);
                    lastBotColumn.add(white);
                } else {
                    int rgb1 = x < width ? bi1.getRGB(x, y) : Color.WHITE
                            .getRGB();
                    int rgb2 = bi2.getRGB(x - xOff, y);
                    boolean firstBlack = rgb1 != -1;
                    boolean secBlack = rgb2 != -1;
                    int yy = y + (height * (x % (xOff)));
                    ArrayList<Color> lastBotColumnPix = lastBotColumn.get(yy);
                    ArrayList<Color> invPix = getInverted(secBlack,
                            lastBotColumnPix);
                    paintSubpixel(opGTop, x, y, invPix);
                    ArrayList<Color> invPixBot = getInverted(firstBlack, invPix);
                    if (x < width) {
                        paintSubpixel(opGBottom, x, y, invPixBot);
                        nextBotColumn.add(invPixBot);
                    } else {
                        paintSubpixel(opGBottom, x, y, invPix);
                    }
                }
            }
            if (x >= xOff && x % xOff == (xOff - 1)) {
                lastBotColumn = copyColors(nextBotColumn);
                nextBotColumn = new ArrayList<ArrayList<Color>>();
            }
        }
    }

    private ArrayList<Color> createWhites() {
        ArrayList<Color> white = new ArrayList<Color>();
        boolean isWhite = false;
        for (int y = 0; y < subpixels; y++) {
            for (int x = 0; x < subpixels; x++) {
                white.add(isWhite ? Color.WHITE : Color.BLACK);
                isWhite = !isWhite;
            }
            isWhite = !isWhite;
        }
        return white;
    }

    private ArrayList<Color> createRandomWhites() {
        ArrayList<Color> white = new ArrayList<Color>();
        for (int y = 0; y < subpixels; y++) {
            for (int x = 0; x < subpixels; x++) {
                white.add(Color.BLACK);
            }
        }
        int numWhites = 0;
        while (numWhites < subpixels * subpixels / 2) {
            int rnd = (int) (Math.random() * 2);
            if (rnd == 1) {
                int rndPos = (int) (Math.random() * subpixels * subpixels);

                int i = 0;
                for (int y = 0; y < subpixels; y++) {
                    for (int x = 0; x < subpixels; x++) {
                        if (i == rndPos) {
                            if (white.get(i).equals((Color.BLACK))) {
                                white.remove(i);
                                white.add(i, Color.WHITE);
                                numWhites++;
                            }
                        }
                        i++;
                    }
                }

            }
        }
        return white;
    }

    private ArrayList<ArrayList<Color>> copyColors(
            ArrayList<ArrayList<Color>> nextBotColumn) {
        ArrayList<ArrayList<Color>> copied = new ArrayList<ArrayList<Color>>();
        for (ArrayList<Color> pix : nextBotColumn) {
            copied.add(copyPixel(pix));
        }
        return copied;
    }

    private ArrayList<Color> copyPixel(ArrayList<Color> pix) {
        ArrayList<Color> copied = new ArrayList<Color>();
        for (Color col : pix) {
            copied.add(col.equals(Color.BLACK) ? Color.BLACK : Color.WHITE);
        }
        return copied;
    }

    private ArrayList<Color> getInverted(boolean firstBlack,
                                         ArrayList<Color> colors) {
        ArrayList<Color> inv = new ArrayList<Color>();
        for (Color col : colors) {
            Color inverse = col.equals(Color.WHITE) ? Color.BLACK : Color.WHITE;
            inv.add(firstBlack ? inverse : col);
        }
        return inv;
    }

    private void paintSubpixel(Graphics2D opG, int x, int y,
                               ArrayList<Color> colors) {
        for (int j = 0; j < subpixels; j++) {
            for (int i = 0; i < subpixels; i++) {
                int ind = (subpixels * j) + i;
                Color color = colors.get(ind);
                opG.setColor(color);
                int xx = (subpixels * x) + i;
                int yy = (subpixels * y) + j;
                opG.fillRect(xx, yy, 1, 1);
            }
        }
    }

    protected void setupOutputImages() throws IOException {
        System.out.println("Creating...");
        map = new HashMap<String, Color>();
        map.put("w", Color.WHITE);
        map.put("l", Color.BLACK);

        opImageBottom = createBufferedImage(ww, hh);
        opGBottom = (Graphics2D) opImageBottom.getGraphics();
        opGBottom.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        opGBottom.setColor(Color.WHITE);
        opGBottom.fillRect(0, 0, ww, hh);

        opImageTop = createBufferedImage(ww, hh);
        opGTop = (Graphics2D) opImageTop.getGraphics();
        opGBottom.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        opGTop.setColor(Color.WHITE);
        opGTop.fillRect(0, 0, ww, hh);
    }

    protected void saveImages() throws Exception {
        saveImage(opBottom, opImageBottom, opGBottom);
        saveImage(opTop, opImageTop, opGTop);
    }

    protected void saveImage(String op, BufferedImage opImage, Graphics2D opG)
            throws Exception {
        BufferedImage opImage3 = opImage;
        if (addBorder) {
            int www = ww + border;
            int hhh = hh + border;
            BufferedImage opImage2 = createBufferedImage(www, hhh);
            Graphics2D opG2 = (Graphics2D) opImage2.getGraphics();
            opG2.setColor(Color.WHITE);
            opG2.fillRect(0, 0, www, hhh);
            opG2.drawImage(opImage, null, border, border);
            opImage3 = opImage2;
        }

        System.out.println("Saving...");
        String src = hostDir + dir;

        if (outFileExt.equals(".png")) {
            savePNGFile(opImage3, src + op + outFileExt, dpi);
        } else {
            saveJPGFile(opImage3, src + op + outFileExt, dpi, 1);
        }
        opG.dispose();
    }

    private void printFileInfo(File fFile1) {
        Date now = new Date();
        System.out.println("Saved " + fFile1.getPath() + " @" + now);
    }
}
