package com.op.moire.fourrotate;

import java.util.ArrayList;
import java.util.List;

public class Row {

    Top top;
    List<Bottom> bottoms = new ArrayList();

    Row(Top b, ArrayList<Bottom> bots) {
        this.top = b;
        this.bottoms = bots;
    }
}
