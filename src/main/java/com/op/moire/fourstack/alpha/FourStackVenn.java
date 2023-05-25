package com.op.moire.fourstack.alpha;

import com.op.moire.Base;
import com.op.moire.fourstack.sets.KMColorUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;

public class FourStackVenn extends Base {


    private static FourStackVenn fourStackAlpha = new FourStackVenn();
    private String imagesDir = "testSmall";
    private String imagesName = "test";
    private String dir = hostDir + "fourVenn/" + imagesDir + "/";
    private String ipExt = ".png";
    private String opExt = ".png";
    private String opSuff = "_VENN";

//    public int ss[] = {40, 60};
//    public int pp[] = {52, 68};
//    public int tt[] = {84, 100};

//    public static int ss[] = {20 * 2, 30 * 2};
//    public static int pp[] = {30 * 2, 40 * 2};
//    public static int tt[] = {40 * 2, 50 * 2};

    public static int ss[] = {8, 10};
    public static int pp[] = {10, 12};
    public static int tt[] = {12, 14};


    private CalculateVenn venn;
    HashMap<String, Integer[]> matches = new HashMap();

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
    int scale = 10;
    int opw = ipw * scale;
    int oph = iph * scale;

    boolean shuffle = false;
    Random random = new Random(1);

    public static void main(String[] args) throws Exception {
        fourStackAlpha.doAll();
    }

    private void doAll() throws Exception {
        init();

        draw();

        save();
        saveLayered(opImage1, opImage2, null, null, "3");
        //saveLayered(opImage1, opImage4, null, null, "5");
        //saveLayered(opImage2, opImage4, null, null, "15");
        //saveLayered(opImage1, opImage2, opImage4, null, "7");
    }

    private void draw() {

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
        boolean[] bs = {b1, b2, b3, b4, b5, b6, b7};
        //b8, b9, b10, b11, b12, b13, b14, b15};
        String key = "";
        for (boolean bn : bs) {
            key = key + (bn ? "1" : "0");
        }

        Integer match[] = matches.get(key);

        drawMatches(x, y, match, bs);
    }

    private void drawMatches(int x, int y, Integer[] match, boolean[] bs) {

        int sc = scale;
        //ArrayList<Cell> cells = getCellsByIntersection(x, y, bs, match, sc);
        ArrayList<Cell> cells = getCellsByUnion(x, y, bs, match, sc);

        if (!shuffle) {
            int count = 0;
            int xx = x * sc;
            int yy = y * sc;
            while (count < cells.size()) {
                if (xx == (x + 1) * sc) {
                    xx = x * sc;
                    yy = yy + 1;
                }

                Cell cell = cells.get(count);
                if (cell.fA) {
                    fill(op1, xx, yy);
                }
                if (cell.fB) {
                    fill(op2, xx, yy);
                }
                if (cell.fC) {
                    fill(op4, xx, yy);
                }
                count++;
                xx++;
            }
        } else {
            Collections.shuffle(cells);
            int count = 0;
            boolean used[][] = new boolean[10][10];
            while (count < cells.size()) {
                int xxx = (int) (Math.random() * sc);
                int yyy = (int) (Math.random() * sc);
                if (!used[xxx][yyy]) {
                    int xx = x * sc + xxx;
                    int yy = y * sc + yyy;
                    Cell cell = cells.get(count);
                    if (cell.fA) {
                        fill(op1, xx, yy);
                    }
                    if (cell.fB) {
                        fill(op2, xx, yy);
                    }
                    if (cell.fC) {
                        fill(op4, xx, yy);
                    }
                    count++;
                }
            }
        }
    }

