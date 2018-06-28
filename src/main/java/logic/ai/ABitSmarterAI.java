package logic.ai;

import java.util.*;

public class ABitSmarterAI extends NotVerySmartAI {

    @Override
    public int[] coordinates(){

        int rowInd, colInd;

        Optional<Map.Entry<Integer, Integer>> maxPopulatedRowOpt = rowPopulation().entrySet().stream()
                .filter(e -> hasRowSpace(e.getKey()))
               .max(Comparator.comparing(Map.Entry::getValue));

        Optional<Map.Entry<Integer, Integer>> maxPopulatedColOpt = colPopulation().entrySet().stream()
                .filter(e -> hasColSpace(e.getKey()))
               .max(Comparator.comparing(Map.Entry::getValue));

        Optional<Map.Entry<Integer, Integer>> maxPopulatedDiagOpt = diagPopulation().entrySet().stream()
               .max(Comparator.comparing(Map.Entry::getValue));

        Map.Entry<Integer, Integer> row = maxPopulatedRowOpt.get();
        Map.Entry<Integer, Integer> col = maxPopulatedColOpt.get();
        Map.Entry<Integer, Integer> diag = maxPopulatedDiagOpt.get();

        Integer[] rowData = {row.getKey(), row.getValue(), 0};
        Integer[] colData = {col.getKey(), col.getValue(), 1};
        Integer[] diagData = {diag.getKey(), diag.getValue(), 2};

        List<Integer[]> data = new ArrayList<>(3);
        data.add(rowData);
        data.add(colData);
        data.add(diagData);

        Optional<Integer[]> maxDataOpt = data.stream()
               .max(new Comparator<Integer[]>() {
                   @Override
                   public int compare(Integer[] integers, Integer[] t1) {
                       return integers[1].compareTo(t1[1]);
                   }
               });

        Integer[] maxData = maxDataOpt.get();

        switch (maxData[2]) {
            case 1: colInd = maxData[0];
                    rowInd = findSlotInCol(colInd);
                break;
            case 2: rowInd = random.nextInt(boardSize);
                    colInd = rowInd;
                break;
            default:
                rowInd = maxData[0];
                colInd = findSlotInRow(rowInd);
        }
        return new int[] {rowInd, colInd};
    }

    private boolean hasRowSpace(int rowInd) {
        for (int i=0; i<boardSize; i++) {
            if (board[rowInd][i] == '\u0000') return true;
        }
        return false;
    }

    private boolean hasColSpace(int colInd) {
        for (int i=0; i<boardSize; i++) {
            if (board[i][colInd] == '\u0000') return true;
        }
        return false;
    }

    private int findSlotInRow(int rowInd) {

        for (int i=0; i<boardSize; i++) {
            if (board[rowInd][i] == '\u0000') return i;
        }
        return -1;
    }

    private int findSlotInCol(int colInd) {

        for (int i=0; i<boardSize; i++) {
            if (board[i][colInd] == '\u0000') return i;
        }
        return -1;
    }

    //maps row index to amount of enemy signs it holds
    private Map<Integer, Integer> rowPopulation() {

        Map<Integer, Integer> rowPopulation = new HashMap<>();
        for (int i=0; i<boardSize; i++) {
            rowPopulation.put(i, enemySignsInStripe(board[i]));
        }

        return rowPopulation;
    }

    //maps col index to amount of enemy signs it holds
    private Map<Integer, Integer> colPopulation() {

        Map<Integer, Integer> colPopulation = new HashMap<>();
        for (int j=0; j<boardSize; j++) {
            int colSum = 0;
            for (int i=0; i<boardSize; i++) {
                if (board[i][j] == enemySign) colSum += 1;
            }
            colPopulation.put(j, colSum);
        }

        return colPopulation;
    }

    private Map<Integer, Integer> diagPopulation() {

        Map<Integer, Integer> diagPopulation = new HashMap<>();

        int decreasing = 0;
        int increasing = 0;

        for(int i=0; i<boardSize; i++) {
            if (board[i][i] == enemySign) decreasing++;
            if (board[boardSize-1-i][i] == enemySign) increasing++;
        }

        diagPopulation.put(0, decreasing);
        diagPopulation.put(1, increasing);

        return diagPopulation;
    }

    private int enemySignsInStripe(char[] stripe) {
        int amount = 0;
        for (char sign: stripe) {
            if (sign==enemySign) amount++;
        }
        return amount;
    }
}
