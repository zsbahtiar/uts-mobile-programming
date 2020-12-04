package com.zamzamsaefulbahtiar.utsmp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zamzamsaefulbahtiar.utsmp.helper.Account;

public class MenuActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        this.setNamePlayer(Account.name);

    }

    private void setNamePlayer(String name){
        TextView namePlayerView = findViewById(R.id.playerName);
        namePlayerView.setText(" " + name + "!");
    }

    public void openProfileActivity(View view){
        Intent profileActivity = new Intent(MenuActivity.this, ProfileActivity.class);
        startActivity(profileActivity);
    }


    public void openQuizActivity(View view){
        Intent QuizActivity = new Intent(MenuActivity.this, QuizActivity.class);
        startActivity(QuizActivity);
    }

    public void openPracticeActivity(View view){
        Intent PracticeActivity = new Intent(MenuActivity.this, PracticeActivity.class);
        startActivity(PracticeActivity);
    }


    @Override
    public void onBackPressed() {
        Account.name = "";
        Account.skor = 0;

        finishAndRemoveTask();
    }
}