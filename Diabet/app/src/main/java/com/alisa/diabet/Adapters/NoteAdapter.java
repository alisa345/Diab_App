package com.alisa.diabet.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alisa.diabet.Model.Note;
import com.alisa.diabet.R;


public class NoteAdapter extends ListAdapter<Note, NoteAdapter.NoteHolder> {
    private OnItemClickListener listener;

    public NoteAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(Note oldItem, Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(Note oldItem, Note newItem) {
            return oldItem.getSugar_level().equals(newItem.getSugar_level()) &&
                    oldItem.getTime().equals(newItem.getTime()) &&
                    oldItem.getDate().equals(newItem.getDate()) &&
                    oldItem.getWellbeing().equals(newItem.getWellbeing()) &&
                    oldItem.getEating().equals(newItem.getEating());
        }
    };

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note currentNote = getItem(position);
        holder.textViewTitle.setText(currentNote.getSugar_level());
        holder.textViewTime.setText(currentNote.getTime());
        holder.textViewDate.setText(currentNote.getDate());
        holder.textViewWell.setText(currentNote.getWellbeing() + " самочувствие");
        holder.textViewEating.setText(currentNote.getEating());
    }

    public Note getNoteAt(int position) {
        return getItem(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewTime;
        private TextView textViewDate;
        private TextView textViewWell;
        private TextView textViewEating;

        public NoteHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_item_sugar_lvl);
            textViewTime = itemView.findViewById(R.id.text_view_item_time);
            textViewDate = itemView.findViewById(R.id.text_view_item_data);
            textViewWell = itemView.findViewById(R.id.text_view_item_well_being);
            textViewEating = itemView.findViewById(R.id.text_view_item_eating);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Note note);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}