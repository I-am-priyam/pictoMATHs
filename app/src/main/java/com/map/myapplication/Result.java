package com.map.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Result extends AppCompatActivity {

    ImageView dImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        dImage=findViewById(R.id.displayimage);

        Bitmap bitmap= BitmapFactory.decodeFile(getIntent().getStringExtra("image_paths"));
        dImage.setImageBitmap(bitmap);

    }
}