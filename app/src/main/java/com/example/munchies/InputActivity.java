package com.example.munchies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class InputActivity extends AppCompatActivity {
    DatabaseHelper mDatabaseHelper;
    private EditText name;
    private EditText weight;
    private EditText height;

    private RadioGroup genderGroup;
    private RadioGroup exerciseGroup;

    private RadioButton selectedGender;
    private RadioButton selectedExercise;
    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        getSupportActionBar().hide();

        name = findViewById(R.id.nameInput);
        weight = findViewById(R.id.weightInput);
        height = findViewById(R.id.heightInput);
        genderGroup = findViewById(R.id.genderGroup);
        exerciseGroup = findViewById(R.id.exerciseGroup);
        confirmButton = findViewById(R.id.confirmButton);

        mDatabaseHelper = new DatabaseHelper(this);


        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int genderID = genderGroup.getCheckedRadioButtonId();
                int exerciseID = exerciseGroup.getCheckedRadioButtonId();
                selectedGender = findViewById(genderID);
                selectedExercise = findViewById(exerciseID);

                if(name.length() != 0 && weight.length() != 0 && height.length() != 0 && genderID != -1 && exerciseID != -1){
                    //Converts EditText to readable format
                    final int weightConverted = Integer.parseInt(weight.getText().toString());
                    final int heightConverted = Integer.parseInt(height.getText().toString());
                    final String nameConverted = name.getText().toString();
                    final String gender = selectedGender.getText().toString();
                    final String exercise = selectedExercise.getText().toString();
                    final double BMI = calculateBMI(weightConverted, heightConverted);
                    final double heightToM = convertCM(heightConverted);
                    //Adds it to database
                    AddData(nameConverted, gender, heightToM, weightConverted, exercise, BMI);
                    Intent homeIntent = new Intent(InputActivity.this, HomeActivity.class);
                    startActivity(homeIntent);
                }
                else
                    toastMessage("Please Fill In All Fields Before Continuing");
            }
        });
    }
    //Converts users height in centimeters to meters
    public double convertCM(int height){
        return height/100;
    }
    //Calculates Users BMI
    public double calculateBMI(int height, int weight){
        return weight/(Math.pow(convertCM(height), 2));
    }
    //Adds data into database
    public void AddData(String name, String gender, double height, int weight, String ActivityLevel, double BMI) {
        boolean insertData = mDatabaseHelper.addData(name, gender, height, weight, ActivityLevel, BMI);
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
