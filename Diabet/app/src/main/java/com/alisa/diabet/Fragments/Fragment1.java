package com.alisa.diabet.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.alisa.diabet.Adapters.NoteAdapter;
import com.alisa.diabet.Model.Note;
import com.alisa.diabet.Model.NoteViewModel;
import com.alisa.diabet.NewNoteActivity;
import com.alisa.diabet.R;

import java.util.List;

import static android.app.Activity.RESULT_OK;

public class Fragment1 extends Fragment {

    public static final int ADD_NOTE_REQUEST = 1;
    public static final int EDIT_NOTE_REQUEST = 2;

    private NoteViewModel noteViewModel;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment1, null);
        FloatingActionButton buttonAddNote = v.findViewById(R.id.add);
        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewNoteActivity.class);
                //startActivityForResult(intent, ADD_NOTE_REQUEST);
                startActivityForResult(intent, ADD_NOTE_REQUEST);
            }
        });
        RecyclerView recyclerView = v.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        final NoteAdapter adapter = new NoteAdapter();
        recyclerView.setAdapter(adapter);

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                adapter.submitList(notes);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                noteViewModel.delete(adapter.getNoteAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getActivity(), "Запись удалена", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new NoteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Note note) {
                Intent intent = new Intent(getActivity(), NewNoteActivity.class);
                intent.putExtra(NewNoteActivity.EXTRA_ID, note.getId());
                intent.putExtra(NewNoteActivity.EXTRA_SUGAR, note.getSugar_level());
                intent.putExtra(NewNoteActivity.EXTRA_DESCRIPTION, note.getDescription());
                intent.putExtra(NewNoteActivity.EXTRA_PRIORITY, note.getPriority());
                startActivityForResult(intent, EDIT_NOTE_REQUEST);
            }
        });
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            String title = data.getStringExtra(NewNoteActivity.EXTRA_SUGAR);
            String description = data.getStringExtra(NewNoteActivity.EXTRA_DESCRIPTION);
            String date = data.getStringExtra(NewNoteActivity.EXTRA_DATA);
            String time = data.getStringExtra(NewNoteActivity.EXTRA_TIME);
            String eating = data.getStringExtra(NewNoteActivity.EXTRA_EATING);
            String ins_leng = data.getStringExtra(NewNoteActivity.EXTRA_INSULINLEN);
            String inj = data.getStringExtra(NewNoteActivity.EXTRA_INJECETED);
            String weight = data.getStringExtra(NewNoteActivity.EXTRA_WEIGHT);
            String bread = data.getStringExtra(NewNoteActivity.EXTRA_BREAD);
            String well = data.getStringExtra(NewNoteActivity.EXTRA_WELLBEING);
            int priority = data.getIntExtra(NewNoteActivity.EXTRA_PRIORITY, 1);

            Note note = new Note(title, description, date, time, eating, ins_leng, inj, weight,
                    bread, well, priority);
            noteViewModel.insert(note);

            Toast.makeText(getActivity(), "Запись сохранена", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_NOTE_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(NewNoteActivity.EXTRA_ID, -1);

            if (id == -1) {
                Toast.makeText(getActivity(), "Запись не удалось обновить", Toast.LENGTH_SHORT).show();
                return;
            }

            String title = data.getStringExtra(NewNoteActivity.EXTRA_SUGAR);
            String description = data.getStringExtra(NewNoteActivity.EXTRA_DESCRIPTION);
            String date = data.getStringExtra(NewNoteActivity.EXTRA_DATA);
            String time = data.getStringExtra(NewNoteActivity.EXTRA_TIME);
            String eating = data.getStringExtra(NewNoteActivity.EXTRA_EATING);
            String ins_leng = data.getStringExtra(NewNoteActivity.EXTRA_INSULINLEN);
            String inj = data.getStringExtra(NewNoteActivity.EXTRA_INJECETED);
            String weight = data.getStringExtra(NewNoteActivity.EXTRA_WEIGHT);
            String bread = data.getStringExtra(NewNoteActivity.EXTRA_BREAD);
            String well = data.getStringExtra(NewNoteActivity.EXTRA_WELLBEING);
            int priority = data.getIntExtra(NewNoteActivity.EXTRA_PRIORITY, 1);

            Note note = new Note(title, description, date, time, eating, ins_leng, inj, weight,
                    bread, well, priority);
            note.setId(id);
            noteViewModel.update(note);

            //  Toast.makeText(getActivity(), "Запись обновлена", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Запись не удалось сохранить", Toast.LENGTH_SHORT).show();
        }
    }

}

