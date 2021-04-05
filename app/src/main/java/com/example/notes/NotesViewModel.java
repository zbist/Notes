package com.example.notes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class NotesViewModel extends ViewModel {

    private final NotesRepository repository = FirestoreNotesRepository.INSTANCE;
    private final MutableLiveData<List<Note>> notesLiveData = new MutableLiveData<>();

    public void fetchNotes(){
        repository.getNotes(new Callback<List<Note>>() {
            @Override
            public void onResult(List<Note> value) {
                notesLiveData.postValue(value);
            }
        });
    }

    public LiveData<List<Note>> getNotesLiveData(){
        return notesLiveData;
    }

    public void delete(int position){

        repository.deleteNote(position, new Callback<Object>() {
            @Override
            public void onResult(Object value) {
                fetchNotes();
            }
        });

    }

    public void addNote(Note note){
        repository.addNote(new Callback<Note>() {
            @Override
            public void onResult(Note value) {

            }
        }, note);
    }
}
