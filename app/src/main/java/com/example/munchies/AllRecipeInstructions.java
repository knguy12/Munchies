package com.example.munchies;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;

public class AllRecipeInstructions extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_recipe_instructions);
        

        Intent in = getIntent();
        int index = in.getIntExtra("com.example.munchies.ALL_RECIPE_INDEX", -1);
        //sets image and scales the image to fit any phone size

        if(index>-1) {
            int picIndex = getInstr(index);
            ImageView instr = (ImageView) findViewById(R.id.allRecipeImageView);
            Display screen = getWindowManager().getDefaultDisplay();
            BitmapFactory.Options options = new BitmapFactory.Options();

            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(getResources(), picIndex, options);

            int instrWidth = options.outWidth;
            int screenWidth = screen.getWidth();

            if (instrWidth> screenWidth) {
                int ratio = Math.round( (float)instrWidth / (float)screenWidth);
                options.inSampleSize = ratio;
            }
            options.inJustDecodeBounds = false;
            Bitmap scaledImg = BitmapFactory.decodeResource(getResources(), picIndex, options);
            instr.setImageBitmap(scaledImg);
        }


    }
    private int getInstr(int index) {
        switch (index) {
            case 0: return R.drawable.chickpeacurry;
            case 1: return R.drawable.sweetpotatoblackbeanburger;
            case 2: return R.drawable.cabbagedietsoup;
            case 3: return R.drawable.instantpotvegetablesoup;
            case 4: return R.drawable.veganpumpkinsoup;
            case 5: return R.drawable.shrimpscampizoodles;
            case 6: return R.drawable.hasselbackcapresechicken;
            case 7: return R.drawable.crispypeanutbutterballs;
            case 8: return R.drawable.sheetpaneggswithspinachham;
            case 9: return R.drawable.rainbowfrittata;
            case 10: return R.drawable.vegansmoothiebowl;
            case 11: return R.drawable.carrotsoup;
            case 12: return R.drawable.loadedblackbeandip;
            case 13: return R.drawable.avocadohummus;
            case 14: return R.drawable.whitebeanandavocadotoast;

            default: return -1;
        }
    }



}