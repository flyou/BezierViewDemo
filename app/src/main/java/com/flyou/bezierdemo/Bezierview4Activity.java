package com.flyou.bezierdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.flyou.bezierdemo.widget.heartview.HeartView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class BezierView4Activity extends AppCompatActivity {

    @InjectView(R.id.button)
    Button button;
    @InjectView(R.id.activity_bezier4)
    HeartView activityBezier4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bezier4);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.button)
    public void onClick() {
        Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();
    }
}
