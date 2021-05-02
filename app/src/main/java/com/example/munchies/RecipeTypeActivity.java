package com.example.munchies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RecipeTypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_type);

    //button for all recipes that will display all recipes when clicked
        Button allRecipeBtn = (Button) findViewById(R.id.allRecipeBtn);

        allRecipeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openAllRecipeView = new Intent(getApplicationContext(), AllRecipeActivity.class);
                startActivity(openAllRecipeView);
            }
        });
        //button for vegan recipes that will display all vegan recipes when clicked
        Button veganRecipeBtn = (Button) findViewById(R.id.veganTypeBtn);

        veganRecipeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openVeganRecipeView = new Intent(getApplicationContext(), VeganRecipeActivity.class);
                startActivity(openVeganRecipeView);
            }
        });
        //button for keto recipes that will display all keto recipes when clicked
        Button ketoRecipeBtn = (Button) findViewById(R.id.ketoBtn);
        ketoRecipeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openKetoRecipeView = new Intent(getApplicationContext(), KetoRecipeActivity.class);
                startActivity(openKetoRecipeView);
            }
        });
        //button for low cal  recipes that will display all low cal recipes when clicked
        Button lowCalRecipeBtn = (Button) findViewById(R.id.lowBtn);
        lowCalRecipeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openLowCalRecipeView = new Intent(getApplicationContext(), LowCalRecipeActivity.class);
                startActivity(openLowCalRecipeView);
            }
        });



    }
}