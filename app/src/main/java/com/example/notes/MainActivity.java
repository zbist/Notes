package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            ShowNoteFragment fragment = ShowNoteFragment.newInstance(-1);
            getSupportFragmentManager().beginTransaction().replace(R.id.second_container, fragment).commit();
        }
        initToolbar();
        initBottomMenu();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.first_container, new MainFragment()).commit();
        }
    }

    private void initToolbar(){
        setSupportActionBar(findViewById(R.id.toolbar));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.setting:
                openFragment(new SettingsFragment());
                return true;
            case R.id.about_app:
                openFragment(new AboutFragment());
                return true;
        }
        return false;
    }

    private void initBottomMenu(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                item -> {
                    int id = item.getItemId();
                    switch (id){
                        case (R.id.setting):
                            openFragment(new SettingsFragment());
                            return true;
                        case (R.id.about_app):
                            openFragment(new AboutFragment());
                            return true;
                        case (R.id.main):
                            openFragment(new MainFragment());
                            return true;
                    }
                    return false;
                });
    }

    private void openFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.first_container, fragment).commit();
    }
}