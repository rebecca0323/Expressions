package com.example.livewell2020;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class Instructions extends AppCompatActivity {

    private ViewPager mSlideViewPager;
    private SlideAdapter slideAdapter;
    private ImageView finish;

    private int mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        mSlideViewPager = findViewById(R.id.slider);
        finish = findViewById(R.id.finish);

        slideAdapter = new SlideAdapter(this);

        mSlideViewPager.setAdapter(slideAdapter);
        mSlideViewPager.addOnPageChangeListener(viewListener);
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            mCurrentPage = position;

            if(mCurrentPage == 2){
                finish.setVisibility(View.VISIBLE);
            }
            else{
                finish.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public void playGame(View view){
        Intent intent = new Intent(this, Game3.class);
        startActivity(intent);
    }
}
