package view;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class GameView {

    private char[][] board;
    private int size;
    private Scanner in = new Scanner(System.in);

    public void displayBoard() {

        for (char[] row: board) {
            for (char square: row) {
                System.out.print(String.format("[%c] ", square));
            }
            System.out.print("\n");
        }
        System.out.println("\n");
    }

    public int[] coordinates() throws IllegalArgumentException, InputMismatchException {

        int[] coordinates = new int[2];

        System.out.println("enter row index");
        int row = Integer.valueOf(in.nextLine());
        if (row < 0 || size <= row) throw new IllegalArgumentException(String.format("coordinate must be contained [0, %d]", size-1));

        System.out.println("enter column index");
        int col = Integer.valueOf(in.nextLine());
        if (col < 0 || size <= col) throw new IllegalArgumentException(String.format("coordinate must be contained [0, %d]", size-1));

        coordinates[0] = row;
        coordinates[1] = col;

        return coordinates;
    }

    public int boardSize() throws IllegalArgumentException, InputMismatchException {

        int size;
        System.out.println("what size board should be?");
        size = Integer.valueOf(in.nextLine());
        if (size < 3) throw new IllegalArgumentException("size must be greater or equal 3");

        return size;
    }

    public String getName() throws IllegalArgumentException {

        System.out.println("Whats your name?");
        String name = in.nextLine();
        if (name.equals("")) throw new IllegalArgumentException("you must provide a name");

        return name;
    }

    public char getSign(Character used) throws IllegalArgumentException {

        Optional<Character> unalowed = Optional.ofNullable(used);

        System.out.println("Provide your sign");
        String answer = in.nextLine();

        if (answer.length()!=1) throw new IllegalArgumentException("sign must have length of 1");

        char sign = answer.charAt(0);
        if (sign == '\u0000') throw new IllegalArgumentException("you must provide sign");

        unalowed.ifPresent(u -> {
            if (u.equals(sign)) throw new IllegalArgumentException("the sign is reserved");
        });

        return sign;
    }


    public boolean isSinglePlayer() {
        System.out.println("Do you want to play with computer? (y/n)");
        String answer = in.nextLine();

        return answer.toLowerCase().startsWith("y");
    }

    public int getLevel() throws NumberFormatException, IllegalArgumentException {

        System.out.println("how hard should he treat you? (0-1-2)");

        int level = Integer.valueOf(in.nextLine());
        if (level<0 || level>2) throw new IllegalArgumentException("provide 0 or 1 or 2");

        return level;
    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
