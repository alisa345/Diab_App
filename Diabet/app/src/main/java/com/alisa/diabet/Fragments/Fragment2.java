package com.alisa.diabet.Fragments;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alisa.diabet.DB.NoteDao;
import com.alisa.diabet.DB.NoteDatabase;
import com.alisa.diabet.Model.Note;
import com.alisa.diabet.Model.NoteViewModel;
import com.alisa.diabet.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;

import java.util.ArrayList;
import java.util.List;

public class Fragment2 extends Fragment {

    LineChart mChart;
    OnDataPointTapListener tapListener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment2, null);

        Toast.makeText(getActivity(), "Недостаточно значений для построения графика", Toast.LENGTH_SHORT).show();

        GraphView graph = (GraphView) v.findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(10.12, 1),
                new DataPoint(11.12, 5),
                new DataPoint(12.12, 3),
                new DataPoint(13.12, 5),
                new DataPoint(14.12, 1),
                new DataPoint(15.12, 5),
                new DataPoint(16.12, 3),
                new DataPoint(17.12, 1)

        });
        series.setAnimated(true);
        series.setTitle("Random Curve 1");
        series.setColor(Color.BLUE);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(10);
        series.setThickness(8);

        graph.addSeries(series);

       return  v;
    }



}
