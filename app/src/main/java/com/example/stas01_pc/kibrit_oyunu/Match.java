package com.example.stas01_pc.kibrit_oyunu;

import java.util.Random;

/**
 * Created by ISTAS01-PC on 07.07.2015.
 */
public class Match {

    private int matchID;
    private boolean isShort;
    private boolean isEnabled;

    public Match(){
        isShort = false;
        isEnabled = true;
    }

    public Match(int matchID,boolean isShort){
        this.matchID = matchID;
        this.isShort = isShort;
        isEnabled = true;
    }

    public int getMatchID() {
        return matchID;
    }

    public void setMatchID(int matchID) {
        this.matchID = matchID;
    }

    public void setIsShort(boolean isShort) {
        this.isShort = isShort;
    }

    public boolean getIsShort(){
        return isShort;
    }

    public void setIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public boolean getIsEnabled(){
        return isEnabled;
    }


}
