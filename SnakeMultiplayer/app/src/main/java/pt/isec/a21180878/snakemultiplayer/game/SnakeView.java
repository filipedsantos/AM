package pt.isec.a21180878.snakemultiplayer.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;

public class SnakeView extends SurfaceView implements Runnable, SwipeInterface {

    final String TAG = SnakeView.this.getClass().getSimpleName();

    static final int MIN_SWIPE_DISTANCE = 100;
    static final int MAX_CLICK_TOLERANCE = 50;
    private float downX, downY;

    private Thread gameThread = null;

    private SurfaceHolder gameHolder;

    private boolean playing;

    boolean paused = true;

    private Canvas canvas;

    private Paint paint;

    long fps;

    private long actualTime;

    private int screenX, sreenY;

    public SnakeView(Context context) {
        super(context);

        gameHolder = getHolder();
        paint = new Paint();

        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();;

        Point size = new Point();
        display.getSize(size);

        screenX = size.x;
        sreenY = size.y;
    }

    @Override
    public void run() {
        while(playing){

            long starTime = System.currentTimeMillis();

            if(!paused){
                update();
            }
            
            draw();

            actualTime = System.currentTimeMillis() - starTime;

            if(actualTime >= 1){
                fps = 1000/actualTime;
            }
        }

    }

    private void draw() {

        if(gameHolder.getSurface().isValid()){

            // Lock the canvas ready to draw
            canvas = gameHolder.lockCanvas();

            canvas.drawColor(Color.argb(255,  26, 128, 182));

            paint.setColor(Color.argb(255,  255, 255, 255));

            gameHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void update() {
    }

    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void pause() {
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction() & MotionEvent.ACTION_MASK){

            // Player has touched the screen
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                return true;

            // Player has removed finger from screen
            case MotionEvent.ACTION_UP: {
                float upX = event.getX();
                float upY = event.getY();

                float deltaX = downX - upX;
                float deltaY = downY - upY;

                // click ??
                if (deltaX <= MAX_CLICK_TOLERANCE && deltaY <= MAX_CLICK_TOLERANCE) {
                    this.onClick(this, (int) upX, (int) upY);
                    return true;
                }

                // swipe horizontal?
                if (Math.abs(deltaX) > MIN_SWIPE_DISTANCE) {
                    // Left or Right Swipe
                    if (deltaX < 0) {
                        swipeLeft(this);
                        return true;
                    }
                    if (deltaX > 0) {
                        swipeRight(this);
                        return true;
                    }
                } else {
                    Log.i(TAG, "Horizontal swipe was only " + Math.abs(deltaX) + " long, need at least " + MIN_SWIPE_DISTANCE);
                }

                // swipe vertical?
                if (Math.abs(deltaY) > MIN_SWIPE_DISTANCE) {
                    // top or down Swipe
                    if (deltaY < 0) {
                        swipeDown(this);
                        return true;
                    }
                    if (deltaY > 0) {
                        swipeUp(this);
                        return true;
                    }
                } else {
                    Log.i(TAG, "Vertical swipe was only " + Math.abs(deltaX) + " long, need at least " + MIN_SWIPE_DISTANCE);

                }
            }
        }

        return false;
    }

    @Override
    public void swipeUp(View v) {
        Log.v(TAG, "SwipeUP!");
        //activity.swipeUp(v);
    }

    @Override
    public void swipeDown(View v) {
        Log.v(TAG, "SwipeDOWN!");
        //activity.swipeDown(v);
    }

    @Override
    public void swipeLeft(View v) {
        Log.v(TAG, "SwipeLeft!");
        //activity.swipeLeft(v);
    }

    @Override
    public void swipeRight(View v) {
        Log.v(TAG, "SwipeRight!");
        //activity.swipeRight(v);
    }

    @Override
    public void onClick(View v, int x, int y) {
        Log.v(TAG, "onClick!");
        //activity.onClick(v, x, y);
    }
}
