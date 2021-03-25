package com.example.ht;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Fragment_ShowChart extends Fragment {
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_showchart, container, false);
        setupLineChart();
        return view;
    }




    public void setupLineChart(){
        float weights[] = {150, 140, 130, 120, 110, 100, 90, 80};
        float months[] = {1, 2, 3, 4, 6, 7, 8};

        //populate list of chartentries
        List<Entry> chartEntries = new ArrayList<>();
        for (int i = 0; i < months.length; i++){
            chartEntries.add(new Entry(months[i],weights[i]));
        }
        //Create data set
        LineDataSet dataSet = new LineDataSet(chartEntries, "Weight progress");
        //Create new data object
        LineData data = new LineData(dataSet);
        //Create the chart
        LineChart lineChart = view.findViewById(R.id.graph);
        lineChart.setData(data);
        lineChart.invalidate();

    }
}
/*

 */
