package com.example.ht;

import java.util.ArrayList;

public class EntryHandler {
    private ArrayList<DataEntry> dataEntries;
    public EntryHandler(){
        dataEntries = new ArrayList<>();
    }

    public void addEntry(DataEntry entry){
        dataEntries.add(entry);
    }
    public ArrayList<DataEntry> returnEntries(){
        return dataEntries;
    }

}
