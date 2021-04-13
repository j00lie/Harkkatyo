package com.example.ht;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class Fragment_DataEntry extends Fragment {
    View view;
    CalendarView calendarView;
    EditText weightEntry;

    EntryHandler entryHandler = EntryHandler.getInstance();
    DataEntry dataEntry = new DataEntry();
    Context context;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dataentry, container, false);
        context = container.getContext(); // get the apps context
        choose_day();
        enter_weight();

        return view;
    }
    public void choose_day(){
        calendarView = view.findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Date date;
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                date = calendar.getTime();
                dataEntry.setDate(date);
            }
        });
    }
    public void enter_weight(){
        Button button = view.findViewById(R.id.SaveDataBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float weight;
                weightEntry = view.findViewById(R.id.weightEntry);
                weight = Float.parseFloat(weightEntry.getText().toString());
                dataEntry.setWeight(weight);
                entryHandler.addEntry(dataEntry);
                entryHandler.writeLog(context); // Pass context to the filewriter
                /*
                miten pystyy tekemään useamman datanetryn avaamatta fragmentin uudelleen?
                 */
            }
        });
    }
}
