package com.example.lost_game.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ResultDao
{
    @Query("SELECT * FROM result ORDER BY time_score DESC")
    LiveData<List<Result>> getAllResults();

    @Query("SELECT * FROM result WHERE resultid IN (:resultIds)")
    List<Result> loadAllByIds(int[] resultIds);

    @Query("SELECT * FROM result WHERE resultid = :id LIMIT 1")
    Result findById(int id);

    @Insert
    void insert(Result result);

    @Insert
    void insertAll(Result... results);

    @Delete
    void delete(Result result);

    @Query("DELETE FROM Result")
    void deleteAll();

    @Query("DELETE FROM Result WHERE resultid NOT IN(SELECT resultid FROM (SELECT resultid FROM Result ORDER BY time_score DESC LIMIT 10))")
    void deleteAllBut10();
}