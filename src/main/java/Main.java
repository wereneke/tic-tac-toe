import logic.Game;
import model.Board;
import model.Player;
import view.Creator;
import view.GameView;

public class Main {

    private Game game;

    public Main() {

        Creator creator = new Creator();

        Board board = creator.createBoard();
        GameView view = new GameView(board);

        Player[] players = creator.createPlayers();

        game = new Game(players, board, view);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.game.play();
    }
}
