package com.example.munchies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
//an adapter class that helps connect the text views layout to the listview to properly display the recipes
public class AllItemAdapter extends BaseAdapter {

    LayoutInflater mInflater;
    String[] items;
    String[] types;
    String[] calories;

    public AllItemAdapter(Context c, String[] i, String[] t, String[] cal){
        items = i;
        types = t;
        calories = cal;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //returns the amount of items
    @Override
    public int getCount() {
        return items.length;
    }

    //returns the item
    @Override
    public Object getItem(int i) {
        return items[i];
    }

    //returns the item id
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

        String name = items[i];
        String type = types[i];
        String cal  = calories[i];

        allNameTextView.setText(name);
        allTypeTextView.setText(type);
        allCalorieTextView.setText(cal);

        return v;
    }
}
