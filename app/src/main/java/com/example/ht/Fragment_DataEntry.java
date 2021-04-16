package com.example.ht;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class Fragment_DataEntry extends Fragment {
    View view;
    CalendarView calendarView;
    Date date;
    EditText weightEntry;

    EntryHandler entryHandler = EntryHandler.getInstance();
    DataEntry dataEntry;
    Context context;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dataentry, container, false);
        context = container.getContext(); // get the apps context
        chooseDay();
        enterWeight();
        deleteData();
        return view;
    }
    public void chooseDay(){
        calendarView = view.findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                date = calendar.getTime();
            }
        });
    }
    public void enterWeight(){
        Button button = view.findViewById(R.id.SaveDataBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataEntry  = new DataEntry(); // Create new entry here, so that the user can input multiple datapoints without closing the fragment in between inputs
                float weight;
                weightEntry = view.findViewById(R.id.weightEntry);
                if (date == null || weightEntry.getText().toString().isEmpty()){
                    if(date == null){
                        Toast.makeText(context, "Enter Date first", Toast.LENGTH_SHORT).show();
                    }
                    if(weightEntry.getText().toString().isEmpty()){
                        Toast.makeText(context, "Enter Weight first", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    dataEntry.setDate(date);
                    weight = Float.parseFloat(weightEntry.getText().toString());
                    dataEntry.setWeight(weight);
                    entryHandler.addEntry(dataEntry);
                    entryHandler.writeLog(context); // Pass context to the filewriter
                }
            }
        });

    }
    public void deleteData(){
        Button btn_delete = view.findViewById(R.id.deleteData_btn);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entryHandler.deleteEntries(); //Delete all data
                entryHandler.writeLog(context); //Remove data from log
            }
        });
    }
}
