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
    public String readJSON(){
        String json = getJSON();
        String bmi_tilasto = "";
        if (json != null){
            try{
                JSONArray jsonArray = new JSONArray(json);
                for (int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    bmi_tilasto = jsonObject.getString("value");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return bmi_tilasto;
    }

    public String getJSON(){
        String response = null;
        //Permissions for internet, permission also added in AndroidManifest.xml:
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try{
            URL url = new URL("https://sotkanet.fi/rest/1.1/json?&indicator=4459&years=2019&genders=total"); // Adress here
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
