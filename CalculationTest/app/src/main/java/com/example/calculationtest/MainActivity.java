package com.example.calculationtest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    NavController nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 不全屏显示
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        nav = navHostFragment.getNavController();
        NavigationUI.setupActionBarWithNavController(this, nav);

    }

    @Override
    public boolean onSupportNavigateUp() {
        if (nav.getCurrentDestination().getId() == R.id.questionFragment) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.quit_dialog_title);
            builder.setPositiveButton(R.string.dialog_positive_message, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    nav.navigateUp();
                }
            });

            builder.setNegativeButton(R.string.dialog_negative_message, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        } else if (nav.getCurrentDestination().getId() == R.id.titleFragment) {
            finish();
        } else {
            nav.navigate(R.id.titleFragment);
        }

        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        onSupportNavigateUp();
    }
}