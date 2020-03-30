package com.example.livewell2020;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
