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

    boolean isWin(int row, int col) {

        boolean diagonalCheck = false;

        int maxIndex = board.size-1;
        if (row==col || row == maxIndex-col) {
            diagonalCheck = checkDecreasingDiagonal() || checkIncreasingDiagonal();
        }
       return checkRow(row) || checkCollumn(col) || diagonalCheck;
    }

    boolean checkRow(int row) {
        char sample = board.board[row][0];
        if (isSampleNull(sample)) return false;

        for (char a: board.board[row]) {
            if (a!=sample) return false;
        }
        return true;
    }

    boolean checkCollumn(int col) {
        char sample = board.board[0][col];
        if (isSampleNull(sample)) return false;

        for (int i=0; i<board.size; i++) {
            if (board.board[i][col]!=sample) return false;
        }
        return true;
    }

    boolean checkDecreasingDiagonal() {
        char sample = board.board[0][0];
        if (isSampleNull(sample)) return false;

        for (int i=0; i<board.size; i++) {
            if (board.board[i][i] != sample) return false;
        }
        return true;
    }

    boolean checkIncreasingDiagonal() {
        int lastIndexOnBoard = board.size-1;
        char sample = board.board[lastIndexOnBoard][0];
        if (isSampleNull(sample)) return false;

        for (int i = 0; i<=lastIndexOnBoard; i++) {
            if (board.board[lastIndexOnBoard-i][i] != sample) return false;
        }
        return true;
    }

    private boolean isSampleNull(char sample) {
        return sample == '\u0000';
    }
}

