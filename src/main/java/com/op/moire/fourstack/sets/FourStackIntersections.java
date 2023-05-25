package com.op.moire.fourstack.sets;

import com.op.moire.Base;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static java.awt.Color.*;

public class FourStackIntersections extends Base {

    private static FourStackIntersections fourStackIntersections = new FourStackIntersections();
    private String imagesDir = "sets";
    private String imagesName = "test";
    private String dir = hostDir + "fourStack/" + imagesDir + "/";
    private String ipExt = ".png";
    private String opExt = ".png";
    private String opSuff = "_ALL";
    private String fontDir = dir + "fonts/";
    private String fontName = "NEWTOWN.TTF";
    private Font font;
    private Random random = new Random(1);

    private String op1src = imagesName + opSuff + "1" + opExt;
    private String op2src = imagesName + opSuff + "2" + opExt;
    private String op4src = imagesName + opSuff + "4" + opExt;
    private String op8src = imagesName + opSuff + "8" + opExt;

    private String ip1src = imagesName + "1" + ipExt;
    private String ip2src = imagesName + "2" + ipExt;
    private String ip3src = imagesName + "3" + ipExt;
    private String ip4src = imagesName + "4" + ipExt;
    private String ip5src = imagesName + "5" + ipExt;
    private String ip6src = imagesName + "6" + ipExt;
    private String ip7src = imagesName + "7" + ipExt;
    private String ip8src = imagesName + "8" + ipExt;
    private String ip9src = imagesName + "9" + ipExt;
    private String ip10src = imagesName + "10" + ipExt;
    private String ip11src = imagesName + "11" + ipExt;
    private String ip12src = imagesName + "12" + ipExt;
    private String ip13src = imagesName + "13" + ipExt;
    private String ip14src = imagesName + "14" + ipExt;
    private String ip15src = imagesName + "15" + ipExt;

    BufferedImage ipImage1;
    BufferedImage ipImage2;
    BufferedImage ipImage3;
    BufferedImage ipImage4;
    BufferedImage ipImage5;
    BufferedImage ipImage6;
    BufferedImage ipImage7;
    BufferedImage ipImage8;
    BufferedImage ipImage9;
    BufferedImage ipImage10;
    BufferedImage ipImage11;
    BufferedImage ipImage12;
    BufferedImage ipImage13;
    BufferedImage ipImage14;
    BufferedImage ipImage15;

    BufferedImage opImage1;
    BufferedImage opImage2;
    BufferedImage opImage4;
    BufferedImage opImage8;
    Graphics2D op1;
    Graphics2D op2;
    Graphics2D op4;
    Graphics2D op8;

    int f = 200;
    int pixelDim = 1;
    int ipw = f;
    int iph = f;
    int opw = pixelDim * f;
    int oph = pixelDim * f;

    double a = 1;
    Color cc = getAlpha(CYAN, a);
    Color cm = getAlpha(MAGENTA, a);
    Color cy = getAlpha(YELLOW, a);
    Color ck = getAlpha(LIGHT_GRAY, a);
    Color[] cols = {cc, cm, cy, ck};


    public static void main(String[] args) throws Exception {
        fourStackIntersections.doAll();
    }

    private void doAll() throws Exception {
        init();

        //testMix();
        testAll();
        //test();
        save();

        //saveLayered(opImage1, opImage2, null, null, "3");
//        saveLayered(opImage1, opImage2, opImage4, null, "7");
//        saveLayered(opImage1, opImage2, opImage4, opImage8, "15");
    }

    private void testMix() {
        Color res = KMColorUtils.mix(Color.CYAN.darker(), Color.YELLOW.darker());
        System.out.println(res);
    }

