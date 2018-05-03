package logic;

import java.util.Random;

public class AI {

    Random random = new Random();
    private char[][] board;
    private int boardSize;

    public int[] coordinates() {
        int row, col;
        do {
            row = random.nextInt(boardSize);
            col = random.nextInt(boardSize);
        } while (!areCoordinatesAllowed(row, col));

        int[] coordinates = {row, col};
        return coordinates;
    }

    public void setLevel(int i) {

    }

    public void setBoard(char[][] board) {
        this.board = board;
        this.boardSize = board.length;
    }

    boolean areCoordinatesAllowed(int row, int col) {
        return board[row][col]=='\u0000';
    }
}
