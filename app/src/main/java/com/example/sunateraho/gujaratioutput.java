package com.example.sunateraho;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class gujaratioutput extends AppCompatActivity {
    TextView gujartitv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gujaratioutput);
        gujartitv = (TextView)findViewById(R.id.gujartitv);

        gujartitv.setText(getIntent().getStringExtra("textgujarti"));
    }
}