package logic.ai;

import java.util.*;

public abstract class AI {

    Random random = new Random();
    char[][] board;
    int boardSize;
    char enemySign;

    volatile List<char[]> rowsOfBoard = new ArrayList<>(boardSize);
    volatile List<char[]> colsOfBoard = new ArrayList<>(boardSize);
    volatile List<char[]> diagOfBoard = new ArrayList<>(2);

    List<char[]> stripesOfBoard = new ArrayList<>(2*boardSize+2);

    public abstract int[] coordinates();

    public void setBoard(char[][] board) {
        this.board = board;
        this.boardSize = board.length;
        setFieldStripesOfBoard();
    }

    public void setEnemySign(char sign) {
        this.enemySign = sign;
    }

    private void setFieldStripesOfBoard() {
        setRowsOfBoard();
        setColsOfBoard();
        setDiagOfBoard();

        stripesOfBoard.addAll(rowsOfBoard);
        stripesOfBoard.addAll(colsOfBoard);
        stripesOfBoard.addAll(diagOfBoard);
    }

    private void setRowsOfBoard() {

        rowsOfBoard.addAll(Arrays.asList(board));
    }

    private void setColsOfBoard() {

        for (int i=0; i<boardSize; i++) {
            char[] col = new char[boardSize];

            for (int j=0; j<boardSize; j++) {

                col[j] = board[j][i];
            }

            colsOfBoard.add(col);
        }
    }

    private void setDiagOfBoard() {

        char[] decreasing = new char[boardSize];
        char[] increasing = new char[boardSize];

        for (int i=0; i<boardSize; i++) {
            decreasing[i] = board[i][i];
        }

        for (int i=0; i<boardSize; i++) {
            increasing[i] = board[boardSize-1-i][i];
        }

        diagOfBoard.add(increasing);
        diagOfBoard.add(decreasing);

    }
}
