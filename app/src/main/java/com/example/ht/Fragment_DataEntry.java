package com.example.ht;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dataentry, container, false);
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
            }
        });
    }
    public void enter_weight(){
        float weight;
        weightEntry = view.findViewById(R.id.weightEntry);
        if(weightEntry.getText().toString().equals("")){
            weight = 0;
        }else{
            weight = Float.parseFloat(weightEntry.getText().toString());
        }
    }
}
