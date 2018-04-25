public class Game {

    Player player1;
    Player player2;
    Board board;
    GameView view;

    public Game(Player player1, Player player2, Board board) {

        this.player1 = player1;
        this.player2 = player2;
        this.board = board;
        this.view = new GameView(board);
    }
}

