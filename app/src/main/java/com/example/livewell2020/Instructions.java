package com.example.livewell2020;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.SoundPool;
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

    private SoundPool soundPool;
    int sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        mSlideViewPager = findViewById(R.id.slider);
        finish = findViewById(R.id.finish);

        slideAdapter = new SlideAdapter(this);

        mSlideViewPager.setAdapter(slideAdapter);
        mSlideViewPager.addOnPageChangeListener(viewListener);

        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        soundPool = new SoundPool.Builder()
                .setMaxStreams(6)
                .setAudioAttributes(audioAttributes)
                .build();

        sound = soundPool.load(getApplicationContext(), R.raw.step1, 1);

        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                soundPool.play(sound, 1, 1, 0, 0, 1);
            }
        });
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

                sound = soundPool.load(getApplicationContext(), R.raw.step3, 1);

                soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                    @Override
                    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                        soundPool.play(sound, 1, 1, 0, 0, 1);
                    }
                });
            }
            else if(mCurrentPage == 0){
                sound = soundPool.load(getApplicationContext(), R.raw.step1, 1);

                soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                    @Override
                    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                        soundPool.play(sound, 1, 1, 0, 0, 1);
                    }
                });
            }
            else{
                finish.setVisibility(View.INVISIBLE);
                sound = soundPool.load(getApplicationContext(), R.raw.step2, 1);

                soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                    @Override
                    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                        soundPool.play(sound, 1, 1, 0, 0, 1);
                    }
                });
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public void playGame(View view){
        Intent intent = new Intent(this, Game.class);
        startActivity(intent);
    }
}
