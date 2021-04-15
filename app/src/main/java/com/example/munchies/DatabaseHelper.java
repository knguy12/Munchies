package com.example.munchies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
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
    private static final String COL6 = "Age";
    private static final String COL7 = "DailyCaloriesNeeded";
    private static final String COL8 = "WeightGoal";




    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ACCOUNTS_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COL0 + " STRING PRIMARY KEY, " + COL1 +
                " STRING, " + COL2 + " DOUBLE, " + COL3 + " INTEGER, "
                + COL4 + " STRING,"
                + COL5 + " DOUBLE,"
                + COL6 + " INTEGER,"
                + COL7 + " INTEGER,"
                + COL8 + " STRING"+")";
        db.execSQL(CREATE_ACCOUNTS_TABLE);
    }

    //Deletes user data
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    //Gets the current amount of rows in the DB
    public int getSizeOfDB(){
        SQLiteDatabase db = this.getWritableDatabase();
        final Cursor cursor = db.rawQuery("SELECT Count(*) from userProfile;", null);
        int size = 0;
        if (cursor.moveToFirst()) {
            do {
                size = cursor.getInt(0);
            } while (cursor.moveToNext());
        }
        return size;
    }
    //Adds user data into database
    public boolean addData(String name, String gender, double height, int weight, String ActivityLevel, double BMI, int age, int dailyCaloriesNeeded, String weightGoal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL0, name);
        contentValues.put(COL1, gender);
        contentValues.put(COL2, height);
        contentValues.put(COL3, weight);
        contentValues.put(COL4, ActivityLevel);
        contentValues.put(COL5, BMI);
        contentValues.put(COL6, age);
        contentValues.put(COL7, dailyCaloriesNeeded);
        contentValues.put(COL8, weightGoal);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    //Updates users information
    public boolean updateData(String name, String gender, double height, int weight, String ActivityLevel, double BMI, int age, int dailyCaloriesNeeded, String weightGoal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL0, name);
        contentValues.put(COL1, gender);
        contentValues.put(COL2, height);
        contentValues.put(COL3, weight);
        contentValues.put(COL4, ActivityLevel);
        contentValues.put(COL5, BMI);
        contentValues.put(COL6, age);
        contentValues.put(COL7, dailyCaloriesNeeded);
        contentValues.put(COL8, weightGoal);

        db.update(TABLE_NAME, contentValues, "Name = ?", new String[]{ name });
        return true;
    }
    public String getStringUserName() throws SQLException {
        String username = "";
        Cursor cursor = this.getReadableDatabase().query(
                TABLE_NAME, new String[] { COL0 },
                null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                username = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return username;
    }
    public String getStringWeight() throws SQLException {
        String weight = "";
        Cursor cursor = this.getReadableDatabase().query(
                TABLE_NAME, new String[] { COL3 },
                null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                weight = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return weight;
    }
    public String getStringHeight() throws SQLException {
        String height = "";
        Cursor cursor = this.getReadableDatabase().query(
                TABLE_NAME, new String[] { COL2 },
                null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                height = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return height;
    }
    public String getStringGender() throws SQLException {
        String gender = "";
        Cursor cursor = this.getReadableDatabase().query(
                TABLE_NAME, new String[] { COL1 },
                null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                gender = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return gender;
    }
    public String getStringBMI() throws SQLException {
        String BMI = "";
        Cursor cursor = this.getReadableDatabase().query(
                TABLE_NAME, new String[] { COL5 },
                null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                BMI = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return BMI;
    }
    public String getStringActivityLevel() throws SQLException {
        String activityLevel = "";
        Cursor cursor = this.getReadableDatabase().query(
                TABLE_NAME, new String[] { COL4 },
                null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                activityLevel = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return activityLevel;
    }
    public String getStringCaloriesNeeded() throws SQLException {
        String caloriesNeeded = "";
        Cursor cursor = this.getReadableDatabase().query(
                TABLE_NAME, new String[] { COL7 },
                null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                caloriesNeeded = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return caloriesNeeded;
    }
    public String getStringWeightGoal() throws SQLException {
        String weightGoal = "";
        Cursor cursor = this.getReadableDatabase().query(
                TABLE_NAME, new String[] { COL8 },
                null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                weightGoal = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return weightGoal;
    }
    public String getStringAge() throws SQLException {
        String age = "";
        Cursor cursor = this.getReadableDatabase().query(
                TABLE_NAME, new String[] { COL6 },
                null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                age = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return age;
    }

}
