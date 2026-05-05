package org.example;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.HashMap;

@JsonRootName("Game")
public class Game{
    public String gameID;
    public int count1=0;
    public int count2=0;
    @JsonIgnore
    public HashMap<String,Integer> stats=new HashMap<>();

    public Game(String gameID, int count1, int count2) {
        this.count1 = count1;
        this.count2 = count2;
        this.gameID = gameID;
    }

    public int getCount2() {
        return count2;
    }

    public int getCount1() {
        return count1;
    }

    public String getGameID() {
        return gameID;
    }

    public void incrementCount1(String navigator){
        stats.merge(navigator, 1, Integer::sum);
        count1++;
    }

    public void incrementCount2(String navigator){
        stats.merge(navigator, 1, Integer::sum);
        count2++;
    }

    public void raz(){
        count1=0;
        count2=0;
    }

}