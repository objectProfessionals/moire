package com.op.moire.fourstack.random;

import com.op.moire.Base;

import java.awt.*;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Random;

import static java.awt.Color.*;

public class FourStackRandom extends Base {
    private static final FourStackRandom fourStackRandom = new FourStackRandom();

    private String imagesDir = "random";
    private String dir = hostDir + "fourStack/" + imagesDir + "/";

    private BufferedImage bi1;
    private Graphics2D g2D1;
    private BufferedImage bi2;
    private Graphics2D g2D2;
    private BufferedImage bi3;
    private Graphics2D g2D3;
    private BufferedImage boA;
    private Graphics2D g2DA;
    private BufferedImage boB;
    private Graphics2D g2DB;


    private int size = 200;
    private Font font;
    private Random random = new Random(1);
    Area usedSh = new Area();

    public static void main(String[] args) {
        System.out.println("Started...");
        fourStackRandom.start();
        System.out.println("Finished...");
    }

    private void start() {
        setup();

        draw();
        save();
    }

    private void draw() {
        Shape sh1 = getShape("1");
        Shape sh2 = getShape("2");
        Shape sh3 = getShape("3");
        g2DA.setStroke(new BasicStroke(1));
        g2DA.setColor(BLACK);
        g2DB.setStroke(new BasicStroke(1));
        g2DB.setColor(BLACK);
        //g2DA.draw(sh1);
        //g2DA.draw(sh3);
        //g2DB.draw(sh2);
        //g2DB.draw(sh3);

        int used = 0;
        int num = size * size / 5;
        int s = size / 200;
        int dNone = s * 2;
        int dUnique = s * 4;
        int dPairOnly = s * 2;
        int dPair = s * 6;

        for (int i = 0; i < num; i++) {
            double x = (int) (((double) (size)) * random.nextDouble());
            double y = (int) (((double) (size)) * random.nextDouble());

            boolean in1 = sh1.contains(x, y);
            boolean in2 = sh2.contains(x, y);
            boolean in3 = sh3.contains(x, y);

            int xx = (int) x;
            int yy = (int) y;

            if (in3 && !in1 && !in2) {
                if (alreadyUsed(xx, yy, dPair)) {
                    continue;
                }
                Color[] col = chooseRndCols(CYAN, YELLOW);
                fillPoint(g2DA, col[0], xx, yy, dPair);
                fillPoint(g2DB, col[1], xx, yy, dPair);
                usedSh.add(createUsedArea(xx, yy, dPair));
            } else if (in3) {
                if (alreadyUsed(xx, yy, dUnique)) {
                    continue;
                }
                if (in1 && in2) {
                    Color[] col = chooseRndCols(CYAN, YELLOW);
                    if (random.nextBoolean()) {
                        fillPoint(g2DA, col[0], xx, yy, dUnique);
                    } else {
                        fillPoint(g2DB, col[1], xx, yy, dUnique);
                    }
                    usedSh.add(createUsedArea(xx, yy, dUnique));
                } else{
                    Color[] col = chooseRndCols(CYAN, YELLOW);
                    if (in1) {
                        fillPoint(g2DA, col[0], xx, yy, dUnique);
                    }
                    if (in2) {
                        fillPoint(g2DB, col[1], xx, yy, dUnique);
                    }
                    usedSh.add(createUsedArea(xx, yy, dUnique));
                }
            } else if (!in1 && !in2 && !in3) {
                if (alreadyUsed(xx, yy, dNone)) {
                    continue;
                }
                Color[] col = chooseRndCols(YELLOW, CYAN);
                if (random.nextBoolean()) {
                    fillPoint(g2DA, col[0], xx, yy, dNone);
                } else {
                    fillPoint(g2DB, col[1], xx, yy, dNone);
                }
                usedSh.add(createUsedArea(xx, yy, dNone));
            } else if (((in1 && !in2) || (!in1 && in2)) && !in3) {
                if (alreadyUsed(xx, yy, dUnique)) {
                    continue;
                }
                Color[] col = chooseRndCols(CYAN, MAGENTA);
                if (in1) {
                    fillPoint(g2DA, col[0], xx, yy, dUnique);
                }
                if (in2) {
                    fillPoint(g2DB, col[1], xx, yy, dUnique);
                }
                usedSh.add(createUsedArea(xx, yy, dUnique));
            } else if (in1 && in2 && !in3) {
                if (alreadyUsed(xx, yy, dPairOnly)) {
                    continue;
                }
                Color[] col = chooseRndCols(CYAN, MAGENTA);
                if (random.nextBoolean()) {
                    fillPoint(g2DA, col[0], xx, yy, dPairOnly);
                } else {
                    fillPoint(g2DB, col[1], xx, yy, dPairOnly);
                }
                usedSh.add(createUsedArea(xx, yy, dPairOnly));
            } else {
                continue;
            }

            used++;
        }
        System.out.println("alreadyUsed=" + used);
    }

