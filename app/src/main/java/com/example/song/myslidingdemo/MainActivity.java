package com.example.song.myslidingdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ViewUtils;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    private View mViewTop;
    private View mViewBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewTop = findViewById(R.id.view_top);
        mViewBottom = findViewById(R.id.view_bottom);
        mViewBottom.setFocusable(true);
        mViewBottom.setClickable(true);
        mViewBottom.setLongClickable(true);
        final GestureDetector gestureDetector = new GestureDetector(this, new SlidingListener());
        mViewBottom.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                return gestureDetector.onTouchEvent(event);
            }
        });
    }

    private float dx;
    private float dy;

    class SlidingListener implements GestureDetector.OnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
           // Log.i("TAG", "onDown");
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            // Log.i("TAG","onShowPress");
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            // Log.i("TAG","onSingleTapUp");
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.i("TAG", "onScroll--distanceX==" + distanceX + "  distanceY==" + distanceY);
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mViewBottom.getLayoutParams();
            if (dy >= 0 && dy <= 100) {
                dy += distanceY;
                params.setMargins(0, (int) -(dy ), 0, 0);
                mViewBottom.setLayoutParams(params);
            } else if (dy < 0) {
                dy = 0;
                params.setMargins(0, (int) -(dy ), 0, 0);
                mViewBottom.setLayoutParams(params);
            } else {
                dy = 100;
                params.setMargins(0, (int) -(dy ), 0, 0);
                mViewBottom.setLayoutParams(params);
            }

            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            //  Log.i("TAG","onLongPress");
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            //Log.i("TAG","onFling--velocityX=="+velocityX+"  velocityY=="+velocityY);
            return false;
        }
    }
}
