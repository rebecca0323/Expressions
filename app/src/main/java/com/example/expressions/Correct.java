package com.example.expressions;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Correct extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correct);
        mAuth = FirebaseAuth.getInstance();
    }

    public void CorrectNext(View view){
        Intent intent = new Intent(this, Game.class);
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
