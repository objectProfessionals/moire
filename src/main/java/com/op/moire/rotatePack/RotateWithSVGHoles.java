package com.op.moire.rotatePack;

import com.op.moire.Base;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

public class RotateWithSVGHoles extends Base {
    private static final RotateWithSVGHoles rotateWithSVGHoles = new RotateWithSVGHoles();

    private String imagesDir = "1234x10B";
    private String imagesName = "test";
    private String topName = "_TOP";
    private String dir = hostDir + "rotateSquare/" + imagesDir + "/";
    private String ipExt = ".jpg";
    private String opExt = ".png";
    private String topFile = dir + topName + ".svg";
    private String ip1src = imagesName + "1" + ipExt;
    private String ip2src = imagesName + "2" + ipExt;
    private String ip3src = imagesName + "3" + ipExt;
    private String ip4src = imagesName + "4" + ipExt;
    private String opBsrc = imagesName + "_BOT" + opExt;
    private double pixelWidth = 0;
    private double topRadius = 0;
    private double bwThresh = 0.5;

    private int ipw;
    private int iph;
    private double dpi = 300;
    private double mm2in = 25.4;
    private double wmm = 90;
    private double hmm = 90;
    private double opw = (dpi * (wmm / mm2in));
    private double oph = (dpi * (hmm / mm2in));


    private BufferedImage ipImage1;
    private BufferedImage ipImage2;
    private BufferedImage ipImage3;
    private BufferedImage ipImage4;
    private BufferedImage opImageB;
    private Graphics2D opGB;
    private PrintWriter printWriter;
    private Color col1 = Color.BLACK;
    private Color col2 = Color.RED;
    private Color col3 = Color.BLUE;
    private Color col4 = Color.MAGENTA;
    private double str = 0.1;

    public static void main(String[] args) throws Exception {
        rotateWithSVGHoles.doAll();
    }

    private void doAll() throws IOException {
        setup();

        drawTop();
        drawBottom();

        closeAll();
    }

    private void drawBottom() {
        for (int y = 0; y < iph; y++) {
            for (int x = 0; x < ipw; x++) {
                fillBottom(x, y);
            }
        }
    }

    private void fillBottom(int x, int y) {
        boolean b1 = isBlack(ipImage1, x, y);
        boolean b2 = isBlack(ipImage2, x, y);
        boolean b3 = isBlack(ipImage3, x, y);
        boolean b4 = isBlack(ipImage4, x, y);
        int pd = (int) (pixelWidth * 0.5);
        if (b1) {
            fillBottomPixel(col1, (int) (x * pixelWidth), (int) (y * pixelWidth), pd);
        }
        if (b2) {
            fillBottomPixel(col2, (int) (x * pixelWidth) + pd, (int) (y * pixelWidth), pd);
        }
        if (b3) {
            fillBottomPixel(col3, (int) (x * pixelWidth) + pd, (int) (y * pixelWidth) + pd, pd);
        }
        if (b4) {
            fillBottomPixel(col4, (int) (x * pixelWidth), (int) (y * pixelWidth) + pd, pd);
        }
    }

    private void fillBottomPixel(Color col, int x, int y, int pd) {
        opGB.setColor(col);
        opGB.fillRect(x + (int) pixelWidth, y + (int) (pixelWidth), pd, pd);
    }

    private void drawTop() {
        double pd = pixelWidth * 0.25;
        for (double y = 0; y < iph; y++) {
            for (double x = 0; x < ipw; x++) {
                double xc = x * pixelWidth + pd;
                double yc = y * pixelWidth + pd;
                drawPathCircle(xc, yc, topRadius);
            }
        }
    }

    private boolean isBlack(BufferedImage image, int x, int y) {
        return getGrey(image, x, y) < bwThresh;
    }

    private double getGrey(BufferedImage image, int x, int y) {
        Color col = new Color(image.getRGB(x, y));
        return (col.getRed() + col.getGreen() + col.getBlue()) / (255.0 * 3.0);
    }

