package logic;

import model.Board;
import model.NPC;
import model.Player;
import view.GameView;

import java.util.*;

public class Creator {

    private GameView view;

    public Creator(GameView view) {
        this.view = view;
    }

    public Board createBoard() {

        Board board = null;
        int size;

        do {
            try {
                size = view.boardSize();
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

        if (view.isSinglePlayer()) {
            player1 = createComputerPlayer(usedSign);
            System.out.println("Your competitor is " + player1.getName());

            Integer level = null;
            do {
                try {
                    level = view.getLevel();
                    ((NPC) player1).setLevel(level);

                } catch (NumberFormatException e) {
                    System.out.println("provide number (0-1-2)");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } while (Objects.isNull(level));


        } else {
            System.out.println("===============\nanother player:");
            player1 = createPlayer(usedSign);
        }

        players[0] = player0;
        players[1] = player1;

        return players;
    }

    private Player createPlayer(Character usedSign) {

        String name = null;
        Character sign = null;

         do {
            try {
                name = view.getName();
            }catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (Objects.isNull(name));

        do {
            try {
                sign = view.getSign(usedSign);
            }catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (Objects.isNull(sign));

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
}
