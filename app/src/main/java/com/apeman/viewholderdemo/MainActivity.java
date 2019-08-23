package com.apeman.viewholderdemo;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.apeman.viewholderdemo.protocl.AutoWind;

public class MainActivity extends AppCompatActivity {
    @AutoWind(viewId = 1, fieldName = "", extra = "")
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
