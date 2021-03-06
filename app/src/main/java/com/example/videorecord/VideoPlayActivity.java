package com.example.videorecord;

import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

public class VideoPlayActivity extends AppCompatActivity {

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);

        videoView = findViewById(R.id.videoView);

        Uri uri = Uri.parse(getIntent().getExtras().getString("uri"));
        videoView.setVideoURI(uri);
        videoView.start();


    }
}