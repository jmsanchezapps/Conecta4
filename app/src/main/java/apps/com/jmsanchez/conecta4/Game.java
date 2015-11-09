package apps.com.jmsanchez.conecta4;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Game {

    private final int FREE = 0;
    public final int PLAYER1 = 1;
    public final int PLAYER2 = 2;
    private final int ROWNUMBER = 6;
    private final int COLUMNNUMBER = 7;
    private final int MATCHS = 4;

    private int board[][];
    private int winner = FREE;

    public Game() {
        this.board = new int[ROWNUMBER][COLUMNNUMBER];

        for(int i=0; i<ROWNUMBER; i++){
            for(int j=0; j<COLUMNNUMBER; j++){
                this.board[i][j] = FREE;
            }
        }
    }

    public boolean isFree(int i, int j){
        return (this.board[i][j] == FREE);
    }

    public boolean isPlayer1(int i, int j){
        return (this.board[i][j] == PLAYER1);
    }

    public boolean isPlayer2(int i, int j){
        return (this.board[i][j] == PLAYER2);
    }

    public boolean isPosiblePutPieceIn(int i, int j){
        if(!this.isFree(i,j))
            return false;

        int maxFreeRow = 0;

        for(int row = i; row < ROWNUMBER; row++){
            if(this.isFree(row,j)){
                maxFreeRow = row;
            }
        }

        return (i == maxFreeRow);
    }

    public void playPlayer1() {
        //API 21
        //int row = ThreadLocalRandom.current().nextInt(0, ROWNUMBER);
        boolean isAvailable = false;

        do{
            Random rand = new Random();
            int row = rand.nextInt(ROWNUMBER);
            int column = rand.nextInt(COLUMNNUMBER);

            if(this.isPosiblePutPieceIn(row, column)){
                isAvailable = true;
                this.board[row][column] = PLAYER1;
            }
        }while (!isAvailable);

        if (this.check4(PLAYER1))
            winner = PLAYER1;
    }

    public int getBoardPosition(int i, int j){
        return this.board[i][j];
    }

    public void playPlayer2(int i, int j){
        this.board[i][j] = PLAYER2;

        if (this.check4(PLAYER2))
            winner = PLAYER2;
    }

    public boolean boardIsFull() {
        for (int i = 0; i < ROWNUMBER; i++) {
            for (int j = 0; j < COLUMNNUMBER; j++) {
                if (this.board[i][j] == FREE)
                    return false;
            }
        }

        return true;
    }

    public boolean isFinished() {
        return (this.boardIsFull() || (this.winner != FREE));
    }

    private boolean checkRow(int turn) {
        for (int i = 0; i < ROWNUMBER; i++) {
            int matchNumber = 0;
            for (int j = 0; j < COLUMNNUMBER; j++) {
                if (this.board[i][j] == turn)
                    matchNumber++;
                else
                    matchNumber = 0;

                if (matchNumber == MATCHS)
                    return true;
            }
        }
        return false;
    }

    private boolean checkColumn(int turn) {
        for (int i = 0; i < COLUMNNUMBER; i++) {
            int matchNumber = 0;
            for (int j = 0; j < ROWNUMBER; j++) {
                if (this.board[j][i] == turn)
                    matchNumber++;
                else
                    matchNumber = 0;

                if (matchNumber == MATCHS)
                    return true;
            }
        }
        return false;
    }

    private boolean checkDiagonal(int turn) {
        for (int i = 0; i < 3; i++) {
            int matchNumber = 0;
            for (int k = 0; k < ROWNUMBER - i; k++) {
                if (this.board[i + k][k] == turn)
                    matchNumber++;
                else
                    matchNumber = 0;

                if (matchNumber == MATCHS)
                    return true;
            }
        }

        for (int j = 1; j < 4; j++) {
            int matchNumber = 0;
            for (int k = 0; k < COLUMNNUMBER - j; k++) {
                if (this.board[k][j + k] == turn)
                    matchNumber++;
                else
                    matchNumber = 0;

                if (matchNumber == MATCHS)
                    return true;
            }
        }

        return false;
    }

    private boolean check4(int turn) {
        return (this.checkRow(turn) || this.checkColumn(turn) || this.checkDiagonal(turn));
    }

    public String getResult() {
        if (!this.isFinished())
            return "";

        StringBuilder result = new StringBuilder();
        result.append("Juego terminado.");

        if (winner != FREE) {
            String finalWinner = (this.winner == PLAYER1) ? " Has perdido." : " Has ganado.";
            result.append(finalWinner);
        }

        return result.toString();
    }
}