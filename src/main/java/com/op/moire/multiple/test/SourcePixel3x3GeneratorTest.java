package com.op.moire.multiple.test;

import com.op.moire.multiple.CombinedPixelGenerator;

public class SourcePixel3x3GeneratorTest {
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
    // @Test
    // public void canCreateBlackSourceBox() {
    // SourceBlackPixel box = sourceGenerator.getNewBlackSourcePixel();
    // assertEquals(true, isBlack(box));
    // }
    //
    // @Test
    // public void canCreateWhiteSourceBox() {
    // SourceWhitePixel box = sourceGenerator.getNewWhiteSourcePixel();
    // assertEquals(false, isBlack(box));
    // }
    //
    // @Test
    // public void shouldCreateBBB() {
    // HashMap<String, Pixel> hm = sourceGenerator.getPixelsForHidden(true,
    // true, true);
    //
    // printALL("bbb:", hm);
    // assertAll(hm, true, true, true);
    // }
    //
    // @Test
    // public void shouldCreateBWB() {
    // HashMap<String, Pixel> hm = sourceGenerator.getPixelsForHidden(true,
    // false, true);
    //
    // printALL("bwb", hm);
    // assertAll(hm, true, false, true);
    // }
    //
    // @Test
    // public void shouldCreateWBB() {
    // HashMap<String, Pixel> hm = sourceGenerator.getPixelsForHidden(false,
    // true, true);
    //
    // printALL("wbb:", hm);
    // assertAll(hm, false, true, true);
    // }
    //
    // @Test
    // public void shouldCreateWWB() {
    // HashMap<String, Pixel> hm = sourceGenerator.getPixelsForHidden(false,
    // false, true);
    //
    // printALL("wwb:", hm);
    // assertAll(hm, false, false, true);
    // }
    //
    // @Test
    // public void shouldCreateBBW() {
    // HashMap<String, Pixel> hm = sourceGenerator.getPixelsForHidden(true,
    // true, false);
    //
    // printALL("bbw:", hm);
    // assertAll(hm, true, true, false);
    // }
    //
    // @Test
    // public void shouldCreateBWW() {
    // HashMap<String, Pixel> hm = sourceGenerator.getPixelsForHidden(true,
    // false, false);
    //
    // printALL("bww:", hm);
    // assertAll(hm, true, false, false);
    // }
    //
    // @Test
    // public void shouldCreateWWW() {
    // HashMap<String, Pixel> hm = sourceGenerator.getPixelsForHidden(false,
    // false, false);
    //
    // printALL("www:", hm);
    // assertAll(hm, false, false, false);
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
    // System.out.println();
    // }
    //
    // private void assertAll(HashMap<String, Pixel> hm, boolean i, boolean j,
    // boolean k) {
    // assertEquals(i, isBlack(hm.get("source1")));
    // assertEquals(j, isBlack(hm.get("source2")));
    // assertEquals(k, isBlack(hm.get("hidden")));
    // }
}
