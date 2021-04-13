package com.example.ht;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {


    private DrawerLayout drawerLayout;

    Context context = MainActivity.this;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup_menu();
    }
    public void setup_menu(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawerLayout = findViewById(R.id.drawer_layout);

        //Make the 3 lines to open drawer, strings.xml appended with open and close_drawer strings, required by the toggle method
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_enterData:
                        //Open fragment for data entries
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new Fragment_DataEntry()).addToBackStack(null).commit();
                        break;
                    case R.id.menu_showChart:
                        //Open chart fragment
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new Fragment_ShowChart()).addToBackStack(null).commit();
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START); //Check that backpress doesn't kill the whole app, just closes the menu
        }else{
            super.onBackPressed();
        }
    }
    public void openProfile(View view){
        //Open profile fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new Fragment_profile()).addToBackStack(null).commit();

    }
    public void openBMIcomparison(View view){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new Fragment_BMIcomparison()).addToBackStack(null).commit();
    }

}