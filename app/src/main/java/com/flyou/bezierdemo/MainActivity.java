package com.flyou.bezierdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.beizerview1)
    Button beizerview1;
    @InjectView(R.id.beizerview2)
    Button beizerview2;
    @InjectView(R.id.beizerview3)
    Button beizerview3;
    @InjectView(R.id.beizerview4)
    Button beizerview4;
    @InjectView(R.id.beizerview5)
    Button beizerview5;
    @InjectView(R.id.beizerview6)
    Button beizerview6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.beizerview1, R.id.beizerview2, R.id.beizerview3, R.id.beizerview4, R.id.beizerview5, R.id.beizerview6})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.beizerview1:
                intent.setClass(MainActivity.this, BezierView1Activity.class);
                break;
            case R.id.beizerview2:
                intent.setClass(MainActivity.this, BezierView2Activity.class);
                break;
            case R.id.beizerview3:
                intent.setClass(MainActivity.this, BezierView3Activity.class);
                break;
            case R.id.beizerview4:
                intent.setClass(MainActivity.this, BezierView4Activity.class);
                break;
            case R.id.beizerview5:
                intent.setClass(MainActivity.this, BezierView5Activity.class);
                break;
            case R.id.beizerview6:
                intent.setClass(MainActivity.this, BezierView6Activity.class);
                break;
        }
        startActivity(intent);
    }
}
