package com.example.munchies;

import android.content.Intent;
import android.content.res.Resources;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


//creates the all recipe activity
public class AllRecipeActivity extends AppCompatActivity {

    ListView allRecipeView;
    String[] items;
    String[] types;
    String[] calories;

    //creates and displays the activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_recipe);
        Resources res = getResources();
        allRecipeView = (ListView) findViewById(R.id.allRecipeListView);
        items = res.getStringArray(R.array.items);
        types = res.getStringArray(R.array.types);
        calories = res.getStringArray(R.array.calories);

        AllItemAdapter itemAdapter = new AllItemAdapter(this, items, types, calories);
        allRecipeView.setAdapter(itemAdapter);

        allRecipeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent showInstructions = new Intent(getApplicationContext(), AllRecipeInstructions.class);
                showInstructions.putExtra("com.example.munchies.ALL_RECIPE_INDEX", i);
                startActivity(showInstructions);

            }
        });

    }


}