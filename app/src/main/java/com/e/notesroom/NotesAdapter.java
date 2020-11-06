package com.e.notesroom;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class NotesAdapter extends ListAdapter<Note,NotesViewHolder> {

    public NotesAdapter(@NonNull DiffUtil.ItemCallback<Note> diffCallback) {
        super(diffCallback);
    }

    @Override
    public NotesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return NotesViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(NotesViewHolder holder, int position) {
        Note current = getItem(position);
        holder.bind(current.getNote());
    }

    static class WordDiff extends DiffUtil.ItemCallback<Note> {

        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getNote().equals(newItem.getNote());
        }
    }


}
