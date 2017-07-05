package com.example.stas01_pc.kibrit_oyunu;


import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


public class MainActivity extends Activity implements View.OnClickListener {

    private ImageButton rightButton;
    private ImageButton leftButton;
    private Button startButton;
    private Button exitButton;
    private TextView playerCount;
    private int pCountt = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rightButton = (ImageButton) findViewById(R.id.rightBtn);
        rightButton.setOnClickListener(this);
        leftButton = (ImageButton) findViewById(R.id.leftBtn);
        leftButton.setOnClickListener(this);
        startButton = (Button) findViewById(R.id.startBtn);
        startButton.setOnClickListener(this);
        exitButton = (Button) findViewById(R.id.exitBtn);
        exitButton.setOnClickListener(this);
        playerCount = (TextView) findViewById(R.id.pCount);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.leftBtn){
            if(pCountt > 2){
                pCountt--;
                playerCount.setText(pCountt + " Oyuncu");
            }
        }else if(v.getId() == R.id.rightBtn){
            if(pCountt < 5){
                pCountt++;
                playerCount.setText(pCountt + " Oyuncu");
            }
        }else if(v.getId() == R.id.startBtn){
            Intent intentim = new Intent(MainActivity.this,Game.class);
            intentim.putExtra("oSayi",String.valueOf(pCountt));
            startActivity(intentim);
        }else if(v.getId() == R.id.exitBtn){
            this.finish();
        }
    }
}
