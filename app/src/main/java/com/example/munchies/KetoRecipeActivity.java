package com.example.munchies;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class KetoRecipeActivity extends AppCompatActivity {
    ListView ketoRecipeView;
    String[] ketoItems;
    String[] ketoTypes;
    String[] ketoCalories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keto_recipe);
        Resources res = getResources();
        ketoRecipeView = (ListView) findViewById(R.id.ketoRecipeListView);
        ketoItems = res.getStringArray(R.array.ketoitems);
        ketoTypes = res.getStringArray(R.array.ketotypes);
        ketoCalories = res.getStringArray(R.array.ketocalories);

        KetoItemAdapter ketoAdapter = new KetoItemAdapter(this, ketoItems, ketoTypes, ketoCalories);
        ketoRecipeView.setAdapter(ketoAdapter);

        ketoRecipeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent showInstructions = new Intent(getApplicationContext(), KetoRecipeInstructions.class);
                showInstructions.putExtra("com.example.munchies.KETO_RECIPE_INDEX", i);
                startActivity(showInstructions);

            }
        });
    }
}