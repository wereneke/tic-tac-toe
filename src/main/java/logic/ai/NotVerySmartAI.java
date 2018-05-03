package logic.ai;

public class NotVerySmartAI extends AI {

    @Override
    public int[] coordinates() {
        int row, col;
        do {
            row = random.nextInt(boardSize);
            col = random.nextInt(boardSize);
        } while (!areCoordinatesAllowed(row, col));

        int[] coordinates = {row, col};
        return coordinates;
    }

    private boolean areCoordinatesAllowed(int row, int col) {
        return board[row][col]=='\u0000';
    }
}
