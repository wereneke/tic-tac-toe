package model;

import logic.AI;

public class NPC extends Player {

    private AI ai;

    public NPC(String name, char sign) {
        super(name, sign);
        this.ai = new AI();
    }

    public int[] coordinates() {
        return ai.coordinates();
    }

    public void setLevel(int i) {
        ai.setLevel(i);
    }

    public void setBoard(char[][] board) {
        ai.setBoard(board);
    }
}
