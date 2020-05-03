package com.example.expressions;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Congrats extends AppCompatActivity {

    private SoundPool soundPool;
    int sound;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congrats);

        mAuth = FirebaseAuth.getInstance();

        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_ACCESSIBILITY)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();
        soundPool = new SoundPool.Builder()
                .setMaxStreams(6)
                .setAudioAttributes(audioAttributes)
                .build();
        playSound();
    }

    private void playSound() {
        sound = soundPool.load(this, R.raw.congrats, 1);

        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                soundPool.play(sound, 1, 1, 0, 0, 1);
            }
        });
    }

    public void Level2(View view){
        Intent intent = new Intent(this, Level2.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(mAuth.getCurrentUser() == null){
            finish();
            Toast.makeText(this, "You are not signed in!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, Splash.class));
        }
    }
}
