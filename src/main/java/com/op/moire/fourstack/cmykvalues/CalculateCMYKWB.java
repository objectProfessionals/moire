package com.op.moire.fourstack.cmykvalues;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class CalculateCMYKWB {
    static final CalculateCMYKWB calculate = new CalculateCMYKWB();
    ArrayList<Pixel> allPixels = new ArrayList();
    long start;
    PrintWriter writer;

    public static void main(String[] args) {
        System.out.println("Started...");
        calculate.test();
        System.out.println("Finished...");
    }

    private void test() {
        start = System.currentTimeMillis();
        //boolean[] blacks = {false, true, false, true, false, false, true, true, true, true, false, true, true, true, true};
        //boolean[] blacks = {false,false,false,false,false,false,false,false,false,true,true,true,true,true,true};
        //boolean[] blacks = {false, false, false, false, false, false, false, false, false, true, true, true, true, true, true};
        //boolean[] blacks = {false, true, true, true, false, false, true, true, true, true, false, true, true, true, false};
        //boolean[] blacks = {true, true, true, true, true, true, true, true, true, true, true, true, true, true, false};
        //boolean[] blacks = {false, true, true, true, true, true, true, true, true, true, true, true, true, true, false};
        boolean[] blacks = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};

        //Pixel[] arr = calculate(blacks);
        ArrayList<Quad> quads = calculateAll();
        //System.out.println(arr[0] + "," + arr[1] + "," + arr[2] + "," + arr[3]);
        System.out.println("Qs="+quads+" time:" + ((System.currentTimeMillis()-start)/1000));
        writer.close();
    }

    ArrayList<Quad> calculateAll() {
        setup();
        ArrayList<Pixel> allPixels = getCorrectSingles();

        ArrayList<Quad> quads = new ArrayList<>();
        HashMap<String, Quad> bools2Quad = new HashMap<>();
        HashMap<Double, Quad> fit2Quad = new HashMap<>();

        int c1 = 0;
        for (Pixel pix1 : allPixels) {
            int c2=0;
            for (Pixel pix2 : allPixels) {
                int c3=0;
                for (Pixel pix4 : allPixels) {
                    int c4=0;
                    for (Pixel pix8 : allPixels) {
                        System.out.println(c1+":"+c2+":"+c3+":"+c4);

                        Quad quad = new Quad(pix1, pix2, pix4, pix8);
                        fit2Quad.put(quad.fit, quad);

                        String booleanStr = quad.toBooleanString();
                        if (bools2Quad.get(booleanStr) == null) {
                            System.out.println(quad.toString());
                            bools2Quad.put(booleanStr, quad);
                        } else {
                            Quad exQ = bools2Quad.get(booleanStr);
                            if (quad.fit > exQ.fit) {
                                System.out.println(quad.toString());
                                bools2Quad.put(booleanStr, quad);
                            }
                        }

                        //write(quad);
                        c4++;
                    }
                    c3++;
                }
                c2++;
            }
            c1++;
        }

//        for (Double key: fit2Quad.keySet()) {
//            write(fit2Quad.get(key));
//        }
        for (String key: bools2Quad.keySet()) {
            write(bools2Quad.get(key));
        }
        return quads;
    }

    private void write(Quad quad) {
        writer.println(quad.toString());
    }

    private void setup() {
        Pixel.setup();
        try {
            writer = new PrintWriter("../host/moire/fourstack/values/PIXEL.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 256; i++) {
            String b4 = Integer.toString(i, 4);
            String padded = String.format("%4s", b4).replaceAll(" ", "0");
            padded = padded.replaceAll("0", "o");
            padded = padded.replaceAll("1", "y");
            padded = padded.replaceAll("2", "c");
            padded = padded.replaceAll("3", "m");
            Pixel pixel = new Pixel(padded);
            allPixels.add(pixel);
        }
        Collections.sort(allPixels);

    }


    private ArrayList<Pixel> getCorrectSingles() {

        int lower1 = (int) (((double) allPixels.size()) * 0.1);
        int lower2 = (int) (((double) allPixels.size()) * 0.4);
        int upper1 = (int) (((double) allPixels.size()) * 0.6);
        int upper2 = (int) (((double) allPixels.size()) * 0.9);

        ArrayList<Pixel> lowers = new ArrayList(allPixels.subList(lower1, lower2));
        ArrayList<Pixel> uppers = new ArrayList(allPixels.subList(upper1, upper2));

        lowers.addAll(uppers);
        return lowers;

    }
}
