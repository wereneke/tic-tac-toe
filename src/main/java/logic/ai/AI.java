package logic.ai;

import java.util.Random;

public abstract class AI {

    protected Random random = new Random();
    protected char[][] board;
    protected int boardSize;

    public abstract int[] coordinates();

    public void setBoard(char[][] board) {
        this.board = board;
        this.boardSize = board.length;
    }
}