    private void testAll() {
        double d = 0.5;
        float ff = 0.5f;
        float fr = -0.025f;
        Area area1 = getCharacterArea("1", ff);
        Area area2 = getCharacterArea("2", ff=ff-fr);
        Area area3 = getCharacterArea("3", ff=ff-fr);
        Area area4 = getCharacterArea("4", ff=ff-fr);
        Area area5 = getCharacterArea("5", ff=ff-fr);
        Area area6 = getCharacterArea("6", ff=ff-fr);
        Area area7 = getCharacterArea("7", ff=ff-fr);
        Area area8 = getCharacterArea("8", ff=ff-fr);
        Area area9 = getCharacterArea("9", ff=ff-fr);
        Area area10 = getCharacterArea("10", ff=ff-fr);
        Area area11 = getCharacterArea("11", ff=ff-fr);
        Area area12 = getCharacterArea("12", ff=ff-fr);
        Area area13 = getCharacterArea("13", ff=ff-fr);
        Area area14 = getCharacterArea("14", ff=ff-fr);
        Area area15 = getCharacterArea("15", ff=ff-fr);

        op1.setColor(BLACK);
        op1.draw(area1);
        op1.draw(area3);
        op1.draw(area5);
        op1.draw(area9);
        op1.draw(area7);
        op1.draw(area11);
        op1.draw(area13);
        op1.draw(area15);

//        op2.setColor(c2);
//        op2.fill(area2);
//        op2.setColor(c12);
//        op2.fill(area3);
//        op2.fill(area6);
//        op2.fill(area7);
//        op2.fill(area10);
//        op2.fill(area11);
//        op2.fill(area14);
//        op2.fill(area15);

//        op4.fill(area4);
//        op4.fill(area5);
//        op4.fill(area6);
//        op4.fill(area7);
//        op4.fill(area12);
//        op4.fill(area13);
//        op4.fill(area14);
//        op4.fill(area15);

//        op8.fill(area8);
//        op8.fill(area9);
//        op8.fill(area10);
//        op8.fill(area11);
//        op8.fill(area12);
//        op8.fill(area13);
//        op8.fill(area14);
//        op8.fill(area15);
    }

    private void test() {
        double qOn = 90;
        double tOn = 75;
        double pOn = 50;
        double sOn = 75;
        double bgOn = 50;

        Color c1 = getAlpha(CYAN, 0.5); // new Color(0.66f, 0.66f, 0.66f, 0.5f);
        Color c2 = getAlpha(MAGENTA, 0.5); //new Color(0.75f, 0.75f, 0.75f, 0.5f);

        Area areaAll = new Area(new Rectangle.Double(0, 0, opw, oph));
        int sqAll = getTotalAreaPixels(areaAll);
        Area area1 = getCharacterArea("1", 1);
        int sq1 = getTotalAreaPixels(area1);
        Area area2 = getCharacterArea("2", 1);
        int sq2 = getTotalAreaPixels(area2);
        Area area3 = getCharacterArea("3", 1);
        int sq3 = getTotalAreaPixels(area3);

        Area area3Not1 = getCharacterArea("3" , 1);
        area3Not1.subtract(getCharacterArea("1", 1));
        int sq3Not1 = getTotalAreaPixels(area3Not1);
        Area areaNot31 = new Area(new Rectangle.Double(0, 0, opw, oph));
        areaNot31.subtract(area3);
        areaNot31.subtract(area1);
        int sqNot31 = getTotalAreaPixels(areaNot31);
        ArrayList<Point> used31 = new ArrayList<>();
        used31 = fillArea(op1, c1, area3Not1, sq3Not1, 0, pOn, used31, false);
        ArrayList<Point> usedNot1 = fillArea(op1, c1, areaNot31, sqNot31, 0, bgOn, used31, false);
        ArrayList<Point> usedOnly1  = fillArea(op1, c1, area1, sq1, 0, sOn, new ArrayList<>(), true);

        Area area3Not2 = getCharacterArea("3", 1);
        area3Not2.subtract(getCharacterArea("2", 1));
        int sq3Not2 = getTotalAreaPixels(area3Not2);
        Area areaNot32 = new Area(new Rectangle.Double(0, 0, opw, oph));
        areaNot32.subtract(area3);
        areaNot32.subtract(area2);
        int sqNot32 = getTotalAreaPixels(areaNot32);
        ArrayList<Point> used32 = new ArrayList<>();
        used32 = fillArea(op2, c2, area3Not2, sq3Not2, 0, pOn, used32, false);
        ArrayList<Point> usedNot2 = fillArea(op2, c2, areaNot32, sqNot32, 0, bgOn, used32, false);
        ArrayList<Point> usedOnly2  = fillArea(op2, c2, area2, sq2, 0, sOn, new ArrayList<>(), true);
    }

