package com.example.munchies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "userProfile";
    private static final String COL0 = "Name";
    private static final String COL1 = "Gender";
    private static final String COL2= "Height";
    private static final String COL3 = "Weight";
    private static final String COL4 = "ActivityLevel";
    private static final String COL5 = "BMI";




    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ACCOUNTS_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COL0 + " STRING PRIMARY KEY, " + COL1 +
                " STRING, " + COL2 + " DOUBLE, " + COL3 + " INTEGER, "
                + COL4 + " STRING,"
                + COL5 + " DOUBLE" +")";
        db.execSQL(CREATE_ACCOUNTS_TABLE);
    }

    //Deletes user data
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //Adds user data into database
    public boolean addData(String name, String gender, double height, int weight, String ActivityLevel, double BMI) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL0, name);
        contentValues.put(COL1, gender);
        contentValues.put(COL2, height);
        contentValues.put(COL3, weight);
        contentValues.put(COL4, ActivityLevel);
        contentValues.put(COL5, BMI);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public Cursor getUserName(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL0 + " FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    public Cursor getUserBMI(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    public Cursor getUserDiet(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL2 + " FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void updateName(String newName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL0 + " = '" + newName;
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + newName);
        db.execSQL(query);
    }
    public void updateBMI(double newBMI){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL1 + " = '" + newBMI;
        Log.d(TAG, "updateBMI: query: " + query);
        Log.d(TAG, "updateBMI: Setting name to " + newBMI);
        db.execSQL(query);
    }
    public void updateDiet(String newDiet){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL2 + " = '" + newDiet;
        Log.d(TAG, "updateBMI: query: " + query);
        Log.d(TAG, "updateBMI: Setting name to " + newDiet);
        db.execSQL(query);
    }

}
