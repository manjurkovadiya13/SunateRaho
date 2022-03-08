package com.example.sunateraho;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class outputmove extends AppCompatActivity {
    TextView mtextview;
    PDFView pdfView;
    TextToSpeech tts;
    String text;
    Button speakbtn;
    private String sentance = "";
    private String typingString = "";
    private int paragraphCount = 0;
    private HashMap<String, String> map = new HashMap<>();
    private ArrayList<String> stringArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outputmove);
speakbtn = findViewById(R.id.speakbtnn);
        mtextview = (TextView) findViewById(R.id.textviewoutput);
        mtextview.setText(getIntent().getStringExtra("text"));



        tts=new TextToSpeech(outputmove.this, new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status) {
                // TODO Auto-generated method stub
                if(status == TextToSpeech.SUCCESS){
                    int result=tts.setLanguage(Locale.forLanguageTag("hi"));
                    if(result==TextToSpeech.LANG_MISSING_DATA ||
                            result==TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("error", "This Language is not supported");
                    }
                    else{
                        ConvertTextToSpeech();
                    }
                }
                else
                    Log.e("error", "Initilization Failed!");
            }
        });

    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub

        if(tts != null){

            tts.stop();
            tts.shutdown();
        }
        super.onPause();
    }

    public void speakbtnn(View view) {

        Toast.makeText(outputmove.this, "working", Toast.LENGTH_SHORT).show();
        ConvertTextToSpeech();
    }

    private void ConvertTextToSpeech() {
        // TODO Auto-generated method stub
        text = mtextview.getText().toString();
        if(text==null||"".equals(text))
        {
            text = "Content not available";
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);

        }else
            tts.speak(text+"is saved", TextToSpeech.QUEUE_FLUSH, null,"UNIQUE_UTTERANCE_ID");

    }





}