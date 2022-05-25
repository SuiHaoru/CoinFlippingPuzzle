package CoinFlippingGame.javafx.controller;

import java.io.*;
import CoinFlippingGame.result.*;
import java.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.core.type.TypeReference;
import org.tinylog.Logger;

/**
 * class for storing game information in JSON file.
 */
public class GameResultToJSON {
    private File file = new File("GameResults.json");
    private ObjectMapper mapper = new ObjectMapper();

    /**
     * a constructor.
     */
    public GameResultToJSON(){
        mapper.registerModule(new JavaTimeModule());
    }

    /**
     * Adding game information to the list of game results.
     * @param result GameResult object for storing information
     */
    public void addGameResult(GameResult result){
        try{
            List<GameResult> gameResults = new ArrayList<GameResult>();
            if(file.exists()) {
                FileReader reader = new FileReader(file);
                gameResults = mapper.readValue(reader,new TypeReference<ArrayList<GameResult>>(){});
            }
            FileWriter writer = new FileWriter(file);
            gameResults.add(result);
            mapper.writeValue(writer,gameResults);
            Logger.info("Writing to the file");
        }catch (Exception e){
            Logger.error(e.getStackTrace());
        }
    }

    /**
     * method for getting the top 5 winners of the game.
     * @return the top 5 winners
     */
    public List<Map.Entry<String,Integer>> getTopScoreList(){
        Map<String,Integer> winners = new HashMap<>();
        try(FileReader reader = new FileReader(file)){
            List<GameResult> gameResults = new ArrayList<GameResult>();
            gameResults = mapper.readValue(reader,new TypeReference<ArrayList<GameResult>>(){});
            gameResults.stream().forEach(element->{
                if(winners.containsKey(element.getWinner())) {
                    winners.put(element.getWinner(), Integer.valueOf(winners.get(element.getWinner()) + Integer.valueOf(1)));
                }else{
                    winners.put(element.getWinner(), Integer.valueOf(Integer.valueOf(1)));
                }
            });

        }catch(Exception e){
            Logger.error(e.getStackTrace());
        }
        return sortMap(winners).stream().limit(5).toList();
    }
    private List<Map.Entry<String,Integer>> sortMap(Map<String,Integer> map){
        List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(map.entrySet());
        Collections.sort(list,(firstElement,secondElement)->{
            return secondElement.getValue().compareTo(firstElement.getValue());
        });
        return list;
    }


}
