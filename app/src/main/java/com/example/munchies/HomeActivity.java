package com.example.munchies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    private Button recipeBtn, userBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        userBtn = (Button) findViewById(R.id.userBtn);
        recipeBtn = (Button) findViewById(R.id.recipeBtn);

        recipeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityRecipeTypes();
            }
        });
        userBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityUserProfile();
            }
        });

    }


    //method that opens the recipe type activity
    public void openActivityRecipeTypes(){
        Intent recipeType = new Intent(HomeActivity.this, RecipeTypeActivity.class);
        startActivity(recipeType);
    }

    //method that opens the user profile activity
    public void openActivityUserProfile(){
        Intent userProfile = new Intent(HomeActivity.this, UserProfileActivity.class);
        startActivity(userProfile);
    }
}
