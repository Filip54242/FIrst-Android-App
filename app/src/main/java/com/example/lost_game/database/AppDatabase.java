package com.example.lost_game.database;

// ROOM WITH A VIEW:
// https://codelabs.developers.google.com/codelabs/android-room-with-a-view/#0

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Result.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase
{

    public abstract ResultDao resultDao();

    //singleton part
    private static volatile AppDatabase INSTANCE;

    static AppDatabase getDatabase(final Context context)
    {
        if (INSTANCE == null)
        {
            synchronized (AppDatabase.class)
            {
                if (INSTANCE == null)
                {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "puzzleDB")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    //REPOPULATE THE DATABASE EVERY TIME THE APP IS RESTARTED
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback()
            {

                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db)
                {
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    // https://developer.android.com/reference/android/os/AsyncTask

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void>
    {

        private final ResultDao mDao;

        PopulateDbAsync(AppDatabase db)
        {
            mDao = db.resultDao();
        }

        @Override
        protected Void doInBackground(final Void... params)
        {
            //mDao.deleteAll();

            return null;
        }

    }
}
