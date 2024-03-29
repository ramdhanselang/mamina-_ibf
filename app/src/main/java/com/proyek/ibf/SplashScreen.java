package com.proyek.ibf;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;


public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_SCREEN_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setContentView( R.layout.activity_splash_screen );
                Intent intent = new Intent( SplashScreen.this, LoginActivity.class );
                startActivity( intent );
            }
        }, SPLASH_SCREEN_OUT );
    }
}
