package com.example.notes;

import java.util.List;

public interface NotesRepository {
    List<Note> getNotes(Callback<List<Note>> callback);
    void addNote(Callback<Note> callback, Note note);
    void deleteNote(int number, Callback<Object> callback);
    Note getNote(int index);
    void updateNote(String id, String title, String context, String date, Callback<Note> callback);
}
