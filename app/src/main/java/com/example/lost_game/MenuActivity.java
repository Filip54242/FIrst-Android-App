package com.example.lost_game;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

public class MenuActivity extends AppCompatActivity {
private EditText textEdit;
private String name;
private ImageButton soundControl;
private Boolean sound;
private InputStream imageStream;
private Bitmap soundOnIcon;
private Bitmap soundOffIcon;

    private static final String NAME_KEY = "com.example.lost_game.name";
    private final static String DEFAULT_NAME = "Unknown";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        imageStream = this.getResources().openRawResource(R.raw.sound_on_round);
        soundOnIcon=BitmapFactory.decodeStream(imageStream);
        imageStream = this.getResources().openRawResource(R.raw.sound_off_round);
        soundOffIcon=BitmapFactory.decodeStream(imageStream);
        super.onCreate(savedInstanceState);
        sound=Boolean.TRUE;
        setContentView(R.layout.activity_menu);
        textEdit =findViewById(R.id.editText);
        name=textEdit.getText().toString();
        soundControl=findViewById(R.id.mute_unmute);
        soundControl.setImageBitmap(soundOnIcon);
        soundControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sound)
                {
                    soundControl.setImageBitmap(soundOffIcon);
                }
                else
                {
                    soundControl.setImageBitmap(soundOnIcon);
                }
                sound=!sound;
            }
        });
        TextWatcher listenToEdit=new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            name=s.toString();
            }
        };
        textEdit.addTextChangedListener(listenToEdit);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        InputStream imageStream = this.getResources().openRawResource(R.raw.menu);
        Bitmap image = BitmapFactory.decodeStream(imageStream);
        ImageView view = findViewById(R.id.blackboardImage);

        view.setImageBitmap(image);
        view.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    public void btnPlay_Click(View view)
    {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("sound",sound);
        startActivity(intent);
    }
    public void btnStats_Click(View view)
    {
        // StatesActivity.class
        Intent intent = new Intent(this, ResultActivity.class);
        startActivity(intent);
    }
    public void btnHelp_Click(View view)
    {
        Intent intent = new Intent(this, HelpActivity.class);
        startActivity(intent);
    }

}
