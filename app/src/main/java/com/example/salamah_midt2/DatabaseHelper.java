package com.example.salamah_midt2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "NEW.db";
    public static final String TABLE_NAME = "users_data";
    public static final String COL1 = "ID";
    public static final String COL2 = "num";
    public static final String COL3 = "name";
    public static final String COL4 = "mail";
    public static final String COL5 = "number";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    /* Code runs automatically when the dB is created */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " num INTEGER, name TEXT, mail TEXT, number INTEGER)";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String number, String name,  String mail, String phone) {



        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues(); // we create it in db instead of using strings
        contentValues.put(COL2, number); // where to add the data
        contentValues.put(COL3, name);
        contentValues.put(COL4, mail);

        if(phone.equals(null)==true){
            contentValues.put(COL5, "NULL");
        }

        else{
            contentValues.put(COL5, phone);
        }



        long result = db.insert(TABLE_NAME, null, contentValues);

        //if data are inserted incorrectly, it will return -1
        if(result == -1) {return false;} else {return true;}
    }

    public Cursor structuredQuery(int ID) {
        SQLiteDatabase db = this.getReadableDatabase(); // No need to write
        Cursor cursor = db.query(TABLE_NAME, new String[]{COL1,
                        COL2, COL3,COL4,COL5}, COL1 + "=?",
                new String[]{String.valueOf(ID)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        return cursor;
    }

    public Cursor getSpecificResult(String productName){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME +" Where name=? ",new String[]{productName});
        return data;
    }

    public Cursor getSpecifiedResultByID(int ID){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME +" Where ID=?  ",new String[]{String.valueOf(ID)});
        return data;

    }

    // Return everything inside a specific table
    public Cursor getListContents() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }

    public int dltRow(String productName){

        SQLiteDatabase db = this.getWritableDatabase();
        int delete=0;

        long result= DatabaseUtils.queryNumEntries(db,TABLE_NAME,"PRODUCT_NAMES=?",new String[]{productName});

        if(result>=1)
            delete=db.delete(TABLE_NAME,"PRODUCT_NAMES=?",new String[]{productName});

        return delete;

    }

    public void update(int id,int productQuantity){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL3,productQuantity);

        db.update(TABLE_NAME,contentValues,"ID=?",new String[]{String.valueOf(id)});

    }

    public Cursor ViewEmployess(){
        // the method is cursor cuz we have a table and each table got cells where we store date
        // so the cursor helps u moving from one cell to another and will then retrieve data
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor x = db.rawQuery("SELECT * FROM "+ TABLE_NAME, null);
        return x;


    }

    public Integer DeleteEmployees( String id){
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete((TABLE_NAME), "num = ?", new String[]{id});
    }
}
