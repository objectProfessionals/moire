package com.op.moire.stacked;

import com.op.moire.Base;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CreateStubs extends Base {

    private static CreateStubs all = new CreateStubs();
    private static final String dir = "misc/cards/stacked/";

    private int w = 5;
    private int h = 5;

    public static void main(String[] args) throws IOException {
        all.createFiles("ip1.png", "wwwwwwbbbwwbwbwwbbbwwwwww");
        all.createFiles("ip2.png", "bwwwwwbwwwwwbwwwwwbwwwwwb");
        all.createFiles("ip3.png", "wwwwbwwwbwwwbwwwbwwwbwwww");
        all.createFiles("h1.png", "wbbbwbwwwbbwwwbbwwwbwbbbw");
        all.createFiles("h2.png", "wwbwwwwbwwbbbbbwwbwwwwbww");
    }

    private void createFiles(String fileName, String cols) throws IOException {
        String src = hostDir + dir;
        int f = 4;
        File ip1 = new File(src + fileName);
        BufferedImage obi1 = new BufferedImage(w * f, h * f,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D opG1 = (Graphics2D) obi1.getGraphics();
        opG1.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        opG1.setColor(Color.WHITE);
        opG1.fillRect(0, 0, w * f, h * f);

        opG1.setColor(Color.BLACK);
        for (int i = 0; i < cols.length(); i++) {
            String sub = cols.substring(i, i + 1);
            int x = f * (i % w);
            int y = f * (i / w);
            if ("b".equals(sub)) {
                opG1.fillRect(x, y, f, f);
            }
        }
        ImageIO.write(obi1, "png", ip1);
    }

}
