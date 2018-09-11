package com.example.mskaca.fittrexdemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
//import android

public class ImageCapturePage extends AppCompatActivity {

    ImageView imageView;
    Button btnCamera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_capture_page);

        btnCamera = (Button)findViewById(R.id.button1);
        imageView = (ImageView)findViewById(R.id.imageView1);

        btnCamera.setOnClickListener(new View.OnClickListener(){
            @Override
                    public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(intent, 0);
            }
        });
    }

    // When startActivityForResult() is called, activity is launched. Once that activity finishes,
    // the calling activity is presented with a result within its onActivityResult() handler
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
        btnCamera.setTextColor(Color.parseColor("#FF0F0F")); //custom color
        imageView.setImageBitmap(bitmap);
        imageView.setRotation(90);
    }
}