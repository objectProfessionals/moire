package com.op.moire.rotatePack;

import com.op.moire.Base;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class RotateRegularSquare extends Base {
    private static final RotateRegularSquare fourRotate = new RotateRegularSquare();
    private String imagesDir = "1234tiny";
    private String imagesName = "test";
    private String dir = hostDir + "rotateSquare/" + imagesDir + "/";
    private String ipExt = ".jpg";
    private String opExt = ".png";
    private String ip1src = imagesName + "1" + ipExt;
    private String ip2src = imagesName + "2" + ipExt;
    private String ip3src = imagesName + "3" + ipExt;
    private String ip4src = imagesName + "4" + ipExt;
    private String opBsrc = imagesName + "B" + opExt;
    private String opTsrc = imagesName + "T" + opExt;
    private String opBTsrc = imagesName + "BT.png";
    private boolean saveOnOneImageToo = true;

    double dpi = -1;
    int ipw = -1;
    int iph = -1;
    int opw = -1;
    int oph = -1;
    int border = -1;
    int pixelWidth = 100;
    double wmm = 100;
    double hmm = wmm;
    double mm2in = 25.4;

    BufferedImage ipImage1;
    BufferedImage ipImage2;
    BufferedImage ipImage3;
    BufferedImage ipImage4;
    BufferedImage opImageB;
    BufferedImage opImageT;
    Graphics2D opGB;
    Graphics2D opGT;
    double bwThresh = 0.5;
    private Color[] mainCol = {Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK};
    private Color[] bgCol = {Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE};

    ArrayList<Square> sqaures = new ArrayList<>();
    long rndSeed = 25;
    Random random = new Random(rndSeed);

    public static void main(String[] args) throws Exception {
        fourRotate.doAll();
    }

    private void doAll() throws IOException {
        initImages();

        setupTop();
        setUpBottom();

        saveImages();
    }

    private void setUpBottom() {
        for (Square square : sqaures) {
            setUpBottomSquares(square);
        }
    }

    private void setUpBottomSquares(Square square) {
        int[][] xys = getAllCoords(square);
        int xTL = xys[0][0];
        int yTL = xys[0][1];
        int xTR = xys[1][0];
        int yTR = xys[1][1];
        int xBR = xys[2][0];
        int yBR = xys[2][1];
        int xBL = xys[3][0];
        int yBL = xys[3][1];
        boolean[] blacksNeededImg1 = getBlacks(ipImage1, xTL, yTL, xTR, yTR, xBR, yBR, xBL, yBL);
        boolean[] blacksNeededImg2 = getBlacks(ipImage2, xTL, yTL, xTR, yTR, xBR, yBR, xBL, yBL);
        boolean[] blacksNeededImg3 = getBlacks(ipImage3, xTL, yTL, xTR, yTR, xBR, yBR, xBL, yBL);
        boolean[] blacksNeededImg4 = getBlacks(ipImage4, xTL, yTL, xTR, yTR, xBR, yBR, xBL, yBL);

        boolean[] blacksTL = {blacksNeededImg1[0], blacksNeededImg2[0], blacksNeededImg3[0], blacksNeededImg4[0]};
        boolean[] blacksTR = {blacksNeededImg1[1], blacksNeededImg2[1], blacksNeededImg3[1], blacksNeededImg4[1]};
        boolean[] blacksBR = {blacksNeededImg1[2], blacksNeededImg2[2], blacksNeededImg3[2], blacksNeededImg4[2]};
        boolean[] blacksBL = {blacksNeededImg1[3], blacksNeededImg2[3], blacksNeededImg3[3], blacksNeededImg4[3]};

        drawSquareBottom(square, xTL, yTL, blacksTL, 0);
        drawSquareBottom(square, xTR, yTR, blacksTR, 0);
        drawSquareBottom(square, xBR, yBR, blacksBR, 0);
        drawSquareBottom(square, xBL, yBL, blacksBL, 0);
    }

    private void drawSquareBottom(Square square, int x, int y, boolean[] blacks, int a) {
        int r = pixelWidth / 2;
        int startAng = (int) square.startAng;
        int i = 0;
        for (boolean black : blacks) {
            int ang = (startAng + (a + i * 90)) % 360;
            Color main = mainCol[i];
            Color bg = bgCol[i];
            if (black) {
                opGB.setColor(main);
                int currAng = ang % 360;
                int offx = (currAng == 0 || currAng == 270) ? 0 : 1;
                int offy = (currAng == 180 || currAng == 270) ? 1 : 0;
                fillSquare(opGB, x - r, y - r, r, r, offx, offy);
            } else {
                opGB.setColor(bg);
                int currAng = ang % 360;
                int offx = (currAng == 0 || currAng == 270) ? 0 : 1;
                int offy = (currAng == 180 || currAng == 270) ? 1 : 0;
                fillSquare(opGB, x - r, y - r, r, r, offx, offy);
            }
            i++;
        }
    }

    private int[][] getAllCoords(Square square) {
        int[][] coords = {{0, 0}, {1, 1}, {2, 2}, {3, 3}};
        int[] xyTL = getCoords(square, 0);
        int[] xyTR = getCoords(square, 90);
        int[] xyBR = getCoords(square, 180);
        int[] xyBL = getCoords(square, 270);
        coords[0][0] = xyTL[0];
        coords[0][1] = xyTL[1];
        coords[1][0] = xyTR[0];
        coords[1][1] = xyTR[1];
        coords[2][0] = xyBR[0];
        coords[2][1] = xyBR[1];
        coords[3][0] = xyBL[0];
        coords[3][1] = xyBL[1];

        return coords;
    }

    private int[] getCoords(Square square, double ang) {
        double cx = square.cx;
        double cy = square.cy;

        double rads = Math.toRadians(ang);
        double x1 = cx - ((double) (opw / 2));
        double y1 = cy - ((double) (oph / 2));
        double rr = Math.sqrt(x1 * x1 + y1 * y1);
        double rad = Math.atan2(y1, x1);
        double xx = ((double) (opw / 2)) + Math.round(rr * Math.cos(rad + rads));
        double yy = ((double) (oph / 2)) + Math.round(rr * Math.sin(rad + rads));
        int xxx = (int) xx;
        int yyy = (int) yy;

        int[] coords = {xxx, yyy};
        return coords;
    }

    private boolean[] getBlacks(BufferedImage image, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        boolean[] blacksNeeded = {false, false, false, false};
        blacksNeeded[0] = isBlack(image, x1, y1);
        blacksNeeded[1] = isBlack(image, x2, y2);
        blacksNeeded[2] = isBlack(image, x3, y3);
        blacksNeeded[3] = isBlack(image, x4, y4);
        return blacksNeeded;
    }

    private boolean isBlack(BufferedImage image, int x, int y) {
        return getGrey(image, x, y) < bwThresh;
    }

    private double getGreyForAll(int x, int y) {
        double grey = getGreys(x, y);

        double min = 0;
        double max = 0.1;
        return (min + (grey * (max - min)));
    }

    private double getGreys(int x, int y) {
        double g1 = getGrey(ipImage1, x, y);
        double g2 = getGrey(ipImage2, x, y);
        double g3 = getGrey(ipImage3, x, y);
        double g4 = getGrey(ipImage4, x, y);

        return (g1 + g2 + g3 + g4) / 4.0;
    }

    private double getGrey(BufferedImage image, int x, int y) {
        int xx = x / (int) pixelWidth;
        int yy = y / (int) pixelWidth;
        Color col = new Color(image.getRGB(xx, yy));
        return (col.getRed() + col.getGreen() + col.getBlue()) / (255.0 * 3.0);
    }

    private void setupTop() {
        for (int x = 0; x < opw / 2; x = x + pixelWidth) {
            for (int y = 0; y < oph / 2; y = y + pixelWidth) {
                int startAng = 90 * (int) (getRandom() * 4);
                double g = getGreyForAll((int) x, (int) y);
                Square square = new Square(x + pixelWidth / 2, y + pixelWidth / 2, pixelWidth, startAng, g);

                drawSquareTop(square, 0);
                drawSquareTop(square, 90);
                drawSquareTop(square, 180);
                drawSquareTop(square, 270);


                sqaures.add(square);
            }
        }
    }

    private double getRandom() {
        return random.nextDouble();
    }


    private void drawSquareTop(Square square, int ang) {
        int x = square.cx;
        int y = square.cy;
        int r = square.d / 2;
        double rads = Math.toRadians(ang);
        double x1 = ((double) x) - ((double) (opw / 2));
        double y1 = ((double) y) - ((double) (oph / 2));
        double rr = Math.sqrt(x1 * x1 + y1 * y1);
        double rad = Math.atan2(y1, x1);
        double xx = (opw / 2) + Math.round(rr * Math.cos(rad + rads));
        double yy = (oph / 2) + Math.round(rr * Math.sin(rad + rads));
        int xxx = (int) (xx - pixelWidth / 2);
        int yyy = (int) (yy - pixelWidth / 2);
        int rrr = (int) r;
        Color dark = new Color(square.grey, square.grey, square.grey);

        opGT.setColor(dark);

        int currAng = (square.startAng) % 360;
        int offx = (currAng == 0 || currAng == 270) ? 0 : 1;
        int offy = (currAng == 180 || currAng == 270) ? 1 : 0;
        fillSquare(opGT, xxx, yyy, pixelWidth / 2, pixelWidth / 2, offx, offy);
        currAng = (currAng + 90) % 360;
        offx = (currAng == 0 || currAng == 270) ? 0 : 1;
        offy = (currAng == 180 || currAng == 270) ? 1 : 0;
        fillSquare(opGT, xxx, yyy, pixelWidth / 2, pixelWidth / 2, offx, offy);
        currAng = (currAng + 90) % 360;
        offx = (currAng == 0 || currAng == 270) ? 0 : 1;
        offy = (currAng == 180 || currAng == 270) ? 1 : 0;
        fillSquare(opGT, xxx, yyy, pixelWidth / 2, pixelWidth / 2, offx, offy);


        Color colA = new Color(dark.getRed(), dark.getGreen(), dark.getBlue(), 10);
        opGT.setColor(colA);
        currAng = (currAng + 90) % 360;
        offx = (currAng == 0 || currAng == 270) ? 0 : 1;
        offy = (currAng == 180 || currAng == 270) ? 1 : 0;
        fillSquare(opGT, xxx, yyy, pixelWidth / 2, pixelWidth / 2, offx, offy);
    }

    private void fillSquare(Graphics2D opG, int x, int y, int w, int h, int offx, int offy) {
        int xx = x + (offx * pixelWidth / 2);
        int yy = y + (offy * pixelWidth / 2);
        opG.fillRect(xx, yy, w, h);
    }

    private void saveImages() {
        if (saveOnOneImageToo) {
            saveAsOneImage();
        }
        saveAsTwoImages();
    }

    private void initImages() throws IOException {
        System.out.println("Reading...");
        ipImage1 = ImageIO.read(new File(dir + ip1src));
        ipImage2 = ImageIO.read(new File(dir + ip2src));
        ipImage3 = ImageIO.read(new File(dir + ip3src));
        ipImage4 = ImageIO.read(new File(dir + ip4src));
        ipw = ipImage1.getWidth();
        iph = ipImage1.getHeight();

        opw = ipw * (int) pixelWidth;
        oph = iph * (int) pixelWidth;

        //border = (int) ((double) (opw) * 0.05);
        border = 0;

        dpi = opw / wmm * mm2in;

        System.out.println("Creating...");
        opImageB = createAlphaBufferedImage(opw + 2 * border, oph + 2 * border);
        opGB = (Graphics2D) opImageB.getGraphics();
        opGB.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        opGB.setColor(Color.BLACK);
        opGB.fillRect(0, 0, opw, oph);
        opGB.setColor(Color.BLACK);

        opImageT = createAlphaBufferedImage(opw + 2 * border, oph + 2 * border);
        opGT = (Graphics2D) opImageT.getGraphics();
        opGT.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        opGT.setColor(Color.BLACK);
    }

    private void saveAsOneImage() {
        double bf = 0.05;
        border = (int) (((double) opw) * bf);
        BufferedImage opImageC = createAlphaBufferedImage((2 * opw + 3 * border), oph + 2 * border);
        Graphics2D opG = (Graphics2D) (opImageC.getGraphics());
        opG.drawImage(opImageB, AffineTransform.getTranslateInstance(border, border), null);
        opG.drawImage(opImageT, AffineTransform.getTranslateInstance(opw + 2 * border, border), null);
        opG.setColor(Color.RED);
        float strmm = 0.25f;
        float str = strmm * (float) (dpi / 25.4); // 1mm
        opG.setStroke(new BasicStroke(str));
        opG.drawRect(border, border, opw, oph);
        opG.drawRect(border + opw + border, border, opw, oph);

        savePNGFile(opImageC, dir + opBTsrc, dpi);
    }

    private void saveAsTwoImages() {
        opGB.setColor(Color.RED);
        float strmm = 0.25f;
        float str = strmm * (float) (dpi / 25.4); // 1mm
        int stri = (int)str;
        opGB.setStroke(new BasicStroke(str));
        opGB.drawRect(0, 0, opw-stri, oph-stri);

        opGT.setColor(Color.RED);
        opGT.setStroke(new BasicStroke(str));
        opGT.drawRect(0, 0, opw-stri, oph-stri);

        savePNGFile(opImageB, dir + opBsrc, dpi);
        savePNGFile(opImageT, dir + opTsrc, dpi);
    }
}
