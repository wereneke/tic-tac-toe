package logic.ai;

import java.util.*;

public class ABitSmarterAI extends NotVerySmartAI {

    private CoordinatesFactory coordinatesFactory = new CoordinatesFactory();

    @Override
    public int[] coordinates(){

        Map<List, Optional> maxMapping = maxMapping(rowsOfBoard, colsOfBoard, diagOfBoard);

        Optional<Map.Entry<List, Optional>> maxEntryOpt = maxMapping.entrySet().stream()
                .reduce((e1, e2) ->
                        enemySignsInStripe((char[])e1.getValue().get()) > enemySignsInStripe((char[])e2.getValue().get())
                ? e1 : e2);

        Map.Entry<List, Optional> maxEntry = maxEntryOpt.get();

        int[] coordinates = coordinatesFactory.coordinates(maxEntry);

        return coordinates;
    }

    private Map<List, Optional> maxMapping(List ... stripeListed) {


        Map<List, Optional> maxs = new HashMap<>();

        for (List stripes: stripeListed) {
            maxs.put(stripes, maxPopulated(stripes));
        }

        return maxs;
    }

    private Optional<char[]> maxPopulated(List<char[]> stripes) {

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


    private class CoordinatesFactory {

        int[] coordinates(Map.Entry<List, Optional> entry) {

            List stripes = entry.getKey();
            char[] stripe = (char[]) entry.getValue().get();
            int row, col;

            if (stripes == rowsOfBoard) {
                row = stripes.indexOf(stripe);
                col = findSlot(stripe);

            } else if (stripes == colsOfBoard) {
                row = findSlot(stripe);
                col = stripes.indexOf(stripe);
            } else {

                switch (stripes.indexOf(stripe)) {
                    case 0: col = findSlot(stripe);
                            row = boardSize - 1 - col;
                            break;
                    case 1: row = col = findSlot(stripe);
                            break;
                    default: row = col = random.nextInt(boardSize);
                }
            }

            return new int[]{row, col};
        }

        private int findSlot(char[] stripe) {

            for (int i=0; i<boardSize; i++) {
                if (stripe[i]=='\u0000') return i;
            }
            return -1;
        }
    }

}
