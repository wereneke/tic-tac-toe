package view;

import model.Board;
import model.NPC;
import model.Player;

import java.util.*;

public class Creator {

    private Scanner in = new Scanner(System.in);

    private int boardSize() throws IllegalArgumentException, InputMismatchException {

        int size;
        System.out.println("what size board should be?");
        size = Integer.valueOf(in.nextLine());
        if (size < 3) throw new IllegalArgumentException("size must be greater or equal 3");

        return size;
    }

    public Board createBoard() {

        Board board = null;
        int size;

        do {
            try {

                size = boardSize();
                board = new Board(size);

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("size must be integer >= 3");
            }
        } while (Objects.isNull(board));

        return board;
    }

    public Player[] createPlayers() {

        Player[] players = new Player[2];
        final char usedSign;

        Player player0 = createPlayer(null);
        usedSign = player0.getSign();

        Player player1;
        if (isSinglePlayer()) {
            player1 = createComputerPlayer(usedSign);
            System.out.println("Your competitor is " + player1.getName());
            ((NPC) player1).setLevel(setLevel());

        } else {
            System.out.println("===============\nanother player:");
            player1 = createPlayer(usedSign);
        }

        players[0] = player0;
        players[1] = player1;

        return players;
    }

    private String getName() throws IllegalArgumentException {

        System.out.println("Whats your name?");
        String name = in.nextLine();
        if (name.equals("")) throw new IllegalArgumentException("you must provide a name");

        return name;
    }

    private char getSign(Character used) throws IllegalArgumentException {

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

    private Player createPlayer(Character usedSign) {

        String name = null;
        Character sign = null;

        while (Objects.isNull(name)) {
            try {
                name = getName();
            }catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        while (Objects.isNull(sign)) {
            try {
                sign = getSign(usedSign);
            }catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        return new Player(name, sign);
    }

    private NPC createComputerPlayer(Character usedSign) {

        Character sign = getRandomSign();
        while (sign.equals(usedSign)) sign = getRandomSign();
        String name = "Trombert";

        return new NPC(name, sign);
    }

    private Character getRandomSign() {

        Random r = new Random();
        Character sign = (char) (r.nextInt() + 'a');

        return sign;
    }

    private boolean isSinglePlayer() {
        System.out.println("Do you want to play with computer? (y/n)");
        String answer = in.nextLine();

        return answer.toLowerCase().startsWith("y");
    }

    private int setLevel() {

        Integer level = null;
        int temp;
        System.out.println("how hard should he treat you? (0-1-2)");
        do {
            try {
                temp = Integer.valueOf(in.nextLine());
                if (temp<0 || temp>2) throw new IllegalArgumentException();
                level = temp;

            } catch (NumberFormatException e) {
                System.out.println("provide number");
            } catch (IllegalArgumentException e) {
                System.out.println("provide 0 or 1 or 2");
            }
        } while (Objects.isNull(level));

        return level;
    }
}
