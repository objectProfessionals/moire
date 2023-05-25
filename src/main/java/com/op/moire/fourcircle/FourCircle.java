package com.op.moire.fourcircle;

import com.op.moire.Base;
import com.op.moire.rotate.Bottom;
import com.op.moire.rotate.Top;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FourCircle extends Base {
    private static final FourCircle fourRotate = new FourCircle();
    private String imagesDir = "testLarge";
    private String imagesName = "test";
    private String dir = hostDir + "fourCircle/" + imagesDir + "/";
    private String ipExt = ".jpg";
    private String opExt = ".png";
    private String ip1src = imagesName + "1" + ipExt;
    private String ip2src = imagesName + "2" + ipExt;
    private String ip3src = imagesName + "3" + ipExt;
    private String ip4src = imagesName + "4" + ipExt;
    private String opBsrc = imagesName + "B" + opExt;
    private String opTsrc = imagesName + "T" + opExt;
    private String opBTsrc = imagesName + "BT.png";
    private boolean saveOnOneImage = false;

    int ipw = -1;
    int iph = -1;
    int opw = -1;
    int oph = -1;
    int border = -1;

    int opFactor = 10;
    double dpi = 254;

    BufferedImage ipImage1;
    BufferedImage ipImage2;
    BufferedImage ipImage3;
    BufferedImage ipImage4;
    BufferedImage opImageB;
    BufferedImage opImageT;
    Graphics2D opGB;
    Graphics2D opGT;

    private double bwThresh = 0.5;

    public static void main(String[] args) throws Exception {
        fourRotate.doAll();
    }

    private void doAll() throws IOException {
        initImages();

        drawAll();
        saveImages();
    }

    private void drawAll() {

    }

    private boolean isBlack(BufferedImage image, int x, int y) {
        return getGrey(image, x, y) < bwThresh;
    }

    private double getGrey(BufferedImage image, int x, int y) {
        System.out.println("x,y:"+x+","+y);
        Color col = new Color(image.getRGB(x, y));
        return (col.getRed() + col.getGreen() + col.getBlue()) / (255.0 * 3.0);
    }

    private Color getColor(BufferedImage image, int x, int y) {
        return new Color(image.getRGB(x, y));
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
        ipw = ipImage1.getWidth();
        iph = ipImage1.getHeight();
        opw = ipw * opFactor;
        oph = iph * opFactor;

        border = (int) ((double) (opw) * 0.05);
        border = 0;

        double tot = opw + 2 * border;


        System.out.println("Creating...");
        opImageB = createAlphaBufferedImage(opw + 2 * border, oph + 2 * border);
        opGB = (Graphics2D) opImageB.getGraphics();
        opGB.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        opGB.setColor(Color.WHITE);
        opGB.fillRect(0, 0, opw, oph);
        opGB.setColor(Color.BLACK);

        opImageT = createAlphaBufferedImage(opw + 2 * border, oph + 2 * border);
        opGT = (Graphics2D) opImageT.getGraphics();
        opGT.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        opGT.setColor(Color.BLACK);
    }

    private void saveAsOneImage() {
        BufferedImage opImageC = createAlphaBufferedImage(1 + 2 * (opw + 2 * border), oph + 2 * border);
        Graphics2D opG = (Graphics2D) (opImageC.getGraphics());
        opG.drawImage(opImageB, null, null);
        opG.setColor(Color.RED);
        opG.fillRect((opw + 2 * border), 0, 1, oph + 2 * border);
        opG.drawImage(opImageT, AffineTransform.getTranslateInstance(1 + opw + 2 * border, 0), null);

        savePNGFile(opImageC, dir + opBTsrc, dpi);
    }

    private void saveAsTwoImages() {
        savePNGFile(opImageB, dir + opBsrc, dpi);
        savePNGFile(opImageT, dir + opTsrc, dpi);
    }

}
