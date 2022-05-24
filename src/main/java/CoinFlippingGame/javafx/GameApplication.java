package CoinFlippingGame.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameApplication extends Application {
    public void start(Stage stage) throws Exception {

        stage.setTitle("Coin Flipping Game");
        stage.setResizable(false);
        stage.setScene(new Scene((Parent) FXMLLoader.load(getClass().getResource("/fxml/launch.fxml"))));
        stage.show();
    }
}
