package com.op.moire.stacked;


import com.op.moire.Base;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class ShowAll extends Base {

    private static ShowAll all = new ShowAll();
    private Graphics2D opG1;
    private Graphics2D opG2;
    private Graphics2D opG3;
    private Combinations combinations;

    private int f = 1;
    private int w = 80 * f;
    private int h = 50 * f;
    private int r = 15;
    private int ww = w * r * 2;
    private int hh = h * r * 2;
    private String ipFile1 = "sanjay80.png";
    private String ipFile2 = "and80.png";
    private String ipFile3 = "virga80.png";
    private String hFile1 = "love280.png";
    private String hFile2 = "kiss80.png";
    private static final String dir = "misc/cards/stacked/";

    public static void main(String[] args) throws Exception {
        all.initFiles();

    }

    private void initFiles() throws Exception {
        String src = hostDir + dir;
        File ip1 = new File(src + ipFile1);
        BufferedImage bi1 = ImageIO.read(ip1);
        BufferedImage obi1 = new BufferedImage(ww, hh,
                BufferedImage.TYPE_INT_ARGB);
        opG1 = (Graphics2D) obi1.getGraphics();
        drawExtras(opG1);

        File ip2 = new File(src + ipFile2);
        BufferedImage bi2 = ImageIO.read(ip2);
        BufferedImage obi2 = new BufferedImage(ww, hh,
                BufferedImage.TYPE_INT_ARGB);
        opG2 = (Graphics2D) obi2.getGraphics();
        drawExtras(opG2);

        File ip3 = new File(src + ipFile3);
        BufferedImage bi3 = ImageIO.read(ip3);
        BufferedImage obi3 = new BufferedImage(ww, hh,
                BufferedImage.TYPE_INT_ARGB);
        opG3 = (Graphics2D) obi3.getGraphics();
        drawExtras(opG3);

        File h1 = new File(src + hFile1);
        BufferedImage bih1 = ImageIO.read(h1);

        File h2 = new File(src + hFile2);
        BufferedImage bih2 = ImageIO.read(h2);

        combinations = new Combinations(opG1, opG2, opG3);

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                boolean s1b = bi1.getRGB(x, y) != -1;
                boolean s2b = bi2.getRGB(x, y) != -1;
                boolean s3b = bi3.getRGB(x, y) != -1;
                boolean h1b = bih1.getRGB(x, y) != -1;
                boolean h2b = bih2.getRGB(x, y) != -1;
                int xx = x * r * 2 + r;
                int yy = y * r * 2 + r;
                combinations.fillAll(xx, yy, s1b, s2b, s3b, h1b, h2b);
            }
        }

        boolean combine = false;
        if (combine) {
            int www = ww;
            int hhh = hh * 3;
            BufferedImage opImage4 = createBufferedImage(www, hhh);
            Graphics2D opG4 = (Graphics2D) opImage4.getGraphics();
            opG4.setColor(Color.WHITE);
            opG4.fillRect(0, 0, www, hhh);
            opG4.drawImage(obi1, null, 0, 0);
            opG4.drawImage(obi2, null, 0, hh);
            opG4.drawImage(obi3, null, 0, hh * 2);
            savePNGFile(opImage4, src + "opALL.png", 635.0);
        } else {
            File op1 = new File(src + "op1.png");
            ImageIO.write(obi1, "png", op1);
            System.out.println("Saved:" + op1.getPath());
            File op2 = new File(src + "op2.png");
            ImageIO.write(obi2, "png", op2);
            System.out.println("Saved:" + op2.getPath());
            File op3 = new File(src + "op3.png");
            ImageIO.write(obi3, "png", op3);
            System.out.println("Saved:" + op3.getPath());
        }

    }

    private void drawExtras(Graphics opG) {
        opG.setColor(new Color(255, 255, 255, 0));
        opG.fillRect(0, 0, ww, hh);
        opG.setColor(Color.RED);
        opG.drawRect(0, 0, ww - 1, hh - 1);

        int d = 40;
        int dd = d * 2;
        opG.fillOval(dd, dd, d, d);
        opG.fillOval(ww - dd - d, dd, d, d);
        opG.fillOval(dd, hh - dd - d, d, d);
        opG.fillOval(ww - dd - d, hh - dd - d, d, d);
    }

}