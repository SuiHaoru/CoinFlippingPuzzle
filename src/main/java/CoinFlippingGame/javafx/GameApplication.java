package CoinFlippingGame.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * The Application class of the project.
 */
public class GameApplication extends Application {
    /**
     * @author Sui Haoru
     * @param stage the stage for showing a scene
     * @throws Exception declare exceptions that may occur during the execution of the program
     * */
    public void start(Stage stage) throws Exception {
        stage.setTitle("Coin Flipping Game");
        stage.setResizable(false);
        stage.setScene(new Scene((Parent) FXMLLoader.load(getClass().getResource("/fxml/launch.fxml"))));
        stage.show();
    }
}
