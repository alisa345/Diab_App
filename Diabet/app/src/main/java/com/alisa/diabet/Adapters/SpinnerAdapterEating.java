package com.alisa.diabet.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alisa.diabet.Model.Eating;
import com.alisa.diabet.R;

import java.util.ArrayList;

public class SpinnerAdapterEating extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private ArrayList<Eating> autoEatList;


    public SpinnerAdapterEating(Context context, ArrayList<Eating> autoEatList) {
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.autoEatList = autoEatList;
    }

    @Override
    public int getCount() {
        return autoEatList.size();
    }

    @Override
    public Object getItem(int position) {
        return autoEatList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = layoutInflater.inflate(R.layout.spinner_item, parent, false);

        Eating eatingl = (Eating) getItem(position);

        TextView timeText = (TextView) view.findViewById(R.id.timeText);
        timeText.setText(eatingl.getEatings());

        return view;

    }
}
