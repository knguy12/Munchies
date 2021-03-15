package com.example.munchies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    private Button recipeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recipeBtn = (Button) findViewById(R.id.recipeBtn);
        recipeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityRecipeTypes();
            }
        });

    }


    //method that opens the recipe type activity
    public void openActivityRecipeTypes(){
        Intent recipeType = new Intent(HomeActivity.this, RecipeTypeActivity.class);
        startActivity(recipeType);
    }

}
