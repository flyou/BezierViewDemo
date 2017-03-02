package com.flyou.bezierviewdemo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by flyou on 2017/3/2.
 * VersionCode: 1
 * Desc:
 */

public class BezierView1 extends View {
    private Path mPath;
    private Point mStartPoint;
    private Point mEndPoint;
    private Point mContralPoint;
    private Paint mPaint;

    private int sizelenth;

    public BezierView1(Context context) {
        this(context, null);
    }

    public BezierView1(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BezierView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {


        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(15);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mStartPoint = new Point(0, 0);
        mEndPoint = new Point(sizelenth, sizelenth);
        mContralPoint = new Point(sizelenth, 0);
        mPath=new Path();
        mPath.moveTo(mStartPoint.x,mStartPoint.y);
        mPath.quadTo(mContralPoint.x,mContralPoint.y,mEndPoint.x,mEndPoint.y);
        canvas.drawPath(mPath,mPaint);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = getDefaultSize(getSuggestedMinimumHeight(),
                heightMeasureSpec);
        int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        sizelenth = Math.min(width, height) ;
        setMeasuredDimension(sizelenth,sizelenth);

    }
}
