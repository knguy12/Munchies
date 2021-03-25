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
    private EditText age;

    private RadioGroup genderGroup;
    private RadioGroup exerciseGroup;
    private RadioGroup weightGroup;

    private RadioButton selectedGender;
    private RadioButton selectedExercise;
    private RadioButton selectedWeightGoal;

    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        getSupportActionBar().hide();

        name = findViewById(R.id.nameInput);
        weight = findViewById(R.id.weightInput);
        height = findViewById(R.id.heightInput);
        age = findViewById(R.id.ageInput);

        genderGroup = findViewById(R.id.genderGroup);
        exerciseGroup = findViewById(R.id.exerciseGroup);
        weightGroup = findViewById(R.id.weightGroup);

        confirmButton = findViewById(R.id.confirmButton);

        mDatabaseHelper = new DatabaseHelper(this);


        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int genderID = genderGroup.getCheckedRadioButtonId();
                int exerciseID = exerciseGroup.getCheckedRadioButtonId();
                int weightID = weightGroup.getCheckedRadioButtonId();

                selectedGender = findViewById(genderID);
                selectedExercise = findViewById(exerciseID);
                selectedWeightGoal = findViewById(weightID);

                if(name.length() != 0 && weight.length() != 0 && height.length() != 0 && genderID != -1 && exerciseID != -1){
                    //Converts EditText to readable format
                    final int weightConverted = Integer.parseInt(weight.getText().toString());
                    final int heightConverted = Integer.parseInt(height.getText().toString());
                    final int ageConverted = Integer.parseInt(age.getText().toString());

                    final String nameConverted = name.getText().toString();
                    final String gender = selectedGender.getText().toString();
                    final String exercise = selectedExercise.getText().toString();
                    final String weightGroup = selectedWeightGoal.getText().toString();

                    final double BMI = calculateBMI(weightConverted, heightConverted);
                    final double heightToM = convertCM(heightConverted);


                    //Adds it to database
                    AddData(nameConverted, gender, heightToM, weightConverted, exercise, BMI, ageConverted, calculateCalories(weightConverted, heightConverted, ageConverted, gender, weightGroup) ,weightGroup);
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
    public int calculateCalories(int weight, int height, int age, String gender, String weightGoal){
        double calories;
        if(gender.equals("Male"))
            calories = (66 + (6.3 * weight) + (12.9 * height/2.54) - (6.8 * age));
        else
            calories = (655 + (4.3 * weight) + (4.7 * height/2.54) - (4.7 * age));
        if(weightGoal.equals("Lose Weight"))
            calories -= 500;
        else if(weightGoal.equals("Gain Weight"))
            calories += 500;
        return (int)calories;
    }
    //Adds data into database
    public void AddData(String name, String gender, double height, int weight, String ActivityLevel, double BMI, int age, int dailyCaloriesNeeded, String weightGoal) {
        boolean insertData = mDatabaseHelper.addData(name, gender, height, weight, ActivityLevel, BMI, age, dailyCaloriesNeeded, weightGoal);
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
