package logic;

import model.Board;
import model.Player;

import java.util.HashMap;
import java.util.InputMismatchException;

public class Game {

    HashMap<Integer, Player> players;

    Board board;
    WinChecker winChecker;
    GameView view;

    boolean isWin = false;

    public Game(Player player0, Player player1, Board board, GameView view) {

        this.board = board;
        this.winChecker = new WinChecker(board.board);
        this.view = view;

        this.players = new HashMap();
        players.put(0, player0);
        players.put(1, player1);
    }

    private void turn(Player player) {

        int row = 0, col = 0;
        boolean turned = false;
        int[] coordinates;
        System.out.println(String.format("%s's turn", player.name));

        while (!turned) {
            try {
                coordinates = view.coordinates();
                turned = board.putSign(coordinates[0], coordinates[1], player.sign);

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("enter number!");
            }
        }
        if (winChecker.isWin(row, col)) {
            isWin = true;
            System.out.println(String.format("Winner is %s", player.name));
        }

        view.displayBoard();
    }

    public void play() {
        int turnCounter = 0;
        Player actualPlayer;

        while (board.isGamePossible() && !isWin) {
            actualPlayer = playerChanger(turnCounter);
            turn(actualPlayer);
            turnCounter++;
        }
    }

    private Player playerChanger(int i) {
        return players.get(i%2);
    }
}

