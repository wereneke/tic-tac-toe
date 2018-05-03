import logic.Game;
import model.Board;
import model.Player;
import logic.Creator;
import view.GameView;

public class Main {

    private Game game;

    public Main() {

        GameView view = new GameView();
        Creator creator = new Creator(view);

        Board board = creator.createBoard();
        Player[] players = creator.createPlayers();

        view.setBoard(board.getBoard());
        view.setSize(board.getSize());

        game = new Game(players, board, view);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.game.play();
    }
}
