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

                    final double BMI = calculateBMI(convertCM(heightConverted), weightConverted);
                    //Checks to see if DB is empty first, if it is then add in data
                    if(mDatabaseHelper.getSizeOfDB() == 0) {
                        //Adds it to database
                        AddData(nameConverted, gender, heightConverted, weightConverted, exercise, BMI, ageConverted, calculateCalories(weightConverted, heightConverted, ageConverted, gender, weightGroup, exercise) ,weightGroup);
                        Intent homeIntent = new Intent(InputActivity.this, HomeActivity.class);
                        startActivity(homeIntent);
                    }//If DB already has a information in it, update the profile
                    else if(mDatabaseHelper.getSizeOfDB() > 0){
                        updateData(nameConverted, gender, heightConverted, weightConverted, exercise, BMI, ageConverted, calculateCalories(weightConverted, heightConverted, ageConverted, gender, weightGroup, exercise) ,weightGroup);
                        Intent profileIntent = new Intent(InputActivity.this, UserProfileActivity.class);
                        startActivity(profileIntent);
                    }
                }
                else
                    toastMessage("Please Fill In All Fields Before Continuing");
            }
        });
    }
    //Converts users height in centimeters to meters
    public double convertCM(int height){
        return (double)height/100;
    }
    //Calculates Users BMI
    public double calculateBMI(double height, int weight){
        double heightM = Math.pow(height, 2);
        double weightM = weight * 0.453592;

        return weightM/heightM;
    }
    public int calculateCalories(int weight, int height, int age, String gender, String weightGoal, String selectedExercise){
        double calories;
        if(gender.equals("Male"))
            calories = (66 + (6.3 * weight) + (12.9 * height/2.54) - (6.8 * age));
        else
            calories = (655 + (4.3 * weight) + (4.7 * height/2.54) - (4.7 * age));
        if(weightGoal.equals("Lose Weight"))
            calories -= 500;
        else if(weightGoal.equals("Gain Weight"))
            calories += 500;
        if(selectedExercise.equals("Little to None"))
            calories *= 1.2;
        else if (selectedExercise.equals("Moderate(2-3 times a week)"))
            calories *= 1.55;
        else if(selectedExercise.equals("Excessive(6 or more times a week)"))
            calories *= 1.9;
        return (int)calories;
    }
    //Adds data into database
    public void AddData(String name, String gender, double height, int weight, String ActivityLevel, double BMI, int age, int dailyCaloriesNeeded, String weightGoal) {
        boolean insertData = mDatabaseHelper.addData(name, gender, height, weight, ActivityLevel, BMI, age, dailyCaloriesNeeded, weightGoal);
        if (insertData) {
            toastMessage("User Profile Saved!");
        } else {
            toastMessage("Something went wrong");
        }
    }
    //Adds data into database
    public void updateData(String name, String gender, double height, int weight, String ActivityLevel, double BMI, int age, int dailyCaloriesNeeded, String weightGoal) {
        boolean insertData = mDatabaseHelper.updateData(name, gender, height, weight, ActivityLevel, BMI, age, dailyCaloriesNeeded, weightGoal);
        if (insertData) {
            toastMessage("User Profile Updated!");
        } else {
            toastMessage("Something went wrong");
        }
    }
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
