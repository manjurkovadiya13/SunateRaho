package com.example.sunateraho;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.text.method.ScrollingMovementMethod;
import android.speech.tts.TextToSpeech;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;


import java.util.Locale;

public class Output_Translated extends AppCompatActivity {
    TextView outputTextView;
    private String outputstring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output__translated);

        outputTextView = findViewById(R.id.translatedText);
        outputTextView.setMovementMethod(new ScrollingMovementMethod());
        outputstring=getIntent().getExtras().getString("text");











    }

}
