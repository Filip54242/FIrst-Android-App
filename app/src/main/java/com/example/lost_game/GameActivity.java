package com.example.lost_game;

import android.arch.lifecycle.ViewModelProviders;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lost_game.database.Result;
import com.example.lost_game.database.ResultViewModel;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private TextView textView;
    private TextView textViewScore;
    CountDownTimer timer;
    private ResultViewModel mResultViewModel;
    private int score;
    private ImageView linstenerView;
    private MediaPlayer themeSongPlayer;
    private ImageRandomiser randomiser;
    private ImageView backround;
    private ImageView[][] changingImages;
    private ImageContainer currentMatrix;
    private Random rand;
    private String playerName;
    private LayoutInflater li;
    private View layout;
    private Boolean sound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.game_window);
        randomiser = new ImageRandomiser();
        rand = new Random();
        li = getLayoutInflater();

        playerName = getIntent().getStringExtra("name");
        sound = getIntent().getBooleanExtra("sound", true);
        chooseBackround();
        chooseSong();
        initChangingImages();
        startGame();
    }

    @Override
    public void onDestroy() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy, HH:mm:ss");
        String currentDateAndTime = sdf.format(new Date());

        Result result = new Result(playerName, currentDateAndTime, score);
        mResultViewModel.insert(result);
        super.onDestroy();
        themeSongPlayer.stop();
    }

    @Override
    public void onPause() {
        super.onPause();
        themeSongPlayer.stop();
    }




    private void initChangingImages() {
        changingImages = new ImageView[6][6];
        changingImages[0][0] = findViewById(R.id.bird1);
        changingImages[0][1] = findViewById(R.id.bird2);
        changingImages[0][2] = findViewById(R.id.bird3);
        changingImages[0][3] = findViewById(R.id.bird4);
        changingImages[0][4] = findViewById(R.id.bird5);
        changingImages[0][5] = findViewById(R.id.bird6);
        changingImages[1][0] = findViewById(R.id.bird7);
        changingImages[1][1] = findViewById(R.id.bird8);
        changingImages[1][2] = findViewById(R.id.bird9);
        changingImages[1][3] = findViewById(R.id.bird10);
        changingImages[1][4] = findViewById(R.id.bird11);
        changingImages[1][5] = findViewById(R.id.bird12);
        changingImages[2][0] = findViewById(R.id.bird13);
        changingImages[2][1] = findViewById(R.id.bird14);
        changingImages[2][2] = findViewById(R.id.bird15);
        changingImages[2][3] = findViewById(R.id.bird16);
        changingImages[2][4] = findViewById(R.id.bird17);
        changingImages[2][5] = findViewById(R.id.bird18);
        changingImages[3][0] = findViewById(R.id.bird19);
        changingImages[3][1] = findViewById(R.id.bird20);
        changingImages[3][2] = findViewById(R.id.bird21);
        changingImages[3][3] = findViewById(R.id.bird22);
        changingImages[3][4] = findViewById(R.id.bird23);
        changingImages[3][5] = findViewById(R.id.bird24);
        changingImages[4][0] = findViewById(R.id.bird25);
        changingImages[4][1] = findViewById(R.id.bird26);
        changingImages[4][2] = findViewById(R.id.bird27);
        changingImages[4][3] = findViewById(R.id.bird28);
        changingImages[4][4] = findViewById(R.id.bird29);
        changingImages[4][5] = findViewById(R.id.bird30);
        changingImages[5][0] = findViewById(R.id.bird31);
        changingImages[5][1] = findViewById(R.id.bird32);
        changingImages[5][2] = findViewById(R.id.bird33);
        changingImages[5][3] = findViewById(R.id.bird34);
        changingImages[5][4] = findViewById(R.id.bird35);
        changingImages[5][5] = findViewById(R.id.bird36);
    }

    public void chooseBackround() {
        backround = findViewById(R.id.backroundImage);
        int backgroundNumber = rand.nextInt(4) + 1;
        InputStream imageStream = null;
        switch (backgroundNumber) {
            case 1:
                imageStream = this.getResources().openRawResource(R.raw.backgroud1);
                break;
            case 2:
                imageStream = this.getResources().openRawResource(R.raw.backgroud2);
                break;
            case 3:
                imageStream = this.getResources().openRawResource(R.raw.backgroud3);
                break;
            case 4:
                imageStream = this.getResources().openRawResource(R.raw.backgroud4);
                break;
        }

        Bitmap image = BitmapFactory.decodeStream(imageStream);

        backround.setImageBitmap(image);
        backround.setScaleType(ImageView.ScaleType.FIT_XY);

    }


    private void configureImageViewMatrix() {
        InputStream imageStream = null;
        randomiser.generateMatrix();
        currentMatrix = randomiser.container;
        Bitmap image = null;
        for (int row = 0; row < 6; row++) {
            for (int column = 0; column < 6; column++) {
                switch (currentMatrix.matrix[row][column]) {
                    case "RIGHT,BLUE":
                        imageStream = this.getResources().openRawResource(R.raw.bird42);
                        image = BitmapFactory.decodeStream(imageStream);
                        changingImages[row][column].setImageBitmap(image);
                        break;
                    case "RIGHT,BLACK":
                        imageStream = this.getResources().openRawResource(R.raw.bird12);
                        image = BitmapFactory.decodeStream(imageStream);
                        changingImages[row][column].setImageBitmap(image);
                        break;
                    case "RIGHT,RED":
                        imageStream = this.getResources().openRawResource(R.raw.bird22);
                        image = BitmapFactory.decodeStream(imageStream);
                        changingImages[row][column].setImageBitmap(image);
                        break;
                    case "RIGHT,YELLOW":
                        imageStream = this.getResources().openRawResource(R.raw.bird52);
                        image = BitmapFactory.decodeStream(imageStream);
                        changingImages[row][column].setImageBitmap(image);
                        break;
                    case "RIGHT,LIGHTBLUE":
                        imageStream = this.getResources().openRawResource(R.raw.bird32);
                        image = BitmapFactory.decodeStream(imageStream);
                        changingImages[row][column].setImageBitmap(image);
                        break;
                    case "RIGHT,GREEN":
                        imageStream = this.getResources().openRawResource(R.raw.bird62);
                        image = BitmapFactory.decodeStream(imageStream);
                        changingImages[row][column].setImageBitmap(image);
                        break;
                    case "LEFT,BLUE":
                        imageStream = this.getResources().openRawResource(R.raw.bird44);
                        image = BitmapFactory.decodeStream(imageStream);
                        changingImages[row][column].setImageBitmap(image);
                        break;
                    case "LEFT,BLACK":
                        imageStream = this.getResources().openRawResource(R.raw.bird14);
                        image = BitmapFactory.decodeStream(imageStream);
                        changingImages[row][column].setImageBitmap(image);
                        break;
                    case "LEFT,RED":
                        imageStream = this.getResources().openRawResource(R.raw.bird24);
                        image = BitmapFactory.decodeStream(imageStream);
                        changingImages[row][column].setImageBitmap(image);
                        break;
                    case "LEFT,YELLOW":
                        imageStream = this.getResources().openRawResource(R.raw.bird54);
                        image = BitmapFactory.decodeStream(imageStream);
                        changingImages[row][column].setImageBitmap(image);
                        break;
                    case "LEFT,LIGHTBLUE":
                        imageStream = this.getResources().openRawResource(R.raw.bird34);
                        image = BitmapFactory.decodeStream(imageStream);
                        changingImages[row][column].setImageBitmap(image);
                        break;
                    case "LEFT,GREEN":
                        imageStream = this.getResources().openRawResource(R.raw.bird64);
                        image = BitmapFactory.decodeStream(imageStream);
                        changingImages[row][column].setImageBitmap(image);
                        break;
                    case "TOP,BLUE":
                        imageStream = this.getResources().openRawResource(R.raw.bird41);
                        image = BitmapFactory.decodeStream(imageStream);
                        changingImages[row][column].setImageBitmap(image);
                        break;
                    case "TOP,BLACK":
                        imageStream = this.getResources().openRawResource(R.raw.bird11);
                        image = BitmapFactory.decodeStream(imageStream);
                        changingImages[row][column].setImageBitmap(image);
                        break;
                    case "TOP,RED":
                        imageStream = this.getResources().openRawResource(R.raw.bird21);
                        image = BitmapFactory.decodeStream(imageStream);
                        changingImages[row][column].setImageBitmap(image);
                        break;
                    case "TOP,YELLOW":
                        imageStream = this.getResources().openRawResource(R.raw.bird51);
                        image = BitmapFactory.decodeStream(imageStream);
                        changingImages[row][column].setImageBitmap(image);
                        break;
                    case "TOP,LIGHTBLUE":
                        imageStream = this.getResources().openRawResource(R.raw.bird31);
                        image = BitmapFactory.decodeStream(imageStream);
                        changingImages[row][column].setImageBitmap(image);
                        break;
                    case "TOP,GREEN":
                        imageStream = this.getResources().openRawResource(R.raw.bird61);
                        image = BitmapFactory.decodeStream(imageStream);
                        changingImages[row][column].setImageBitmap(image);
                        break;
                    case "BOTTOM,BLUE":
                        imageStream = this.getResources().openRawResource(R.raw.bird43);
                        image = BitmapFactory.decodeStream(imageStream);
                        changingImages[row][column].setImageBitmap(image);
                        break;
                    case "BOTTOM,BLACK":
                        imageStream = this.getResources().openRawResource(R.raw.bird13);
                        image = BitmapFactory.decodeStream(imageStream);
                        changingImages[row][column].setImageBitmap(image);
                        break;
                    case "BOTTOM,RED":
                        imageStream = this.getResources().openRawResource(R.raw.bird23);
                        image = BitmapFactory.decodeStream(imageStream);
                        changingImages[row][column].setImageBitmap(image);
                        break;
                    case "BOTTOM,YELLOW":
                        imageStream = this.getResources().openRawResource(R.raw.bird53);
                        image = BitmapFactory.decodeStream(imageStream);
                        changingImages[row][column].setImageBitmap(image);
                        break;
                    case "BOTTOM,LIGHTBLUE":
                        imageStream = this.getResources().openRawResource(R.raw.bird33);
                        image = BitmapFactory.decodeStream(imageStream);
                        changingImages[row][column].setImageBitmap(image);
                        break;
                    case "BOTTOM,GREEN":
                        imageStream = this.getResources().openRawResource(R.raw.bird63);
                        image = BitmapFactory.decodeStream(imageStream);
                        changingImages[row][column].setImageBitmap(image);
                        break;
                    case "EMPTY":
                        changingImages[row][column].setImageResource(android.R.color.transparent);
                        break;

                }
            }
        }
    }

    private void checkCurrentAndGoNext(String move) {
        if (move.equals(currentMatrix.correctMove)) {
            increaseScore(100);
            layout = li.inflate(R.layout.correct_toast, (ViewGroup) findViewById(R.id.custom_toast_layout));
            Toast nice = new Toast(getApplicationContext());
            nice.setDuration(Toast.LENGTH_SHORT);
            nice.setView(layout);
            nice.show();
            CD(nice);

        } else {
            increaseScore(-50);
            layout = li.inflate(R.layout.wrong_toast, (ViewGroup) findViewById(R.id.custom_toast_layout));
            Toast tooBad = new Toast(getApplicationContext());
            tooBad.setDuration(Toast.LENGTH_SHORT);
            tooBad.setView(layout);
            tooBad.show();
            CD(tooBad);
        }
        configureImageViewMatrix();
    }

    private void increaseScore(int amount) {
        score += amount;
        textViewScore.setText(getResources().getString(R.string.score)+": " + score);
    }

    public void chooseSong()
    {
        int themeSongNumber = rand.nextInt(6)+1;
        switch (themeSongNumber)
        {
            case 1:
                themeSongPlayer = MediaPlayer.create(GameActivity.this, R.raw.theme);
                break;

            case 2:
                themeSongPlayer = MediaPlayer.create(GameActivity.this, R.raw.theme2);
                break;
            case 3:
                themeSongPlayer = MediaPlayer.create(GameActivity.this, R.raw.theme3);
                break;
            case 4:
                themeSongPlayer = MediaPlayer.create(GameActivity.this, R.raw.theme4);
                break;
            case 5:
                themeSongPlayer = MediaPlayer.create(GameActivity.this, R.raw.theme5);
                break;
            case 6:
                themeSongPlayer = MediaPlayer.create(GameActivity.this, R.raw.theme6);
                break;

        }

    }

    public void startGame() {

        textView = findViewById(R.id.countDownTextV);
        textViewScore = findViewById(R.id.scoreTextV);

        score = 0;
        textViewScore.setText(getResources().getString(R.string.score)+": " + score);

        mResultViewModel = ViewModelProviders.of(this).get(ResultViewModel.class);
        if (sound) {
            themeSongPlayer.start();
        }
        // START COUNT DOWN
        startCountDown();
        configureImageViewMatrix();
        InitListener();

    }

    private void InitListener() {
        linstenerView = findViewById(R.id.swipeOn);

        linstenerView.setOnTouchListener(new OnSwipeTouchListener(GameActivity.this) {
            public void onSwipeTop() {
                checkCurrentAndGoNext("TOP");
            }
            public void onSwipeRight() {
                checkCurrentAndGoNext("RIGHT");
            }
            public void onSwipeLeft() {
                checkCurrentAndGoNext("LEFT");
            }
            public void onSwipeBottom() {
                checkCurrentAndGoNext("BOTTOM");
            }
        });
    }

    ///////////////////////
    // Count-Down
    //
    public void startCountDown() {
        timer = new CountDownTimer(45000, 1000) {

            public void onTick(long millisUntilFinished) {
                textView.setText(getResources().getString(R.string.time_remaining)+": " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                //modifies Stats
                linstenerView.setOnTouchListener(null);

                // game_window close
                themeSongPlayer.stop();
                GameActivity.this.finish();
            }
        }.start();
    }

    public void CD(final Toast toast) {
        timer = new CountDownTimer(600, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                toast.cancel();
            }
        }.start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }


}
