package com.example.notes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NotesRepository {

    private List<Note> notes;

    public static final NotesRepository INSTANCE = new NotesRepository();

    private  NotesRepository(){
        notes = new ArrayList<>();
        notes.add(new Note("Note 1" , "some text", new Date()));
        notes.add(new Note("Note 2" , "some text", new Date()));
        notes.add(new Note("Note 3" , "some text", new Date()));
        notes.add(new Note("Note 4" , "some text", new Date()));
        notes.add(new Note("Note 5" , "some text", new Date()));
        notes.add(new Note("Note 6" , "some text", new Date()));
        notes.add(new Note("Note 7" , "some text", new Date()));
        notes.add(new Note("Note 8" , "some text", new Date()));
        notes.add(new Note("Note 9" , "some text", new Date()));
        notes.add(new Note("Note 10" , "some text", new Date()));
        notes.add(new Note("Note 11" , "some text", new Date()));
        notes.add(new Note("Note 12" , "some text", new Date()));
        notes.add(new Note("Note 13" , "some text", new Date()));
        notes.add(new Note("Note 14" , "some text", new Date()));
        notes.add(new Note("Note 15" , "some text", new Date()));
        notes.add(new Note("Note 16" , "some text", new Date()));
    }

    public List<Note> getNotes(){
        return notes;
    }

    public void addNote(Note note){
        notes.add(note);
    }

    public void clearNotes(){
        notes.clear();
    }

    public void deleteNote(int number){
        notes.remove(number);
    }
}
