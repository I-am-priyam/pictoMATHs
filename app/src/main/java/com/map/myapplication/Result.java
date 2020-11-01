package com.map.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

public class Result extends AppCompatActivity {

    ImageView qImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent=getIntent();
        Bitmap bitmap = (Bitmap) intent.getParcelableExtra("Bitmap");
        qImage=findViewById(R.id.quesImage);
        qImage.setImageBitmap(bitmap);
    }
}