package com.op.moire.voucher;

import com.op.moire.Base;

import java.awt.*;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class VoucherSVG extends Base {
    private static final VoucherSVG voucherAdd = new VoucherSVG();
    double opwmm = 164;
    double ophmm = 69;
    double dpi = 145;
    double mm2in = 25.4;
    int opw = (int) (dpi * opwmm / mm2in);
    int oph = (int) (dpi * ophmm / mm2in);


    private String imagesDir = "vouchers";
    private String imagesName = "voucherTop";
    private String ext = ".png";
    private String dir = hostDir + imagesDir + "/";
    private String fontDir = "../host/fonts/";
    private String fontName = "MARKER.TTF";

    BufferedImage opImage;
    BufferedImage ipImage;
    Graphics2D opG;
    private Font font;

    public static void main(String[] args) throws Exception {
        voucherAdd.doAll();
    }

    private void doAll() throws IOException, FontFormatException {
        init();
        combineAll();
    }

    private void combineAll() throws IOException {
        float fontSize = 200;
        Font newFont = font.deriveFont(fontSize);
        opG.setFont(newFont);
        GlyphVector glyphVector = newFont.createGlyphVector(opG.getFontRenderContext(), "V");
        Shape textShape = glyphVector.getOutline();

        int w = textShape.getBounds().width;
        int h = textShape.getBounds().height;

        AffineTransform at = new AffineTransform();
        AffineTransform tr = AffineTransform.getTranslateInstance(opw / 2 - w / 2, oph / 2 + h / 2);
        at.concatenate(tr);

        Shape transformed = at.createTransformedShape(textShape);
        //drawShapeByPoints(opG, transformed);
        opG.draw(transformed);

        double ang = -45;
        double sinA = Math.sin(Math.toRadians(ang));
        double cosA = Math.cos(Math.toRadians(ang));
        double tanA = Math.tan(Math.toRadians(ang));
        double wCA = ((double) opw) * cosA;
        double hSA = ((double) opw) * tanA;
        for (int y = 0; y < h * 5; y = y + 10) {
            drawLine(opG, 0, y, (int) opw, y + (int) hSA, transformed);
        }

        savePNGFile(opImage, dir + imagesName + ext, dpi);
    }

    private void drawShapeByPoints(Graphics2D opG, Shape shape) {
        PathIterator it = shape.getPathIterator(null);
        ArrayList<Point2D.Double> points = new ArrayList<>();
        float[] coords = new float[6];
        int n = 0;
        for (; !it.isDone(); it.next()) {
            int type = it.currentSegment(coords);
            System.out.println("coords: " + Arrays.toString(coords));
            Point2D.Double p = new Point2D.Double(coords[0], coords[1]);
            points.add(p);
            n++;
        }

        for (Point2D.Double p : points) {
        }

    }

    private void drawLine(Graphics2D opG, double x1, double y1, double x2, double y2, Shape transformed) {
        double xd = (x2 - x1);
        double yd = (y2 - y1);
        int lx = (int) x1;
        int ly = (int) y1;
        for (double d = 0; d < 1; d = d + 0.001) {
            int xx = (int) (x1 + xd * d);
            int yy = (int) (y1 + yd * d);
            if (transformed.contains(xx, yy)) {
                opG.drawLine(lx, ly, xx, yy);
            }
            lx = xx;
            ly = yy;
        }

    }

    private void init() throws IOException, FontFormatException {
        opImage = createBufferedImage(opw, oph);
        opG = (Graphics2D) opImage.getGraphics();
        opG.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        opG.setColor(Color.WHITE);
        opG.fillRect(0, 0, opw, oph);

        File file = new File(fontDir + fontName);
        font = Font.createFont(Font.TRUETYPE_FONT, file);
        opG.setColor(Color.BLACK);
    }


}
