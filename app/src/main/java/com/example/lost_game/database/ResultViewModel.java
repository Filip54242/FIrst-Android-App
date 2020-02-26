package com.example.lost_game.database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

// not a replacement of onSaveInstanceState() because it doesn't
// survive to process shutdown

public class ResultViewModel extends AndroidViewModel
{

    private ResultRepository mRepository;

    private LiveData<List<Result>> mAllResults;

    public ResultViewModel(Application application)
    {
        super(application);

        mRepository = new ResultRepository(application);
        mAllResults = mRepository.getAllResults();
    }

    public LiveData<List<Result>> getAllResults()
    {
        return mAllResults;
    }

    public void insert(Result result)
    {
        mRepository.insert(result);
    }

    public void emptyAllLeaderboard2()
    {
        mRepository.emptyAllLeaderboard1();
    }
    public void deleteAllButTen()
    {
        mRepository.leaveTen();
    }


}

