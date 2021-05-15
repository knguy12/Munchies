package com.example.munchies;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;
//creates the instruction activity and sets and scales the image of the instruction
public class VeganRecipeInstructions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vegan_recipe_instructions);
        Intent in = getIntent();
        int index = in.getIntExtra("com.example.munchies.VEGAN_RECIPE_INDEX", -1);

        //sets image and scales the image to fit any phone size
        if (index > -1) {
            int picIndex = getInstr(index);
            ImageView instr = (ImageView) findViewById(R.id.veganRecipeImageView);
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
    //returns the image depending on the index of the image selected
    private int getInstr(int index) {
        switch (index) {
            case 0:
                return R.drawable.chickpeacurry;
            case 1:
                return R.drawable.sweetpotatoblackbeanburger;
            case 2:
                return R.drawable.cabbagedietsoup;
            case 3:
                return R.drawable.instantpotvegetablesoup;
            case 4:
                return R.drawable.veganpumpkinsoup;

            default:
                return -1;
        }
    }
}