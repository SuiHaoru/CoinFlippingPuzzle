package CoinFlippingGame.model;
import java.util.*;

/**
 * Class representing the state of the game.
 */

public class CoinFlippingState {
    /**
     * Creates the board of 1x10 cells, initialize all of them to heads up at the beginning
     */
    Stack editedCoins = new Stack();
    public char[] board = new char[10];
    public String currentPlayer, player1, player2;
    int countChosenCoins=0;
    int rightMostIndex = 0;
    public CoinFlippingState(){
        for(int i = 0;i<board.length;i++){
            board[i]='H';
        }
        currentPlayer=player1;
        System.out.println(currentPlayer);
    }



    public void flipCoin(int index) {
        if (board[index] == 'H') {
            board[index] = 'T';
        }else {
            board[index] = 'H';
        }
        countChosenCoins++;
        editedCoins.push(index);
        if (index > rightMostIndex) {
            rightMostIndex = index;
        }
    }

    public boolean canCoinsFlipped() {
        if (countChosenCoins < 4 && board[rightMostIndex] == 'T') {
            return true;
        }
        return false;
    }

    public boolean nextPlayer() {
        if(isGameComplete()){
            System.out.println("WINNER IS" + currentPlayer);
            return true;
        }
        if (canCoinsFlipped()) {
            if (currentPlayer == player1) {
                currentPlayer = player2;
            }else{
                currentPlayer = player1;
            }
            rightMostIndex = 0;
            countChosenCoins = 0;
            editedCoins = new Stack();
            return true;
        }
        else {
            clearCurrentStep();
            rightMostIndex = 0;
            countChosenCoins = 0;
            return false;
        }
    }


    public void clearCurrentStep(){
        Stack temporaryCoins = (Stack)editedCoins.clone();
        temporaryCoins.stream().forEach(e -> flipCoin((int)e));
        draw();
        editedCoins = new Stack();
    }

    public void draw(){
        for(int i = 0;i<board.length;i++){
            System.out.print(board[i]+" | ");
        }
    }
    public boolean isGameComplete() {
        int countTails = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[i] == 'T')
                countTails++;
        }
        if (countTails == 10)
            return true;
        return false;
    }


}
