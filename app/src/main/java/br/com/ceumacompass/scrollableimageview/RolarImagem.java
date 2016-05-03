package br.com.ceumacompass.scrollableimageview;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.OverScroller;

/**
 * Created by Marcos on 03/05/2016.
 */
public class RolarImagem extends ImageView {

    private GestureDetectorCompat gestureDetector;
    private OverScroller overScroller;

    private final int       screenW;
    private final int       screenH;
    private int             positionX = 0;
    private int             positionY = 0;

    public RolarImagem(Context context){
        super(context);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        screenW = dm.widthPixels;
        screenH = dm.heightPixels;

        gestureDetector = new GestureDetectorCompat(context, gestureListener);
        overScroller = new OverScroller(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e){
        gestureDetector.onTouchEvent(e);
        return true;
    }

    @Override
    public void computeScroll(){
        super.computeScroll();

        if(overScroller.computeScrollOffset()){
            positionX = overScroller.getCurrX();
            positionY = overScroller.getCurrY();
            scrollTo(positionX, positionY);
        }else{
            overScroller.springBack(positionX, positionY, 0, getMaxHorizontal(), 0, getMaxVertical());
        }
    }

    private int getMaxHorizontal(){
        return (Math.abs(getDrawable().getBounds().width() - screenW));
    }

    private int getMaxVertical(){
        return (Math.abs(getDrawable().getBounds().height() - screenH));
    }

    private GestureDetector.SimpleOnGestureListener gestureListener = new GestureDetector.SimpleOnGestureListener(){
        @Override
        public boolean onDown(MotionEvent e){
            overScroller.forceFinished(true);
            ViewCompat.postInvalidateOnAnimation(RolarImagem.this);
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY){
            overScroller.forceFinished(true);
            overScroller.fling(positionX, positionY, (int)-velocityX, (int) -velocityY,0,getMaxHorizontal(),0,getMaxVertical());
            ViewCompat.postInvalidateOnAnimation(RolarImagem.this);
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY){
            overScroller.forceFinished(true);

            int dx = (int) distanceX;
            int dy = (int) distanceY;
            int newPositionX = positionX + dx;
            int newPositionY = positionY + dy;

            if(newPositionX < 0){
                dx -= newPositionX;
            }else if(newPositionX > getMaxHorizontal()){
                dx -= (newPositionX - getMaxHorizontal());
            }
            if (newPositionY < 0){
                dy -= newPositionY;
            }else if(newPositionY > getMaxVertical()){
                dy -= (newPositionY - getMaxVertical());
            }

            overScroller.startScroll(positionX, positionY, dx,dy,0);
            ViewCompat.postInvalidateOnAnimation(RolarImagem.this);
            return true;
        }
    };
}
