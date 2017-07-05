package com.example.stas01_pc.kibrit_oyunu;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by ISTAS01-PC on 07.07.2015.
 */

public class Game extends Activity implements View.OnClickListener{

    private int gCount;
    private int aktifOyuncu;
    private int kisaCop;
    private int kalanKibrit = 6;
    private Bundle bundle;
    private TextView gPCount;
    private Match[] matchArray;
    private Player[] playerArray;
    private int[] intArray = {R.id.kibrit_1,R.id.kibrit_2,R.id.kibrit_3,R.id.kibrit_4,R.id.kibrit_5,R.id.kibrit_6};
    private ImageButton[] btnArray;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);

        bundle = new Bundle();
        bundle = getIntent().getExtras();
        gCount = Integer.parseInt(bundle.getString("oSayi"));

        gPCount = (TextView) findViewById(R.id.playerCount);
        gPCount.setText((aktifOyuncu + 1) + ".Oyuncu");
        matchArray = new Match[6];
        playerArray = new Player[gCount];
        btnArray = new ImageButton[6];
        createMatch();
        createPlayers();
        createButtons();
        shuffle();
    }

    //Kisa Kibriti Degistir
    public void shuffle(){
        Random rnd = new Random();
        kisaCop = rnd.nextInt(6);
        while(!matchArray[kisaCop].getIsEnabled()){
            kisaCop = rnd.nextInt(6);
        }
        matchArray[kisaCop].setIsShort(true);
    }

    public void createMatch(){
        for(int i = 0; i < matchArray.length; i++){
            matchArray[i] = new Match(i+1,false);
        }
    }

    public void createPlayers(){
        for (int i = 0; i < playerArray.length; i++){
            playerArray[i] = new Player(i+1,0);
        }
    }

    public void createButtons(){
        for (int i = 0; i < btnArray.length; i++){
            btnArray[i] = (ImageButton)findViewById(intArray[i]);
            btnArray[i].setOnClickListener(this);
        }
    }
    public void moveForward(ImageButton gButton){
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) gButton.getLayoutParams();
        params.topMargin = 260;
        gButton.setLayoutParams(params);
    }

    public void moveBack(ImageButton gButtonn){
        ViewGroup.MarginLayoutParams paramsB = (ViewGroup.MarginLayoutParams) gButtonn.getLayoutParams();
        paramsB.topMargin = 560;
        gButtonn.setLayoutParams(paramsB);
    }

    public void moveCenter(ImageButton gButton){
        ViewGroup.MarginLayoutParams paramsB = (ViewGroup.MarginLayoutParams) gButton.getLayoutParams();
        paramsB.topMargin = 500;
        gButton.setLayoutParams(paramsB);
    }

    public void showLoserDialog(final int index){

        int loseScore = playerArray[aktifOyuncu].getLoseScore() + 1;
        playerArray[aktifOyuncu].setLoseScore(loseScore);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        alertDialogBuilder.setTitle("Kaybettin");
        alertDialogBuilder
                .setMessage("Kısa Çöpü Çektin.Bravo!")
                .setCancelable(false)
                .setPositiveButton("Yeni Oyun", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        newGame(index);
                    }
                })
                .setNegativeButton("Skorlar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        newGame(index);
                        createIntent();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    public void showSuccessDialog(final int index){

        AlertDialog.Builder successDialogBuilder = new AlertDialog.Builder(
                this);

        successDialogBuilder.setTitle("Tebrikler");
        successDialogBuilder
                .setMessage("Kısa Kibrit Sende Değil")
                .setCancelable(false)
                .setPositiveButton("Devam Et", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        matchArray[index].setIsEnabled(false);
                        if ((aktifOyuncu + 1) < gCount)
                            ++aktifOyuncu;
                        else {
                            aktifOyuncu = 0;
                            matchArray[kisaCop].setIsShort(false);
                            shuffle();
                        }
                        moveBack(btnArray[index]);
                        gPCount.setText((aktifOyuncu + 1) + ".Oyuncu");
                        --kalanKibrit;
                        if(kalanKibrit == 1)
                            showLoserDialog(index);
                    }
                });

        AlertDialog successDialog = successDialogBuilder.create();
        successDialog.show();
    }

    public void createIntent(){
        Intent sIntent = new Intent(this,Score.class);
        String data = "";
        for (int i = 0; i < playerArray.length; i++){
            data += "\t\t\t\tOyuncu " + playerArray[i].getPlayerID() +"\t\t\t\t\t\t\t\t\t\t" + playerArray[i].getLoseScore() + "\n";
        }
        sIntent.putExtra("gData", data);
        startActivity(sIntent);
    }

    public void newGame(int index){
        aktifOyuncu = 0;
        kalanKibrit = 6;
        matchArray[kisaCop].setIsShort(false);
        restartButton();
        shuffle();
        gPCount.setText((aktifOyuncu + 1) + ".Oyuncu");
    }

    public void restartButton(){
        for (int i = 0; i < btnArray.length; i++){
            btnArray[i].setEnabled(true);
            moveCenter(btnArray[i]);
            matchArray[i].setIsEnabled(true);
        }
    }
    @Override
    public void onClick(View v) {
        for(int i = 0;i < btnArray.length; i++){
            if(v.getId() == intArray[i]){

                moveForward(btnArray[i]);
                if(matchArray[i].getIsShort()){
                    showLoserDialog(i);
                }else{
                    btnArray[i].setEnabled(false);
                    showSuccessDialog(i);
                }
                break;
            }
        }
    }
}
