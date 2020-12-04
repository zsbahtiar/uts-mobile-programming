package com.zamzamsaefulbahtiar.utsmp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DBAdapter dbAdapter = new DBAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void setName(View view){
        EditText nameText = findViewById(R.id.inputName);
        String namePlayer = nameText.getText().toString();

        if(!namePlayer.isEmpty()){
            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            dbAdapter.open();
            dbAdapter.handleUser(namePlayer);
            startActivity(intent);
        } else{
            Toast.makeText(getBaseContext(),"Silahkan isi nama terlebih dahulu! :)", Toast.LENGTH_SHORT).show();
        }
    }
}