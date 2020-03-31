package com.example.livewell2020;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class Song extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        mediaPlayer = new MediaPlayer();
        try{
            mediaPlayer.setDataSource("https://firebasestorage.googleapis.com/v0/b/livewell2020-10b52.appspot.com/o/bensound-relaxing.mp3?alt=media&token=54a3197f-8695-4e96-8d57-57a121659e42");
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                    Toast.makeText(Song.this, "Song is playing", Toast.LENGTH_SHORT).show();
                }
            });
            mediaPlayer.prepareAsync();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
