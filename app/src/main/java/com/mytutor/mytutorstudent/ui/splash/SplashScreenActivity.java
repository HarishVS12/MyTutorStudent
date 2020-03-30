package com.mytutor.mytutorstudent.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.mytutor.mytutorstudent.R;
import com.mytutor.mytutorstudent.ui.authentication.signin.LoginActivity;
import com.mytutor.mytutorstudent.ui.dashboard.DashboardActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private static int SPLASH_TIME = 2000;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);
        FirebaseApp.initializeApp(this);
        auth = FirebaseAuth.getInstance();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (auth != null && auth.getCurrentUser() != null) {
                    startActivity(new Intent(SplashScreenActivity.this, DashboardActivity.class));
                } else {
                    startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                }

                finish();
            }
        }, SPLASH_TIME);

    }

}
