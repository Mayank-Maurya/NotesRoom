package com.e.notesroom;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {
    private NotesRepository mRepository;

    private final LiveData<List<Note>> mAllWords;

    public NotesViewModel (Application application) {
        super(application);
        mRepository = new NotesRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    LiveData<List<Note>> getAllWords() { return mAllWords; }

    public void insert(Note word) { mRepository.insert(word); }

}
