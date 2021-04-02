package com.example.notes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.util.Date;

public class ShowNoteFragment extends Fragment {

    public static final String ARG_INDEX = "index";
    private int index;
    private NotesRepository repository;
    private Note note;
    private MaterialTextView dateView;
    private TextInputEditText nameView;
    private TextInputEditText textView;
    private MaterialButton saveButton;

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
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_INDEX);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_note, container, false);
        init(view);
        if (index == -1) {
            setAddListener();
            return view;
        }
        initNote();
        setSaveListener();
        return view;
    }

    private void init(View view){
        repository = NotesRepository.INSTANCE;
        dateView = view.findViewById(R.id.date_note);
        nameView = view.findViewById(R.id.name_note_input);
        textView = view.findViewById(R.id.text_note_input);
        saveButton = view.findViewById(R.id.save_note);
    }

    private void initNote(){
        note = repository.getNotes().get(index);
        dateView.append(note.getDate());
        nameView.setText(note.getName());
        textView.setText(note.getContent());
    }

    private void setAddListener(){
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repository.addNote(new Note(nameView.getText().toString(),
                        textView.getText().toString(), new Date()));
            }
        });
    }

    private void setSaveListener(){
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repository.getNotes().set(index, new Note(nameView.getText().toString(),
                        textView.getText().toString(), new Date()));
            }
        });
    }

}