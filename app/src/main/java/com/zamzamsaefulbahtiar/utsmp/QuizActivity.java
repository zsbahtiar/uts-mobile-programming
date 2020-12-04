package com.zamzamsaefulbahtiar.utsmp;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.zamzamsaefulbahtiar.utsmp.helper.Account;
import com.zamzamsaefulbahtiar.utsmp.helper.GenerateNumber;
import com.zamzamsaefulbahtiar.utsmp.helper.NumberToWord;
import com.zamzamsaefulbahtiar.utsmp.helper.Validation;

import java.text.NumberFormat;
import java.util.Locale;

public class QuizActivity extends AppCompatActivity {
    GenerateNumber generateNumber = new GenerateNumber();
    private int number;
    private String terbilang;
    private TextToSpeech mTTS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        this.createPractice();


        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = mTTS.setLanguage(Locale.getDefault());
                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } else {
                        findViewById(R.id.btnVoice).setEnabled(true);
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });

        findViewById(R.id.btnVoice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });


    }

    private void getSkor(){
        TextView skorPts = (TextView)findViewById(R.id.skor);
        skorPts.setText(String.valueOf(Account.skor));
    }
    private void speak() {
        mTTS.setSpeechRate(0.8f);
        mTTS.speak(this.terbilang, TextToSpeech.QUEUE_FLUSH, null);
    }
    @Override
    protected void onDestroy() {
        if (mTTS != null) {
            mTTS.stop();
            mTTS.shutdown();
        }
        super.onDestroy();
    }
    private void createPractice(){
        try{
            this.number = this.generateNumber.getNumber();
            this.terbilang = NumberToWord.number(this.number);
            NumberFormat nf = NumberFormat.getInstance(new Locale("en", "ID"));
            TextView numberView = (TextView)findViewById(R.id.number);

            numberView.setText(String.valueOf(nf.format(this.number)));
            this.getSkor();

        }catch (Exception error){
            finish();
            Log.e("error", "Sorry Error: " + error.getMessage());
        }

    }

    public void checkAnswer(View view){
        EditText answerView = (EditText)findViewById(R.id.angkaTerbilang);
        String answer = answerView.getText().toString();

        if(!answer.isEmpty()){
            if(Validation.terbilangAnswer(answer.trim(), this.number)){
                DBAdapter dbAdapter = new DBAdapter(this);
                Toast.makeText(getBaseContext(), "Yea jawaban kamu benar!", Toast.LENGTH_SHORT).show();
                dbAdapter.open();
                dbAdapter.updateSkor(false);
                this.createPractice();
                answerView.setText("");
            }else{
                Toast.makeText(getBaseContext(), "jawaban kamu belum tepat:(", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getBaseContext(), "Silahkan diisi ya!", Toast.LENGTH_SHORT).show();
        }

    }

    public void openPapanActivity(View view){
        Intent PapanActivity = new Intent(QuizActivity.this, PapanActivity.class);
        startActivity(PapanActivity);
    }

    @Override
    public void onBackPressed() {
        finishAndRemoveTask();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.createPractice();
    }
}