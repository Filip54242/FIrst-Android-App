package com.example.lost_game.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Result
{

    public Result(String name, String date, int timeScore)
    {
        this.name = name;
        this.date = date;
        this.timeScore = timeScore;
    }

    @PrimaryKey(autoGenerate = true)
    public int resultid;

    @ColumnInfo(name = "name")
    @NonNull
    public String name;

    @ColumnInfo(name = "date")
    public String date;

    @ColumnInfo(name = "time_score")
    public int timeScore;
}

