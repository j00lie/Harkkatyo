package com.example.ht;

import android.content.Context;
import android.widget.Toast;

public class BMICalc {
    private double bmi;
    Person person = Person.getInstance();

    public double calculateBMI(Context context) {
        //Calculate latest bmi value
        EntryHandler entryHandler = EntryHandler.getInstance();
        if (entryHandler.returnEntries().isEmpty()) {
            Toast.makeText(context, "Add weight(s) first", Toast.LENGTH_SHORT).show();
            return 0;
        } else if (person.getHeight() == 0){
            Toast.makeText(context, "Add height first", Toast.LENGTH_SHORT).show();
            return 0;
        } else{
            float latest_weight = entryHandler.returnEntries().get(entryHandler.returnEntries().size() - 1).getWeight(); //Get last element of dataentry list
            this.bmi = latest_weight / Math.pow(((person.getHeight()) / 100), 2); // BMI is calculated by dividing weight with the height in meters squared
        }
        return bmi;
    }
}

