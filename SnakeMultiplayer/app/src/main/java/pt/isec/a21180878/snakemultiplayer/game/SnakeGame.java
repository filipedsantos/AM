package pt.isec.a21180878.snakemultiplayer.game;

import android.app.Activity;
import android.os.Bundle;

public class SnakeGame extends Activity {

    // SnakeView will be the view of the game
    // It will also hold the logic of the game and respond to screen touches as well
    private SnakeView snakeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize gameView and set it as the view
        snakeView = new SnakeView(this);
        setContentView(snakeView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Tell the snakeView resume method to execute
        snakeView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Tell the snakeView pause method to execute
        snakeView.pause();
    }
}
