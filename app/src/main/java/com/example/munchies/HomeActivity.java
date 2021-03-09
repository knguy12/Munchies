package com.example.munchies;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    DatabaseHelper mDatabaseHelper;
    private EditText name;
    private EditText weight;
    private EditText height;
    private EditText diet;
    private Button confirmButton;
    private Button displayButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        name = findViewById(R.id.nameInput);
        weight = findViewById(R.id.weightInput);
        height = findViewById(R.id.heightInput);
        diet = findViewById(R.id.dietInput);
        confirmButton = findViewById(R.id.confirmButton);
        mDatabaseHelper = new DatabaseHelper(this);


        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.length() != 0 && weight.length() != 0 && height.length() != 0 && diet.length() != 0){
                    //Converts EditText to readable format
                    final double weightConverted = Double.parseDouble(weight.getText().toString());
                    final double heightConverted = Double.parseDouble(height.getText().toString());
                    final String nameConverted = name.toString();
                    final String dietConverted = diet.toString();
                    //Adds it to database
                    AddData(nameConverted, calculateBMI(weightConverted,heightConverted), dietConverted);
                }
                else
                    toastMessage("Please Fill In All Fields Before Continuing");
            }
        });

        //Ignore this section(Meant for testing)
        displayButton = findViewById(R.id.displayButton);
        displayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastMessage("User Name: " + mDatabaseHelper.getUserName() +
                        "User BMI: " + mDatabaseHelper.getUserBMI() +
                        "User Diet: " + mDatabaseHelper.getUserDiet());

            }
        });
    }
    //Converts users height in centimeters to meters
    public double convertCM(double height){
        return height/100;
    }
    //Calculates users bmi
    public double calculateBMI(double height, double weight){
        return weight/(Math.pow(convertCM(height), 2));
    }
    //Adds data into database
    public void AddData(String name, double BMI, String diet) {
        boolean insertData = mDatabaseHelper.addData(name, BMI, diet);
        if (insertData) {
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("Something went wrong");
        }
    }
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
