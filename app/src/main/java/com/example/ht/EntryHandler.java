package com.example.ht;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static android.content.Context.MODE_PRIVATE;

public class EntryHandler {
    private ArrayList<DataEntry> dataEntries;
    private static EntryHandler entryHandler = new EntryHandler(); //Singleton, so that there's only one handler
    public EntryHandler(){
        dataEntries = new ArrayList<>();
    }
    public static EntryHandler getInstance(){
        return entryHandler;
    }

    public void addEntry(DataEntry entry){
        dataEntries.add(entry);
    }
    public void deleteEntries(){
        dataEntries.clear();
    }
    public ArrayList<DataEntry> returnEntries(){

        return dataEntries;
    }
    public void writeLog(Context context){
        try {
            OutputStreamWriter ows = new OutputStreamWriter(context.openFileOutput("log.csv", Context.MODE_PRIVATE));
            ows.write("Date;Weight\n");
            for(int i = 0; i < dataEntries.size(); i++){
                ows.write(dataEntries.get(i).getDate().toString() + ";" + dataEntries.get(i).getWeight() + "\n");
            }
            ows.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
