package com.op.moire;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Card3Print extends Base {

    private static final String ipFileName1 = "sanjay";
    private static final String ipFileName2 = "virginija";
    private static final String ipFileName3 = "love";
    private static final String opFileName = "Card3Print";
    private static final String dir = "misc/cards/";
    private static final String outFileExt = ".png";

    private int width = 160;
    private int height = 100;
    private int ww = width * 2;
    private int hh = height * 2;
    private boolean addBorder = false;
    private double border = 20;
    private double dpi = 81.280;
    private Color bg1 = Color.WHITE;
    private Color bg2 = Color.WHITE;
    private Color white = Color.WHITE;
    private Color black = Color.BLACK;

    private BufferedImage opImage1;
    private Graphics2D opG1;
    private BufferedImage opImage2;
    private Graphics2D opG2;

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Card3Print cp = new Card3Print();
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

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb1 = bi1.getRGB(x, y);
                int rgb2 = bi2.getRGB(x, y);
                int rgb3 = bi3.getRGB(x, y);

                boolean firstBlack = rgb1 != -1;
                boolean secBlack = rgb2 != -1;
                boolean hidBlack = rgb3 != -1;
                // hidden is black
                if (hidBlack) {
                    if (firstBlack && secBlack) {
                        paintB(x, y, 1, 1);
                    } else if (firstBlack && !secBlack) {
                        paintB(x, y, 1, 2);
                    } else if (!firstBlack && secBlack) {
                        paintB(x, y, 2, 1);
                    } else if (!firstBlack && !secBlack) {
                        paintB(x, y, 2, 2);
                    }
                } else {
                    if (firstBlack && secBlack) {
                        paintW(x, y, 1, 0);
                    } else if (firstBlack && !secBlack) {
                        paintW(x, y, 1, 1);
                    } else if (!firstBlack && secBlack) {
                        paintWA(x, y, 2, 1);
                    } else if (!firstBlack && !secBlack) {
                        paintWB(x, y, 2, 1);
                    }
                }
            }
        }
    }

    private void paintWB(int x, int y, int numW1, int numW2) {
        ArrayList<Color> cols1 = new ArrayList<Color>();
        cols1.add(black);
        cols1.add(black);
        cols1.add(black);
        cols1.add(black);

        ArrayList<Integer> whited1 = new ArrayList<Integer>();
        while (whited1.size() < numW1) {
            int rndWhite = (int) (Math.random() * 4);
            if (!whited1.contains(rndWhite)) {
                cols1.remove(rndWhite);
                cols1.add(rndWhite, white);
                whited1.add(rndWhite);
            }
        }

        paintSquare1(x, y, cols1);

        ArrayList<Color> cols2 = new ArrayList<Color>();
        cols2.add(cols1.get(0));
        cols2.add(cols1.get(1));
        cols2.add(cols1.get(2));
        cols2.add(cols1.get(3));
        ArrayList<Integer> whited = new ArrayList<Integer>();
        while (whited.size() < numW2) {
            int rndWhite = (int) (Math.random() * 4);
            if (!whited.contains(rndWhite)) {
                if (!cols1.get(rndWhite).equals(white)) {
                    cols2.remove(rndWhite);
                    cols2.add(rndWhite, white);
                    whited.add(rndWhite);
                }
            }
        }
        ArrayList<Integer> blacked = new ArrayList<Integer>();
        while (blacked.size() < numW2) {
            int rndBlack = (int) (Math.random() * 4);
            if (!blacked.contains(rndBlack)) {
                if (cols1.get(rndBlack).equals(white)) {
                    cols2.remove(rndBlack);
                    cols2.add(rndBlack, black);
                    blacked.add(rndBlack);
                }
            }
        }

        paintSquare2(x, y, cols2);
    }

    private void paintSquare1(int x, int y, ArrayList<Color> cols1) {
        int c = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                Color col = cols1.get(c);
                opG1.setColor(col.equals(white) ? bg1 : black);
                // opG1.setColor(col.equals(white) ? bg1
                // : Math.random() < 0.5 ? black : Color.GRAY);
                opG1.fillRect(x * 2 + i, y * 2 + j, 1, 1);
                c++;
            }
        }
    }

    private void paintSquare2(int x, int y, ArrayList<Color> cols1) {
        int c = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                Color col = cols1.get(c);
                opG2.setColor(col.equals(white) ? bg2 : black);
                // opG2.setColor(col.equals(white) ? bg2
                // : Math.random() < 0.5 ? black : Color.GRAY);
                opG2.fillRect(x * 2 + i, y * 2 + j, 1, 1);
                c++;
            }
        }
    }

    private void paintWA(int x, int y, int numW1, int numW2) {
        ArrayList<Color> cols1 = new ArrayList<Color>();
        cols1.add(black);
        cols1.add(black);
        cols1.add(black);
        cols1.add(black);

        ArrayList<Integer> whited1 = new ArrayList<Integer>();
        while (whited1.size() < numW1) {
            int rndWhite = (int) (Math.random() * 4);
            if (!whited1.contains(rndWhite)) {
                cols1.remove(rndWhite);
                cols1.add(rndWhite, white);
                whited1.add(rndWhite);
            }
        }

        paintSquare1(x, y, cols1);

        ArrayList<Color> cols2 = new ArrayList<Color>();
        cols2.add(cols1.get(0));
        cols2.add(cols1.get(1));
        cols2.add(cols1.get(2));
        cols2.add(cols1.get(3));
        ArrayList<Integer> blacked = new ArrayList<Integer>();
        while (blacked.size() < numW2) {
            int rndWhite = (int) (Math.random() * 4);
            if (!blacked.contains(rndWhite)) {
                if (!cols1.get(rndWhite).equals(black)) {
                    cols2.remove(rndWhite);
                    cols2.add(rndWhite, black);
                    blacked.add(rndWhite);
                }
            }
        }

        paintSquare2(x, y, cols2);
    }

    private void paintW(int x, int y, int numW1, int numW2) {
        ArrayList<Color> cols1 = new ArrayList<Color>();
        cols1.add(black);
        cols1.add(black);
        cols1.add(black);
        cols1.add(black);

        ArrayList<Integer> whited1 = new ArrayList<Integer>();
        while (whited1.size() < numW1) {
            int rndWhite = (int) (Math.random() * 4);
            if (!whited1.contains(rndWhite)) {
                cols1.remove(rndWhite);
                cols1.add(rndWhite, white);
                whited1.add(rndWhite);
            }
        }

        paintSquare1(x, y, cols1);

        ArrayList<Color> cols2 = new ArrayList<Color>();
        cols2.add(cols1.get(0));
        cols2.add(cols1.get(1));
        cols2.add(cols1.get(2));
        cols2.add(cols1.get(3));
        ArrayList<Integer> whited2 = new ArrayList<Integer>();
        while (whited2.size() < numW2) {
            int rndWhite = (int) (Math.random() * 4);
            if (!whited2.contains(rndWhite)) {
                if (!cols1.get(rndWhite).equals(white)) {
                    cols2.remove(rndWhite);
                    cols2.add(rndWhite, white);
                    whited2.add(rndWhite);
                }
            }
        }

        paintSquare2(x, y, cols2);
    }

    private void paintB(int x, int y, int numW1, int numW2) {
        ArrayList<Color> cols1 = new ArrayList<Color>();
        cols1.add(black);
        cols1.add(black);
        cols1.add(black);
        cols1.add(black);

        ArrayList<Integer> whited1 = new ArrayList<Integer>();
        while (whited1.size() < numW1) {
            int rndWhite = (int) (Math.random() * 4);
            if (!whited1.contains(rndWhite)) {
                cols1.remove(rndWhite);
                cols1.add(rndWhite, white);
                whited1.add(rndWhite);
            }
        }

        paintSquare1(x, y, cols1);

        ArrayList<Color> cols2 = new ArrayList<Color>();
        cols2.add(black);
        cols2.add(black);
        cols2.add(black);
        cols2.add(black);
        ArrayList<Integer> whited2 = new ArrayList<Integer>();
        while (whited2.size() < numW2) {
            int rndWhite = (int) (Math.random() * 4);
            if (!whited2.contains(rndWhite)) {
                if (!cols1.get(rndWhite).equals(white)) {
                    cols2.remove(rndWhite);
                    cols2.add(rndWhite, white);
                    whited2.add(rndWhite);
                }
            }
        }

        paintSquare2(x, y, cols2);
    }

    protected void setupOutputImages() throws IOException {
        System.out.println("Creating...");
        opImage1 = createBufferedImage(ww, hh);
        opG1 = (Graphics2D) opImage1.getGraphics();
        opG1.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        opG1.setColor(bg1);
        opG1.fillRect(0, 0, ww, hh);

        opImage2 = createBufferedImage(ww, hh);
        opG2 = (Graphics2D) opImage2.getGraphics();
        opG1.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        opG2.setColor(bg2);
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
