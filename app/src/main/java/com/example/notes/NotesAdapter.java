package com.example.notes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    private List<Note> items = new ArrayList<>();

    private OnNoteClicked noteClicked;
    private OnNoteLongClicked noteLongClicked;

    public void addItems(List<Note> items){
        this.items.addAll(items);
    }

    public void clear(){
        items.clear();
    }

    @NonNull
    @Override
    public NotesAdapter.NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.NoteViewHolder holder, int position) {
        Note item = items.get(position);

        holder.getTitle().setText(item.getName());
        holder.getData().setText(item.getDate());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    interface OnNoteClicked{
        void onNoteClick(Note note);
    }

    interface OnNoteLongClicked{
        void onNoteLongClick(Note note);
    }

    public void setNoteClicked(OnNoteClicked noteClicked) {
        this.noteClicked = noteClicked;
    }

    public void setNoteLongClicked(OnNoteLongClicked noteLongClicked) {
        this.noteLongClicked = noteLongClicked;
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder{

        private TextView title;
        private TextView data;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            data = itemView.findViewById(R.id.date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (noteClicked != null){
                        noteClicked.onNoteClick(items.get(getAdapterPosition()));
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (noteLongClicked != null){
                        noteLongClicked.onNoteLongClick(items.get(getAdapterPosition()));
                    }
                    return true;
                }
            });
        }

        public TextView getTitle() {
            return title;
        }

        public TextView getData() {
            return data;
        }
    }
}
