package view;

import model.Board;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Creator {

    private Scanner in = new Scanner(System.in);

    private int boardSize() throws IllegalArgumentException, InputMismatchException {

        int size;
        System.out.println("what size board should be?");
        size = in.nextInt();
        if (size < 3) throw new IllegalArgumentException("size must be greater or equal 3");
        return size;
    }

    public Board createBoard() {

        boolean finalized = false;
        int size = 0;

        do {
            try {
                size = boardSize();
                finalized = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (!finalized);

        return new Board(size);
    }
}
