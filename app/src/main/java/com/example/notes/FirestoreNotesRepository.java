package com.example.notes;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class FirestoreNotesRepository implements NotesRepository {

    private static final String FIRESTORE = "notes";

    public static final NotesRepository INSTANCE = new FirestoreNotesRepository();
    public static final String FIELD_TITLE = "title";
    public static final String FIELD_CONTEXT = "context";
    public static final String FIELD_DATA = "data";

    private final FirebaseFirestore fireStore = FirebaseFirestore.getInstance();

    private ArrayList<Note> result = new ArrayList<>();

    @Override
    public List<Note> getNotes(Callback<List<Note>> callback) {
        fireStore.collection(FIRESTORE).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        List<DocumentSnapshot> documents = task.getResult().getDocuments();
                        result.clear();
                        for (DocumentSnapshot doc : documents){
                            String id = doc.getId();
                            String title = doc.getString(FIELD_TITLE);
                            String context = doc.getString(FIELD_CONTEXT);
                            String date = doc.getString(FIELD_DATA);

                            result.add(new Note(id, title, context, date));
                        }
                        callback.onResult(result);
                    }
                });
        return null;
    }

    @Override
    public void addNote(Callback<Note> callback, Note note) {

        HashMap<String, Object> data = new HashMap<>();
        data.put(FIELD_TITLE, note.getName());
        data.put(FIELD_CONTEXT, note.getContent());
        data.put(FIELD_DATA, note.getDate());

        fireStore.collection(FIRESTORE)
                .add(data).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                note.setId(task.getResult().getId());
                callback.onResult(null);
            }
        });
    }

    @Override
    public void deleteNote(int number, Callback<Object> callback) {
        String idForDelete = result.get(number).getId();
        fireStore.collection(FIRESTORE).document(idForDelete)
                .delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                callback.onResult(new Object());
            }
        });
    }

    public Note getNote(int index){
        return result.get(index);
    }

    @Override
    public void updateNote(String id, String title,
                           String context, String date, Callback<Note> callback) {

        HashMap<String, Object> data = new HashMap<>();
        data.put(FIELD_TITLE, title);
        data.put(FIELD_CONTEXT, context);
        data.put(FIELD_DATA, date);

        fireStore.collection(FIRESTORE).document(id)
                .update(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                callback.onResult(null);
            }
        });
    }
}
