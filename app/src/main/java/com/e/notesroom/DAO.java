package com.e.notesroom;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Note note);

    @Query("DELETE FROM Notes_table")
    void DeleteAll();

    @Query("SELECT * FROM Notes_table ORDER BY Note ASC ")
    LiveData<List<Note>> getAlphabetizedWords();
}
