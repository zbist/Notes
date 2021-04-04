package com.example.notes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private List<Note> items = new ArrayList<>();

    private OnNoteClicked noteClicked;
    private OnNoteLongClicked noteLongClicked;
    private Fragment fragment;

    public NotesAdapter(Fragment fragment) {
        this.fragment = fragment;
    }

    public void addItems(List<Note> items){
        clear();
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
        void onNoteClick(Note note, int position);
    }

    interface OnNoteLongClicked{
        void onNoteLongClick(View view, int position);
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

            fragment.registerForContextMenu(itemView);

            title = itemView.findViewById(R.id.title);
            data = itemView.findViewById(R.id.date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (noteClicked != null){
                        noteClicked.onNoteClick(items.get(getAdapterPosition()), getAdapterPosition());
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (noteLongClicked != null){
                        noteLongClicked.onNoteLongClick(itemView, getAdapterPosition());
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
