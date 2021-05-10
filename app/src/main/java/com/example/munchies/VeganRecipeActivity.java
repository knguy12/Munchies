package com.example.munchies;

import android.content.Intent;
import android.content.res.Resources;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class VeganRecipeActivity extends AppCompatActivity {

    ListView veganRecipeView;
    String[] veganItems;
    String[] veganTypes;
    String[] veganCalories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vegan_recipe);
        Resources res = getResources();
        veganRecipeView = (ListView) findViewById(R.id.veganRecipeListView);
        veganItems = res.getStringArray(R.array.veganitems);
        veganTypes = res.getStringArray(R.array.vegantypes);
        veganCalories = res.getStringArray(R.array.vegancalories);

        VeganItemAdapter veganAdapter = new VeganItemAdapter(this, veganItems, veganTypes, veganCalories);
        veganRecipeView.setAdapter(veganAdapter);

        veganRecipeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent showInstructions = new Intent(getApplicationContext(), VeganRecipeInstructions.class);
                showInstructions.putExtra("com.example.munchies.VEGAN_RECIPE_INDEX", i);
                startActivity(showInstructions);

            }
        });

    }
}