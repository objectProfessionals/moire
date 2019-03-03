package com.op.moire.rotate;

import java.util.ArrayList;
import java.util.HashMap;

public class Corner {

    HashMap<Top, ArrayList<Bottom>> top2Bots;

    public Corner(HashMap<Top, ArrayList<Bottom>> top2Bots) {
        this.top2Bots = top2Bots;
    }

    public void filterMatchingTopBots(Corner cornerB, double ang, int offset) {
        cornerB.removeEmpties();
        for (Top top : this.top2Bots.keySet()) {
            ArrayList<Bottom> botsToRet = new ArrayList<>();
            for (Top topB : cornerB.top2Bots.keySet()) {
                if (topB.rotateEquals(ang, top)) {
                    ArrayList<Bottom> bots = this.top2Bots.get(top);
                    ArrayList<Bottom> botsB = cornerB.top2Bots.get(topB);
//                    bots.retainAll(botsB);
                    botsToRet.addAll(botsB);
                }
            }
            this.top2Bots.get(top).retainAll(botsToRet);
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
