package com.example.notes;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainFragment extends Fragment {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.add_toolbar_menu, menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_node){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    private void init(View view){
        LinearLayout linearLayout = view.findViewById(R.id.liner_for_main_fragment);
        for (int i = 0; i < Notes.notes.size(); i++){
            TextView tv = new TextView(getContext());
            tv.setText(Notes.notes.get(i).getName() + "\n" + Notes.notes.get(i).getDate());
            tv.setTextSize(30);
            tv.setBackgroundColor(R.color.purple_500);
            linearLayout.addView(tv);
            int index = i;
            tv.setOnClickListener(v -> showNote(index));
        }
    }

    private void showNote(int index){
        ShowNoteFragment fragment = ShowNoteFragment.newInstance(index);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getChildFragmentManager().beginTransaction().replace(R.id.second_container, fragment).commit();
        } else {
        getChildFragmentManager().beginTransaction()
                .replace(R.id.first_container, fragment).addToBackStack(null).commit();
        }
    }
}