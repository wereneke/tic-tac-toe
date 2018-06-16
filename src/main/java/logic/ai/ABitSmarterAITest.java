package logic.ai;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ABitSmarterAITest {

    private static char[][] board;
    private static ABitSmarterAI ai;
    private static List<char[]> listed;

    @BeforeAll
    static void setUp() {
        board = new char[10][10];
        listed = Arrays.asList(board);
        ai = new ABitSmarterAI();

        ai.setBoard(board);
        ai.setEnemySign('\123');

        for (int i=0; i<20; i++) {
            int row = ai.random.nextInt(10);
            int col = ai.random.nextInt(10);

            board[row][col] = '\123';
        }

        for (char[] row: board) System.out.println(row);
        System.out.println();
    }

    @Test
    void testIfMostPopulatedWorks() {

        for (char[] stripe: ai.rowsOfBoard) System.out.println(stripe);
        System.out.println();
        for (char[] stripe: ai.colsOfBoard) System.out.println(stripe);
        System.out.println();
        for (char[] stripe: ai.diagOfBoard) System.out.println(stripe);
    }
}