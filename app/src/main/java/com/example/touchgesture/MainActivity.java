package com.example.touchgesture;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

import androidx.core.view.GestureDetectorCompat;

public class MainActivity extends Activity {

    private GestureDetectorCompat mDetector;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDetector = new GestureDetectorCompat(this, new MyGestureListener());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        private final static int LIMIT_DISTANCE = 50;
        private final static int LIMIT_VELOCITY = 30;

        private final static int duration = Toast.LENGTH_SHORT;


        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,float velocityY) {


            Context context = getApplicationContext();

            float distX = e2.getX() - e1.getX();
            float distY = e2.getY() - e1.getY();

            if (Math.abs(distX) > Math.abs(distY) && Math.abs(distX) > LIMIT_DISTANCE && velocityX > LIMIT_VELOCITY) {
                if (distX > 0) {
                    CharSequence text = "RIGHT";
                    Toast toast = Toast.makeText(context, text, this.duration);
                    toast.show();
                } else if (distX < 0) {
                    CharSequence text = "LEFT";
                    Toast toast = Toast.makeText(context, text, this.duration);
                    toast.show();
                }
                return true;
            }


            if (Math.abs(distY) > Math.abs(distX) && Math.abs(distY) > LIMIT_DISTANCE && velocityY > LIMIT_VELOCITY) {
                if (distY < 0) {
                    CharSequence text = "UP";
                    Toast toast = Toast.makeText(context, text, this.duration);
                    toast.show();
                } else if (distY > 0) {
                    CharSequence text = "DOWN";
                    Toast toast = Toast.makeText(context, text, this.duration);
                    toast.show();
                }
                return true;
            }
            return false;
        }

        @Override
        public boolean onDown(MotionEvent motionEvent) {
            return true;
        }
    }
}

