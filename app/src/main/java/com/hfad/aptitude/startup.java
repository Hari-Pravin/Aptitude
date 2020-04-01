package com.hfad.aptitude;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class startup extends AppCompatActivity {
Handler handler;
ImageView iv1,iv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
        iv1 = findViewById(R.id.img1);
        iv2 = findViewById(R.id.img2);
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {


                iv2.setVisibility(View.GONE);
                iv1.setImageResource(R.drawable.thnks);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(startup.this,cover.class);
                        startActivity(intent);

                    }
                },500);
            }
        },1500);
    }



}
