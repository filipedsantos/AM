package pt.isec.a21180878.snakemultiplayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import pt.isec.a21180878.snakemultiplayer.game.SnakeGame;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onStartGame(View view) {
        Intent intent = new Intent(MainActivity.this, SnakeGame.class);
        startActivity(intent);
    }
}
