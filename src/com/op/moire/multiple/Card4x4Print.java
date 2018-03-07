package com.op.moire.multiple;

import com.op.moire.Base;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Card4x4Print extends Base {

    private static final String ipFileName1 = "sanjay80";
    private static final String ipFileName2 = "and80";
    private static final String ipFileName3 = "virga80";
    private static final String hiFileName = "love280";
    private static final String hiFileName2 = "kiss80";
    private static final String opFileName = "card4x4";
    private static final String dir = "misc/cards/";
    private static final String outFileExt = ".png";

    private int width = 80;
    private int height = 50;
    private int ww = width * 4;
    private int hh = height * 4;
    private boolean addBorder = false;
    private double border = 20;
    private double dpi = 81.280;
    private Color bg1 = Color.WHITE;
    private Color bg2 = Color.WHITE;
    private Color bg3 = Color.WHITE;

    private BufferedImage opImage1;
    private Graphics2D opG1;
    private BufferedImage opImage2;
    private Graphics2D opG2;
    private BufferedImage opImage3;
    private Graphics2D opG3;

    private CombinedPixelGenerator generator;

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Card4x4Print cp = new Card4x4Print();
        cp.generator = new CombinedPixelGenerator();

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
        BufferedImage biH = ImageIO
                .read(new File(src + hiFileName + outFileExt));
        BufferedImage biH2 = ImageIO.read(new File(src + hiFileName2
                + outFileExt));

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb1 = bi1.getRGB(x, y);
                int rgb2 = bi2.getRGB(x, y);
                int rgb3 = bi3.getRGB(x, y);
                int rgbH1 = biH.getRGB(x, y);
                int rgbH2 = biH2.getRGB(x, y);

                boolean firstBlack = rgb1 != -1;
                boolean secBlack = rgb2 != -1;
                boolean thirdBlack = rgb3 != -1;

                boolean firstHBlack = rgbH1 != -1;
                boolean secHBlack = rgbH2 != -1;

                HashMap<String, Pixel> hm = generator.get4x4PixelsForHidden(
                        firstBlack, secBlack, thirdBlack, firstHBlack,
                        secHBlack);

                Pixel s1 = hm.get("source1");
                Pixel s2 = hm.get("source2");
                Pixel s3 = hm.get("source3");
                Pixel h1 = hm.get("hidden");
                Pixel h2 = hm.get("hidden2");

                paint(x, y, opG1, s1);
                paint(x, y, opG2, s2);
                paint(x, y, opG3, s3);
            }
        }
    }

    private void paint(int x, int y, Graphics2D opG, Pixel s1) {
        opG.setColor(Color.BLACK);
        int dim = s1.w;
        int xx = x * dim;
        int yy = y * dim;
        for (int i = 0; i < s1.subpixels.length(); i++) {
            boolean b = "b".equals(s1.subpixels.substring(i, i + 1));
            if (b) {
                opG.fillRect(xx, yy, 1, 1);
            }
            xx = x * dim + (i % dim);
            yy = y * dim + (i / dim);
        }

    }

    protected void setupOutputImages() throws IOException {
        System.out.println("Creating...");
        opImage1 = createBufferedImage(ww, hh);
        opG1 = (Graphics2D) opImage1.getGraphics();
        opG1.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        opG1.setColor(bg1);
        opG1.fillRect(0, 0, ww, hh);
        opG1.setColor(Color.RED);
        opG1.drawRect(0, 0, ww - 1, hh - 1);

        opImage2 = createBufferedImage(ww, hh);
        opG2 = (Graphics2D) opImage2.getGraphics();
        opG1.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        opG2.setColor(bg2);
        opG2.fillRect(0, 0, ww, hh);
        opG2.setColor(Color.RED);
        opG2.drawRect(0, 0, ww - 1, hh - 1);

        opImage3 = createBufferedImage(ww, hh);
        opG3 = (Graphics2D) opImage3.getGraphics();
        opG3.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        opG3.setColor(bg3);
        opG3.fillRect(0, 0, ww, hh);
        opG3.setColor(Color.RED);
        opG3.drawRect(0, 0, ww - 1, hh - 1);
    }

    protected void saveImages() throws Exception {
        saveImage(ipFileName1, opImage1, opG1);
        saveImage(ipFileName2, opImage2, opG2);
        saveImage(ipFileName3, opImage3, opG3);
        saveCombinedImage();
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

    protected void saveCombinedImage() throws Exception {
        int www = ww;
        int hhh = hh * 3;
        BufferedImage opImage4 = createBufferedImage(www, hhh);
        Graphics2D opG4 = (Graphics2D) opImage4.getGraphics();
        opG4.setColor(Color.WHITE);
        opG4.fillRect(0, 0, www, hhh);
        opG4.drawImage(opImage1, null, 0, 0);
        opG4.drawImage(opImage2, null, 0, hh);
        opG4.drawImage(opImage3, null, 0, hh * 2);

        System.out.println("Saving...");
        String src = hostDir + dir;
        String ip = src + opFileName + ipFileName1 + ipFileName2
                + ipFileName3 + outFileExt;

        if (outFileExt.equals(".png")) {
            savePNGFile(opImage3, src + opFileName + ip + outFileExt, dpi);
        } else {
            saveJPGFile(opImage3, src + opFileName + ip + outFileExt, dpi, 1);
        }

        opG4.dispose();
    }

}
