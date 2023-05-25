package com.op.moire.fourstack.cmykvalues;

import com.op.moire.Base;
import com.op.moire.fourstack.sets.KMColorUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import static java.awt.Color.WHITE;

public class FourStackValues extends Base {

    private static final FourStackValues fourStack = new FourStackValues();
    private String imagesDir = "values";
    private String imagesName = "testl";
    private String dir = hostDir + "fourStack/" + imagesDir + "/";
    private String ipExt = ".png";
    private String opExt = ".png";
    private String opSuff = "_VAL";
    private String valuesFile = "PIXEL_1.csv";
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

    private String op1src = imagesName + opSuff + "1" + opExt;
    private String op2src = imagesName + opSuff + "2" + opExt;
    private String op4src = imagesName + opSuff + "4" + opExt;
    private String op8src = imagesName + opSuff + "8" + opExt;

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
    int opFactor = 2;
    HashMap<String, Quad> bools2Quad = new HashMap<>();
    Random random = new Random(1);

    public static void main(String[] args) throws Exception {
        fourStack.doAll();
    }

    private void doAll() throws IOException {
        initImages();

        readCSVFile();
        drawAll();

        saveLayered(opImage1, opImage2, null, null, "3");
        saveLayered(opImage1, opImage2, opImage4, null, "7");
        saveLayered(opImage1, opImage2, opImage4, opImage8, "15");
        saveAsImages();
    }

    private void readCSVFile() {
        BufferedReader reader;
        int c = 1;
        try {
            reader = new BufferedReader(new FileReader(
                    dir + valuesFile));
            String line = reader.readLine();
            while (line != null) {
                //System.out.println(c + ":" + line);
                line = reader.readLine();
                if (line == null) {
                    continue;
                }
                String pixels = line.substring(0, line.indexOf(","));
                String rest = line.substring(line.indexOf(",") + 1);
                String other = rest.substring(rest.indexOf(",") + 1);
                String cell1 = pixels.substring(1, 5);
                String cell2 = pixels.substring(6, 10);
                String cell4 = pixels.substring(11, 15);
                String cell8 = pixels.substring(16, 20);
                Pixel p1 = new Pixel(cell1, false);
                Pixel p2 = new Pixel(cell2, false);
                Pixel p4 = new Pixel(cell4, false);
                Pixel p8 = new Pixel(cell8, false);
                Quad quad = new Quad(p1, p2, p4, p8, false);
                bools2Quad.put(other, quad);
                c++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void drawAll() {
//        String parms = calculate.getAllParms();
//        printWriter.println(parms);
        for (int y = 0; y < iph; y++) {
            for (int x = 0; x < ipw; x++) {
                drawCell(x, y);
            }
        }

    }

    private void drawCell(int x, int y) {
        System.out.println("x,y:" + x + "," + y);
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

        int offset = (int)(random.nextDouble()*4);

        Boolean[] blacks = {b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15};
        String bl = getBlacksString(blacks);
        Quad quad = bools2Quad.get(bl);

        int c = blacks.length-1;
        while (quad == null) {
            System.out.println(bl + ": does not exist");
            blacks[c] = false;
            bl = getBlacksString(blacks);
            quad = bools2Quad.get(bl);
            c--;
        }

        drawCell(op1, x, y, quad.p1, offset);
        drawCell(op2, x, y, quad.p2, offset);
        drawCell(op4, x, y, quad.p4, offset);
        drawCell(op8, x, y, quad.p8, offset);
    }

    private String getBlacksString(Boolean[] blacks) {
        String bl = "";
        for (boolean b : blacks) {
            bl = bl + (b ? "true," : "false,");
        }
        bl = bl.substring(0, bl.length() - 1);
        return bl;
    }

    private void drawCell(Graphics2D op, int x, int y, Pixel pix, int offset) {

        int dim = 2;
        char[] chars = pix.cell.toCharArray();
        char[] charsOff = {chars[offset], chars[(offset+1)%4], chars[(offset+2)%4],chars[(offset+3)%4]};
        int i = 0;
        for (int yy = 0; yy < dim; yy++) {
            for (int xx = 0; xx < dim; xx++) {
                if (i < charsOff.length) {
                    String colStr = Character.toString(charsOff[i]);
                    Color col = Pixel.getColor(colStr);
                    op.setColor(col);
                    op.fillRect(x * opFactor + xx, y * opFactor + yy, 1, 1);
                }
                i++;
            }
        }
    }

    private void drawCellCircle(Graphics2D op, int x, int y, Pixel pix) {

        char[] chars = pix.cell.toCharArray();
        int degs = 0;
        int degsD = 360 / chars.length;
        for (int i = 0; i < chars.length; i++) {
            String colStr = Character.toString(chars[i]);
            Color col = Pixel.str2Col.get(colStr);
            op.setColor(col);
            op.fillArc(x * opFactor, y * opFactor, opFactor, opFactor, degs, degsD);

            degs = degs + degsD;
            //op.fillRect(x * opFactor + xx, y * opFactor + yy, 1, 1);
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

    private void saveLayered(BufferedImage opImageA, BufferedImage opImageB, BufferedImage opImageC, BufferedImage opImageD, String file) {
        BufferedImage opImage = createAlphaBufferedImage(opw, oph);
        Graphics2D op = (Graphics2D) opImage.getGraphics();
        op.setColor(WHITE);
        op.fillRect(0, 0, opw, oph);

        mixImage(op, opImageA, opImageB, opImageC, opImageD);
        savePNGFile(opImage, dir + imagesName + opSuff + file + opExt, 100);
    }

    private void mixImage(Graphics2D op, BufferedImage opImageA, BufferedImage opImageB, BufferedImage opImageC, BufferedImage opImageD) {
        for (int y = 0; y < oph; y++) {
            for (int x = 0; x < opw; x++) {
                Color colA = new Color(opImageA.getRGB(x, y));
                Color colB = new Color(opImageB.getRGB(x, y));
                Color res = KMColorUtils.mix(colA, colB);
                Color res3 = res;
                if (opImageC !=null) {
                    Color colC = new Color(opImageC.getRGB(x, y));
                    Color res2 = KMColorUtils.mix(res, colC);
                    if (opImageD !=null) {
                        Color colD = new Color(opImageD.getRGB(x, y));
                        res3 = KMColorUtils.mix(res2, colD);
                    } else {
                        res3 = res2;
                    }
                } else {
                    res3 = res;
                }
                op.setColor(res3);
                op.fillRect(x, y, 1, 1);
            }

        }
    }
}
