package com.example.fakenews;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    private ImageView splashImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);

        splashImage = findViewById(R.id.splashImage);

        splashImage.setVisibility(View.VISIBLE);
        animateImage();
    }

    private void animateImage() {
        float bottom = getResources().getDisplayMetrics().heightPixels;

        // Move the ImageView from bottom to center
        ObjectAnimator animator = ObjectAnimator.ofFloat(splashImage, "translationY", bottom, 0);
        animator.setDuration(3000);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                // Do nothing
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                // Start the Signup activity after the animation ends
                Intent intent = new Intent(Splash.this, Signup.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                // Do nothing
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                // Do nothing
            }
        });
        animator.start();
    }
}
