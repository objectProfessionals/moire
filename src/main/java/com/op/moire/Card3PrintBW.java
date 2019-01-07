package com.op.moire;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Card3PrintBW extends Base {

    private static final String ipFileName1 = "sanjay1";
    private static final String ipFileName2 = "virginija1";
    private static final String ipFileName3 = "love1";
    private static final String opFileName = "cardBW";
    private static final String dir = "misc/cards/";
    private static final String outFileExt = ".png";

    private int sub = 2;
    private int width = 80;
    private int height = 50;
    private int ww = width * sub;
    private int hh = height * sub;
    private boolean addBorder = false;
    private double border = 20;
    private double dpi = 20.32;
    private HashMap<String, Color> map;

    private BufferedImage opImage1;
    private Graphics2D opG1;
    private BufferedImage opImage2;
    private Graphics2D opG2;

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Card3PrintBW cp = new Card3PrintBW();
        cp.setupOutputImages();
        cp.draw();
        cp.saveImages();
    }

    private void draw() throws IOException {
        String src = hostDir + dir;
        BufferedImage bi1 = ImageIO.read(new File(src + ipFileName1
                + outFileExt));
        BufferedImage bi2 = ImageIO.read(new File(src + ipFileName2
                + outFileExt));
        BufferedImage bi3 = ImageIO.read(new File(src + ipFileName3
                + outFileExt));

        for (int yy = 0; yy < height; yy++) {
            for (int xx = 0; xx < width; xx++) {
                int rgb1 = bi1.getRGB(xx, yy);
                int rgb2 = bi2.getRGB(xx, yy);
                int rgb3 = bi3.getRGB(xx, yy);

                boolean firstBlack = rgb1 != -1;
                boolean secBlack = rgb2 != -1;
                boolean hidBlack = rgb3 != -1;
                // hidden is black
                if (hidBlack) {
                    if (firstBlack && secBlack) {
                        paintSquare(opG1, xx, yy, 3, 3, true);
                        paintSquare(opG2, xx, yy, "wrrg");
                    } else if (firstBlack && !secBlack) {
                        paintSquare(opG1, xx, yy, "rbbw");
                        paintSquare(opG2, xx, yy, "wrwb");
                    } else if (!firstBlack && secBlack) {
                        paintSquare(opG1, xx, yy, "wrwb");
                        paintSquare(opG2, xx, yy, "rbbw");
                    } else if (!firstBlack && !secBlack) {
                        paintSquare(opG1, xx, yy, "gwrw");
                        paintSquare(opG2, xx, yy, "rwgw");
                    }
                } else {
                    if (firstBlack && secBlack) {
                        paintSquare(opG1, xx, yy, "bggw");
                        paintSquare(opG2, xx, yy, "bggw");
                    } else if (firstBlack && !secBlack) {
                        paintSquare(opG1, xx, yy, "bggw");
                        paintSquare(opG2, xx, yy, "wggw");
                    } else if (!firstBlack && secBlack) {
                        paintSquare(opG1, xx, yy, "wggw");
                        paintSquare(opG2, xx, yy, "bggw");
                    } else if (!firstBlack && !secBlack) {
                        paintSquare(opG1, xx, yy, "wrwg");
                        paintSquare(opG2, xx, yy, "wrwg");
                    }
                }
            }
        }
    }

    private void paintSquare(Graphics2D opG, int x, int y, int sub1, int sub2,
                             boolean blacken) {
        ArrayList<Color> bl1 = getRandomBlacks(sub1);
    }

    private ArrayList<Color> getRandomBlacks(int sub1) {
        ArrayList<Color> cols = new ArrayList<Color>();
        for (int i = 0; i < (sub * sub); i++) {
            cols.add(Color.WHITE);
        }
        int blackened = 0;
        for (int x = 0; x < sub; x = x + 1) {
            for (int y = 0; y < sub; y++) {
                while (blackened < sub1) {
                    int rnd = (int) (Math.random() * (sub * sub));
                    if (!cols.get(rnd).equals(Color.BLACK)) {
                        cols.remove(rnd);
                        cols.add(rnd, Color.BLACK);
                        blackened++;
                    }
                }
            }
        }
        return cols;
    }

    private void paintSquare(Graphics2D opG, int x, int y, String cols) {
        ArrayList<Color> all = new ArrayList<Color>();
        for (int i = 0; i < cols.length(); i++) {
            String col = cols.substring(i, i + 1);
            all.add(map.get(col));
        }
        paintSquare(opG, x, y, all);

    }

    private void paintSquare(Graphics2D opG, int x, int y, ArrayList<Color> cols) {
        int c = 0;
        for (int j = 0; j < sub; j++) {
            for (int i = 0; i < sub; i++) {
                paintPixel(opG, x, y, cols.get(c), i, j);
                c++;
            }
        }
    }

    private void paintSquare(Graphics2D opG, int x, int y, Color c1, Color c2,
                             Color c3, Color c4) {
        paintPixel(opG, x, y, c1, 0, 0);
        paintPixel(opG, x, y, c2, 1, 0);
        paintPixel(opG, x, y, c3, 0, 1);
        paintPixel(opG, x, y, c4, 1, 1);
    }

    private void paintPixel(Graphics2D opG, int x, int y, Color c, int offX,
                            int offY) {
        opG.setColor(c);
        opG.fillRect(x * sub + offX, y * sub + offY, 1, 1);
    }

    protected void setupOutputImages() throws IOException {
        System.out.println("Creating...");
        map = new HashMap<String, Color>();
        map.put("w", Color.WHITE);
        map.put("l", Color.BLACK);

        opImage1 = createBufferedImage(ww, hh);
        opG1 = (Graphics2D) opImage1.getGraphics();
        opG1.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        opG1.setColor(Color.WHITE);
        opG1.fillRect(0, 0, ww, hh);

        opImage2 = createBufferedImage(ww, hh);
        opG2 = (Graphics2D) opImage2.getGraphics();
        opG1.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        opG2.setColor(Color.WHITE);
        opG2.fillRect(0, 0, ww, hh);
    }

    protected void saveImages() throws Exception {
        saveImage(ipFileName1, opImage1, opG1);
        saveImage(ipFileName2, opImage2, opG2);
    }

    protected void saveImage(String ip, BufferedImage opImage, Graphics2D opG)
            throws Exception {
        BufferedImage opImage3 = opImage;
        if (addBorder) {
            int ww = (int) (((double) width * 2) + border);
            int hh = (int) (((double) height * 2) + border);
            int wd = (int) (border);
            int hd = (int) (border);
            BufferedImage opImage2 = createBufferedImage(ww, hh);
            Graphics2D opG2 = (Graphics2D) opImage2.getGraphics();
            opG2.setColor(Color.WHITE);
            opG2.fillRect(0, 0, ww, hh);
            opG2.drawImage(opImage, null, wd, hd);
            opImage3 = opImage2;
        }

        System.out.println("Saving...");
        String src = hostDir + dir;
        if (outFileExt.equals(".png")) {
            savePNGFile(opImage3, src + opFileName + ip + outFileExt, dpi);
        } else {
            saveJPGFile(opImage3, src + opFileName + ip + outFileExt, dpi, 1);
        }
        opG.dispose();
    }

}