    private ArrayList<Point> fillArea(Graphics2D op, Color col, Area area, int totalAreaPixels, double startPC, double endPC,
                                      ArrayList<Point> usedAlready, boolean newPointsOnly) {
        ArrayList<Point> used = new ArrayList();
        ArrayList<Point> pointsUsedInAreaAlready = getUsedInArea(usedAlready, area);
        int c = newPointsOnly ? 0 : pointsUsedInAreaAlready.size();
        double pcNeeded = (endPC);
        double pc = (startPC);
        while (pc < pcNeeded) {
            int x = (int) (opw * random());
            int y = (int) (oph * random());
            if (area.contains(x, y)) {
                int ci = (int) (4 * random());
                op.setColor(cols[ci]);
                Point p = new Point(x, y, cols[ci]);
                if (!pointsUsedInAreaAlready.contains(p)) {
                    used.add(p);
                    pointsUsedInAreaAlready.add(p);
                    op.fillRect(x*pixelDim, y*pixelDim, pixelDim, pixelDim);
                    c++;
                    pc = 100.0 * c / totalAreaPixels;
                }
            }
        }

//        op.fill(area);

        used.addAll(usedAlready);
        return used;
    }

    private ArrayList<Point> overlayArea(Graphics2D op, Color col, ArrayList<Point> toCopy) {
        ArrayList<Point> used = new ArrayList<>();
        for (Point point : toCopy) {
            int ci = (int) (4 * random());
            int off = Arrays.asList(cols).indexOf(point.color) + 1;
            Color c = cols[(ci + off) % 4];
            op.setColor(c);
            int x = point.x;
            int y = point.y;
            op.fillRect(x*pixelDim, y*pixelDim, pixelDim, pixelDim);
            used.add(new Point(point.x, point.y, c));
        }

        return used;
    }

    private ArrayList<Point> getUsedInArea(ArrayList<Point> usedAlready, Area area) {
        double used = 0;
        ArrayList<Point> filtered = new ArrayList();
        for (Point p : usedAlready) {
            if (area.contains(p.x, p.y)) {
                filtered.add(p);
            }
        }
        return filtered;
    }

    private int getTotalAreaPixels(Area area) {
        int a = 0;
        for (int y = 0; y < oph; y++) {
            for (int x = 0; x < opw; x++) {
                if (area.contains(x, y)) {
                    a++;
                }
            }
        }

        return a;
    }

    private double random() {
        return random.nextDouble();
    }

    private Area getAreaUnion(String[] chars) {

        Area or = null;
        int i = 0;
        for (String str : chars) {
            Area a1 = getCharacterArea(chars[i], 1);
            if (or == null) {
                or = a1;
            } else {
                or.add(a1);
            }
            i++;
        }
        return or;
    }

    private Area getAreaIntersection(String[] chars) {

        Area intersects = null;
        int i = 0;
        for (String str : chars) {
            Area a1 = getCharacterArea(chars[i], 1);
            if (intersects == null) {
                intersects = a1;
            } else {
                intersects.intersect(a1);
            }
            i++;
        }
        return intersects;
    }

    private Area getAreaSubtract(String[] chars) {

        Area subtracts = null;
        int i = 0;
        for (String str : chars) {
            Area a1 = getCharacterArea(chars[i], 1);
            if (subtracts == null) {
                subtracts = a1;
            } else {
                subtracts.subtract(a1);
            }
            i++;
        }
        return subtracts;
    }

    private Area getCharacterArea(String str, float ff) {
        float fontSize = opw *ff;

        BufferedImage opImage = createAlphaBufferedImage(opw, oph);
        Graphics2D opG = (Graphics2D) opImage.getGraphics();

        Font newFont = font.deriveFont(fontSize);
        opG.setFont(newFont);
        GlyphVector glyphVector = newFont.createGlyphVector(opG.getFontRenderContext(), str);
        Shape textShape = glyphVector.getOutline();
        int w = textShape.getBounds().width;
        int h = textShape.getBounds().height;

        AffineTransform at = new AffineTransform();
        AffineTransform tr = AffineTransform.getTranslateInstance(opw / 2 - w / 2, oph / 2 + h / 2);
        at.concatenate(tr);

        Shape transformed = at.createTransformedShape(textShape);
        Area area = new Area(transformed);
        return area;
    }

