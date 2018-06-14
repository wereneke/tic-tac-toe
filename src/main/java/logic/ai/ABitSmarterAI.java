package logic.ai;

import java.util.*;

public class ABitSmarterAI extends NotVerySmartAI {

    @Override
    public int[] coordinates() {

        int rowIndx, colIndx;

        Optional<char[]> maxPopulatedRow = maxPopulated(rowsOfBoard);
        Optional<char[]> maxPopulatedCol = maxPopulated(colsOfBoard);

        if (maxPopulatedRow.isPresent()) {
            rowIndx = rowsOfBoard.indexOf(maxPopulatedRow.get());
        } else {
            rowIndx = random.nextInt(boardSize);
        }

        if (maxPopulatedCol.isPresent()) {
            colIndx = colsOfBoard.indexOf(maxPopulatedCol.get());
        } else {
            colIndx = random.nextInt(boardSize);
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

}
