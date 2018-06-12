package model;

import logic.ai.AI;
import logic.ai.AIFactory;

public class NPC extends Player {

    private AIFactory factory = new AIFactory();
    private AI ai;

    public NPC(String name, char sign) {
        super(name, sign);
    }

    public int[] coordinates() {
        return ai.coordinates();
    }

    public void setLevel(int i) {
        ai = factory.create(i);
    }

    public void setBoard(char[][] board) {
        ai.setBoard(board);
    }

    public AI getAi() {
        return this.ai;
    }
}
