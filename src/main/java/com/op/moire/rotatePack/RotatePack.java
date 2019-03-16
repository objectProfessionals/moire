package com.op.moire.rotatePack;

import com.op.moire.Base;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class RotatePack extends Base {
    private static final RotatePack fourRotate = new RotatePack();
    private String imagesDir = "sw";
    private String imagesName = "sw";
    private String dir = hostDir + "rotatePack/" + imagesDir + "/";
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

    double dpi = -1;
    int ipw = -1;
    int iph = -1;
    int opw = -1;
    int oph = -1;
    int border = -1;
    double pixelWidth = 20;

    BufferedImage ipImage1;
    BufferedImage ipImage2;
    BufferedImage ipImage3;
    BufferedImage ipImage4;
    BufferedImage opImageB;
    BufferedImage opImageT;
    Graphics2D opGB;
    Graphics2D opGT;
    double angInc = 5;
    int maxCircles = 4000;
    double absMinR = 2; // need 2mm dia
    double minR = 15;
    double maxR = 20;
    private int maxTryCount = 10000;
    double spacer = 1;
    double bwThresh = 0.5;
    //private Color[] mainCol = {Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK};
    private Color[] mainCol = {Color.BLACK, Color.BLUE, Color.RED, Color.GREEN};
    private Color[] bgCol = {Color.WHITE, Color.CYAN.brighter(), Color.MAGENTA.brighter().brighter().brighter().brighter(), Color.YELLOW.brighter().brighter().brighter()};

    ArrayList<Circle> circleList = new ArrayList<>();

    float circleBorder = 5f;


    public static void main(String[] args) throws Exception {
        fourRotate.doAll();
    }

    private void doAll() throws IOException {
        initImages();

        setupTop();
        setUpBottom();

        calcCoverage();
        saveImages();
    }

    private void calcCoverage() {
        double total = 0;
        for (Circle circle : circleList) {
            double r = circle.r;
            double area = (Math.PI * r * r);
            total = total + area;
        }
        double all = opw * oph;
        double percentageCoverage = 100 * total / all;

        System.out.println("%=" + percentageCoverage);


        opGB.setStroke(new BasicStroke(circleBorder));
        opGB.setColor(Color.RED);
        opGB.drawOval(0, 0, opw, oph);
        opGT.setStroke(new BasicStroke(circleBorder));
        opGT.setColor(Color.RED);
        opGT.drawOval(0, 0, opw, oph);
    }

    private void setUpBottom() {
        for (Circle circle : circleList) {
            setUpBottomCircles(circle);
        }
        System.out.println("minR:" + minR);
    }

    private void setUpBottomCircles(Circle circle) {
        int[][] xys = getAllCoords(circle);
        int x1 = xys[0][0];
        int y1 = xys[0][1];
        int x2 = xys[1][0];
        int y2 = xys[1][1];
        int x3 = xys[2][0];
        int y3 = xys[2][1];
        int x4 = xys[3][0];
        int y4 = xys[3][1];
        boolean[] blacksNeededA = getBlacks(ipImage1, x1, y1, x2, y2, x3, y3, x4, y4);
        boolean[] blacksNeededB = getBlacks(ipImage2, x1, y1, x2, y2, x3, y3, x4, y4);
        boolean[] blacksNeededC = getBlacks(ipImage3, x1, y1, x2, y2, x3, y3, x4, y4);
        boolean[] blacksNeededD = getBlacks(ipImage4, x1, y1, x2, y2, x3, y3, x4, y4);

        boolean[] blacks0 = {blacksNeededA[0], blacksNeededB[0], blacksNeededC[0], blacksNeededD[0]};
        boolean[] blacks1 = {blacksNeededA[1], blacksNeededB[1], blacksNeededC[1], blacksNeededD[1]};
        boolean[] blacks2 = {blacksNeededA[2], blacksNeededB[2], blacksNeededC[2], blacksNeededD[2]};
        boolean[] blacks3 = {blacksNeededA[3], blacksNeededB[3], blacksNeededC[3], blacksNeededD[3]};

        drawCircleBottom(circle, x1, y1, blacks0);
        drawCircleBottom(circle, x2, y2, blacks1);
        drawCircleBottom(circle, x3, y3, blacks2);
        drawCircleBottom(circle, x4, y4, blacks3);
    }

    private void drawCircleBottom(Circle circle, int x, int y, boolean[] blacks) {
        double cx = circle.cx;
        double cy = circle.cy;
        int r = (int) circle.r;
        int startAng = (int) circle.startAng;
        int i = 0;
        for (boolean black : blacks) {
            int ang = ((i * 90) + startAng - 90) % 360;
            Color main = mainCol[i];
            Color bg = bgCol[i];
            if (black) {
                opGB.setColor(main);
                opGB.fillArc(x - r, y - r, r * 2, r * 2, ang, 90);
            } else {
                opGB.setColor(bg);
                opGB.fillArc(x - r, y - r, r * 2, r * 2, ang, 90);
            }
            i++;
        }
    }

    private int[][] getAllCoords(Circle circle) {
        int[][] coords = {{0, 0}, {1, 1}, {2, 2}, {3, 3}};
        int[] xy0 = getCoords(circle, 0);
        int[] xy1 = getCoords(circle, 90);
        int[] xy2 = getCoords(circle, 180);
        int[] xy3 = getCoords(circle, 270);
        coords[0][0] = xy0[0];
        coords[0][1] = xy0[1];
        coords[1][0] = xy1[0];
        coords[1][1] = xy1[1];
        coords[2][0] = xy2[0];
        coords[2][1] = xy2[1];
        coords[3][0] = xy3[0];
        coords[3][1] = xy3[1];

        return coords;
    }

    private int[] getCoords(Circle circle, double ang) {
        double cx = circle.cx;
        double cy = circle.cy;
        double r = circle.r;
        double startAng = circle.startAng;

        double rads = Math.toRadians(ang);
        double x1 = cx - ((double) (opw / 2));
        double y1 = cy - ((double) (oph / 2));
        double rr = Math.sqrt(x1 * x1 + y1 * y1);
        double rad = Math.atan2(y1, x1);
        double xx = ((double) (opw / 2)) + rr * Math.cos(rad + rads);
        double yy = ((double) (oph / 2)) + rr * Math.sin(rad + rads);
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
        //return -1 != image.getRGB(x, y);
    }

    private double getGrey(BufferedImage image, int x, int y) {
        int xx = x / (int) pixelWidth;
        int yy = y / (int) pixelWidth;
        Color col = new Color(image.getRGB(xx, yy));
        return (col.getRed() + col.getGreen() + col.getBlue()) / (255.0 * 3.0);
    }

    private void setupTop() {
        Circle circleC = setupCenterTopCircle(0);
        drawCircleTop(circleC, 0);
        for (int i = 1; i < maxCircles; i++) {
            Circle circle = setupTopCircle(i);

            //initCircleCol(circle);

            if (circle != null) {
                drawCircleTop(circle, 0);
                drawCircleTop(circle, 90);
                drawCircleTop(circle, 180);
                drawCircleTop(circle, 270);
            }
        }
    }

    private void initCircleCol(Circle circle) {
        int[][] coords = getAllCoords(circle);
        int x1 = coords[0][0];
        int y1 = coords[0][1];
        int x2 = coords[1][0];
        int y2 = coords[1][1];
        int x3 = coords[2][0];
        int y3 = coords[2][1];
        int x4 = coords[3][0];
        int y4 = coords[3][1];

        float avgGrey1 = (float) ((getGrey(ipImage1, x1, y1) + getGrey(ipImage2, x1, y1) + getGrey(ipImage3, x1, y1) + getGrey(ipImage4, x1, y1)) / 4.0);
        float avgGrey2 = (float) ((getGrey(ipImage1, x2, y2) + getGrey(ipImage2, x2, y2) + getGrey(ipImage3, x2, y2) + getGrey(ipImage4, x2, y2)) / 4.0);
        float avgGrey3 = (float) ((getGrey(ipImage1, x3, y3) + getGrey(ipImage2, x3, y3) + getGrey(ipImage3, x3, y3) + getGrey(ipImage4, x3, y3)) / 4.0);
        float avgGrey4 = (float) ((getGrey(ipImage1, x4, y4) + getGrey(ipImage2, x4, y4) + getGrey(ipImage3, x4, y4) + getGrey(ipImage4, x4, y4)) / 4.0);

        float avgGrey = (avgGrey1 + avgGrey2 + avgGrey3 + avgGrey4) / 4.0f;

        if (avgGrey > 0.9) {
            circle.col = new Color(0.9f, 0.9f, 0.9f);
        } else if (avgGrey > 0.25) {
            circle.col = new Color(avgGrey, avgGrey, avgGrey);
        } else {
            circle.col = Color.BLACK;
        }
    }

    private Circle setupCenterTopCircle(int i) {
        double startAng = 360.0 * (Math.random());
        Circle circle = new Circle(opw / 2, oph / 2, maxR, startAng);
        System.out.println("circleList=" + circleList.size());
        circleList.add(circle);
        return circle;
    }

    private void drawCircleTop(Circle circle, double ang) {
        double x = circle.cx;
        double y = circle.cy;
        double r = circle.r;
        double startAng = circle.startAng;

        double rads = Math.toRadians(ang);
        double x1 = x - ((double) (opw / 2));
        double y1 = y - ((double) (oph / 2));
        double rr = Math.sqrt(x1 * x1 + y1 * y1);
        double rad = Math.atan2(y1, x1);
        double xx = ((double) (opw / 2)) + rr * Math.cos(rad + rads);
        double yy = ((double) (oph / 2)) + rr * Math.sin(rad + rads);
        int xxx = (int) xx;
        int yyy = (int) yy;
        int rrr = (int) r;
        //opGT.setColor(circle.col);
        opGT.setColor(Color.BLACK);
        opGT.fillArc(xxx - rrr, yyy - rrr, rrr * 2, rrr * 2, (int) (startAng), 270);


        Color colA = new Color(0, 0, 0, 10);
        opGT.setColor(colA);
        opGT.fillArc(xxx - rrr, yyy - rrr, rrr * 2, rrr * 2, (int) (startAng + 270), 90);
    }

    private Circle setupTopCircle(int i) {
        Circle circle = null;
        int tryCount = 0;
        //maxTryCount = opw * oph;
        boolean hasGroundOnlyColor;
        double maximumR = maxR - minR;
        double r = (minR + Math.random() * maximumR);
        double x = Math.random() * opw;
        double y = Math.random() * oph;
        double rr = r + spacer;
        hasGroundOnlyColor = hasOnlyGroundColor(x, y, rr);

        double xs = 0;
        double ys = 0;
        while (!hasGroundOnlyColor && rr > minR) {
            rr--;
            hasGroundOnlyColor = hasOnlyGroundColor(x, y, rr);
            tryCount++;
            //System.out.println("rr"+rr);
            //if (!hasGroundOnlyColor && rr <= maxR && xs < opw && ys < oph) {
            if (!hasGroundOnlyColor && rr <= maxR) {
                r = minR + Math.random() * maximumR;
                x = Math.random() * opw;
                y = Math.random() * oph;

//                xs = xs + 1;
//                if (xs >= oph) {
//                    xs = 0;
//                    ys = ys + 1;
//                }
//                x = xs;
//                y = ys;

                rr = r + spacer;
                hasGroundOnlyColor = hasOnlyGroundColor(x, y, rr);

//                Circle c2 = new Circle(x,y,r, 0);
//                if (!circleList.contains(c2)) {
//                    //System.out.println("tryAgain");
//                }
            }
        }

        if (hasGroundOnlyColor) {
            double startAng = 360.0 * (Math.random());
            circle = new Circle(x, y, r, startAng);
            System.out.println("circleList=" + circleList.size());
            circleList.add(circle);
        }
        if (tryCount > maxTryCount) {
            minR = Math.max(2, minR - 1);
            System.out.println("minR=" + minR);
            if (minR < absMinR) {
                throw new RuntimeException("tooSmall minR=" + minR);
            }
        }

        return circle;
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

        opw = ipw * (int) pixelWidth;
        oph = iph * (int) pixelWidth;

        //border = (int) ((double) (opw) * 0.05);
        border = 0;

        double wmm = 100;
        double hmm = 100;
        double mm2in = 25.4;
        dpi = opw / wmm * mm2in;

        System.out.println("Creating...");
        opImageB = createAlphaBufferedImage(opw + 2 * border, oph + 2 * border);
        opGB = (Graphics2D) opImageB.getGraphics();
        opGB.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        opGB.setColor(Color.DARK_GRAY);
        opGB.fillRect(0, 0, opw, oph);
        opGB.setColor(Color.BLACK);

        opImageT = createAlphaBufferedImage(opw + 2 * border, oph + 2 * border);
        opGT = (Graphics2D) opImageT.getGraphics();
        opGT.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        opGT.setColor(Color.BLACK);
    }

    private boolean hasOnlyGroundColor(double x, double y, double r) {
        int rgb = opImageT.getRGB((int) x, (int) y);
        int alpha = (rgb >> 24) & 0xff;

        float thresh = 10f;
        boolean hasGroundColor = (alpha < thresh);
        if (!hasGroundColor) {
            return false;
        }

        for (double ang = 0; ang < 360; ang = ang + angInc) {
            for (double rr = 0; rr < r; rr++) {
                double xx = x + rr * Math.cos(Math.toRadians(ang));
                double yy = y + rr * Math.sin(Math.toRadians(ang));
                if ((int) xx >= opw || (int) yy >= oph || (int) xx < 0 || (int) yy < 0) {
                    continue;
                }
                rgb = opImageT.getRGB((int) xx, (int) yy);
                alpha = (rgb >> 24) & 0xff;
                hasGroundColor = (alpha < thresh);
                if (!hasGroundColor) {
                    return false;
                }
            }
        }

        return true;
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
