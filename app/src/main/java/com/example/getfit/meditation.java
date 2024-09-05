package com.example.getfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;
import android.widget.Button;

public class meditation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditation);



        VideoView videoview = findViewById(R.id.video_view);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video;
        Uri uri = Uri.parse(videoPath);
        videoview.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        videoview.setMediaController(mediaController);
        mediaController.setAnchorView(videoview);




        Button backMedi = findViewById(R.id.backBtn);
        backMedi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(meditation.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}