import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameViewTest {

    Player player1;
    Player player2;
    Game game;
    Board board;
    GameView view;

    @BeforeEach
    void setUp() {
        player1 = new Player("wera", 'o');
        player2 = new Player("zenonek", 'x');
        board = new Board(3);
        view = new GameView(board);
        game = new Game(player1, player2, board);

        //1st row
        board.board[0][0] = 'o';
        board.board[0][1] = 'o';
        board.board[0][2] = 'o';
        //1st col
        board.board[1][0] = 'o';
        //sec col
        board.board[1][1] = 'x';
    }

    @Test
    void testIfDisplayWorks() {
        view.displayBoard();
    }

    @Test
    void testIfCheckingOfWinsWorksWhenIsWin() {
        assertFalse(game.checkRow(2));
    }

}