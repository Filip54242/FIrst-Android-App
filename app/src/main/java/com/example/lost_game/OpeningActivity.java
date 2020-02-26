package com.example.lost_game;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.InputStream;

public class OpeningActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        InputStream imageStream = this.getResources().openRawResource(R.raw.lost);
        Bitmap image = BitmapFactory.decodeStream(imageStream);
        ImageView view=findViewById(R.id.startImage);
        view.setImageBitmap(image);
        view.setScaleType(ImageView.ScaleType.FIT_XY);
        int secondsDelayed = 1;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(OpeningActivity.this, MenuActivity.class));
                finish();
            }
        }, secondsDelayed * 3000);
    }
}