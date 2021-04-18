package com.example.notes;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;


public class MainFragment extends Fragment {

    private NotesViewModel notesViewModel;
    private NotesAdapter adapter;
    private int positionOfNote;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);
        notesViewModel.fetchNotes();
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new NotesAdapter(this);
        adapter.setNoteClicked(new NotesAdapter.OnNoteClicked() {
            @Override
            public void onNoteClick(Note note, int position) {
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.second_container, ShowNoteFragment.newInstance(position))
                            .addToBackStack(null).commit();
                } else {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.first_container, ShowNoteFragment.newInstance(position))
                            .addToBackStack(null).commit();
                }
            }
        });

        adapter.setNoteLongClicked(new NotesAdapter.OnNoteLongClicked() {
            @Override
            public void onNoteLongClick(View view, int position) {
                positionOfNote = position;
                view.showContextMenu();
            }
        });
        RecyclerView recyclerView = view.findViewById(R.id.recycleView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));


        notesViewModel.getNotesLiveData().observe(getViewLifecycleOwner(), new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                adapter.addItems(notes);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.add_toolbar_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_node) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.second_container, ShowNoteFragment.newInstance(-1))
                        .addToBackStack(null).commit();
            } else {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.first_container, ShowNoteFragment.newInstance(-1))
                        .addToBackStack(null).commit();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        requireActivity().getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @SuppressLint("ResourceType")
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.context_delete) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle(R.string.title_for_dialog)
                    .setMessage(R.string.message_for_dialog)
                    .setCancelable(false)
                    .setPositiveButton(R.string.positive_for_dialog, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            notesViewModel.delete(positionOfNote);
                        }
                    }).setNegativeButton(R.string.cancel_for_dialog, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).create().show();

            return true;
        }
        return super.onContextItemSelected(item);
    }
}