    private Color[] chooseRndCols(Color col1, Color col2) {
        Color[] arr = {col1, col2};
        if (random.nextDouble() < 0.5) {
            Color[] arr2 = {col2, col1};
            return arr2;
        }
        return arr;
    }

    private Area createUsedArea(int xx, int yy, int d) {
        return new Area(new Rectangle(xx - (d / 2) - 1, yy - (d / 2) - 1, d + 2, d + 2));
    }

    private boolean alreadyUsed(int xx, int yy, int d) {
        Rectangle key = new Rectangle(xx - (d / 2), yy - (d / 2), d, d);
        if (((Shape) usedSh).contains(key)) {
            return true;
        }

        return false;
    }

    Shape getShape(String str) {
        int scale = size / 10;
        GlyphVector gv = font.createGlyphVector(g2DA.getFontRenderContext(), str);
        Shape sh = gv.getOutline();
        Rectangle rect = sh.getBounds();
        AffineTransform at = new AffineTransform();
        AffineTransform tr = AffineTransform.getTranslateInstance((size - ((rect.width - rect.x) * scale)) / 2,
                (((rect.height - rect.y) * scale)) / 2);
        AffineTransform sc = AffineTransform.getScaleInstance(scale, scale);
        at.concatenate(tr);
        at.concatenate(sc);

        return at.createTransformedShape(sh);
    }

    private void fillPoint(Graphics2D g2D, Color col, int xx, int yy, int d) {
        g2D.setColor(col);
        g2D.fillRect(xx - (d / 2), yy - (d / 2), d, d);
    }

    private void setup() {
        bi1 = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
        g2D1 = (Graphics2D) bi1.getGraphics();
        g2D1.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2D1.setColor(Color.WHITE);
        g2D1.fillRect(0, 0, size, size);

        bi2 = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
        g2D2 = (Graphics2D) bi2.getGraphics();
        g2D2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2D2.setColor(Color.WHITE);
        g2D2.fillRect(0, 0, size, size);

        bi3 = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
        g2D3 = (Graphics2D) bi3.getGraphics();
        g2D3.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2D3.setColor(Color.WHITE);
        g2D3.fillRect(0, 0, size, size);

        boA = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
        g2DA = (Graphics2D) boA.getGraphics();
        g2DA.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2DA.setColor(Color.WHITE);
        g2DA.fillRect(0, 0, size, size);

        boB = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
        g2DB = (Graphics2D) boB.getGraphics();
        g2DB.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2DB.setColor(Color.WHITE);
        g2DB.fillRect(0, 0, size, size);

        Font f = g2D1.getFont();
        font = f.deriveFont(size * 10);
        g2D1.setFont(f);
        g2D2.setFont(f);
        g2D3.setFont(f);
        g2DA.setFont(f);
        g2DB.setFont(f);
    }


    private void save() {
        mergeFile(boA, boB, dir + "op3.png", 100);

        savePNGFile(boA, dir + "opA.png", 100);
        savePNGFile(boB, dir + "opB.png", 100);
    }

    private void mergeFile(BufferedImage boA, BufferedImage boB, String file, int dpi) {
        BufferedImage bo = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2D = (Graphics2D) bo.getGraphics();
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.setColor(Color.WHITE);
        g2D.fillRect(0, 0, size, size);

        HashMap<Color, String> col2Str = new HashMap<>();
        col2Str.put(CYAN, "C");
        col2Str.put(MAGENTA, "M");
        col2Str.put(YELLOW, "Y");
        col2Str.put(RED, "R");
        col2Str.put(GREEN, "G");
        col2Str.put(BLUE, "B");
        col2Str.put(WHITE, "W");

        HashMap<String, Color> add2Str = new HashMap<>();
        add2Str.put("CC", CYAN);
        add2Str.put("MM", MAGENTA);
        add2Str.put("YY", YELLOW);

        add2Str.put("WW", WHITE);
        add2Str.put("WC", CYAN);
        add2Str.put("CW", CYAN);
        add2Str.put("WM", MAGENTA);
        add2Str.put("MW", MAGENTA);
        add2Str.put("WY", YELLOW);
        add2Str.put("YW", YELLOW);

        add2Str.put("CM", BLUE);
        add2Str.put("MC", BLUE);
        add2Str.put("YC", GREEN);
        add2Str.put("CY", GREEN);
        add2Str.put("YM", RED);
        add2Str.put("MY", RED);

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                Color cola = new Color(boA.getRGB(x, y));
                Color colb = new Color(boB.getRGB(x, y));

                String colA = col2Str.get(cola);
                String colB = col2Str.get(colb);
                Color col3 = add2Str.get(colA + colB);
                g2D.setColor(col3);
                g2D.fillRect(x, y, 1, 1);
            }
        }

        savePNGFile(bo, file, dpi);
    }

}
