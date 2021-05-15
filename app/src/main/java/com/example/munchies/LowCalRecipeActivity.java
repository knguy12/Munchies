package com.example.munchies;

import android.content.Intent;
import android.content.res.Resources;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
// creates the low cal recipe activity
public class LowCalRecipeActivity extends AppCompatActivity {
    ListView lowCalRecipeView;
    String[] LowCalItems;
    String[] LowCalTypes;
    String[] LowCalCalories;

    //creates and displays the activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_low_cal_recipe);
        Resources res = getResources();
        lowCalRecipeView = (ListView) findViewById(R.id.LowCalRecipeListView);
        LowCalItems = res.getStringArray(R.array.lowcalitems);
        LowCalTypes = res.getStringArray(R.array.lowcaltypes);
        LowCalCalories = res.getStringArray(R.array.lowcalcalories);

        LowCalItemAdapter ketoAdapter = new LowCalItemAdapter(this, LowCalItems, LowCalTypes, LowCalCalories);
        lowCalRecipeView.setAdapter(ketoAdapter);

        lowCalRecipeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent showInstructions = new Intent(getApplicationContext(), LowCalRecipeInstructions.class);
                showInstructions.putExtra("com.example.munchies.LOWCAL_RECIPE_INDEX", i);
                startActivity(showInstructions);

            }
        });
    }
}