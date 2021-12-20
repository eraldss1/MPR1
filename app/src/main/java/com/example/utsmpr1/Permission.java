package com.example.utsmpr1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;

import android.widget.Button;

public class Permission extends AppCompatActivity {

    Button cameraBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        cameraBtn = (Button) findViewById(R.id.cameraBtn);
        cameraBtn.setOnClickListener(view -> captureImage());
    }

    private void captureImage(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
    }
}