    public void drawPathCircle(double cx, double cy, double radius) {
        double[] start = polarToCartesian(cx + pixelWidth, cy + pixelWidth, radius, 350);
        double[] end = polarToCartesian(cx + pixelWidth, cy + pixelWidth, radius, 0);
        String d = " M " + formatD2(start[0]) + " " + formatD2(start[1]) + " A " + formatD2(radius) + " " + formatD2(radius)
                + " 0 1 0 " + formatD2(end[0]) + " " + formatD2(end[1]);
        printWriter.println(d);

        start = polarToCartesian(cx + pixelWidth, cy + pixelWidth, radius, 440);
        end = polarToCartesian(cx + pixelWidth, cy + pixelWidth, radius, 90);
        d = " M" + formatD2(start[0]) + " " + formatD2(start[1]) + " A " + formatD2(radius) + " " + formatD2(radius)
                + " 0 1 0 " + formatD2(end[0]) + " " + formatD2(end[1]);
        printWriter.println(d);
    }

    double[] polarToCartesian(double centerX, double centerY, double radius, double angleInDegrees) {
        double angleInRadians = Math.toRadians(angleInDegrees);
        float x = (float) (centerX + (radius * Math.cos(angleInRadians)));
        float y = (float) (centerY + (radius * Math.sin(angleInRadians)));

        double[] arr = {x, y};
        return arr;
    }


    private double formatD2(double d) {
        return new BigDecimal(d).setScale(2,
                BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    private void closeAll() {
        printWriter.println("\" style=\"fill:none;stroke-width:" + str + ";stroke:#000000\" />");
        printWriter.println("</svg>");
        printWriter.close();

        savePNGFile(opImageB, dir + opBsrc, dpi);
    }

    private void setup() throws IOException {
        printWriter = new PrintWriter(topFile);
        System.out.println("Reading...");
        ipImage1 = ImageIO.read(new File(dir + ip1src));
        ipImage2 = ImageIO.read(new File(dir + ip2src));
        ipImage3 = ImageIO.read(new File(dir + ip3src));
        ipImage4 = ImageIO.read(new File(dir + ip4src));
        ipw = ipImage1.getWidth();
        iph = ipImage1.getHeight();

        pixelWidth = opw / (ipw + 2);
        topRadius = pixelWidth * 0.2;
        //topRadius = pixelWidth * 0.05;

        System.out.println("Creating...");
        int w = (int) opw;
        int h = (int) oph;
        opImageB = createAlphaBufferedImage(w, h);
        opGB = (Graphics2D) opImageB.getGraphics();
        opGB.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        opGB.setColor(Color.WHITE);
        opGB.fillRect(0, 0, w, h);
        opGB.setColor(Color.BLACK);
        opGB.drawRect(0, 0, w-1, h-1);

        printWriter.println("<svg width=\"" + w + "\" height=\"" + h + "\" xmlns=\"http://www.w3.org/2000/svg\">");
        printWriter.println("");
        printWriter.println("<path id=\"box\" d=\"");
        int sqOff = (int)(opw / 10);
        printWriter.println(" M" + 0 + " " + sqOff + " L" + 0 + " " + 0 + " L" + sqOff + " " + 0);
        printWriter.println(" M" + (w - sqOff) + " " + 0 + " L" + w + " " + 0 + " L" + w + " " + sqOff);

        printWriter.println(" M" + (w - sqOff) + " " + h + " L" + w + " " + h + " L" + w + " " + (h - sqOff));
        printWriter.println(" M" + 0 + " " + (w - sqOff) + " L" + 0 + " " + h + " L" + sqOff + " " + h);
        printWriter.println("\" style=\"fill:none;stroke-width:" + (str * 2) + ";stroke:#000000\" />");
        printWriter.println("<path id=\"holes\" d=\"");
    }

}
