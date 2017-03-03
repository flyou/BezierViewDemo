package com.flyou.bezierdemo.widget;

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

public class BezierView3 extends View {
    private Path mPath;
    private Point mStartPoint;
    private Point mEndPoint;
    private Point mContral1Point;
    private Point mContral2Point;
    private int mControl1X=0;
    private int mControl2X=0;
    private int mControl1Y=0;
    private int mControl2Y=0;
    private Paint mPaint;
    private int viewSize;


    public BezierView3(Context context) {
        this(context, null);
    }

    public BezierView3(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BezierView3(Context context, AttributeSet attrs, int defStyleAttr) {
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
        mStartPoint = new Point(viewSize/2, viewSize);
        mEndPoint= new Point(viewSize/2, 0);
        mContral1Point = new Point((int)(viewSize *0.8),(int)(viewSize *0.21));
        mContral2Point = new Point((int)(viewSize *0.1), (int)(viewSize *0.8));
        mPath=new Path();
        mPath.moveTo(mStartPoint.x,mStartPoint.y);
        mPath.cubicTo(mContral1Point.x,mContral1Point.y,mContral2Point.x,mContral2Point.y,mEndPoint.x,mEndPoint.y);
        canvas.drawPath(mPath,mPaint);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = getDefaultSize(getSuggestedMinimumHeight(),
                heightMeasureSpec);
        int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        viewSize= Math.min(width, height) ;
        setMeasuredDimension(viewSize,viewSize);

    }


}
