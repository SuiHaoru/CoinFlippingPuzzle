package CoinFlippingGame.model;

import java.util.*;
import CoinFlippingGame.javafx.controller.*;
import CoinFlippingGame.result.*;
import org.tinylog.Logger;
import java.time.LocalDateTime;
/**
 * Class representing the state of the game.
 */

public class CoinFlippingState {
    /**
     * Creates the board of 1x10 cells, initialize all of them to heads up at the beginning
     */
    Stack editedCoins = new Stack();
    public char[] board = new char[10];
    LocalDateTime startOfGame;
    public String currentPlayer;
    public String player1;
    public String player2;
    public int countChosenCoins = 0;
    public int player1Moves = 0;
    public int player2Moves = 0;
    public String winner;
    int rightMostIndex = 0;
    GameResultToJSON resultWriter;
    GameResult gameInfo;

    public CoinFlippingState() {
        for (int i = 0; i < board.length; i++) {
            board[i] = 'H';
        }
        resultWriter = new GameResultToJSON();
        gameInfo = new GameResult();
        Logger.info("Game started");
        startOfGame = LocalDateTime.now();
    }

    /**
     * flips the coin at the given index.
     * @param index
     */
    public void flipCoin(int index) {
        if (board[index] == 'H') {
            board[index] = 'T';
        } else {
            board[index] = 'H';
        }
        countChosenCoins++;
        editedCoins.push(index);
        Logger.debug("Flipped the coin");
        if (index > rightMostIndex) {
            rightMostIndex = index;
        }
    }

    /**
     * checks if the all the requirements are met before changing the player
     * @return
     */
    public boolean canCoinsFlipped() {
        if (countChosenCoins < 4 && board[rightMostIndex] == 'T') {
            return true;
        }
        return false;
    }

    /**
     * swaps the players after nect button is pressed
     * @return
     */
    public boolean nextPlayer() {
        if (isGameComplete()) {
            Logger.info("WINNER IS {}",currentPlayer);
            gameInfo.setWinner(currentPlayer);
            gameInfo.setPlayer1(player1);
            gameInfo.setPlayer2(player2);
            gameInfo.setStartOfGame(startOfGame);
            gameInfo.setPlayer1Count(player1Moves);
            gameInfo.setPlayer2Count(player2Moves);
            resultWriter.addGameResult(gameInfo);
            return true;
        }
        if (canCoinsFlipped()) {
            if (currentPlayer == player1) {
                currentPlayer = player2;
                player1Moves++;
            } else {
                currentPlayer = player1;
                player2Moves++;
            }
            Logger.info(currentPlayer);
            rightMostIndex = 0;
            countChosenCoins = 0;
            editedCoins = new Stack();
            return true;
        } else {
            clearCurrentStep();
            rightMostIndex = 0;
            countChosenCoins = 0;
            return false;
        }
    }


    private void clearCurrentStep() {
        Stack temporaryCoins = (Stack) editedCoins.clone();
        Logger.debug("Clearing the incorrect state of the game");
        temporaryCoins.stream().forEach(e -> flipCoin((int) e));
        draw();
        editedCoins = new Stack();
    }

    /**
     * used for drawing state of the game
     */
    public void draw() {
        for (int i = 0; i < board.length; i++) {
            System.out.print(board[i] + " | ");
        }
    }

    /**
     * checks if the game is complete
     * @return
     */
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

    /**
     * restarts the game by resetting settings
     */
    public void restart(){
        for (int i = 0; i < board.length; i++) {
            board[i] = 'H';
        }
        countChosenCoins = 0;
        rightMostIndex = 0;
        editedCoins = new Stack();
        Logger.info("Game restarted!");
    }

}
