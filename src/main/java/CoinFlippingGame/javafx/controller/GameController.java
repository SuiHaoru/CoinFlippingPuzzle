package CoinFlippingGame.javafx.controller;

import CoinFlippingGame.model.CoinFlippingState;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


public class GameController {
    FXMLLoader fxmlLoader = new FXMLLoader();
    @FXML
    GridPane grid;

    @FXML
    Button nextButton;

    @FXML
    Button resetButton;

    @FXML
    Button exitButton;

    @FXML
    Label errorLabel;

    CoinFlippingState model = new CoinFlippingState();

    public void setPlayer1(String player1) {
        model.player1 = player1;
    }
    public void setPlayer2(String player2) {
        model.player2 = player2;
    }


    public void initialize(){
        for(int i =0;i<model.board.length;i++){
            grid.add(createCell(i),i,0);
        }
    }

    private Pane createCell(int index) {
        Pane pane = new Pane();
        pane.setPrefSize(80, 80);
        pane.setStyle("-fx-border-color:black");
        pane.setOnMouseClicked(e -> handleClick(e));
        Text text = new Text();
        text.setText(""+ model.board[index]);
        text.setX(pane.getHeight() +45);
        text.setY(pane.getWidth() +45);
        pane.getChildren().add(text);
        return pane;
    }
    public void handleNextClick(){
        if(model.nextPlayer()){
            errorLabel.setText("current player is: " + model.currentPlayer);
        }else{
            errorLabel.setText("ERROR!Not conforming to rules!");
            grid.getChildren().clear();
            for(int i =0;i<model.board.length;i++){
                grid.add(createCell(i),i,0);
            }
        }
    }
    public void handleExitClick(ActionEvent actionEvent) throws IOException{
        fxmlLoader.setLocation(getClass().getResource("/fxml/highscores.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void handleClick(MouseEvent event) {
        Pane pane = (Pane) event.getSource();
        int column = GridPane.getColumnIndex(pane);
        model.flipCoin(column);
        Text text = new Text();
        text.setText(""+ model.board[column]);
        text.setX(pane.getHeight() / 2);
        text.setY(pane.getWidth() / 2);
        pane.getChildren().clear();
        pane.getChildren().add(text);
        System.out.println(model.board[column]);

    }
}
