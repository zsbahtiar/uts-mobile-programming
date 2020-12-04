package com.zamzamsaefulbahtiar.utsmp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.zamzamsaefulbahtiar.utsmp.helper.Account;
import com.zamzamsaefulbahtiar.utsmp.helper.Database;

import java.util.ArrayList;

public class DBAdapter {
    private final Context context;
    private Database dbHelper;
    private SQLiteDatabase db;
    private String[] semuaKolom = {dbHelper.KOLOM_ID, dbHelper.KOLOM_USERNAME, dbHelper.KOLOM_SKOR};

    public DBAdapter(Context context){
        this.context = context;
        dbHelper = new Database(this.context);
    }

    public DBAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return  this;
    }

    public void close(){
        dbHelper.close();
    }

    public long insertData(String nama, int skor){
        ContentValues values = new ContentValues();
        values.put(dbHelper.KOLOM_USERNAME, nama);
        values.put(dbHelper.KOLOM_SKOR, skor);

        return db.insert(dbHelper.TABLE_NAME, null, values) == 1 ? 1:0;

    }


    public ArrayList<String> getAllData() {
        ArrayList<String> allData = new ArrayList<String>();

        Cursor cursor = db.query(dbHelper.TABLE_NAME, semuaKolom, null, null, null, null, dbHelper.KOLOM_SKOR + " DESC");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String data = (cursor.getPosition() + 1) + ". " + cursor.getString(1) + " | " + cursor.getInt(2);
            Log.w("Info", "data = " + data);
            allData.add(data);
            cursor.moveToNext();
        }
        cursor.close();

        return allData;
    }

    public void handleUser(String username){
        Cursor cursor = db.rawQuery("SELECT id, username, skor FROM " + dbHelper.TABLE_NAME + " WHERE "+ dbHelper.KOLOM_USERNAME +"= ? ", new String[]{ username });
        int skor = 0;
        Log.d("gagalwae", String.valueOf(cursor.getCount()));
        if(cursor.getCount() > 0){
            try{
                cursor.moveToFirst();
                skor = cursor.getInt(2);
            }catch (Exception error){
                Log.e("error", error.getMessage());
            }


        }else{
            long insertData = this.insertData(username, skor);
            Log.i("insert", String.valueOf(insertData > 0 ? "Sukses":"Gagal"));
        }
        cursor.close();

        Account.name = username;
        Account.skor = skor;


    }

    public void updateSkor(boolean reset){
        ContentValues values = new ContentValues();
        int skor = reset ? 0 : Account.skor + 5;
        values.put(dbHelper.KOLOM_SKOR, skor);
        int succes;
        try{
            succes = db.update(dbHelper.TABLE_NAME, values, dbHelper.KOLOM_USERNAME+ " =?", new String[]{Account.name});
            Log.d("update", String.valueOf(skor));
            if(succes > 0){
                Account.skor = skor;
            }
        }catch (Exception error){
            Log.e("failed_update", "Error update skor: " + error.getMessage());
        }
        if(!reset) db.close();
    }

    public void resetSkor(){
        try{
            this.updateSkor(true);
            int success = db.delete(dbHelper.TABLE_NAME, dbHelper.KOLOM_USERNAME + " <> ?", new String[]{Account.name});
            Log.d("success_reset", String.valueOf(success));
        }catch (Exception error){
            Log.e("error_reset", error.getMessage());
        }
        db.close();
    }
}
