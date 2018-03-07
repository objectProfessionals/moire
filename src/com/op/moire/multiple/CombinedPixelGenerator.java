package com.op.moire.multiple;

import java.util.HashMap;

public class CombinedPixelGenerator {
    public static final int W = 4;
    public static final int H = 4;

    public static final int SB = 9;
    public static final int SW = 6;
    public static final int HB = 12;
    public static final int HW = 9;
    public static final int H2B = 16;
    public static final int H2W = 12;

    public SourceBlackPixel getNewBlackSourcePixel() {
        return new SourceBlackPixel(SB, W, H);
    }

    public SourceWhitePixel getNewWhiteSourcePixel() {
        return new SourceWhitePixel(SW, W, H);
    }

    public HiddenBlackPixel getNewBlackHiddenPixel() {
        return new HiddenBlackPixel(HB, W, H);
    }

    public HiddenWhitePixel getNewWhiteHiddenPixel() {
        return new HiddenWhitePixel(HW, W, H);
    }

    public HiddenBlackPixel getNewBlackHidden2Pixel() {
        return new HiddenBlackPixel(H2B, W, H);
    }

    public HiddenWhitePixel getNewWhiteHidden2Pixel() {
        return new HiddenWhitePixel(H2W, W, H);
    }

    protected boolean combinedIsCorrect(Pixel source1, Pixel source2,
                                        Pixel hidden) {
        String combined = "";
        for (int i = 0; i < source1.subpixels.length(); i++) {
            String s1Sub = source1.subpixels.substring(i, i + 1);
            String s2Sub = source2.subpixels.substring(i, i + 1);
            boolean s1B = "b".equals(s1Sub);
            boolean s2B = "b".equals(s2Sub);
            combined = combined + (s1B || s2B ? "b" : "w");
        }
        return combined.equals(hidden.subpixels);
    }

    public HashMap<String, Pixel> getPixelsForHidden(boolean isSource1Black,
                                                     boolean isSource2Black, boolean isHiddenBlack) {
        HashMap<String, Pixel> hm = new HashMap<String, Pixel>();

        SourcePixel source1 = isSource1Black ? getNewBlackSourcePixel()
                : getNewWhiteSourcePixel();
        SourcePixel source2 = isSource2Black ? getNewBlackSourcePixel()
                : getNewWhiteSourcePixel();
        source2.resetAllWhite();
        HiddenPixel hidden = isHiddenBlack ? getNewBlackHiddenPixel()
                : getNewWhiteHiddenPixel();
        hidden.subpixels = source1.subpixels;
        while (hidden.getNumberOfBlacks() < hidden.nBlack) {
            hidden.changeRandomWhiteToBlack();
        }

        updateSource2ForHidden(source1, source2, hidden);

        hm.put("source1", source1);
        hm.put("source2", source2);
        hm.put("hidden", hidden);
        return hm;
    }

    private void updateSource2ForHidden(Pixel source1, SourcePixel source2,
                                        HiddenPixel hidden) {
        for (int i = 0; i < source1.subpixels.length(); i++) {
            String s1Sub = source1.subpixels.substring(i, i + 1);
            String hSub = hidden.subpixels.substring(i, i + 1);
            boolean s1B = "b".equals(s1Sub);
            boolean hB = "b".equals(hSub);
            if (hB && !s1B) {
                source2.changeWhiteToBlack(i);
            }
        }
        while (source2.getNumberOfBlacks() < source2.nBlack) {
            int i = (int) (Math.random() * source2.n);
            String hSub = hidden.subpixels.substring(i, i + 1);
            boolean hB = "b".equals(hSub);
            if (hB) {
                source2.changeWhiteToBlack(i);
            }
        }
    }

    public HashMap<String, Pixel> get4x4PixelsForHidden(boolean isSource1Black,
                                                        boolean isSource2Black, boolean isSource3Black,
                                                        boolean isHiddenBlack, boolean isHidden2Black) {
        HashMap<String, Pixel> hm = getPixelsForHidden(isSource1Black,
                isSource2Black, isHiddenBlack);

        SourcePixel source3 = isSource3Black ? getNewBlackSourcePixel()
                : getNewWhiteSourcePixel();
        source3.resetAllWhite();
        HiddenPixel hidden2 = isHidden2Black ? getNewBlackHidden2Pixel()
                : getNewWhiteHidden2Pixel();
        Pixel hidden = hm.get("hidden");

        hidden2.subpixels = hidden.subpixels;
        while (hidden2.getNumberOfBlacks() < hidden2.nBlack) {
            hidden2.changeRandomWhiteToBlack();
        }

        updateSource2ForHidden(hidden, source3, hidden2);

        hm.put("source3", source3);
        hm.put("hidden2", hidden2);
        return hm;
    }

}
