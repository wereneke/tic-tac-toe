package model;

public class Board {

    private int size;
    private int capacity;
    private char[][] board;

    public Board(int size) {
        this.size = size;
        this.capacity = size*size;
        this.board = new char[size][size];
    }

    public boolean putSign(int row, int col, char sign) {

        if (board[row][col]=='\u0000') {
            board[row][col] = sign;
            capacity--;
            return true;
        }
        System.out.println("the square is occupied, try again");
        return false;
    }

    public boolean isGamePossible() {
        return capacity>0;
    }

    public int getSize() {
        return size;
    }

    public char[][] getBoard() {
        return board;
    }
}
