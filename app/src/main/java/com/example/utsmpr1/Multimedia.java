package com.example.utsmpr1;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageButton;

import java.io.IOException;

public class Multimedia extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multimedia);

        String url = "https://www.soundjay.com/nature/ocean-wave-1.mp3";
        final MediaPlayer player = new MediaPlayer();
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);

        try {
            player.setDataSource(url);
        }catch (IOException e){
            e.printStackTrace();
        }

        try{
            player.prepare();
        }catch (IOException e){
            e.printStackTrace();
        }

        final ImageButton play = findViewById(R.id.playBtn);
        final ImageButton stop = findViewById(R.id.stopBtn);

        play.setOnClickListener(v -> player.start());
        stop.setOnClickListener(v -> player.stop());
    }
}