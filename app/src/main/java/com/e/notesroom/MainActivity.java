package com.e.notesroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Dao;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.security.PrivateKey;
import java.util.List;

import static com.e.notesroom.NotesDatabase.databaseWriteExecutor;
import static java.time.chrono.IsoChronology.INSTANCE;

public class MainActivity extends AppCompatActivity {
    private EditText Input;
    private Button btn;
    private RecyclerView recyclerView;
    private NotesViewModel mWordViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Input=findViewById(R.id.etInput);
        btn=findViewById(R.id.btninput);
        recyclerView=findViewById(R.id.recyclerview);
        final NotesAdapter adapter = new NotesAdapter(new NotesAdapter.WordDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mWordViewModel=ViewModelProviders.of(this).get(NotesViewModel.class);
      //  mWordViewModel = new ViewModelProvider(MainActivity.this).get(NotesViewModel.class);


        mWordViewModel.getAllWords().observe( this, adapter::submitList);
        btn.setOnClickListener(view -> {
            String s=Input.getText().toString();
            Note note=new Note(s);
            mWordViewModel.insert(note);

        });

    }

}
