package com.example.notes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

public class ShowNoteFragment extends Fragment {

    public static final String ARG_INDEX = "index";
    private int index;

    public static ShowNoteFragment newInstance(int index) {
        ShowNoteFragment fragment = new ShowNoteFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_INDEX, index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (  getArguments() != null) {
            index = getArguments().getInt(ARG_INDEX);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_note, container, false);
        if (index == -1) {
            return view;
        }
        Notes note = Notes.notes.get(index);
        MaterialTextView dateView = view.findViewById(R.id.date_note);
        dateView.append(note.getDate());
        TextInputEditText nameView = view.findViewById(R.id.name_note_input);
        nameView.setText(note.getName());
        TextInputEditText textView = view.findViewById(R.id.text_note_input);
        textView.setText(note.getContent());
        return view;
    }
}