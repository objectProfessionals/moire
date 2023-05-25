package com.op.moire.fourstack.sets;

import com.op.moire.Base;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static java.awt.Color.WHITE;

public class FourStackSets extends Base {


    private static FourStackSets fourStackSets = new FourStackSets();
    private String imagesDir = "sets";
    private String imagesName = "test";
    private String dir = hostDir + "fourStack/" + imagesDir + "/";
    private String ipExt = ".png";
    private String opExt = ".png";
    private String opSuff = "_SETS";

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

    int f = 1;
    int ff = 880;
    int ipw = 10 * f;
    int iph = 10 * f;
    int opw = ff * f;
    int oph = ff * f;


    public static void main(String[] args) throws Exception {
        fourStackSets.doAll();
    }

    private void doAll() throws Exception {
        init();
        test();
        save();
    }

    private void test() {
//        int sOff = 30;
//        int sOn = 35;
//        int pOff = 40;
//        int pOn = 45;
//        int tOff = 50;
//        int tOn = 55;
//        int qOff = 60;
//        int qOn = 65;

        int sOff = 35;
        int sOn = 40;
        int pOff = 50;
        int pOn = 55;
        int tOff = 65;
        int tOn = 70;
        int qOff = 80;
        int qOn = 85;

        boolean[] blacks = {false, true, false, true, false, true, false, true, false, true, false, true, false, true, false};

        int n1 = blacks[0] ? sOn : sOff;
        int n2 = blacks[1] ? sOn : sOff;
        int n3 = blacks[2] ? pOn : pOff;
        int n4 = blacks[3] ? sOn : sOff;
        int n5 = blacks[4] ? pOn : pOff;
        int n6 = blacks[5] ? pOn : pOff;
        int n7 = blacks[6] ? tOn : tOff;
        int n8 = blacks[7] ? sOn : sOff;
        int n9 = blacks[8] ? pOn : pOff;
        int n10 = blacks[9] ? pOn : pOff;
        int n11 = blacks[10] ? tOn : tOff;
        int n12 = blacks[11] ? pOn : pOff;
        int n13 = blacks[12] ? tOn : tOff;
        int n14 = blacks[13] ? tOn : tOff;
        int n15 = blacks[14] ? qOn : qOff;

        int n1i2 = n1 + n2 - n3;
        int n1i4 = n1 + n4 - n5;
        int n2i4 = n2 + n4 - n6;
        int n2i8 = n2 + n8 - n10;
        int n4i8 = n4 + n8 - n12;
        int n1i8 = n1 + n8 - n9;

        //P (A U B U C) = P(A) + P(B) + P(C) - P(A ∩ B) - P(A ∩ C) - P(B ∩ C) + P(A ∩ B ∩ C)
        int n1i2i4 = -1 * (n1 + n2 + n4 - n1i2 - n2i4 - n1i4 - n7);
        int n2i4i8 = -1 * (n2 + n4 + n8 - n2i4 - n2i8 - n4i8 - n14);
        int n1i2i8 = -1 * (n1 + n2 + n8 - n1i2 - n1i8 - n2i8 - n11);
        int n1i4i8 = -1 * (n1 + n4 + n8 - n1i4 - n1i8 - n4i8 - n13);

        int n1i2i4i8 = n1 + n2 + n4 + n8
                - n1i2 - n1i4 - n1i8 - n2i4 - n2i8 - n4i8
                + n1i2i4 + n1i2i8 + n2i4i8 + n1i4i8
                - n15;

        int nABD = n1i2i8 - n1i2i4i8;
        int nABC = n1i2i4 - n1i2i4i8;
        int nACD = n1i4i8 - n1i2i4i8;
        int nBCD = n2i4i8 - n1i2i4i8;

        int nAB = n1i2 - n1i2i4i8 - nABD - nABC;
        int nAC = n1i4 - n1i2i4i8 - nACD - nABC;
        int nAD = n1i8 - n1i2i4i8 - nABD - nACD;
        int nBC = n2i4 - n1i2i4i8 - nBCD - nABC;
        int nBD = n2i8 - n1i2i4i8 - nABD - nBCD;
        int nCD = n4i8 - n1i2i4i8 - nACD - nBCD;

        int n_2 = n2 - n1i2;
        int n_4 = n4 - n2i4;
        int n_8 = n8 - n1i8;
        /*
        P(A ∩ B ∩ C ∩ D) =
        P(A) + P(B) + P(C) +P(D)
        - P(A ∩ B) - P(A ∩ C) - P(A ∩ D)- P(B ∩ C) - P(B ∩ D) - P(C ∩ D)
        + P(A ∩ B ∩ C) + P(A ∩ B ∩ D) + P(A ∩ C ∩ D) + P(B ∩ C ∩ D)
        - P (A U B U C U D).
         */

        ArrayList<Integer> used = new ArrayList<>();
        ArrayList<Integer> pABCD = getPoints(n1i2i4i8);
        addAll(used, pABCD);
        ArrayList<Integer> pBCD = getUnusedPoints(n2i4i8, used);
        addAll(used, pBCD);
        ArrayList<Integer> pACD = getUnusedPoints(n1i4i8, used);
        addAll(used, pACD);
        ArrayList<Integer> pABD = getUnusedPoints(n1i2i8, used);
        addAll(used, pABD);
        ArrayList<Integer> pABC = getUnusedPoints(n1i2i4, used);
        addAll(used, pABC);
        ArrayList<Integer> pCD = getUnusedPoints(n4i8, used);
        addAll(used, pCD);
        ArrayList<Integer> pAD = getUnusedPoints(n1i8, used);
        addAll(used, pAD);
        ArrayList<Integer> pAB = getUnusedPoints(n1i2, used);
        addAll(used, pAB);
        ArrayList<Integer> pAC = getUnusedPoints(n1i4, used);
        addAll(used, pAC);
        ArrayList<Integer> pBC = getUnusedPoints(n2i4, used);
        addAll(used, pBC);
        ArrayList<Integer> pBD = getUnusedPoints(n2i8, used);
        addAll(used, pBD);

        System.out.println(used.size());
        for (int i : used) {
            System.out.println(i);
        }

        return;
    }

    private void addAll(ArrayList<Integer> used, ArrayList<Integer> toAdd) {
        for (int i : toAdd) {
            if (!used.contains(i)) {
                used.add(i);
            }
        }
    }

    private ArrayList<Integer> getUnusedPoints(int numToUse, ArrayList<Integer> used) {
        int max = 100;
        ArrayList<Integer> usedThisTime = new ArrayList();
        for (int i = 0; i < numToUse; i++) {
            int r = (int) (Math.random() * max);
            while (usedThisTime.contains(r) || used.contains(r)) {
                r = (int) (Math.random() * max);
            }
            usedThisTime.add(r);
        }

        return usedThisTime;
    }

    private ArrayList<Integer> getPoints(int numToUse) {
        int max = 100;
        ArrayList<Integer> used = new ArrayList();
        for (int i = 0; i < numToUse; i++) {
            int r = (int) (Math.random() * max);
            while (used.contains(r)) {
                r = (int) (Math.random() * max);
            }
            used.add(r);
        }

        return used;
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
