package com.op.moire.fourstack;

import com.op.moire.Base;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class FourStackSpan extends Base {

    private static final FourStackSpan fourStack = new FourStackSpan();
    private String imagesDir = "test";
    private String imagesName = "test";
    private String dir = hostDir + "fourStack/" + imagesDir + "/";
    private String ipExt = ".png";
    private String opSuff = "_OUT";
    private String opExt = ".png";
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

    private String op1src = imagesName + opSuff+"1" + opExt;
    private String op2src = imagesName + opSuff+"2" + opExt;
    private String op4src = imagesName + opSuff+"4" + opExt;
    private String op8src = imagesName + opSuff+"8" + opExt;

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
    int ipw = 0;
    int iph = 0;
    int opw = 0;
    int oph = 0;
    int opFactor = 8;

    HashMap<String, Value[]> stored = new HashMap<>();

    public static void main(String[] args) throws Exception {
        fourStack.doAll();
    }

    private void doAll() throws IOException {
        initImages();

        drawAll();

        saveAsImages();
    }

    private void drawAll() {
        for (int y = 0; y < iph; y++) {
            for (int x = 0; x < ipw; x++) {
                drawCell(x, y);
            }
        }

    }

    private void drawCell(int x, int y) {
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

        Boolean[] Blacks = {b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15};
        String bl = "";
        for (boolean b : Blacks) {
            bl = bl + (b ? "1" : "0");
        }
        Value[] result = stored.get(bl);
        if (result == null) {
            boolean[] blacks = {b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15};
            result = calculate(blacks);
            stored.put(bl, result);
        }

        Value v1 = new Value("X10357090BD0F");
        Value v2 = new Value("6X230700AB0EF");
        Value v4 = new Value("6CX4570000DEF");
        Value v8 = new Value("0C000X89ABDEF");

        drawCell(op1, x, y, result[0], v1);
        drawCell(op2, x, y, result[1], v2);
        drawCell(op4, x, y, result[2], v4);
        drawCell(op8, x, y, result[3], v8);
    }

    private void drawCell(Graphics2D op, int x, int y, Value value, Value gv) {

        int dim = 4;

        double[] greys = {1, 1, 0.33, 1, 0.33, 0.33, 0.25, 1, 0.33, 0.33, 0.25, 0.33, 0.23, 0.23, 0.2};
        String[] pos = {};
        char[] chars = value.v.toCharArray();
        int i = 0;
        int d = 2;
        for (int yy = 0; yy < dim*2; yy = yy +d) {
            for (int xx = 0; xx < dim*2; xx = xx+ d) {
                if (i < chars.length && chars[i] == '1') {

                    int g = 255 - (int)(greys[i]*255.0);
                    Color col = new Color(g, g, g);
                    op.setColor(col);

                    op.fillRect(opFactor*x + xx, opFactor*y + yy, d, d);
                }
                i++;
            }
        }
    }

    Value[] calculate(boolean[] blacks) {
        Value v1 = new Value("X10357090BD0F");
        Value v2 = new Value("6X230700AB0EF");
        Value v4 = new Value("6CX4570000DEF");
        Value v8 = new Value("0C000X89ABDEF");

        replaceWith1s(blacks[0], v1, "1");
        replaceWith1s(blacks[1], v2, "2");
        replaceWith1s(blacks[3], v4, "4");
        replaceWith1s(blacks[7], v8, "8");

        Value[] vs = {v1, v2, v4, v8};
        replaceWith1s(blacks[2], vs, "3");
        replaceWith1s(blacks[4], vs, "5");
        replaceWith1s(blacks[5], vs, "6");
        replaceWith1s(blacks[6], vs, "7");
        replaceWith1s(blacks[8], vs, "9");
        replaceWith1s(blacks[9], vs, "A");
        replaceWith1s(blacks[10], vs, "B");
        replaceWith1s(blacks[11], vs, "C");
        replaceWith1s(blacks[12], vs, "D");
        replaceWith1s(blacks[13], vs, "E");
        replaceWith1s(blacks[14], vs, "F");

        replaceWith1s(true, vs, "X");

        return vs;
    }

    private void replaceWith1s(boolean b, Value v1, String s) {
        if (b) {
            v1.v = v1.v.replaceAll(s, "1");
        } else {
            v1.v = v1.v.replaceAll(s, "0");
        }
    }

    private void replaceWith1s(boolean b, Value[] vs, String s) {
        for (Value v : vs) {
            if (b) {
                v.v = v.v.replaceAll(s, "1");
            } else {
                v.v = v.v.replaceAll(s, "0");
            }
        }
    }

    private void saveAsImages() {
        double dpi = 100;
        savePNGFile(opImage1, dir + op1src, dpi);
        savePNGFile(opImage2, dir + op2src, dpi);
        savePNGFile(opImage4, dir + op4src, dpi);
        savePNGFile(opImage8, dir + op8src, dpi);
    }

    private void initImages() throws IOException {
        System.out.println("Reading...");
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
        opw = ipw * opFactor;
        oph = iph * opFactor;
        System.out.println("Creating...");

        opImage1 = createAlphaBufferedImage(opw, oph);
        op1 = (Graphics2D) opImage1.getGraphics();
        op1.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        op1.setColor(Color.WHITE);
        op1.fillRect(0, 0, opw, oph);
        op1.setColor(Color.BLACK);

        opImage2 = createAlphaBufferedImage(opw, oph);
        op2 = (Graphics2D) opImage2.getGraphics();
        op2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        op2.setColor(Color.WHITE);
        op2.fillRect(0, 0, opw, oph);
        op2.setColor(Color.BLACK);

        opImage4 = createAlphaBufferedImage(opw, oph);
        op4 = (Graphics2D) opImage4.getGraphics();
        op4.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        op4.setColor(Color.WHITE);
        op4.fillRect(0, 0, opw, oph);
        op4.setColor(Color.BLACK);

        opImage8 = createAlphaBufferedImage(opw, oph);
        op8 = (Graphics2D) opImage8.getGraphics();
        op8.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        op8.setColor(Color.WHITE);
        op8.fillRect(0, 0, opw, oph);
        op8.setColor(Color.BLACK);

    }

}
