package logic.ai;

import java.util.*;

public class ABitSmarterAI extends NotVerySmartAI {

    @Override
    public int[] coordinates(){

        int rowIndx, colIndx;

        Map<List, Optional> maxMapping = maxMapping(rowsOfBoard, colsOfBoard, diagOfBoard);

        Optional<Map.Entry<List, Optional>> maxEntryOpt = maxMapping.entrySet().stream()
                .reduce((e1, e2) ->
                        enemySignsInStripe((char[])e1.getValue().get()) > enemySignsInStripe((char[])e2.getValue().get())
                ? e1 : e2);

        Map.Entry<List, Optional> maxEntry = maxEntryOpt.get();

        int[] coordinates = Strategy.coordinates();


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

    private int findSlot(char[] stripe) {

        for (int i=0; i<boardSize; i++) {
            if (stripe[i]=='\u0000') return i;
        }
        return -1;
    }

    private static class Strategy {

        static int[] coordinates() {
            return new int[2];
        }
    }

}
