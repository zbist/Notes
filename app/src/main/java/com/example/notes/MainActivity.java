package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            ShowNoteFragment fragment = ShowNoteFragment.newInstance(0);
            getSupportFragmentManager().beginTransaction().replace(R.id.land_note, fragment).commit();
        }
        init();
    }

    private void init(){
        getSupportFragmentManager().beginTransaction().replace(R.id.main_activity, new MainFragment()).commit();
    }
}