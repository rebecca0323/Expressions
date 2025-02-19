package com.example.expressions;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;

public class Song extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private SeekBar seekbar;
    private Runnable runnable;
    private Handler handler;
    private TextView songname;
    private VideoView videoView;
    private ImageView pause, play;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        songname = findViewById(R.id.song_name);
        seekbar = findViewById(R.id.song_progress);
        pause = findViewById(R.id.pause);
        play = findViewById(R.id.play);

        handler = new Handler();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String name = bundle.getString("Name");

        videoView = findViewById(R.id.imageView14);

        mAuth = FirebaseAuth.getInstance();

        String url = "https://firebasestorage.googleapis.com/v0/b/livewell2020-10b52.appspot.com/o/screen.mp4?alt=media&token=1b23660f-fe9f-4cd4-aaa2-f51ca87c3a35";
        Uri uri = Uri.parse(url);

        //Video Loop
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                videoView.start();
            }
        });

        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();

        songname.setText(name);

        mediaPlayer = new MediaPlayer();
        try{
            if(name.equals("Zen")){
                mediaPlayer.setDataSource("https://firebasestorage.googleapis.com/v0/b/livewell2020-10b52.appspot.com/o/bensound-relaxing.mp3?alt=media&token=54a3197f-8695-4e96-8d57-57a121659e42");
            }
            else if(name.equals("Peace")){
                mediaPlayer.setDataSource("https://firebasestorage.googleapis.com/v0/b/livewell2020-10b52.appspot.com/o/peace.mp3?alt=media&token=748e5408-2156-4d55-8ba5-c7281c0b4f25");
            }
            else if(name.equals("Serenity")){
                mediaPlayer.setDataSource("https://firebasestorage.googleapis.com/v0/b/livewell2020-10b52.appspot.com/o/serenity.mp3?alt=media&token=e4158ce7-46a5-4f35-a376-f5ed62559fb2");
            }
            else{
                mediaPlayer.setDataSource("https://firebasestorage.googleapis.com/v0/b/livewell2020-10b52.appspot.com/o/meditation.mp3?alt=media&token=f17388bc-f2f9-40c5-8883-65089ba52080");
            }
            //mediaPlayer.setDataSource("https://firebasestorage.googleapis.com/v0/b/livewell2020-10b52.appspot.com/o/bensound-relaxing.mp3?alt=media&token=54a3197f-8695-4e96-8d57-57a121659e42");
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    seekbar.setMax(mp.getDuration());
                    mp.start();
                    changeSeekbar();
                }
            });
            mediaPlayer.prepareAsync();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void changeSeekbar() {
        if(mediaPlayer != null){
            seekbar.setProgress(mediaPlayer.getCurrentPosition());

            if(mediaPlayer.isPlaying()){
                runnable = new Runnable(){

                    @Override
                    public void run() {
                        changeSeekbar();
                    }
                };

                handler.postDelayed(runnable, 1000);
            }
        }
    }

    public void pause(View view){
        if(!mediaPlayer.isPlaying()){
            mediaPlayer.start();
            changeSeekbar();
            pause.setVisibility(View.VISIBLE);
            play.setVisibility(View.INVISIBLE);
        }
        else{
            mediaPlayer.pause();
            pause.setVisibility(View.INVISIBLE);
            play.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if(mediaPlayer != null){
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
        }
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
