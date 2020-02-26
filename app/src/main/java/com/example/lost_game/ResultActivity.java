package com.example.lost_game;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lost_game.database.AppDatabase;
import com.example.lost_game.database.Result;
import com.example.lost_game.database.ResultDao;
import com.example.lost_game.database.ResultViewModel;

import java.io.InputStream;
import java.util.List;

public class ResultActivity extends AppCompatActivity
{

    private ResultViewModel mResultViewModel;
    private ResultDao resultDao;
    private Button resultClear;
    private Button resultLeaveTen;

    // NEED TO BE 1. FROM HIGHEST SCORE TO LOWEST SORTED

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        InputStream imageStream = this.getResources().openRawResource(R.raw.stats);
        Bitmap image = BitmapFactory.decodeStream(imageStream);
        ImageView view = findViewById(R.id.blackboardImage);
        resultClear=findViewById(R.id.btnLeaderboard);
        resultLeaveTen=findViewById(R.id.keepTen);
        resultClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread queryThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mResultViewModel.emptyAllLeaderboard2();
                    }
                });
                queryThread.start();
            }
        });
        resultLeaveTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread queryThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mResultViewModel.deleteAllButTen();
                    }
                });
                queryThread.start();
            }
        });

        view.setImageBitmap(image);
        view.setScaleType(ImageView.ScaleType.FIT_XY);
        final ResultListAdapter adapter = new ResultListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mResultViewModel = ViewModelProviders.of(this).get(ResultViewModel.class);

        mResultViewModel.getAllResults().observe(this, new Observer<List<Result>>()
        {
            @Override
            public void onChanged(@Nullable final List<Result> results)
            {
                // Update the cached copy of the results in the adapter.
                adapter.setResults(results);
            }
        });


    }

}
