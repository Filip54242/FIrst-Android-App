package com.example.lost_game.database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class ResultRepository
{
    private ResultDao mResultDao;
    private LiveData<List<Result>> mAllResults;

    ResultRepository(Application application)
    {
        AppDatabase db = AppDatabase.getDatabase(application);
        mResultDao = db.resultDao();
        mAllResults = mResultDao.getAllResults();
    }

    LiveData<List<Result>> getAllResults()
    {
        return mAllResults;
    }

    public void insert(Result result)
    {
        new insertAsyncTask(mResultDao).execute(result);
    }

    private static class insertAsyncTask extends AsyncTask<Result, Void, Void>
    {

        private ResultDao mAsyncTaskDao;

        insertAsyncTask(ResultDao dao)
        {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Result... params)
        {
            mAsyncTaskDao.insert(params[0]);

            return null;
        }
    }
    public void leaveTen()
    {
        mResultDao.deleteAllBut10();
    }

    public void emptyAllLeaderboard1()
    {

        mResultDao.deleteAll();

    }
}
