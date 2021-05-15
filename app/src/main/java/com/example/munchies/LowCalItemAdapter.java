package com.example.munchies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
//an adapter class for the low cal recipes that helps connect the text views layout to the listview to properly display the recipes
public class LowCalItemAdapter extends BaseAdapter {

    LayoutInflater mInflater;
    String[] LowCalItems;
    String[] LowCalTypes;
    String[] LowCalCalories;

    public LowCalItemAdapter(Context c, String[] i, String[] t, String[] cal){
        LowCalItems = i;
        LowCalTypes = t;
        LowCalCalories = cal;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //get the amount of items
    @Override
    public int getCount() {
        return LowCalItems.length;
    }

    //gets item
    @Override
    public Object getItem(int i) {
        return LowCalItems[i];
    }

    //gets item id
    @Override
    public long getItemId(int i) {
        return i;
    }

    //sets the text of each text view
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = mInflater.inflate(R.layout.all_recipe_detail, null);
        TextView allNameTextView = (TextView) v.findViewById(R.id.allNameTextView);
        TextView allTypeTextView = (TextView) v.findViewById(R.id.allTypeTextView);
        TextView allCalorieTextView = (TextView) v.findViewById(R.id.allCalorieTextView);

        String name = LowCalItems[i];
        String type = LowCalTypes[i];
        String cal  = LowCalCalories[i];

        allNameTextView.setText(name);
        allTypeTextView.setText(type);
        allCalorieTextView.setText(cal);

        return v;
    }
}
