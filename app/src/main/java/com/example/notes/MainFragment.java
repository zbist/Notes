package com.example.notes;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Date;

public class MainFragment extends Fragment {
    private final String SaveLastIndex = "SAVE_LAST_INDEX";
    private int lastIndex;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            new Notes("1st note", "qnggniqjpijpjkjkgq! qfwg.", new Date());
            new Notes("2st note", "qnggniqjpijpjkjkgq! whrrdfk.", new Date());
            new Notes("3st note", "0000qjpijpjkjkgq! qfwg.", new Date());
            new Notes("4st note", "12342151jkgq! qfwg.", new Date());
            init(view);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(SaveLastIndex, lastIndex);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
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
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            ShowNoteFragment fragment = ShowNoteFragment.newInstance(index);
            getFragmentManager().beginTransaction().replace(R.id.land_note, fragment).commit();
        } else {
        ShowNoteFragment fragment = ShowNoteFragment.newInstance(index);
        getFragmentManager().beginTransaction().replace(R.id.main_activity, fragment).addToBackStack(null).commit();
        }
    }
}