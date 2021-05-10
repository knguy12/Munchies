package com.example.munchies;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;


public class MainActivity extends AppCompatActivity {
    private static int SPASH_TIME_OUT = 2000;
    DatabaseHelper mDatabaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabaseHelper = new DatabaseHelper(this);

        //Hides action bar
        getSupportActionBar().hide();
        //Shows splash screen for 4 seconds before showing home screen
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                //Checks to see if user has already inserted their info into the DB, if yes then take them straight to home screen
                //if no then ask them to input their information
                if(mDatabaseHelper.getSizeOfDB() == 0) {
                    Intent homeIntent = new Intent(MainActivity.this, InputActivity.class);
                    startActivity(homeIntent);
                }
                else if(mDatabaseHelper.getSizeOfDB() > 0){
                    Intent homeIntent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(homeIntent);
                }
            }
        },SPASH_TIME_OUT);
    }
}
