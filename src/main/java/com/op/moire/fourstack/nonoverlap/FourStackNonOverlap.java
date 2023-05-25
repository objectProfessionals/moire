package com.op.moire.fourstack.nonoverlap;

import com.op.moire.Base;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;

public class FourStackNonOverlap extends Base {


    private static FourStackNonOverlap fourStackConentric = new FourStackNonOverlap();
    private String imagesDir = "nonOverlap/testMed";
    private String imagesName = "test";
    private String dir = hostDir + "fourStack/" + imagesDir + "/";
    private String ipExt = ".png";
    private String opExt = ".png";
    private String opSuff = "_NOVL";

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

    int ipw = 0;
    int iph = 0;
    int opw = 0;
    int oph = 0;


    public static void main(String[] args) throws Exception {
        fourStackConentric.doAll();
    }

    private void doAll() throws Exception {
        init();
        drawBlocks();
        save();
    }

    private void drawBlocks() {

        for (int y = 0; y < iph; y++) {
            for (int x = 0; x < ipw; x++) {
                drawLines(x, y);
            }
        }

        saveLayered(opImage1, opImage2, null, null, "3");
//        saveLayered(opImage1, opImage4, null, null, "5");
//        saveLayered(opImage1, opImage2, opImage4, null, "7");
//        saveLayered(opImage1, opImage8, null, null, "9");
        saveLayered(opImage1, opImage2, opImage4, opImage8, "15");
    }

        /*
        1248356790ABCDEF
        CMYKCMYKCMYKCMYK

        CMYCMYCMYCM
        MYCMYCMYCMY
        YXMYCMYCMYC

        30,50,00,700,90,00,B00,,00,D00,000,F000,1111111,0,0,0
        03,00,60,070,00,A0,0B0,00,000,E00,0F00,0,2222222,0,0
        00,05,06,007,,00,00,000,C0,0D0,0E0,00F0,0,0,4444444,0
        00,0,00,00,000,09,0A,00B,0C,00D,00E,000F,0,0,0,8888888
         */

    private void drawLines(int x, int y) {
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

        int xx = x * 16;
        int yy = y * 6;
        drawLine(op1, xx, yy, b1, 0);
        drawLine(op2, xx, yy, b2, 1);
        //3
        drawMultiLineA(op1, xx, yy, 2);
        drawMultiLineB(op2, xx, yy, b3, 2);
        //4
        drawLine(op4, xx, yy, b4, 3);
        //5
        drawMultiLineA(op1, xx, yy, 4);
        drawMultiLineC(op4, xx, yy, b5, 4);
        //6
        drawMultiLineB(op2, xx, yy, b6, 5);
        drawMultiLineC(op4, xx, yy, b6, 5);
        //7
        drawMultiLineA(op1, xx, yy, 6);
        drawMultiLineB(op2, xx, yy, b7, 6);
        drawMultiLineC(op4, xx, yy, b7, 6);
        //8
        drawLine(op8, xx, yy, b8, 7);
        //9
        drawMultiLineA(op1, xx, yy, 8);
        drawMultiLineD(op8, xx, yy, b9, 8);
        //10
        drawMultiLineB(op2, xx, yy, b10, 9);
        drawMultiLineD(op8, xx, yy, b10, 9);
        //11
        drawMultiLineA(op1, xx, yy, 10);
        drawMultiLineB(op2, xx, yy, b11, 10);
        drawMultiLineD(op8, xx, yy, b11, 10);
        //12
        drawMultiLineC(op4, xx, yy, b12, 11);
        drawMultiLineD(op8, xx, yy, b12, 11);
        //13
        drawMultiLineA(op1, xx, yy, 12);
        drawMultiLineC(op4, xx, yy, b13, 12);
        drawMultiLineD(op8, xx, yy, b13, 12);
        //14
        drawMultiLineB(op2, xx, yy, b14, 13);
        drawMultiLineC(op4, xx, yy, b14, 13);
        drawMultiLineD(op8, xx, yy, b14, 13);
        //15
        drawMultiLineA(op1, xx, yy, 14);
        drawMultiLineB(op2, xx, yy, b15, 14);
        drawMultiLineC(op4, xx, yy, b15, 14);
        drawMultiLineD(op8, xx, yy, b15, 14);
    }

    private void drawLine(Graphics2D opg, int x, int y, boolean b, int offX) {

        opg.setColor(BLACK);
        int str = b ? 2 : 1;
        opg.drawLine(x + offX, y, x + offX, y + str);
    }

    private void drawMultiLineA(Graphics2D opg, int x, int y, int offX) {

        opg.setColor(BLACK);
        int offY = 0;
        int str = 1;
        opg.drawLine(x + offX, y + offY, x + offX, y + offY + str);
    }

    private void drawMultiLineB(Graphics2D opg, int x, int y, boolean b, int offX) {

        opg.setColor(BLACK);
        int offY = b ? 1 : 0;
        int str = 1;
        opg.drawLine(x + offX, y + offY, x + offX, y + offY + str);
    }

    private void drawMultiLineC(Graphics2D opg, int x, int y, boolean b, int offX) {

        opg.setColor(BLACK);
        int offY = b ? 2 : 1;
        int str = 1;
        opg.drawLine(x + offX, y + offY, x + offX, y + offY + str);
    }

    private void drawMultiLineD(Graphics2D opg, int x, int y, boolean b, int offX) {

        opg.setColor(BLACK);
        int offY = b ? 3 : 2;
        int str = 1;
        opg.drawLine(x + offX, y + offY, x + offX, y + offY + str);
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

        opw = ipw * 16;
        oph = iph * 6;

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
    }
}
