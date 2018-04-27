package view;

import model.Board;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GameView {

    private char[][] board;
    private int size;
    private Scanner scanner = new Scanner(System.in);

    GameView(Board board) {
        this.board = board.getBoard();
        this.size = board.getSize();
    }

    public void displayBoard() {

        for (char[] row: board) {
            for (char square: row) {
                System.out.print(String.format("[%c] ", square));
            }
            System.out.print("\n");
        }
    }

    public int[] coordinates() throws IllegalArgumentException, InputMismatchException {

        int[] coordinates = new int[2];

        System.out.println("enter row index");
        int row = scanner.nextInt();
        if (row < 0 || size <= row) throw new IllegalArgumentException(String.format("coordinate must be contained [0, %d]", size-1));

        System.out.println("enter column index");
        int col = scanner.nextInt();
        if (col < 0 || size <= col) throw new IllegalArgumentException(String.format("coordinate must be contained [0, %d]", size-1));

        coordinates[0] = row;
        coordinates[1] = col;

        return coordinates;
    }
}
