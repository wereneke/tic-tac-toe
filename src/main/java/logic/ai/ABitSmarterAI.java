package logic.ai;

import java.util.*;

public class ABitSmarterAI extends NotVerySmartAI {

    @Override
    public int[] coordinates() {

        int rowIndx, colIndx;

        Optional<char[]> maxPopulatedRow = maxPopulated(rowsOfBoard);
        //Optional<char[]> maxPopulatedCol = maxPopulated(colsOfBoard);

        if (maxPopulatedRow.isPresent()) {
            char[] maxRow = maxPopulatedRow.get();
            rowIndx = rowsOfBoard.indexOf(maxRow);
            colIndx = findSlot(maxRow);
        } else {
            rowIndx = random.nextInt(boardSize);
            colIndx = findSlot(board[rowIndx]);
        }

        return new int[]{rowIndx, colIndx};
    }

    Optional<char[]> maxPopulated(List<char[]> stripes) {

        return stripes.stream()
                .filter(stripe -> hasStripeSlot(stripe))
                .reduce((s1, s2) -> enemySignsInStripe(s1) > enemySignsInStripe(s2) ? s1: s2);
    }

    private int enemySignsInStripe(char[] stripe) {
        int amount = 0;
        for (char sign: stripe) {
            if (sign==enemySign) amount++;
        }
        return amount;
    }

    private boolean hasStripeSlot(char[] stripe) {

        for (int i=0; i<boardSize; i++) {
            if (stripe[i]=='\u0000') return true;
        }
        return false;
    }

    private int findSlot(char[] stripe) {

        for (int i=0; i<boardSize; i++) {
            if (stripe[i]=='\u0000') return i;
        }
        return -1;
    }

}
