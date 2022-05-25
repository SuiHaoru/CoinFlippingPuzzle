package CoinFlippingGame.javafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller class for the start page.
 */
public class LaunchController {

    FXMLLoader fxmlLoader = new FXMLLoader();

    @FXML
    TextField player1;
    @FXML
    TextField player2;

    @FXML
    Label errorLabel1;
    @FXML
    Label errorLabel2;

    @FXML
    Button startButton;

    /**
     * checks for the user names and changes the scene to the game.
     * @param actionEvent
     * @throws IOException
     */
    public void startAction(ActionEvent actionEvent) throws IOException {
        if (player1.getText().isEmpty() && player2.getText().isEmpty()) {
            errorLabel1.setText("Enter Player One's name!");
            errorLabel2.setText("Enter Player Two's name!");
        }
        else if (player2.getText().isEmpty())
            errorLabel2.setText("Enter Player Two's name!");
        else if (player1.getText().isEmpty())
            errorLabel1.setText("Enter Player One's name!");
        else {
            fxmlLoader.setLocation(getClass().getResource("/fxml/game.fxml"));
            Parent root = fxmlLoader.load();
            fxmlLoader.<GameController>getController().setPlayer1(player1.getText());
            fxmlLoader.<GameController>getController().setPlayer2(player2.getText());
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        }
    }

}
