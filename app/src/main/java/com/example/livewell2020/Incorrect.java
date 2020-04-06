package com.example.livewell2020;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Incorrect extends AppCompatActivity {

    private TextView answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incorrect);

        Intent intent = getIntent();
        String correct = intent.getStringExtra("Answer");
        answer = findViewById(R.id.answer);
        answer.setText(correct);
    }

    public void IncorrectNext(View view){
        Intent intent = new Intent(this, Game.class);
        startActivity(intent);
    }
}