    private ArrayList<Cell> getCellsByIntersection(int x, int y, boolean[] bs,
                                                   Integer[] match, int sc) {
        double nA = match[0];
        double nB = match[1];
        double nAnB = match[2];
        double nC = match[3];
        double nAnC = match[4];
        double nBnC = match[5];
        double nAnBnC = match[6];


        int usedA = 0;
        int usedB = 0;
        int usedAB = 0;
        int usedC = 0;
        int usedAC = 0;
        int usedBC = 0;
        int usedABC = 0;

        ArrayList<Cell> cells = new ArrayList();
        for (int yy = y * sc; yy < (y + 1) * sc; yy++) {
            for (int xx = x * sc; xx < (x + 1) * sc; xx++) {
                if (usedABC < nAnBnC) {
                    cells.add(new Cell(true, true, true));
                    usedABC++;
                } else if (usedAB < nAnB) {
                    cells.add(new Cell(true, true, false));
                    usedAB++;
                } else if (usedAC < nAnC) {
                    cells.add(new Cell(true, false, true));
                    usedAC++;
                } else if (usedBC < nBnC) {
                    cells.add(new Cell(false, true, true));
                    usedBC++;
                } else if (usedA < nA) {
                    cells.add(new Cell(true, false, false));
                    usedA++;
                } else if (usedB < nB) {
                    cells.add(new Cell(false, true, false));
                    usedB++;
                } else if (usedC < nC) {
                    cells.add(new Cell(false, false, true));
                    usedC++;
                } else {
                    cells.add(new Cell(false, false, false));
                }
            }
        }

        //checkAll(cells, match);
        if (cells.size() != 100) {
            System.out.println("ERROR1");
        }
        int usedAll = usedABC + usedAB + usedAC + usedBC + usedA + usedB + usedC;
        if ((usedAll != tt[0] && !bs[6]) || (usedAll != tt[1] && bs[6])) {
            System.out.println("ERROR2: " + bs[0] + "," + bs[1] + "," + bs[2] + "," + bs[3] + "," + bs[4] + "," + bs[5] + "," + bs[6] + ",");
        }
        return cells;
    }

    private ArrayList<Cell> getCellsByUnion(int x, int y, boolean[] bs,
                                            Integer[] match, int sc) {
        double nA = bs[0] ? ss[1] : ss[0];
        double nB = bs[0] ? ss[1] : ss[0];
        double nAuB = bs[0] ? pp[1] : pp[0];
        double nC = bs[0] ? ss[1] : ss[0];
        double nAuC = bs[0] ? pp[1] : pp[0];
        double nBuC = bs[0] ? pp[1] : pp[0];
        double nAuBuC = bs[0] ? tt[1] : tt[0];


        int usedA = 0;
        int usedB = 0;
        int usedAB = 0;
        int usedC = 0;
        int usedAC = 0;
        int usedBC = 0;
        int usedABC = 0;

        ArrayList<Cell> cells = new ArrayList();
        for (int yy = y * sc; yy < (y + 1) * sc; yy++) {
            for (int xx = x * sc; xx < (x + 1) * sc; xx++) {
                if (usedABC < nAuBuC) {
                    double r = random.nextDouble();
                    if (r<0.1) {
                        cells.add(new Cell(true, true, true));
                        usedABC++;
                        usedAB++;
                        usedAC++;
                        usedBC++;
                        usedA++;
                        usedB++;
                        usedC++;
                    } else if (r < 0.2) {
                        cells.add(new Cell(true, true, false));
                        usedAB++;
                        usedA++;
                        usedB++;
                    } else if (r < 0.3) {
                        cells.add(new Cell(true, false, false));
                        usedAB++;
                        usedA++;
                        usedB++;
                    }
                } else if (usedAB < nAuB) {
                    cells.add(new Cell(true, true, false));
                    usedAB++;
                } else if (usedAC < nAuC) {
                    cells.add(new Cell(true, false, true));
                    usedAC++;
                } else if (usedBC < nBuC) {
                    cells.add(new Cell(false, true, true));
                    usedBC++;
                } else if (usedA < nA) {
                    cells.add(new Cell(true, false, false));
                    usedA++;
                } else if (usedB < nB) {
                    cells.add(new Cell(false, true, false));
                    usedB++;
                } else if (usedC < nC) {
                    cells.add(new Cell(false, false, true));
                    usedC++;
                } else {
                    cells.add(new Cell(false, false, false));
                }
            }
        }

        //checkAll(cells, match);
        if (cells.size() != 100) {
            System.out.println("ERROR1");
        }
        int usedAll = usedABC + usedAB + usedAC + usedBC + usedA + usedB + usedC;
        if ((usedAll != tt[0] && !bs[6]) || (usedAll != tt[1] && bs[6])) {
            System.out.println("ERROR2: " + bs[0] + "," + bs[1] + "," + bs[2] + "," + bs[3] + "," + bs[4] + "," + bs[5] + "," + bs[6] + ",");
        }

        return cells;
    }

    private void checkAll(ArrayList<Cell> cells, Integer[] match) {
        int as = 0;
        int bs = 0;
        int cs = 0;
        for (Cell cell : cells) {
            if (cell.fA) as++;
            if (cell.fB) bs++;
            if (cell.fC) cs++;
        }

        if ((as != match[0]) || (bs != match[1]) || (cs != match[3])) {
            System.out.println("CHECK FAILED");
        }

    }

    private void fill(Graphics2D op, int x, int y) {
        op.fillRect(x, y, 1, 1);
    }

