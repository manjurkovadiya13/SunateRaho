package com.example.sunateraho;



import android.graphics.pdf.PdfRenderer;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;


import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Locale;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.common.modeldownload.FirebaseModelDownloadConditions;
import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage;
import com.google.firebase.ml.naturallanguage.languageid.FirebaseLanguageIdentification;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslateLanguage;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslator;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslatorOptions;

public class dashboard extends AppCompatActivity {

    FloatingActionButton pdftn, wordfile,homebtn;
    String fileContent="";

    ExtendedFloatingActionButton mAddFab;
    FirebaseTranslator englishGermanTranslator,englishgujaratiTranslator;

    // These TextViews are taken to make visible and
    // invisible along with FABs except parent FAB's action
    // name
    TextView addAlarmActionText, addPersonActionText,showcontent,home_txt;

    // to check whether sub FABs are visible or not
    Boolean isAllFabsVisible;

Spinner fromspinner,tospinner;
    private Button mk;

String text;


    TextView filename,traslate;
    private  final int PICK_PDF_FILE = 1001;
    private  final int PICK_WORD_FILE = 1002;
    private static final String TAG = "dashboard";

private static final int REQUEST_PERMISSION_CODE=1;
int languageCode,fromlanguagecode,tolanguagecode = 0;
String[] fromLanguages = {"from","English","Afrikaans","Arabic","Belurusian","Bulgarian","Bengali","Catalan","Hindi"};
    String[] toLanguages = {"to","English","Afrikaans","Arabic","Belurusian","Bulgarian","Bengali","Catalan","Hindi"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        FirebaseTranslatorOptions options =
                new FirebaseTranslatorOptions.Builder()

                        .setSourceLanguage(FirebaseTranslateLanguage.EN)

                        .setTargetLanguage(FirebaseTranslateLanguage.HI)

                        .build();

        FirebaseTranslatorOptions gujarti =
                new FirebaseTranslatorOptions.Builder()

                        .setSourceLanguage(FirebaseTranslateLanguage.EN)

                        .setTargetLanguage(FirebaseTranslateLanguage.GU)

                        .build();


        englishGermanTranslator = FirebaseNaturalLanguage.getInstance().getTranslator(options);
        englishgujaratiTranslator = FirebaseNaturalLanguage.getInstance().getTranslator(gujarti);

        mAddFab = findViewById(R.id.add_fab);
homebtn = findViewById(R.id.home_btn);
home_txt = findViewById(R.id.home_text);
        pdftn = findViewById(R.id.choose_file_btn);
        wordfile = findViewById(R.id.choose_word_file_btn);

        mk = findViewById(R.id.mk);

        addAlarmActionText = findViewById(R.id.add_alarm_action_text);
        addPersonActionText = findViewById(R.id.add_person_action_text);


        // Now set all the FABs and all the action name
        // texts as GONE
        homebtn.setVisibility(View.GONE);
        home_txt.setVisibility(View.GONE);
        pdftn.setVisibility(View.GONE);
        wordfile.setVisibility(View.GONE);
        addAlarmActionText.setVisibility(View.GONE);
        addPersonActionText.setVisibility(View.GONE);


        showcontent = findViewById(R.id.file_content);
        filename = findViewById(R.id.path_tv);
        filename.setSelected(true);

        isAllFabsVisible = false;

        // Set the Extended floating action button to
        // shrinked state initially
        mAddFab.shrink();


        mAddFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!isAllFabsVisible) {

                            // when isAllFabsVisible becomes
                            // true make all the action name
                            // texts and FABs VISIBLE.
                            pdftn.show();
                            wordfile.show();
                            homebtn.show();

                            addAlarmActionText.setVisibility(View.VISIBLE);
                            addPersonActionText.setVisibility(View.VISIBLE);


                            // Now extend the parent FAB, as
                            // user clicks on the shrinked
                            // parent FAB
                            mAddFab.extend();

                            // make the boolean variable true as
                            // we have set the sub FABs
                            // visibility to GONE
                            isAllFabsVisible = true;
                        } else {

                            // when isAllFabsVisible becomes
                            // true make all the action name
                            // texts and FABs GONE.
                            pdftn.hide();
                            wordfile.hide();
                            homebtn.hide();
                            addAlarmActionText.setVisibility(View.GONE);
                            addPersonActionText.setVisibility(View.GONE);


                            // Set the FAB to shrink after user
                            // closes all the sub FABs
                            mAddFab.shrink();

                            // make the boolean variable false
                            // as we have set the sub FABs
                            // visibility to GONE
                            isAllFabsVisible = false;
                        }
                    }
                });


        homebtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(dashboard.this,dashboardmain.class);
                        startActivity(intent);
                    }

                });







        mk.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        downloadModal(text);


                    }

                });

        pdftn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callChoosePDFfile();
            }
        });

        wordfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callChooseWordfile();
            }
        });



    }
