package com.op.moire.fourstack.cmykline;

import com.op.moire.Base;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FourStackCMYK extends Base {


    private static FourStackCMYK fourStackLine = new FourStackCMYK();
    private String imagesDir = "line";
    private String imagesName = "test";
    private String dir = hostDir + "fourStack/" + imagesDir + "/";
    private String opExt = ".png";
    private String opSuff = "_CMYK";
    private String fontDir = "fonts/";
    private String fontName = "NEWTOWN.TTF";
    private Font font;

    private String op1src = imagesName + opSuff + "1" + opExt;

    BufferedImage ipImage1;
    BufferedImage ipImage2;
    BufferedImage opImage1;
    Graphics2D op1;

    int opw = 1000;
    int oph = 1000;


    public static void main(String[] args) throws Exception {
        fourStackLine.doAll();
    }

    private void init() throws IOException, FontFormatException {
        File file = new File(fontDir + fontName);
        font = Font.createFont(Font.TRUETYPE_FONT, file);
//        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//        ge.registerFont(font);

        opImage1 = createAlphaBufferedImage(opw, oph);
        op1 = (Graphics2D) opImage1.getGraphics();
        op1.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        op1.setColor(Color.WHITE);
        op1.fillRect(0, 0, opw, oph);

        ipImage1 = ImageIO.read(new File(dir + "DOTS3.png"));
        ipImage2 = ImageIO.read(new File(dir + "DOTS2.png"));

    }

    private void doAll() throws Exception {
        init();
        //drawSingle("1", op1, ipImage1, Color.CYAN, 0);
        Color[] cols = {Color.CYAN, Color.MAGENTA, Color.YELLOW, Color.BLACK};
        double[] angs = {0, 15, 30, 45, 60};
        double dF = ((double) opw) / 8;
        int c = 0;
        for (double y = 0; y < 8; y++) {
            for (double x = 0; x < 8; x++) {
                int i = (int) (y * 8 + x) % 4;
                Color col = cols[i];

                if (c % 2 > 0) {
                    drawCell(x * dF, y * dF, op1, ipImage1, cols[0], angs[0]);
                }
                if (c/2 % 4 > 0) {
                    drawCell(x * dF, y * dF, op1, ipImage1, cols[1], angs[1]);
                }
                if (c/4 % 8 > 0) {
                    drawCell(x * dF, y * dF, op1, ipImage1, cols[2], angs[2]);
                }
                c++;
            }
        }
        save();
    }

    private void drawCell(double x, double y, Graphics2D opG, BufferedImage ipG, Color col, double ang1) {
        Color colb = getAlpha(col, 0.5);
        opG.setColor(colb);

        AffineTransform rot = AffineTransform.getRotateInstance(Math.toRadians(ang1), 125 / 2, 125 / 2);
        AffineTransform trans = AffineTransform.getTranslateInstance(x, y);
        trans.concatenate(rot);
        BufferedImage toPaint = createAlphaBufferedImage(125, 125);
        Graphics2D g2d = ((Graphics2D) toPaint.getGraphics());
        g2d.setColor(colb);
        for (int yy = 0; yy < 125; yy++) {
            for (int xx = 0; xx < 125; xx++) {
                if (new Color(ipG.getRGB(xx, yy)).equals(Color.BLACK)) {

                    g2d.fillRect(xx, yy, 1, 1);
                }
            }
        }

        opG.drawImage(toPaint, trans, null);

        opG.setClip(null);
    }

    private void drawSingle(String char1, Graphics2D opG, BufferedImage ipG, Color col, double ang1) {
        float fontSize = 500;
        double alpha = 0.33;
        Font newFont = font.deriveFont(fontSize);
        opG.setFont(newFont);
        GlyphVector glyphVector = newFont.createGlyphVector(opG.getFontRenderContext(), char1);
        Shape textShape = glyphVector.getOutline();
        int w = textShape.getBounds().width;
        int h = textShape.getBounds().height;

        AffineTransform at = new AffineTransform();
        AffineTransform tr = AffineTransform.getTranslateInstance(opw / 2 - w / 2, oph / 2 + h / 2);
        at.concatenate(tr);

        Shape transformed = at.createTransformedShape(textShape);

        Color colb = getAlpha(col, alpha * 0.5);
        opG.setColor(colb);
        opG.clip(transformed);

        AffineTransform trans = AffineTransform.getRotateInstance(Math.toRadians(ang1), opw / 2, oph / 2);
        BufferedImage toPaint = createAlphaBufferedImage(opw, oph);
        Graphics2D g2d = ((Graphics2D) toPaint.getGraphics());
        g2d.setColor(colb);
        for (int y = 0; y < oph; y++) {
            for (int x = 0; x < oph; x++) {
                if (new Color(ipG.getRGB(x, y)).equals(Color.BLACK)) {

                    g2d.fillRect(x, y, 1, 1);
                }
            }
        }

        opG.drawImage(toPaint, trans, null);

        opG.setClip(null);
    }

    private void drawStacked() {
        float size = 1000;
        double alpha = 0.5;
        draw("1", "3", op1, Color.CYAN, size, alpha, 0, 30);
    }

    private void draw(String char1, String char2, Graphics2D opG, Color col, float fontSize, double alpha, double ang1, double ang2) {

        Font newFont = font.deriveFont(fontSize);
        opG.setFont(newFont);
        GlyphVector glyphVector = newFont.createGlyphVector(opG.getFontRenderContext(), char1);
        Shape textShape = glyphVector.getOutline();
        int w = textShape.getBounds().width;
        int h = textShape.getBounds().height;

        AffineTransform at = new AffineTransform();
        AffineTransform tr = AffineTransform.getTranslateInstance(opw / 2 - w / 2, oph / 2 + h / 2);
        at.concatenate(tr);

        Shape transformed = at.createTransformedShape(textShape);

        Color colb = getAlpha(col, alpha * 0.5);
        opG.setColor(colb);
        opG.clip(transformed);

        AffineTransform trans = AffineTransform.getRotateInstance(Math.toRadians(ang1), opw / 2, oph / 2);
        BufferedImage toPaint = createAlphaBufferedImage(opw, oph);
        Graphics2D g2d = ((Graphics2D) toPaint.getGraphics());
        g2d.setColor(colb);
        for (int y = 0; y < oph; y++) {
            for (int x = 0; x < oph; x++) {
                if (new Color(ipImage2.getRGB(x, y)).equals(Color.BLACK)) {

                    g2d.fillRect(x, y, 1, 1);
                }
            }
        }

        opG.drawImage(toPaint, trans, null);

        opG.setClip(null);

        glyphVector = newFont.createGlyphVector(opG.getFontRenderContext(), char2);
        textShape = glyphVector.getOutline();
        w = textShape.getBounds().width;
        h = textShape.getBounds().height;

        at = new AffineTransform();
        tr = AffineTransform.getTranslateInstance(opw / 2 - w / 2, oph / 2 + h / 2);
        at.concatenate(tr);

        transformed = at.createTransformedShape(textShape);

        colb = getAlpha(col, alpha * 0.5);
        opG.setColor(colb);
        opG.clip(transformed);

        trans = AffineTransform.getRotateInstance(Math.toRadians(ang2), opw / 2, oph / 2);
        toPaint = createAlphaBufferedImage(opw, oph);
        g2d = ((Graphics2D) toPaint.getGraphics());
        g2d.setColor(colb);
        for (int y = 0; y < oph; y++) {
            for (int x = 0; x < oph; x++) {
                if (new Color(ipImage1.getRGB(x, y)).equals(Color.BLACK)) {

                    g2d.fillRect(x, y, 1, 1);
                }
            }
        }

        opG.drawImage(toPaint, trans, null);

        opG.setClip(null);


        Area area = new Area(new Rectangle2D.Double(0, 0, opw, oph));
        area.subtract(new Area(transformed));

        colb = getAlpha(col, alpha * 0.5);
        opG.setColor(colb);
        opG.clip(area);

        trans = AffineTransform.getRotateInstance(Math.toRadians(ang2), opw / 2, oph / 2);
        toPaint = createAlphaBufferedImage(opw, oph);
        g2d = ((Graphics2D) toPaint.getGraphics());
        g2d.setColor(colb);
        for (int y = 0; y < oph; y++) {
            for (int x = 0; x < oph; x++) {
                if (new Color(ipImage1.getRGB(x, y)).equals(Color.BLACK)) {

                    g2d.fillRect(x, y, 1, 1);
                }
            }
        }

        opG.drawImage(toPaint, trans, null);

        opG.setClip(null);
    }

    private Color getAlpha(Color col, double d) {
        int al = (int) (d * 255);
        return new Color(col.getRed(), col.getGreen(), col.getBlue(), al);
    }


    private void save() {
        double dpi = 100;
        savePNGFile(opImage1, dir + op1src, dpi);
    }


}
