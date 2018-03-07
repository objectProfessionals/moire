package com.op.moire.multiple.test;

import com.op.moire.multiple.CombinedPixelGenerator;

public class SourcePixel4x4GeneratorTest {
    CombinedPixelGenerator sourceGenerator = new CombinedPixelGenerator();

    // public boolean isBlack(Pixel p) {
    // if (p instanceof SourcePixel) {
    // return p.getNumberOfBlacks() == CombinedPixelGenerator.SB;
    // } else if (p instanceof HiddenPixel) {
    // return p.getNumberOfBlacks() == CombinedPixelGenerator.HB;
    // }
    //
    // return false;
    // }
    //
    // public boolean isBlack2(Pixel p) {
    // if (p instanceof SourcePixel) {
    // return p.getNumberOfBlacks() == CombinedPixelGenerator.SB;
    // } else if (p instanceof HiddenPixel) {
    // return p.getNumberOfBlacks() == CombinedPixelGenerator.H2B;
    // }
    //
    // return false;
    // }
    //
    // public void canCreateBlackSourceBox() {
    // SourceBlackPixel box = sourceGenerator.getNewBlackSourcePixel();
    // assertEquals(true, isBlack(box));
    // }
    //
    // public void canCreateWhiteSourceBox() {
    // SourceWhitePixel box = sourceGenerator.getNewWhiteSourcePixel();
    // assertEquals(false, isBlack(box));
    // }
    //
    // public void shouldCreateBBB_BB() {
    // HashMap<String, Pixel> hm = sourceGenerator.get4x4PixelsForHidden(true,
    // true, true, true, true);
    //
    // printALL("bbb_bb:", hm);
    // assertAll(hm, true, true, true, true, true);
    // }
    //
    // @Test
    // public void shouldCreate3Souce2HiddenImages() {
    // // img size = 5x5
    // String s1Img = "wwbww" + "wwbww" + "bbbbb" + "wwbww" + "wwbww";
    // String s2Img = "wwwww" + "wbbbw" + "wbwbw" + "wbbbw" + "wwwww";
    // String h1Img = "bwwwb" + "wbwbw" + "wwbww" + "wbwbw" + "bwwwb";
    // String s3Img = "wwwwb" + "wwwbw" + "wwbww" + "wbwww" + "bwwww";
    // String h2Img = "bbbbb" + "bwwwb" + "bwwwb" + "bwwwb" + "bbbbb";
    //
    // for (int y = 0; y < 5; y++) {
    // for (int x = 0; x < 5; x++) {
    // int i = (y * 5) + x;
    // String s1 = s1Img.substring(i, i + 1);
    // String s2 = s2Img.substring(i, i + 1);
    // String s3 = s3Img.substring(i, i + 1);
    // String h1 = h1Img.substring(i, i + 1);
    // String h2 = h2Img.substring(i, i + 1);
    //
    // boolean s1B = "b".equals(s1);
    // boolean s2B = "b".equals(s2);
    // boolean s3B = "b".equals(s3);
    // boolean h1B = "b".equals(h1);
    // boolean h2B = "b".equals(h2);
    //
    // HashMap<String, Pixel> hm = sourceGenerator
    // .get4x4PixelsForHidden(s1B, s2B, s3B, h1B, h2B);
    // System.out.print("s1:" + hm.get("source1"));
    // System.out.println("s2:" + hm.get("source2"));
    // System.out.println("s3:" + hm.get("source3"));
    // System.out.println("hi:" + hm.get("hidden"));
    // System.out.println("hi2:" + hm.get("hidden2"));
    // }
    // }
    //
    // }
    //
    // private void printALL(String mess, HashMap<String, Pixel> hm) {
    // System.out.println(mess);
    //
    // if (!sourceGenerator.combinedIsCorrect(hm.get("source1"),
    // hm.get("source2"), hm.get("hidden"))) {
    // System.out.println("INCORRECT");
    // }
    //
    // System.out.println("s1:" + hm.get("source1"));
    // System.out.println("s2:" + hm.get("source2"));
    // System.out.println("hi:" + hm.get("hidden"));
    // System.out.println("s3:" + hm.get("source3"));
    // System.out.println("hi2:" + hm.get("hidden2"));
    // System.out.println();
    // }
    //
    // private void assertAll(HashMap<String, Pixel> hm, boolean i, boolean j,
    // boolean k, boolean l, boolean m) {
    // assertEquals(i, isBlack(hm.get("source1")));
    // assertEquals(j, isBlack(hm.get("source2")));
    // assertEquals(k, isBlack(hm.get("source3")));
    // assertEquals(l, isBlack(hm.get("hidden")));
    // assertEquals(m, isBlack2(hm.get("hidden2")));
    // }
}
