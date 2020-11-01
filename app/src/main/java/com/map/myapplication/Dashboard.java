package com.map.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Dashboard extends AppCompatActivity {

    private static final int CAMERA_REQUEST=9999;
    ImageView help,capture;
    TextView tv;
    Animation topAnim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_anim);
        tv=findViewById(R.id.textView);
        tv.setAnimation(topAnim);
        help=findViewById(R.id.help);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Dashboard.this,HowToUse.class);
                startActivity(intent);
            }
        });
        capture=findViewById(R.id.capture);
        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent1,CAMERA_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CAMERA_REQUEST){
            Bitmap bitmap=(Bitmap)data.getExtras().get("data");
            //capture.setImageBitmap(bitmap);
            Intent intent2=new Intent(Dashboard.this,Result.class);
            intent2.putExtra("Bitmap",bitmap);
            startActivity(intent2);
        }
    }
}