package apps.com.jmsanchez.conecta4;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Game {

    private final int FREEPOSITION = 0;
    private final int PLAYER1POSITION = 1;
    private final int PLAYER2POSITION = 2;
    static final int ROWNUMBER = 6;
    static final int COLUMNNUMBER = 7;

    private int board[][];

    public Game() {
        this.board = new int[ROWNUMBER][COLUMNNUMBER];

        for(int i=0; i<ROWNUMBER; i++){
            for(int j=0; j<COLUMNNUMBER; j++){
                this.board[i][j] = FREEPOSITION;
            }
        }
    }

    public boolean isFree(int i, int j){
        return (this.board[i][j] == FREEPOSITION);
    }

    public boolean isPlayer1(int i, int j){
        return (this.board[i][j] == PLAYER1POSITION);
    }

    public boolean isPlayer2(int i, int j){
        return (this.board[i][j] == PLAYER2POSITION);
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

    public void playPlayer1()
    {
        //API 21
        //int row = ThreadLocalRandom.current().nextInt(0, ROWNUMBER);
        boolean isAvailable = false;

        do{
            Random rand = new Random();
            int row = rand.nextInt(ROWNUMBER);
            int column = rand.nextInt(COLUMNNUMBER);

            if(this.isPosiblePutPieceIn(row, column)){
                isAvailable = true;
                this.board[row][column] = PLAYER1POSITION;
            }
        }while (!isAvailable);
    }

    public int getBoardPosition(int i, int j){
        return this.board[i][j];
    }

    public void playPlayer2(int i, int j){
        this.board[i][j] = PLAYER2POSITION;
    }
}