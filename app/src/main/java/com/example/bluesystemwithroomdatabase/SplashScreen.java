package com.example.bluesystemwithroomdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.bluesystemwithroomdatabase.DefultUser.DefaultUser;

public class SplashScreen extends AppCompatActivity {
    private static final String SHARED_NAME = "blue";
    SharedPreferences sharedPreferencesDefault;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                sharedPreferencesDefault = getSharedPreferences("default",MODE_PRIVATE);

                sharedPreferences = getSharedPreferences(SHARED_NAME,MODE_PRIVATE);
                boolean hasLogin_default = sharedPreferencesDefault.getBoolean("hasLogin_default",false);
                boolean hasLogin = sharedPreferences.getBoolean("hasLogin",false);
                if(hasLogin_default){
                    Intent intent = new Intent(SplashScreen.this, DefaultUser.class);
                    startActivity(intent);
                    finish();

                }else if(hasLogin){
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                } else{

                    Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                    startActivity(intent);
                    finish();

                }



            }
        }, 3000);

    }
}