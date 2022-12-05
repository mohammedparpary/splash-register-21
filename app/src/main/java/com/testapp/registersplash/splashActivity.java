package com.testapp.registersplash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.VideoView;

public class splashActivity extends AppCompatActivity {

    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        videoView = (VideoView) findViewById(R.id.video_splash);
        String videoPath = new StringBuilder("android.resource://")
                .append(getPackageName())
                .append("/raw/second_splash")
                .toString();
        videoView.setVideoPath(videoPath);
        //event
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(splashActivity.this,signUpActivity.class));
                        finish();
                    }
                },500); // Wait 500 milliseconds before start new activity
            }
        });
        videoView.start();
    }
}