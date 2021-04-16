package com.example.ht;

import android.os.StrictMode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;

public class ApiHandler {
    //Reads the desired elements from the JSON
    public String readJSON(){
        String json = getJSON();
        String bmi_stats = "";
        String age_group = "";
        if (json != null){
            try{
                JSONArray jsonArray = new JSONArray(json);
                for (int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    if(jsonObject.getString("indicator").equals("4461")){
                        age_group = "yli 64v";
                    }else if (jsonObject.getString("indicator").equals("4460")){
                        age_group = "20v - 64v";
                    }else{
                        age_group = "kaikenikäiset";
                    }
                    bmi_stats = jsonObject.getString("value") + "%" + " ikäluokassa " + age_group;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return bmi_stats;
    }

    public String getJSON(){
        String response = null;
        //Permissions for internet, permission also added in AndroidManifest.xml:
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try{
            int ageGgroup = 0;
            if(Person.getInstance().getAge() < 65 && Person.getInstance().getAge() >19){
                ageGgroup = 4460; //If users entered age is between 20-64
            }else if(Person.getInstance().getAge() > 64){
                ageGgroup = 4461; //If users entered age is over 64
            }else{
                ageGgroup = 4459; //If user < 20 show all age groups
            }
            URL url = new URL("https://sotkanet.fi/rest/1.1/json?&indicator="+ageGgroup+"&years=2019&genders=total"); // Adress here
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET"); // Post to url or GET from url
            InputStream inputStream = new BufferedInputStream(connection.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while((line = br.readLine()) != null){
                sb.append(line).append("\n");
            }
            response = sb.toString();
            inputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
