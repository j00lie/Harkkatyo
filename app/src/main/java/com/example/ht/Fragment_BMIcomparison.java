package com.example.ht;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_BMIcomparison extends Fragment {
    View view;
    Context context;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bmicomparison, container, false);
        context = container.getContext(); // Get the context that is needed when calculating BMI, due to the toast messages in the function
        Button btn_showBMI = view.findViewById(R.id.btn_calculateBMI);
        btn_showBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBMI();
            }
        });
        Button btn_getStats = view.findViewById(R.id.btn_BMIstats);
        btn_getStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showStats();
            }
        });

        return view;
    }
    public void showBMI(){
        BMICalc bmiCalc = new BMICalc();
        double bmi = bmiCalc.calculateBMI(context);
        String bmi_s = String.format("BMI: %.1f", bmi);
        TextView ownBMI = view.findViewById(R.id.textView_ownBMI);
        ownBMI.setText(bmi_s);
    }
    public void showStats(){
        ApiHandler ah = new ApiHandler();
        TextView BMIstats = view.findViewById(R.id.textView_BMIstats);
        String bmi_info = "The percentage of overweight (BMI > 30) people in Finland for year 2019 was " + ah.readJSON() ;
        BMIstats.setText(bmi_info);
    }
}
