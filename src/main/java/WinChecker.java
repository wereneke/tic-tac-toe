public class WinChecker {

    private char[][] board;

    public WinChecker(char[][] board) {
        this.board = board;
    }

    boolean isWin(int row, int col) {

        boolean diagonalCheck = false;

        int maxIndex = board.length-1;
        if (row==col || row == maxIndex-col) {
            diagonalCheck = checkDecreasingDiagonal() || checkIncreasingDiagonal();
        }
        return checkRow(row) || checkCollumn(col) || diagonalCheck;
    }

    private boolean checkRow(int row) {
        char sample = board[row][0];
        if (isSampleNull(sample)) return false;

        for (char a: board[row]) {
            if (a!=sample) return false;
        }
        return true;
    }

    private boolean checkCollumn(int col) {
        char sample = board[0][col];
        if (isSampleNull(sample)) return false;

        for (int i=0; i<board.length; i++) {
            if (board[i][col]!=sample) return false;
        }
        return true;
    }

    private boolean checkDecreasingDiagonal() {
        char sample = board[0][0];
        if (isSampleNull(sample)) return false;

        for (int i=0; i<board.length; i++) {
            if (board[i][i] != sample) return false;
        }
        return true;
    }

    private boolean checkIncreasingDiagonal() {
        int lastIndexOnBoard = board.length-1;
        char sample = board[lastIndexOnBoard][0];
        if (isSampleNull(sample)) return false;

        for (int i = 0; i<=lastIndexOnBoard; i++) {
            if (board[lastIndexOnBoard-i][i] != sample) return false;
        }
        return true;
    }

    private boolean isSampleNull(char sample) {
        return sample == '\u0000';
    }
}
