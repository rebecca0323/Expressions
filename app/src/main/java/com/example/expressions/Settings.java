package com.example.expressions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void toStatistics(View view){
        Intent intent = new Intent(this, Statistics.class);
        startActivity(intent);
    }

    public void toMusic(View view){
        Intent intent = new Intent(this, Playlist.class);
        startActivity(intent);
    }

    public void toGame(View view){
        Intent intent = new Intent(this, Game.class);
        startActivity(intent);
    }

}
