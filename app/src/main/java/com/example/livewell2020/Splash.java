package com.example.livewell2020;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.homevideo);

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
    }

    public void toLogin(View view){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public void toRegister(View view){
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }
}
