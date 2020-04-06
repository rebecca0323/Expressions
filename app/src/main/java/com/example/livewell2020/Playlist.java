package com.example.livewell2020;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Playlist extends AppCompatActivity {

    private BottomNavigationView bnav;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        bnav = findViewById(R.id.bottom_nav_bar);
        bnav.setSelectedItemId(R.id.music);
        mAuth = FirebaseAuth.getInstance();

        bnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.settings:
                        LogOut();
                        return true;
                    case R.id.game:
                        startActivity(new Intent(getApplicationContext(),
                                Instructions.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.music:
                        return true;
                }
                return false;
            }
        });
    }

    private void LogOut() {
        mAuth.getInstance().signOut();
        finish();
        startActivity(new Intent(this, Splash.class));
    }

    public void zen(View view){
        Intent intent = new Intent(this, Song.class);
        Bundle bundle = new Bundle();
        //String songURL = "https://firebasestorage.googleapis.com/v0/b/livewell2020-10b52.appspot.com/o/bensound-relaxing.mp3?alt=media&token=54a3197f-8695-4e96-8d57-57a121659e42";
        String songName = "Zen";
        //bundle.putString("URL", songURL);
        bundle.putString("Name", songName);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void meditate(View view){
        Intent intent = new Intent(this, Song.class);
        Bundle bundle = new Bundle();
        //String songURL = "https://firebasestorage.googleapis.com/v0/b/livewell2020-10b52.appspot.com/o/meditation.mp3?alt=media&token=f17388bc-f2f9-40c5-8883-65089ba52080";
        String songName = "Meditate";
        //bundle.putString("URL", songURL);
        bundle.putString("Name", songName);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
