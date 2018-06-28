package logic.ai;

import java.util.*;

public abstract class AI {

    Random random = new Random();
    char[][] board;
    int boardSize;
    char enemySign;
    char mySign;

    public abstract int[] coordinates();

    public void setBoard(char[][] board) {
        this.board = board;
        this.boardSize = board.length;
    }

    public void setEnemySign(char sign) {
        this.enemySign = sign;
    }

    public void setMySign(char sign) {this.mySign = mySign; }

}
