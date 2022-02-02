package com.hdgroup.plantip.Intro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.hdgroup.plantip.R;
import com.hdgroup.plantip.ui.auth.AuthActivity;

public class Splashscreen extends AppCompatActivity {

    ImageView logo,background;
    LottieAnimationView lottieAnimationView;

    private static final int slidesno =3;

    private ViewPager viewPager;
    private ScreenSliderAdapter sliderAdapter;
    Animation animation;

    private static int SPLASH_TIME_OUT = 6000;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splashscreen);

        logo = findViewById(R.id.logo);
        background = findViewById(R.id.bgimage);
        lottieAnimationView = findViewById(R.id.lottieani);

        viewPager = findViewById(R.id.pager);
        sliderAdapter = new ScreenSliderAdapter(getSupportFragmentManager());
        viewPager.setAdapter(sliderAdapter);

        animation = AnimationUtils.loadAnimation(this,R.anim.onboardanim);
        viewPager.startAnimation(animation);

        background.animate().translationY(-1600).setDuration(4000).setStartDelay(4000);
        logo.animate().translationY(400).setDuration(2000).setStartDelay(3000);
        lottieAnimationView.animate().translationY(300).setDuration(2000).setStartDelay(3000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                sharedPreferences = getSharedPreferences("sharedpref",MODE_PRIVATE);
                boolean isFirestTime = sharedPreferences.getBoolean("firsttime",true);

                if (isFirestTime){
                    //intent
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("firsttime",false);
                    editor.commit();

                }
                else {
                    Intent intent = new Intent(Splashscreen.this, LanguageActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },SPLASH_TIME_OUT);
    }

    private class ScreenSliderAdapter extends FragmentStatePagerAdapter{

        public ScreenSliderAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    OnBoardingFragment1 tab1 = new OnBoardingFragment1();
                    return tab1;
                case 1:
                    OnBoardingFragment2 tab2 = new OnBoardingFragment2();
                    return tab2;
                case 2:
                    OnBoardingFragment3 tab3 = new OnBoardingFragment3();
                    return tab3;

            }
            return null;
        }

        @Override
        public int getCount() {

            return slidesno;
        }
    }
}