    private void save() {
        double dpi = 100;
        savePNGFile(opImage1, dir + op1src, dpi);
        savePNGFile(opImage2, dir + op2src, dpi);
        savePNGFile(opImage4, dir + op4src, dpi);
//        savePNGFile(opImage8, dir + op8src, dpi);
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
        opw = ipw * scale;
        oph = iph * scale;

        opImage1 = createAlphaBufferedImage(opw, oph);
        op1 = (Graphics2D) opImage1.getGraphics();
        op1.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        op1.setColor(WHITE);
        op1.fillRect(0, 0, opw, oph);
        op1.setColor(BLACK);

        opImage2 = createAlphaBufferedImage(opw, oph);
        op2 = (Graphics2D) opImage2.getGraphics();
        op2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        op2.setColor(WHITE);
        op2.fillRect(0, 0, opw, oph);
        op2.setColor(BLACK);

        opImage4 = createAlphaBufferedImage(opw, oph);
        op4 = (Graphics2D) opImage4.getGraphics();
        op4.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        op4.setColor(WHITE);
        op4.fillRect(0, 0, opw, oph);
        op4.setColor(BLACK);

        opImage8 = createAlphaBufferedImage(opw, oph);
        op8 = (Graphics2D) opImage8.getGraphics();
        op8.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        op8.setColor(WHITE);
        op8.fillRect(0, 0, opw, oph);
        op8.setColor(BLACK);

        venn = new CalculateVenn(ss, pp, tt);
        matches = venn.getAll();
    }

    private void saveLayered(BufferedImage opImageA, BufferedImage opImageB, BufferedImage opImageC, BufferedImage opImageD, String file) {
        BufferedImage opImage = createAlphaBufferedImage(opw, oph);
        Graphics2D op = (Graphics2D) opImage.getGraphics();

        mixImageByBlack(op, opImageA, opImageB, opImageC, opImageD);
        //mixImageByGrey(op, opImageA, opImageB, opImageC, opImageD);
        //mixImageByColors(op, opImageA, opImageB, opImageC, opImageD);
        savePNGFile(opImage, dir + imagesName + opSuff + file + opExt, 100);
    }

    private void mixImageByBlack(Graphics2D op, BufferedImage opImageA, BufferedImage opImageB, BufferedImage opImageC, BufferedImage opImageD) {
        for (int y = 0; y < oph; y++) {
            for (int x = 0; x < opw; x++) {
                double gA = 0;
                double gB = 0;
                double gC = 0;

                if (opImageA != null) {
                    gA = 1 - ((double) (new Color(opImageA.getRGB(x, y)).getRed())) / 255.0;
                }
                if (opImageB != null) {
                    gB = 1 - ((double) (new Color(opImageB.getRGB(x, y)).getRed())) / 255.0;
                }
                if (opImageC != null) {
                    gC = 1 - ((double) (new Color(opImageC.getRGB(x, y)).getRed())) / 255.0;
                }

                float tot = (float) (gA + gB + gC);
                if (tot > 1) {
                    tot = 1;
                }
                Color col = new Color(0, 0, 0, tot);
                op.setColor(col);
                op.fillRect(x, y, 1, 1);
            }
        }
    }

    private void mixImageByGrey(Graphics2D op, BufferedImage opImageA, BufferedImage opImageB, BufferedImage opImageC, BufferedImage opImageD) {
        for (int y = 0; y < oph; y++) {
            for (int x = 0; x < opw; x++) {
                int rgbA = opImageA.getRGB(x, y);
                int gA = (rgbA >> 24) & 0xff;
                int rgbB = opImageB.getRGB(x, y);
                int gB = (rgbB >> 24) & 0xff;
                int gC = 0;
                if (opImageC != null) {
                    int rgbC = opImageC.getRGB(x, y);
                    gC = (rgbC >> 24) & 0xff;
                }

                float tot = (float) (gA + gB + gC);
                if (tot > 1) {
                    tot = 1;
                }
                Color col = new Color(0, 0, 0, tot);
                op.setColor(col);
                op.fillRect(x, y, 1, 1);
            }

        }
    }

    private void mixImageByColors(Graphics2D op, BufferedImage opImageA, BufferedImage opImageB, BufferedImage opImageC, BufferedImage opImageD) {
        for (int y = 0; y < oph; y++) {
            for (int x = 0; x < opw; x++) {
                Color colA = new Color(opImageA.getRGB(x, y));
                Color colB = new Color(opImageB.getRGB(x, y));
                Color res = KMColorUtils.mix(colA, colB);
                Color res3 = res;
                if (opImageC != null) {
                    Color colC = new Color(opImageC.getRGB(x, y));
                    Color res2 = KMColorUtils.mix(res, colC);
                    if (opImageD != null) {
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
