package com.flyou.bezierdemo.widget.heartview;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.flyou.bezierdemo.R;

import java.util.Random;

/**
 * Created by fzl on 2017/3/3.
 * VersionCode: 1
 * Desc:
 */

public class HeartView extends RelativeLayout{
    private static final String TAG ="HeartView" ;
    private Path mPath;
    private Point mStartPoint;
    private Point mControl1Point;
    private Point mControl2Point;
    private Point mEndPoint;
    private Paint mPaint;
    private Bitmap mBitmap;

    private Random mRandom;

    public HeartView(Context context) {
        super(context);
        setWillNotDraw(false);
        init();
    }

    public HeartView(Context context, AttributeSet attrs) {
        super(context,attrs);
        setWillNotDraw(false);
        init();
    }

    public HeartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);
        init();
    }

    private void init() {
        mPath = new Path();
        mControl1Point = new Point();
        mStartPoint = new Point();
        mControl2Point = new Point();
        mEndPoint = new Point();
        mPaint = new Paint();

        mPaint.setAntiAlias(true);

        mPaint.setStrokeWidth(10);
        mRandom = new Random();

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.heart);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initPoint();
    }

    private void initPoint() {
        mStartPoint.x = getMeasuredWidth() / 2;
        mStartPoint.y = getMeasuredHeight() - 20;

        mEndPoint.y = 10;
        mEndPoint.x = getMeasuredWidth() / 2;

        mControl1Point.x = 10;
        mControl1Point.y = getMeasuredHeight() * 3 / 4;
        mControl2Point.x = getMeasuredWidth() - 10;
        mControl2Point.y = getMeasuredHeight() / 4;
        Log.d(TAG, "initPoint: getMeasuredWidth() / 2"+getMeasuredWidth() / 2+",getMeasuredHeight() - 10"+(getMeasuredHeight() - 10));


    }

    private int getRandomColor() {
        int color=Color.rgb(mRandom.nextInt(255),mRandom.nextInt(255),mRandom.nextInt(255));
        return color;
    }

    private Point getRandomPoint(){
        return new Point(mRandom.nextInt(getMeasuredWidth()),mRandom.nextInt(getMeasuredHeight()));
    }
    private Bitmap drawHeart(int color) {

        mPath.reset();
        mPath.moveTo(mStartPoint.x,mStartPoint.y);
        mPath.cubicTo(mControl1Point.x,mControl1Point.y,mControl2Point.x,mControl2Point.y,mEndPoint.x,mEndPoint.y);


        Bitmap newBitmap = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
        canvas.drawColor(color, PorterDuff.Mode.SRC_ATOP);
        canvas.setBitmap(null);
        return newBitmap;
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void addHeart() {
        ImageView imageView = new ImageView(getContext());
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(CENTER_HORIZONTAL);
        params.addRule(ALIGN_PARENT_BOTTOM);
        imageView.setImageBitmap(drawHeart(getRandomColor()));
        addView(imageView, params);
        moveHeart(imageView);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void moveHeart(final ImageView imageView) {
        ObjectAnimator alphaAnimator=new ObjectAnimator().ofFloat(imageView,"alpha",1,0);
        ObjectAnimator XYAnimator=new ObjectAnimator().ofFloat(imageView,"X","Y", mPath);
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.playTogether(alphaAnimator,XYAnimator);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.setDuration(2000);

        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
            removeView(imageView);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet.start();

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mControl1Point=getRandomPoint();
                mControl2Point=getRandomPoint();
                mEndPoint.x=mRandom.nextInt(getMeasuredWidth());
                addHeart();
                break;
        }
        return super.onTouchEvent(event);

    }


}
