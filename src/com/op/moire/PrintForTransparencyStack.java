package com.op.moire;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PrintForTransparencyStack extends Base {

    private final String ipFileName1 = "00";
    private final String opFileName = "ST";
    private final String dir = hostDir + "misc/stack/";
    private final String outFileExt = ".png";

    private double dpi = 600;
    private double mm2in = 25.4;
    private int wFramemm = 50;
    private int hFramemm = 50;
    private int ww = (int) (dpi * wFramemm / mm2in);
    private int hh = (int) (dpi * hFramemm / mm2in);
    private int wN = 4;
    private int hN = 5;
    private int w = wN * ww;
    private int h = hN * hh;

    private BufferedImage opImage1;
    private Graphics2D opG1;

    public static void main(String[] args) throws Exception {
        PrintForTransparencyStack cp = new PrintForTransparencyStack();
        cp.setupOutputImages();
        int n = 1;
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 5; y++) {
                cp.draw(x, y, n);
                n++;
            }

        }
        cp.saveImages();
    }

    private void draw(int x, int y, int n) throws IOException {
        String nn = "" + (n < 10 ? "0" + n : n);
        BufferedImage bi1 = ImageIO.read(new File(dir + ipFileName1 + nn
                + outFileExt));
        opG1.drawImage(bi1, x * ww, y * hh, null);
        opG1.drawLine(x * ww, 0, x * ww, h);
        opG1.drawLine(0, y * hh, w, y * hh);

        int off = 20;
        opG1.fillRect(x * ww + off, y * hh + off, off / 2, off / 2);
        opG1.fillRect(x * ww + ww - off, y * hh + off, off / 2, off / 2);

        opG1.fillRect(x * ww + off, y * hh + hh - off, off / 2, off / 2);
        opG1.fillRect(x * ww + ww - off, y * hh + hh - off, off / 2, off / 2);
    }


    protected void setupOutputImages() throws IOException {
        System.out.println("Creating...");
        opImage1 = createBufferedImage(w, h);
        opG1 = (Graphics2D) opImage1.getGraphics();
        opG1.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        opG1.setColor(Color.BLACK);
        opG1.fillRect(0, 0, w, h);
        opG1.setColor(Color.WHITE);
        opG1.setStroke(new BasicStroke(10f));
    }

    protected void saveImages() throws Exception {
        saveImage();
    }

    protected void saveImage()
            throws Exception {
        System.out.println("Saving...");
        String src = dir;
        if (outFileExt.equals(".png")) {
            savePNGFile(opImage1, src + opFileName + outFileExt, dpi);
        } else {
            saveJPGFile(opImage1, src + opFileName + outFileExt, dpi, 1);
        }
    }

}
