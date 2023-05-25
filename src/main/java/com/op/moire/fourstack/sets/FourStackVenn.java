package com.op.moire.fourstack.sets;

import com.op.moire.Base;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import static java.awt.Color.CYAN;
import static java.awt.Color.WHITE;

public class FourStackVenn extends Base {

    private static FourStackVenn fourStackVenn = new FourStackVenn();
    private String imagesDir = "venn";
    private String imagesName = "test";
    private String dir = hostDir + "fourStack/" + imagesDir + "/";
    private String ipExt = ".png";
    private String opExt = ".png";
    private String opSuff = "_VENN";
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

    int ipw = 10;
    int iph = 10;
    int pixelDim = 9;
    int opw = pixelDim * ipw;
    int oph = pixelDim * iph;


    public static void main(String[] args) throws Exception {
        fourStackVenn.doAll();
    }

    private void doAll() throws Exception {
        init();
        test();
        save();

        //saveLayered(opImage1, opImage2, null, null, "3");
        //saveLayered(opImage2, opImage4, null, null, "6");
    }

    private void test() {
        for (int y = 0; y < iph; y++) {
            for (int x = 0; x < ipw; x++) {
                drawCell(x, y, op1);
            }
        }

        return;
    }

    private void drawCell(int x, int y, Graphics2D op) {
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

        String row1 = "811111111";
        String row2 = "893333332";
        String row3 = "897777E62";
        String row4 = "89BFFFE62";
        String row5 = "89BFFFE62";
        String row6 = "89BFFFE62";
        String row7 = "89BDDDD62";
        String row8 = "8CCCCCC62";
        String row9 = "444444442";
        String[] rows = {row1, row2, row3, row4, row5, row6, row7, row8, row9};

        String r = "F";
        int fillF = b15 ? 9 : 6;
        int total = 0;
        rows = replaceRows(rows, r, 0, fillF);

        int rem = 9 - fillF;
        r = "D";
        int fillD = b13 ? 4 : 2;
        rows = replaceRows(rows, r, rem, fillD);

        rem = rem + fillD;
        int fillB = b11 ? 4 : 2;
        r = "B";
        rows = replaceRows(rows, r, rem, fillB);

        rem = 9 + 2 + 2 - fillD;
        int fill9 = b9 ? 4 : 2;
        r = "9";
        rows = replaceRows(rows, r, rem, fill9);

        rem = 9 + 2 + 2 + 2 - fillD;
        int fill7 = b7 ? rem + 2 : rem;
        r = "7";
        rows = replaceRows(rows, r, rem, fill7);

        rem = 9 + 2 + 2 + 2 + 2 - fillD;
        int fill5 = b5 ? rem + 2 : rem;
        r = "5";
        rows = replaceRows(rows, r, rem, fill5);

        rem = 9 + 2 + 2 + 2 + 2 + 2 - fillD;
        int fill3 = b3 ? rem + 2 : rem;
        r = "3";
        rows = replaceRows(rows, r, rem, fill3);

        rem = 9 + 2 + 2 + 2 + 2 + 2 + 2 - fillD;
        int fill1 = b7 ? rem + 2 : rem;
        r = "1";
        rows = replaceRows(rows, r, rem, fill1);

        drawX(op1, CYAN, rows, x, y);
    }

    private String[] replaceRows(String[] rows, String r, int start, int fill) {
        int y = 0;
        int i = start;
        for (String row : rows) {
            int c = 0;
            while (i < fill && c < row.length()) {
                if (row.contains(r)) {
                    row = row.replaceFirst(r, "X");
                    i++;
                }
                c++;
            }
            rows[y] = row;
            y++;
        }
        return rows;
    }

    private void drawX(Graphics2D op, Color col, String[] rows, int x, int y) {
        int r = 0;
        for (String row : rows) {
            int c = 0;
            for (int i = 0; i < row.length(); i++) {
                String ch = row.substring(i, i + 1);
                if ("X".equals(ch)) {
                    int xx = x * pixelDim + c;
                    int yy = y * pixelDim + r;
                    op.setColor(col);
                    op.fillRect(xx, yy, 1, 1);
                }
                c++;
            }
            r++;
        }

    }

    private double random() {
        return random.nextDouble();
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

        opImage2 = createAlphaBufferedImage(opw, oph);
        op2 = (Graphics2D) opImage2.getGraphics();
        op2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);

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
