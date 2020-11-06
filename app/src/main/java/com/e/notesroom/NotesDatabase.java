package com.e.notesroom;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.time.chrono.IsoChronology.INSTANCE;

@Database(entities = {Note.class},version = 1,exportSchema = false)
public abstract class NotesDatabase extends RoomDatabase {

    public abstract DAO dao();
    private static volatile NotesDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static NotesDatabase getDatabase(final Context context)
    {
        if(INSTANCE==null)
        {
            synchronized (NotesDatabase.class)
            {
                if(INSTANCE==null)
                {
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),
                            NotesDatabase.class,"word_databse")
                            .addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
               DAO dao=INSTANCE.dao();
                dao.DeleteAll();


                Note note = new Note("Hello");
                dao.insert(note);

            });
        }
    };




}
