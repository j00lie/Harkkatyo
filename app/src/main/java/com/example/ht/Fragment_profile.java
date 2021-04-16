package com.example.ht;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_profile extends Fragment {
    private View view;
    private Context context;
    private Person person = Person.getInstance();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        context = container.getContext();
        setInfo();
        return view;
    }
    public void setInfo(){
        Button saveInfo_btn = view.findViewById(R.id.save_userinfo_btn);
        EditText age_entry = view.findViewById(R.id.age_field);
        EditText height_entry = view.findViewById(R.id.height_field);
        saveInfo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(age_entry.getText().toString().isEmpty()){
                    Toast.makeText(context, "Age not entered", Toast.LENGTH_SHORT).show();
                }else if (height_entry.getText().toString().isEmpty()){
                    Toast.makeText(context, "Height not entered", Toast.LENGTH_SHORT).show();
                }else{
                    person.setAge(Integer.parseInt(age_entry.getText().toString()));
                    person.setHeight(Float.parseFloat(height_entry.getText().toString()));
                }

            }
        });


    }
}
