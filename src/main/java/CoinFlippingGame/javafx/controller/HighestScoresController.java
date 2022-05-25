package CoinFlippingGame.javafx.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import java.util.Map;
import java.util.*;

/**
 * contoller class for the HighestScores UI.
 */
public class HighestScoresController{

    @FXML
    GridPane resultGrid;

    /**
     * Initializes the highest score list with the top 5 players and their number of wins.
     */
    public void initialize(){
        GameResultToJSON reader = new GameResultToJSON();
        List<Map.Entry<String,Integer>> list = reader.getTopScoreList();
        for(int i = 0 ;i<list.size();i++){
            resultGrid.add(new Text("Name: "+list.get(i).getKey() + "\nScore: " + list.get(i).getValue()),0,i);
        }
    }
}