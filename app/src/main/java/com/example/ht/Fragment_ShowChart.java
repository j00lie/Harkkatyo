package com.example.ht;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Fragment_ShowChart extends Fragment {
    View view;
    EntryHandler entryHandler = EntryHandler.getInstance();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_showchart, container, false);
        setupLineChart();
        return view;
    }

    public void setupLineChart(){
        List<Long> datesInMillis = new ArrayList<>();
        //Sort original entrylist by date, so that the graph doesn't explode..
        Collections.sort(entryHandler.returnEntries(), new Comparator<DataEntry>() {
            public int compare(DataEntry d1, DataEntry d2) {
                return d1.getDate().compareTo(d2.getDate());
            }
        });
        //Transform date into milliseconds
        for(int i = 0; i < entryHandler.returnEntries().size(); i++){
            long dateInMillis = entryHandler.returnEntries().get(i).getDate().getTime();
            datesInMillis.add(dateInMillis);
        }

        //populate chart entries
        List<Entry> chartEntries = new ArrayList<>();
        for (int i = 0; i < datesInMillis.size(); i++){
            chartEntries.add(new Entry(datesInMillis.get(i),entryHandler.returnEntries().get(i).getWeight()));
        }
        //Create data set
        LineDataSet dataSet = new LineDataSet(chartEntries, "Weight progress");
        //Create new data object
        LineData data = new LineData(dataSet);
        //Create the chart
        LineChart lineChart = view.findViewById(R.id.graph);
        lineChart.setData(data);
        //Formate the x-axis to readable date
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
                String dateString = sdf.format(new Date((long) value));
                return dateString;
            }
        });
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(chartEntries.size(), true); // Only the amount of datevalues are shown that exist in the data
        lineChart.getDescription().setEnabled(false); // Removes the default "Description label"
        xAxis.setDrawGridLines(false);
        //xAxis.setGranularity(1f); // Only intervals of 1 day
        xAxis.setTextSize(8);
        lineChart.invalidate();
    }

}
/*

 */
