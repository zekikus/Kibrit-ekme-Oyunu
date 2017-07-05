package com.example.stas01_pc.kibrit_oyunu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/**
 * Created by ISTAS01-PC on 09.07.2015.
 */
public class Score extends Activity {

    private TextView scoreText;
    private TextView header;
    private Button newGame;
    private String gData;
    private Bundle sBundle = null;



    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_layout);


        scoreText = (TextView) findViewById(R.id.sText);
        header = (TextView) findViewById(R.id.header);
        newGame = (Button) findViewById(R.id.newGame);
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent nGameIntent = new Intent(Score.this,MainActivity.class);
               startActivity(nGameIntent);
            }
        });
        header.setText("Oyuncu ID\t\t\tYenilgi Sayısı");
        sBundle = new Bundle();
        sBundle = getIntent().getExtras();
        gData = sBundle.getString("gData");
        scoreText.setText(gData);

    }

}