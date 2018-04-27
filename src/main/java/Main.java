import logic.Game;
import model.Board;
import model.Player;
import view.Creator;
import view.GameView;

public class Main {

    private Creator creator;
    private Player player0;
    private Player player1;
    private Board board;
    private Game game;
    private GameView view;

    public Main() {

        creator = new Creator();
        board = creator.createBoard();
        view = new GameView(board);
        game = new Game(player0, player1, board, view);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.game.play();
    }
}
