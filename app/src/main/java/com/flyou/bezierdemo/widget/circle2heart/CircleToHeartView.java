package com.flyou.bezierdemo.widget.circle2heart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by fzl on 2017/3/6.
 * VersionCode: 1
 * Desc:
 */

public class CircleToHeartView extends View {
    private Path mPath;
    private Point mStartPoint;
    private Point mEndPoint;
    private Point mContral1Point;
    private Point mContral2Point;
    private Paint mPaint;
    private float mMagicValue = 0.551915024494f;

    private int r;

    public CircleToHeartView(Context context) {
        super(context);
        init();
        setWillNotDraw(false);
    }

    public CircleToHeartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        setWillNotDraw(false);
    }

    public CircleToHeartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        setWillNotDraw(false);

    }

    private void init() {

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(15);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(r, r);
        r = r - (int) mPaint.getStrokeWidth();
        initPoint();
        mPath = new Path();
        mPath.moveTo(mStartPoint.x, mStartPoint.y);
        for (int index = 0; index < 4; index++) {
            mPath.cubicTo(mContral1Point.x, mContral1Point.y, mContral2Point.x, mContral2Point.y, mEndPoint.x, mEndPoint.y);
            if (index <= 3) {
                roatePoint90();
            }
        }
        mPath.close();
        canvas.drawPath(mPath, mPaint);
    }

    private void initPoint() {
        mStartPoint = new Point(r, 0);
        mEndPoint = new Point(0, r);
        mContral1Point = new Point(r, (int) (r * mMagicValue));
        mContral2Point = new Point((int) (r * mMagicValue), r);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = getDefaultSize(getSuggestedMinimumHeight(),
                heightMeasureSpec);
        int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        r = Math.min(width, height) / 2;
        setMeasuredDimension(r * 2, r * 2);
    }

    private void roatePoint90() {
        Point tempPoint = new Point(mStartPoint);
        mStartPoint.x = -tempPoint.y;
        mStartPoint.y = tempPoint.x;

        tempPoint = new Point(mEndPoint);
        mEndPoint.x = -tempPoint.y;
        mEndPoint.y = tempPoint.x;

        tempPoint = new Point(mContral1Point);
        mContral1Point.x = -tempPoint.y;
        mContral1Point.y = tempPoint.x;

        tempPoint = new Point(mContral2Point);
        mContral2Point.x = -tempPoint.y;
        mContral2Point.y = tempPoint.x;

    }

}
