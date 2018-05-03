package logic;

import model.Board;
import model.NPC;
import model.Player;
import view.GameView;

import java.util.InputMismatchException;

public class Game {

    private Player[] players;

    private Board board;
    private WinChecker winChecker;
    private GameView view;

    private boolean isWin = false;

    public Game(Player[] players, Board board, GameView view) {

        this.board = board;
        this.winChecker = new WinChecker(board.getBoard());
        this.view = view;

        this.players = players;

        if (players[1] instanceof NPC) ((NPC)players[1]).setBoard(board.getBoard());
    }

    private void turn(Player player) {

        boolean turned = false;
        int[] coordinates = {0, 0};
        System.out.println(String.format("%s's turn", player.getName()));

        while (!turned) {
            try {

                if (player instanceof NPC) {
                    coordinates = ((NPC) player).coordinates();
                } else {
                    coordinates = view.coordinates();
                }

                turned = board.putSign(coordinates[0], coordinates[1], player.getSign());

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("enter number!");
            }
        }

        view.displayBoard();

        if (winChecker.isWin(coordinates[0], coordinates[1])) {
            isWin = true;
            System.out.println(String.format("Winner is %s", player.getName()));
        }
    }

    public void play() {

        int turnCounter = 0;
        Player actualPlayer;

        while (board.isGamePossible() && !isWin) {
            actualPlayer = playerChanger(turnCounter);
            turn(actualPlayer);
            turnCounter++;
        }
        if (!isWin) System.out.println("there is no winner");
    }

    private Player playerChanger(int i) {
        return players[i%2];
    }
}

