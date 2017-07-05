package com.example.stas01_pc.kibrit_oyunu;

/**
 * Created by ISTAS01-PC on 07.07.2015.
 */
public class Player {
    private int playerID;
    private int loseScore;

    public Player(){
        loseScore = 0;
    }

    public Player(int playerID, int loseScore){
        this.playerID = playerID;
        this.loseScore = loseScore;
    }

    public int getLoseScore() {
        return loseScore;
    }

    public void setLoseScore(int loseScore) {
        this.loseScore = loseScore;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }
}
