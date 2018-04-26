import java.util.HashMap;
import java.util.Scanner;

public class Game {

    HashMap<Integer, Player> players;

    Board board;
    WinChecker winChecker;
    GameView view;

    Scanner scan = new Scanner(System.in);
    boolean isWin = false;

    public Game(Player player0, Player player1, Board board) {

        this.board = board;
        this.winChecker = new WinChecker(board.board);
        this.view = new GameView(board);

        this.players = new HashMap<>();
        players.put(0, player0);
        players.put(1, player1);
    }

    public void turn(Player player) {

        int row = 0, col = 0;
        boolean turned = false;

        System.out.println(String.format("%s's turn", player.name));

        while (!turned) {
            System.out.println("enter row index");
            row = scan.nextInt();
            System.out.println("enter collumn index");
            col = scan.nextInt();
            turned = board.putSign(row, col, player.sign);
        }
        if (winChecker.isWin(row, col)) {
            isWin = true;
            System.out.println(String.format("Winner is %s", player.name));
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
    }

    private Player playerChanger(int i) {
        return players.get(i%2);
    }
}

