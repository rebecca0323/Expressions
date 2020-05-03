package com.example.expressions;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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
                        startActivity(new Intent(getApplicationContext(),
                                Statistics.class));
                        overridePendingTransition(0,0);
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

    public void zen(View view){
        Intent intent = new Intent(this, Song.class);
        Bundle bundle = new Bundle();
        String songName = "Zen";
        bundle.putString("Name", songName);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void peace(View view){
        Intent intent = new Intent(this, Song.class);
        Bundle bundle = new Bundle();
        String songName = "Peace";
        bundle.putString("Name", songName);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void serenity(View view){
        Intent intent = new Intent(this, Song.class);
        Bundle bundle = new Bundle();
        String songName = "Serenity";
        bundle.putString("Name", songName);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void meditate(View view){
        Intent intent = new Intent(this, Song.class);
        Bundle bundle = new Bundle();
        String songName = "Meditate";
        bundle.putString("Name", songName);
        intent.putExtras(bundle);
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
