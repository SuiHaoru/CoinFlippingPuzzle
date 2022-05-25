
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import CoinFlippingGame.model.*;

import static org.junit.jupiter.api.Assertions.*;


class ModelTest {

    private ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private CoinFlippingState model = new CoinFlippingState();


    @Test
    public void testDraw(){
        System.setOut(new PrintStream(outputStreamCaptor));
        model.draw();
        assertEquals("H | H | H | H | H | H | H | H | H | H | ",outputStreamCaptor.toString());
    }

    @Test
    void testFlipCoin(){
        System.setOut(new PrintStream((outputStreamCaptor)));
        outputStreamCaptor.reset();
        model.flipCoin(9);
        outputStreamCaptor.reset();
        model.draw();
        assertEquals("H | H | H | H | H | H | H | H | H | T | ",outputStreamCaptor.toString());
    }

    @Test
    void testRestart(){
        System.setOut(new PrintStream(outputStreamCaptor));
        model.restart();
        outputStreamCaptor.reset();
        model.draw();
        assertEquals("H | H | H | H | H | H | H | H | H | H | ",outputStreamCaptor.toString());
    }

    @Test
    void testNextPlayer(){
        model.player1 = "player1";
        model.player2 = "player2";
        model.currentPlayer = model.player1;
        model.countChosenCoins = 1;
        model.flipCoin(4);
        model.nextPlayer();
        assertEquals(model.player2,model.currentPlayer);
    }

    @Test
    void testCanCoinsFlipped(){
        model.restart();
        model.countChosenCoins=5;
        assertFalse(model.canCoinsFlipped());
    }

    @Test
    void testGameFinished(){
        model.restart();
        for(int i = 0;i<model.board.length;i++){
            model.flipCoin(i);
        }
        assertTrue(model.isGameComplete());
    }

}
