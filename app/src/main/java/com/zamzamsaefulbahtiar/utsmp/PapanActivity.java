package com.zamzamsaefulbahtiar.utsmp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zamzamsaefulbahtiar.utsmp.helper.Account;

import java.util.ArrayList;

public class PapanActivity extends AppCompatActivity {
    private ListView listSkor;
    private ArrayList<String> daftarSkor;
    private DBAdapter db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_papan);

        this.getBoard();

    }

    @Override
    public void onBackPressed() {
        finishAndRemoveTask();
    }

    public void resetSkor(View view){
        db = new DBAdapter(this);
        db.open();
        db.resetSkor();

        this.getBoard();
    }

    private void getBoard(){
        db = new DBAdapter(this);
        db.open();
        daftarSkor = new ArrayList<String>();
        daftarSkor.addAll(db.getAllData());
        db.close();

        listSkor = (ListView)findViewById(R.id.list_skor);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, daftarSkor
        );

        listSkor.setAdapter(adapter);
    }
}