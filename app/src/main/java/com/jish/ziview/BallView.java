package com.jish.ziview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Adminjs on 2018/1/30.
 */

public class BallView extends View {
    private int mHeight;
    private int mWidth;
    private int X;
    private int Y;
    private boolean mOnBall;

    public BallView(Context context) {
        this(context, null);
    }

    public BallView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BallView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        intiView();
    }
    private int mRadius =50;
    private void intiView() {
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mHeight = this.getHeight();
        mWidth = this.getWidth();
        //获取屏幕的正中心点
        X = mWidth / 2;
        Y = mHeight / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        //画圆,根据他需要什么参数(ctr+Q),我就参加什么参数
        canvas.drawCircle(X,Y,mRadius,paint);
    //    canvas.drawRect(60, 60, 120, 120, paint);

    }

    //触摸事件的监听
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN://按下时,回调
                int downX = (int) event.getX();
                int downy = (int) event.getY();
                //二者的区别就是,前者获取的坐标是自定义左上角为中心点,后者是屏幕的左上角为中心点(注意屏幕)
/*                event.getRawX();
                event.getRawX();*/

                //进行判断,用户手上是否点在了圆上
                mOnBall = isOnBall(downX, downy);
                Toast.makeText(getContext(), "用户的手点击到圆了吗?"+ mOnBall, Toast.LENGTH_SHORT).show();
                break;
            case MotionEvent.ACTION_MOVE://移动时,回调
                //进行判断,用户手上是否点在了圆上,在圆上的话,我就让代码时时移动
                if (mOnBall){
                    X = (int) event.getX();
                    Y = (int) event.getY();
                    //时时回调OnDrawer方法
                    postInvalidate();
                }
                break;
            case MotionEvent.ACTION_UP://松开时,回调
                break;
        }
        return true;
    }

    //java,对象,方法,参数,结果.   当我遇到一个我不知道的方法,我只需要知道,传的参数是什么,返回结果是什么,其他就不用管了.
    //作为一个程序员必须要有的能力:1.解决问题的能力    2.自学能力   3.表达能力.
    private boolean isOnBall(int downX, int downy) {
        //勾股定理,得到按下的半径
        double sqrt = Math.sqrt((downX - X) * (downX - X) + (downy - Y) * (downy - Y));
        //对应圆的半径和按下半径进行判断,看用户的手是否点在圆上
        if(sqrt <=  mRadius){
            return true;
        }
        return false;
    }


}

