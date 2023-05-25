package com.op.moire.fourstack.vennbybit;

import com.op.moire.fourstack.alpha.CalculateVenn;

import java.util.HashMap;

public class CalculateVennByBit {
    public static int ft1[] = {8, 12};
    public static int ft2[] = {12, 16};
    public static int ft3[] = {16, 24};
    public static int ft4[] = {16, 24};
    public static int ft5[] = {16, 24};
    public static int ft6[] = {16, 24};
    public static int ft7[] = {16, 24};

    public static void main(String[] args) {
        System.out.println("Started...");
        CalculateVennByBit venn = new CalculateVennByBit();
        HashMap<String, Integer[]> all = venn.getAll();
        System.out.println("Finished...");
    }

    public HashMap<String,Integer[]> getAll() {
        //1u2 = 1 + 2 - 1n2
        //1u2u3u4u5u6u7=1+2+3+4+5+6+7 -(all pairs) + allTrips -allQs +AllFives -all6s
        //S1uS2uP12uS4uP13uP23uT123 = S1 + S2 + P12 + S4 + P14 + P24 + T124 -

        HashMap<String,Integer[]> all = new HashMap<>();

        return all;
    }
}
