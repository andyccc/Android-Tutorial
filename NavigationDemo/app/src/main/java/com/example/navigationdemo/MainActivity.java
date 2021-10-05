package com.example.navigationdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavHostFragment navHostFragment = (NavHostFragment)getSupportFragmentManager().findFragmentById( R.id.fragment);
        NavController nav = navHostFragment.getNavController();
        NavigationUI.setupActionBarWithNavController(this, nav);

//        NavController nav = Navigation.findNavController(this, R.id.fragment);
//        NavigationUI.setupActionBarWithNavController(this, nav);

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController nav = Navigation.findNavController(this, R.id.fragment);
        return nav.navigateUp();
    }
}