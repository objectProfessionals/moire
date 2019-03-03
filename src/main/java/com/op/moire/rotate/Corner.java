package com.op.moire.rotate;

import java.util.ArrayList;
import java.util.HashMap;

public class Corner {

    HashMap<Top, ArrayList<Bottom>> top2Bots;

    public Corner(HashMap<Top, ArrayList<Bottom>> top2Bots) {
        this.top2Bots = top2Bots;
    }

    public void filterMatchingTopBots(Corner cornerB, double ang, int offset) {
        this.removeEmpties();
        //System.out.println("top2Bots="+top2Bots.size());
        cornerB.removeEmpties();
        //System.out.println("cornerB.top2Bots="+cornerB.top2Bots.size());
        for (Top top : this.top2Bots.keySet()) {
            for (Top topB : cornerB.top2Bots.keySet()) {
                if (top.rotateEquals(ang, topB)) {
                    ArrayList<Bottom> bots = this.top2Bots.get(top);
                    ArrayList<Bottom> botsB = cornerB.top2Bots.get(topB);
                    bots.retainAll(botsB);
                }
            }
        }
        this.removeEmpties();
        System.out.println("top2Bots="+top2Bots.size());
    }

    void removeEmpties() {
        ArrayList<Top> topsToRemove = new ArrayList<>();
        for (Top top : this.top2Bots.keySet()) {
            if (this.top2Bots.get(top).isEmpty()) {
                topsToRemove.add(top);
            }
        }

        for (Top top : topsToRemove) {
            this.top2Bots.remove(top);
        }
    }
}
