package com.example.livewell2020;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Playlist extends AppCompatActivity {

    private BottomNavigationView bnav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
    }

    public void zen(View view){
        Intent intent = new Intent(this, Song.class);
        startActivity(intent);
    }

    public void meditate(View view){
        Intent intent = new Intent(this, Song.class);
        startActivity(intent);
    }
}
