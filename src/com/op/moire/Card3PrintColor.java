package com.op.moire;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Card3PrintColor extends Base {

    private static final String ipFileName1 = "sanjay1";
    private static final String ipFileName2 = "virginija1";
    private static final String ipFileName3 = "love1";
    private static final String opFileName = "cardCol";
    private static final String dir = "misc/cards/";
    private static final String outFileExt = ".png";

    private int width = 80;
    private int height = 50;
    private int ww = width * 2;
    private int hh = height * 2;
    private boolean addBorder = false;
    private double border = 20;
    private double dpi = 20.32;

    private BufferedImage opImage1;
    private Graphics2D opG1;
    private BufferedImage opImage2;
    private Graphics2D opG2;

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Card3PrintColor cp = new Card3PrintColor();
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
        Color c = Color.CYAN;
        Color m = Color.MAGENTA;
        Color y = Color.YELLOW;
        Color r = Color.RED;
        Color b = Color.BLUE;
        Color g = Color.GREEN;
        Color w = Color.WHITE;
        Color bl = Color.BLACK;

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
                        paintSquare(opG1, xx, yy, y, w, c, m);
                        paintSquare(opG2, xx, yy, w, c, m, y);
                    } else if (firstBlack && !secBlack) {
                        paintSquare(opG1, xx, yy, y, w, c, m);
                        paintSquare(opG2, xx, yy, w, m, w, c);
                    } else if (!firstBlack && secBlack) {
                        paintSquare(opG1, xx, yy, y, w, w, c);
                        paintSquare(opG2, xx, yy, c, m, y, w);
                    } else if (!firstBlack && !secBlack) {
                        paintSquare(opG1, xx, yy, m, w, w, c);
                        paintSquare(opG2, xx, yy, w, m, c, w);
                    }
                } else {
                    if (firstBlack && secBlack) {
                        paintSquare(opG1, xx, yy, c, m, w, y);
                        paintSquare(opG2, xx, yy, c, m, w, y);
                    } else if (firstBlack && !secBlack) {
                        paintSquare(opG1, xx, yy, c, m, w, y);
                        paintSquare(opG2, xx, yy, m, w, w, c);
                    } else if (!firstBlack && secBlack) {
                        paintSquare(opG1, xx, yy, m, w, w, c);
                        paintSquare(opG2, xx, yy, c, m, w, y);
                    } else if (!firstBlack && !secBlack) {
                        paintSquare(opG1, xx, yy, c, w, w, m);
                        paintSquare(opG2, xx, yy, w, w, m, c);
                    }
                }
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
        opG.fillRect(x * 2 + offX, y * 2 + offY, 1, 1);
    }

    protected void setupOutputImages() throws IOException {
        System.out.println("Creating...");
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
