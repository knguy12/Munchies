package com.example.munchies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
//an adapter class for the keto recipes that changes helps connect the text views layout to the listview to properly display the recipes
public class KetoItemAdapter extends BaseAdapter {
    LayoutInflater mInflater;
    String[] ketoItems;
    String[] ketoTypes;
    String[] ketoCalories;
    public KetoItemAdapter(Context c, String[] i, String[] t, String[] cal){
        ketoItems = i;
        ketoTypes = t;
        ketoCalories = cal;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return ketoItems.length;
    }

    @Override
    public Object getItem(int i) {
        return ketoItems[i];
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

        String name = ketoItems[i];
        String type = ketoTypes[i];
        String cal  = ketoCalories[i];

        allNameTextView.setText(name);
        allTypeTextView.setText(type);
        allCalorieTextView.setText(cal);

        return v;
    }
}
