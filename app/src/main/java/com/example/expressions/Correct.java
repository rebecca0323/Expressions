package com.example.expressions;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Correct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correct);
    }

    public void CorrectNext(View view){
        Intent intent = new Intent(this, Game.class);
        startActivity(intent);
    }
}
