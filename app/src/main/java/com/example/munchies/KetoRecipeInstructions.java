package com.example.munchies;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;

public class KetoRecipeInstructions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keto_recipe_instructions);
        Intent in = getIntent();
        int index = in.getIntExtra("com.example.munchies.KETO_RECIPE_INDEX", -1);
        //sets image and scales the image to fit any phone size
        if (index > -1) {
            int picIndex = getInstr(index);
            ImageView instr = (ImageView) findViewById(R.id.ketoRecipeImageView);
            Display screen = getWindowManager().getDefaultDisplay();
            BitmapFactory.Options options = new BitmapFactory.Options();

            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(getResources(), picIndex, options);

            int instrWidth = options.outWidth;
            int screenWidth = screen.getWidth();

            if (instrWidth > screenWidth) {
                int ratio = Math.round((float) instrWidth / (float) screenWidth);
                options.inSampleSize = ratio;
            }
            options.inJustDecodeBounds = false;
            Bitmap scaledImg = BitmapFactory.decodeResource(getResources(), picIndex, options);
            instr.setImageBitmap(scaledImg);
        }
    }

    private int getInstr(int index) {
        switch (index) {
            case 0:
                return R.drawable.shrimpscampizoodles;
            case 1:
                return R.drawable.hasselbackcapresechicken;
            case 2:
                return R.drawable.crispypeanutbutterballs;
            case 3:
                return R.drawable.sheetpaneggswithspinachham;
            case 4:
                return R.drawable.rainbowfrittata;

            default:
                return -1;
        }
    }
}