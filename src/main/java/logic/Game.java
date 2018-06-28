package logic;

import model.Board;
import model.NPC;
import model.Player;
import view.GameView;

import java.util.InputMismatchException;
import java.util.Objects;

public class Game {

    private Player[] players;

    private volatile Board board;
    private WinChecker winChecker;
    private GameView view;

    private boolean isWin = false;

    public Game(Player[] players, Board board, GameView view) {

        this.board = board;
        this.winChecker = new WinChecker(board.getBoard());
        this.view = view;

        this.players = players;

        if (players[1] instanceof NPC) {
            ((NPC)players[1]).setBoard(board.getBoard());
            ((NPC)players[1]).getAi().setEnemySign(players[0].getSign());
        }
    }

    private void turn(Player player) {

        Integer row, col;
        boolean turned;

        System.out.println(String.format("%s's turn", player.getName()));

        do {
            if (player instanceof NPC) {
                int[] coordinates = ((NPC) player).coordinates();
                row = coordinates[0];
                col = coordinates[1];
            } else {
                row = getIndex("row");
                col = getIndex("column");
            }

            turned = board.putSign(row, col, player.getSign());
            view.displayBoard();
        } while (!turned);

        if (winChecker.isWin(row, col)) {
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

    private int getIndex(String of) {

        Integer index = null;
        do {
            try {
                index = view.coordinate(of);

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("enter number!");
            }
        } while (Objects.isNull(index));

        return index;
    }
}

