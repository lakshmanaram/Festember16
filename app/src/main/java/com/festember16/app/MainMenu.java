package com.festember16.app;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

public class MainMenu extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener
{

    MenuCanvas c;

    RelativeLayout LayoutRoot;

    private GestureDetectorCompat mDetector;

    float dpHeight,dpWidth;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        DisplayMetrics displayMetrics = getApplicationContext().getResources().getDisplayMetrics();
        dpHeight = displayMetrics.heightPixels;
        dpWidth = displayMetrics.widthPixels;


        setContentView(R.layout.activity_main_menu);

        LayoutRoot = (RelativeLayout) findViewById(R.id.LayoutRoot);
        c = new MenuCanvas(this);
        LayoutRoot.addView(c);
        mDetector = new GestureDetectorCompat(this,this);


    }


    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        this.mDetector.onTouchEvent(event);


        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e)
    {
        c.tapped( e.getX() , e.getY());
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY)
    {
        Log.d("func distance",String.valueOf(distanceX) + "  and  "+String.valueOf(distanceY));
        c.fling( distanceX, distanceY);
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e){
        c.tapped( e.getX() , e.getY());
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
    {
        Log.d("func velocity",String.valueOf(velocityX) + "  and  "+String.valueOf(velocityY));
        flinghelper(velocityX,velocityY);
//        vX = velocityX;
//        vY = velocityY;
//        flingThread.start();
        return false;
    }



    void flinghelper(float velocityX, float velocityY){
        if(velocityX > 1000 || velocityY > 1000) {
            float vX = velocityX;
//            if(vX > dpWidth)
//                vX = dpWidth;
            float vY = velocityY;
//            if(vY > dpHeight)
//                vY = dpHeight;
            c.fling(vX/1000,vY/1000);
            flinghelper(velocityX /2, velocityY / 2);
        }

    }
}
