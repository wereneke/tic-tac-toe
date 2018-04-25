public class GameView {

    Board board;

    GameView(Board board) {
        this.board = board;
    }

    public void displayBoard() {

        for (char[] row: board.board) {
            for (char square: row) {
                System.out.print(String.format("[%c] ", square));
            }
            System.out.print("\n");
        }
    }
}
