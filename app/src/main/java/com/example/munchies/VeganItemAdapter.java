package com.example.munchies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

//an adapter class for the vegan recipes that changes helps connect the text views layout to the listview to properly display the recipes
public class VeganItemAdapter extends BaseAdapter {
    LayoutInflater mInflater;
    String[] veganItems;
    String[] veganTypes;
    String[] veganCalories;
    public VeganItemAdapter(Context c, String[] i, String[] t, String[] cal){
        veganItems = i;
        veganTypes = t;
        veganCalories = cal;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return veganItems.length;
    }

    @Override
    public Object getItem(int i) {
        return veganItems[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = mInflater.inflate(R.layout.all_recipe_detail, null);
        TextView allNameTextView = (TextView) v.findViewById(R.id.allNameTextView);
        TextView allTypeTextView = (TextView) v.findViewById(R.id.allTypeTextView);
        TextView allCalorieTextView = (TextView) v.findViewById(R.id.allCalorieTextView);

        String name = veganItems[i];
        String type = veganTypes[i];
        String cal  = veganCalories[i];

        allNameTextView.setText(name);
        allTypeTextView.setText(type);
        allCalorieTextView.setText(cal);

        return v;
    }
}
