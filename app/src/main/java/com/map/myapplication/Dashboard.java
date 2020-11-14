package com.map.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Dashboard extends AppCompatActivity {

    private static final int IMAGE_REQUEST=1;

    String currentImagePath=null;

    Button displayBtn;
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
        displayBtn=findViewById(R.id.disbtn);
        displayBtn.setVisibility(View.INVISIBLE);
        displayBtn.setActivated(false);
        displayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentImagePath != null && !currentImagePath.isEmpty()) {
                    Toast.makeText(Dashboard.this, currentImagePath, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Dashboard.this, Result.class);
                    intent.putExtra("image_paths", currentImagePath);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Dashboard.this, "No image captured", Toast.LENGTH_SHORT).show();
                }
            }
        });
        help=findViewById(R.id.help);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Dashboard.this,HowToUse.class);
                startActivity(intent);
            }
        });
        capture=findViewById(R.id.capture);
        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                if(cameraIntent.resolveActivity(getPackageManager())!=null)
                {
                    File imageFile=null;
                    try {
                        imageFile=getImageFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if(imageFile!=null)
                    {
                        Uri imageUri=FileProvider.getUriForFile(Dashboard.this,"com.map.myapplication.fileprovider",imageFile);
                        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                        startActivityForResult(cameraIntent,IMAGE_REQUEST);
                    }
                }
            }
        });


    }
    private File getImageFile() throws IOException
    {
        String timeStamp=new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageName="jpg_"+timeStamp+"_";
        File storageDir=getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File imageFile=File.createTempFile(imageName,".jpg",storageDir);
        currentImagePath=imageFile.getAbsolutePath();
        return imageFile;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_CANCELED){
            currentImagePath=null;
        }
        else if(resultCode==RESULT_OK){
            displayBtn.setActivated(true);
            displayBtn.setVisibility(View.VISIBLE);
        }
    }

    /*@Override
    protected void onResume() {
        super.onResume();
        displayBtn.setActivated(false);
        displayBtn.setVisibility(View.INVISIBLE);
    }*/

    /*@Override
    protected void onRestart() {
        super.onRestart();
        displayBtn.setActivated(false);
        displayBtn.setVisibility(View.INVISIBLE);
    }*/

    @Override
    protected void onPause() {
        super.onPause();
        displayBtn.setActivated(false);
        displayBtn.setVisibility(View.INVISIBLE);
    }

 /*   @Override
    protected void onStop() {
        super.onStop();
        displayBtn.setActivated(false);
        displayBtn.setVisibility(View.INVISIBLE);
    }*/
}