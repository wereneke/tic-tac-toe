import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameViewTest {

    Board board;
    GameView view;

    @BeforeEach
    void setUp() {
        board = new Board(3);
        view = new GameView(board);
    }

    @Test
    void testIfDisplayWorks() {
        view.displayBoard();
    }

}