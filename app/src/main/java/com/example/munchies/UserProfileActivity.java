package com.example.munchies;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class UserProfileActivity extends AppCompatActivity{

    private ImageView profileImage;
    private TextView userName, userWeight, userHeight, userGender, userActivity, userAge, userBMI, userWeightGoal, userCalories;
    private Button setProfileImage;
    DatabaseHelper databaseHelper;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);

        profileImage = findViewById(R.id.profileImage);
        setProfileImage = findViewById(R.id.changeProfilePicture);
        userName = findViewById(R.id.userName);
        userHeight = findViewById(R.id.userHeight);
        userWeight = findViewById(R.id.userWeight);
        userGender = findViewById(R.id.userGender);
        userActivity = findViewById(R.id.userActivity);
        userAge = findViewById(R.id.userAge);
        userBMI = findViewById(R.id.userBMI);
        userWeightGoal = findViewById(R.id.userWeightGoal);
        userCalories = findViewById(R.id.userCalories);

        databaseHelper = new DatabaseHelper(this);

        userName.setText(databaseHelper.getStringUserName());
        userWeight.setText((databaseHelper.getStringWeight()) + "lbs");
        userHeight.setText((databaseHelper.getStringHeight()) + "cm");
        userGender.setText(databaseHelper.getStringGender());
        userActivity.setText(databaseHelper.getStringActivityLevel());
        userBMI.setText(databaseHelper.getStringBMI());
        userCalories.setText(databaseHelper.getStringCaloriesNeeded());
        userWeightGoal.setText(databaseHelper.getStringWeightGoal());
        userAge.setText(databaseHelper.getStringAge());

        setProfileImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Whenever user clicks set image button open gallary
                Intent openGallaryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGallaryIntent,1000);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                Uri imageUri = data.getData();
                profileImage.setImageURI(imageUri);

            }
        }
    }



}
