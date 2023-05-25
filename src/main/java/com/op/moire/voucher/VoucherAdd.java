package com.op.moire.voucher;

import com.op.moire.Base;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class VoucherAdd extends Base {
    private static final VoucherAdd voucherAdd = new VoucherAdd();
    int ipw = 1143;
    int iph = 429;
    double opwmm = 210;
    double ophmm = 297;
    double dpi = 145;
    double mm2in = 25.4;
    int opw = (int) (dpi * opwmm / mm2in);
    int oph = (int) (dpi * ophmm / mm2in);

    private String imagesDir = "vouchers";
    private String imagesName = "voucher";
    private String imagesType = "8";
    private String[] imagesNames = {"5c", "4c", "4d"};
    private String ext = ".png";
    private String dir = hostDir + imagesDir + "/";

    BufferedImage opImage;
    BufferedImage ipImage;
    Graphics2D opG;

    public static void main(String[] args) throws Exception {
        voucherAdd.doAll();
    }

    private void doAll() throws IOException, FontFormatException {
        init();
        combineAll();
    }

    private void combineAll() throws IOException {
        int xOff = (opw - ipw)/2;
        int yOff = (oph - (imagesNames.length * iph)) / 2;
        for (String imgSuff : imagesNames) {
            ipImage = ImageIO.read(new File(dir + imagesName + imgSuff + ".jpg"));
            opG.drawImage(ipImage, xOff, yOff, null);

            yOff = yOff + iph;
        }
        savePNGFile(opImage, dir + imagesName + imagesType + ext, dpi);
    }

    private void init() throws IOException, FontFormatException {
        opImage = createBufferedImage(opw, oph);
        opG = (Graphics2D) opImage.getGraphics();
        opG.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        opG.setColor(Color.WHITE);
        opG.fillRect(0, 0, opw, oph);

    }


}
