package com.example.sunateraho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class dashboardmain extends AppCompatActivity {
ImageView gujartimove,hindimove;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboardmain);

        gujartimove = findViewById(R.id.gujartimove);
        hindimove = findViewById(R.id.hindi_move);

        gujartimove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gujartiintent = new Intent(dashboardmain.this,gujaratioutput.class);
                startActivity(gujartiintent);
            }
        });

        hindimove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hindimove = new Intent(dashboardmain.this,outputmove.class);
                startActivity(hindimove);
            }
        });
    }
}