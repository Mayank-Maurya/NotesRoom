package com.e.notesroom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.NoSuchElementException;

public class NotesViewHolder extends RecyclerView.ViewHolder {

    private final TextView NotesTextView;

    private NotesViewHolder(View itemView)
    {
        super(itemView);
        NotesTextView=itemView.findViewById(R.id.recylertv);
    }

    public void bind(String text)
    {
        NotesTextView.setText(text);
    }

    static NotesViewHolder create(ViewGroup parent)
    {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item,parent,false);
        return new NotesViewHolder(view);
    }
}
