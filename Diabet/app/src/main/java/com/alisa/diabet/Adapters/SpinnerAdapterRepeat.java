package com.alisa.diabet.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alisa.diabet.Model.Repeat;
import com.alisa.diabet.R;

import java.util.ArrayList;

public class SpinnerAdapterRepeat extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private ArrayList<Repeat> autoInsList;


    public SpinnerAdapterRepeat(Context context, ArrayList<Repeat> autoInsList) {
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.autoInsList = autoInsList;
    }

    @Override
    public int getCount() {
        return autoInsList.size();
    }

    @Override
    public Object getItem(int position) {
        return autoInsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = layoutInflater.inflate(R.layout.spinner_item_repeat, parent, false);

        Repeat repeat = (Repeat) getItem(position);

        TextView timeText = (TextView) view.findViewById(R.id.repText);
        timeText.setText(repeat.getRepeat());

        return view;

    }
}
