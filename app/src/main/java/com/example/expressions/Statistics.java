package com.example.expressions;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Statistics extends AppCompatActivity {

    private BottomNavigationView bnav;
    private TextView first, second, third, fourth, total, correct, minutes;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        //Firebase auth
        mAuth = FirebaseAuth.getInstance();

        first = findViewById(R.id.first);
        second = findViewById(R.id.second);
        third = findViewById(R.id.third);
        fourth = findViewById(R.id.fourth);
        total = findViewById(R.id.total);
        correct = findViewById(R.id.correct);
        minutes = findViewById(R.id.minutes);

        bnav = findViewById(R.id.bottom_nav_bar);
        bnav.setSelectedItemId(R.id.settings);

        bnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.settings:
                        return true;
                    case R.id.game:
                        startActivity(new Intent(getApplicationContext(),
                                Congrats.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.music:
                        startActivity(new Intent(getApplicationContext(),
                                Playlist.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        loadInfoFromDatabase();
    }

    private void loadInfoFromDatabase() {
        user = mAuth.getCurrentUser();
        //String uid = user.getUid();
        String email = user.getEmail();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Users");

        Query query = databaseReference.orderByChild("email").equalTo(email);;
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    String f1 = ds.child("first").getValue().toString();
                    String s2 = ds.child("second").getValue().toString();
                    String t3 = ds.child("third").getValue().toString();
                    String f4 = ds.child("fourth").getValue().toString();
                    String tot = ds.child("total").getValue().toString();
                    String cor = ds.child("correctPercent").getValue().toString() + "%";
                    String min = ds.child("minutes").getValue().toString();

                    first.setText(f1);
                    second.setText(s2);
                    third.setText(t3);
                    fourth.setText(f4);
                    total.setText(tot);
                    correct.setText(cor);
                    minutes.setText(min);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