    private void saveLayered(BufferedImage opImageA, BufferedImage opImageB, BufferedImage opImageC, BufferedImage opImageD, String file) {
        BufferedImage opImage = createAlphaBufferedImage(opw, oph);
        Graphics2D op = (Graphics2D) opImage.getGraphics();
        op.setColor(WHITE);
        op.fillRect(0, 0, opw, oph);

        mixImage(op, opImageA, opImageB, opImageC, opImageD);
        savePNGFile(opImage, dir + imagesName + opSuff + file + opExt, 100);
    }

    private void mixImage(Graphics2D op, BufferedImage opImageA, BufferedImage opImageB, BufferedImage opImageC, BufferedImage opImageD) {
        for (int y=0; y<oph; y++) {
            for (int x=0; x<opw; x++) {
                Color colA = new Color(opImageA.getRGB(x, y));
                Color colB = new Color(opImageB.getRGB(x, y));
                //Color colC = new Color(opImageC.getRGB(x, y));
                //Color colD = new Color(opImageD.getRGB(x, y));
                Color res = KMColorUtils.mix(colA, colB);
                op.setColor(res);
                op.fillRect(x,y,1,1);
            }

        }
    }

    private Color getAlpha(Color col, double d) {
        int al = (int) (d * 255);
        return new Color(col.getRed(), col.getGreen(), col.getBlue(), al);
    }


    private void save() {
        double dpi = 100;
        savePNGFile(opImage1, dir + op1src, dpi);
        savePNGFile(opImage2, dir + op2src, dpi);
        savePNGFile(opImage4, dir + op4src, dpi);
        savePNGFile(opImage8, dir + op8src, dpi);
    }

    private void init() throws IOException, FontFormatException {
        File file = new File(fontDir + fontName);
        font = Font.createFont(Font.TRUETYPE_FONT, file);

        opImage1 = createAlphaBufferedImage(opw, oph);
        op1 = (Graphics2D) opImage1.getGraphics();
        op1.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        op1.setColor(Color.WHITE);
        op1.fillRect(0, 0, opw, oph);


        opImage2 = createAlphaBufferedImage(opw, oph);
        op2 = (Graphics2D) opImage2.getGraphics();
        op2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        op2.setColor(Color.WHITE);
        op2.fillRect(0, 0, opw, oph);


        opImage4 = createAlphaBufferedImage(opw, oph);
        op4 = (Graphics2D) opImage4.getGraphics();
        op4.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);

        opImage8 = createAlphaBufferedImage(opw, oph);
        op8 = (Graphics2D) opImage8.getGraphics();
        op8.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);

        ipImage1 = ImageIO.read(new File(dir + ip1src));
        ipImage2 = ImageIO.read(new File(dir + ip2src));
        ipImage3 = ImageIO.read(new File(dir + ip3src));
        ipImage4 = ImageIO.read(new File(dir + ip4src));
        ipImage5 = ImageIO.read(new File(dir + ip5src));
        ipImage6 = ImageIO.read(new File(dir + ip6src));
        ipImage7 = ImageIO.read(new File(dir + ip7src));
        ipImage8 = ImageIO.read(new File(dir + ip8src));
        ipImage9 = ImageIO.read(new File(dir + ip9src));
        ipImage10 = ImageIO.read(new File(dir + ip10src));
        ipImage11 = ImageIO.read(new File(dir + ip11src));
        ipImage12 = ImageIO.read(new File(dir + ip12src));
        ipImage13 = ImageIO.read(new File(dir + ip13src));
        ipImage14 = ImageIO.read(new File(dir + ip14src));
        ipImage15 = ImageIO.read(new File(dir + ip15src));

        ipw = ipImage1.getWidth();
        iph = ipImage1.getHeight();
    }
}
