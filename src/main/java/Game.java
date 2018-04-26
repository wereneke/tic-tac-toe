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

    boolean isWin() {
       return false;
    }

    boolean checkRow(int row) {
        char sample = board.board[row][0];
        if (sample == '\u0000') return false;

        for (char a: board.board[row]) {
            if (a!=sample) return false;
        }
        return true;
    }

    boolean checkCollumn(int col) {
        char sample = board.board[0][col];
        if (sample == '\u0000') return false;

        for (int i=0; i<board.size; i++) {
            if (board.board[i][col]!=sample) return false;
        }
        return true;
    }
}

