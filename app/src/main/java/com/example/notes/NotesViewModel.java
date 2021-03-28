package com.example.notes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class NotesViewModel extends ViewModel {

    private final NotesRepository repository = NotesRepository.INSTANCE;
    private final MutableLiveData<List<Note>> notesLiveData = new MutableLiveData<>();

    public void fetchNotes(){
        notesLiveData.setValue(repository.getNotes());
    }

    public LiveData<List<Note>> getNotesLiveData(){
        return notesLiveData;
    }
}