//    String[] toLanguages = {"to","English","Afrikaans","Arabic","Belurusian","Bulgarian","Bengali","Catalan","Hindi"};


public int getLanguageCode(String language){
        int languageCode=0;

        switch (language){
            case "English":
                languageCode = FirebaseTranslateLanguage.EN;
                break;

            case "Afrikaans":
                languageCode = FirebaseTranslateLanguage.AF;
                break;

            case "Arabic":
                languageCode = FirebaseTranslateLanguage.AR;
                break;

            case "Belurusian":
                languageCode = FirebaseTranslateLanguage.BE;
                break;

            case "Bulgarian":
                languageCode = FirebaseTranslateLanguage.BN;
                break;

            case "Bengali":
                languageCode = FirebaseTranslateLanguage.BN;
                break;

            case "Catalan":
                languageCode = FirebaseTranslateLanguage.CA;
                break;

            case "Hindi":
                languageCode = FirebaseTranslateLanguage.HI;
                break;

            default:
                languageCode = 0;

        }
        return languageCode;
}



    private void downloadModal(String input) {
        // below line is use to download the modal which
        // we will require to translate in german language
        FirebaseModelDownloadConditions conditions = new FirebaseModelDownloadConditions.Builder().requireWifi().build();

        // below line is use to download our modal.
        englishGermanTranslator.downloadModelIfNeeded(conditions).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                // this method is called when modal is downloaded successfully.
                Toast.makeText(dashboard.this, "Please wait language modal is being downloaded.", Toast.LENGTH_SHORT).show();

                // calling method to translate our entered text.
                translateLanguage(input);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(dashboard.this, "Fail to download modal", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void translateLanguage(String input) {
        englishGermanTranslator.translate(input).addOnSuccessListener(new OnSuccessListener<String>() {
            @Override
            public void onSuccess(String hindi) {

                Intent myIntent = new Intent(dashboard.this,outputmove.class);
                myIntent.putExtra("text",hindi);

startActivity(myIntent);



            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(dashboard.this, "Fail to translate", Toast.LENGTH_SHORT).show();
            }
        });
    }



















    private void callChoosePDFfile() {

        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("application/pdf");
        startActivityForResult(intent,PICK_PDF_FILE);
    }

    private void callChooseWordfile() {

        Intent intent1 = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent1.addCategory(Intent.CATEGORY_OPENABLE);
        intent1.setType("*/*");
        String[] mimetypes = {"application/vnd.openxmlformats-officedocument.wordprocessingml.document"};
        intent1.putExtra(Intent.EXTRA_MIME_TYPES,mimetypes);
        startActivityForResult(intent1,PICK_WORD_FILE);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        super.onActivityResult(requestCode, resultCode, resultData);
        if(requestCode == PICK_PDF_FILE && resultCode == Activity.RESULT_OK) {
            if(resultData != null) {


                Log.d(TAG,"onActivityResult: "+ resultData.getData());
                filename.setText("File Path:"+resultData.getData());
                extractTextPdfFile(resultData.getData());
            }
        }

        if(requestCode == PICK_WORD_FILE && resultCode == Activity.RESULT_OK) {
            if(resultData != null) {
                Log.d(TAG,"onActivityResult: "+ resultData.getData());
                filename.setText("File Path:"+resultData.getData());
                extractTextPdfFile(resultData.getData());
            }
        }


    }



    InputStream inputStream;
    public void extractTextPdfFile(Uri uri){
        try {
           inputStream = dashboard.this.getContentResolver().openInputStream(uri);

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {

String fileContent = "";


        StringBuilder builder = new StringBuilder();
        PdfReader reader = null;
        try {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                reader = new PdfReader(inputStream);

                int pages = reader.getNumberOfPages();
                for(int i=1;i <=pages;i++){
                    fileContent = fileContent+PdfTextExtractor.getTextFromPage(reader,i);

                }

                builder.append(fileContent);

            }


            reader.close();

            runOnUiThread(() -> { showcontent.setText(builder.toString());
            text = showcontent.getText().toString();
            });


        }catch (IOException e){

            Log.d(TAG,"run: "+e.getMessage());

        }

            }



        }).start();
    }



}