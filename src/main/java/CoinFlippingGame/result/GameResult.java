package CoinFlippingGame.result;
import java.time.LocalDateTime;

/**
 * class for storing the game info
 */
@lombok.Data
public class GameResult {
    private LocalDateTime startOfGame;
    private int player1Count;
    private int player2Count;
    private String player1;
    private String player2;
    private String winner;
}
