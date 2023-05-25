package com.op.moire.fourstack.circles;

import com.op.moire.Base;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.awt.Color.WHITE;

public class FourStackCirclesCut extends Base {


    private static FourStackCirclesCut fourStackCircles = new FourStackCirclesCut();
    private String imagesDir = "circles";
    private String imagesName = "test";
    private String dir = hostDir + "fourStack/" + imagesDir + "/";
    private String ipExt = ".png";
    private String opExt = ".png";
    private String opSuff = "_CUT";

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

    Area rect1;
    Area rect2;

    int ipw = 10;
    int iph = 10;
    int opw = 2000;
    int oph = 2000;


    public static void main(String[] args) throws Exception {
        fourStackCircles.doAll();
    }

    private void doAll() throws Exception {
        init();
        draw();
        save();
    }

    private void draw() {

        for (int y = 0; y < iph; y++) {
            for (int x = 0; x < ipw; x++) {
                draw(x, y);
            }
        }
        paint(op1, rect1);
        paint(op2, rect2);
        saveLayered(opImage1, opImage2, null, null, "3");

    }

    private void draw(int x, int y) {
        int d = opw / ipw;
        int off = 100;
        boolean b1 = new Color(ipImage1.getRGB(x, y)).getRed() < off;
        boolean b2 = new Color(ipImage2.getRGB(x, y)).getRed() < off;
        boolean b3 = new Color(ipImage3.getRGB(x, y)).getRed() < off;
        boolean b4 = new Color(ipImage4.getRGB(x, y)).getRed() < off;
        boolean b5 = new Color(ipImage5.getRGB(x, y)).getRed() < off;
        boolean b6 = new Color(ipImage6.getRGB(x, y)).getRed() < off;
        boolean b7 = new Color(ipImage7.getRGB(x, y)).getRed() < off;
        boolean b8 = new Color(ipImage8.getRGB(x, y)).getRed() < off;
        boolean b9 = new Color(ipImage9.getRGB(x, y)).getRed() < off;
        boolean b10 = new Color(ipImage10.getRGB(x, y)).getRed() < off;
        boolean b11 = new Color(ipImage11.getRGB(x, y)).getRed() < off;
        boolean b12 = new Color(ipImage12.getRGB(x, y)).getRed() < off;
        boolean b13 = new Color(ipImage13.getRGB(x, y)).getRed() < off;
        boolean b14 = new Color(ipImage14.getRGB(x, y)).getRed() < off;
        boolean b15 = new Color(ipImage15.getRGB(x, y)).getRed() < off;

        double r1 = opw/(2*ipw);
        double r2 = r1*0.9;
        drawCircle(rect1, x, y, r1, b1);
        drawCircle(rect2, x, y, r2, b2);

        double r3 = r1*0.8;
        drawCircle(rect1, x, y, r3, b3);
        drawCircle(rect2, x, y, r3, b3);
    }

    private void paint(Graphics2D op, Area rect) {
        op.setColor(Color.BLACK);
        op.fill(rect);
    }

    private void drawCircle(Area rect, int cx, int cy, double r, boolean b) {
        if (b) {
            double d = opw/ipw;
            double x0 = d*cx - r;
            double y0 = d*cy - r;
            rect.subtract(new Area(new Ellipse2D.Double(x0, y0, r*2, r*2)));
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
        opImage1 = createAlphaBufferedImage(opw, oph);
        op1 = (Graphics2D) opImage1.getGraphics();
        op1.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        rect1 = new Area(new Rectangle2D.Double(0, 0, opw, oph));

        opImage2 = createAlphaBufferedImage(opw, oph);
        op2 = (Graphics2D) opImage2.getGraphics();
        op2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        rect2 = new Area(new Rectangle2D.Double(0, 0, opw, oph));

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

    }

    private void saveLayered(BufferedImage opImageA, BufferedImage opImageB, BufferedImage opImageC, BufferedImage opImageD, String file) {
        BufferedImage opImage = createAlphaBufferedImage(opw, oph);
        Graphics2D op = (Graphics2D) opImage.getGraphics();
        op.setColor(WHITE);
        op.fillRect(0, 0, opw, oph);
        if (opImageA != null) {
            op.drawImage(opImageA, null, null);
        }
        if (opImageB != null) {
            op.drawImage(opImageB, null, null);
        }
        if (opImageC != null) {
            op.drawImage(opImageC, null, null);
        }
        if (opImageD != null) {
            op.drawImage(opImageD, null, null);
        }
        savePNGFile(opImage, dir + imagesName + opSuff + file + opExt, 100);
    }


}
