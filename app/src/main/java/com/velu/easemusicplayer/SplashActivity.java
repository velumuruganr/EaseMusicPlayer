package com.velu.easemusicplayer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Bitmap bg = Bitmap.createBitmap(720, 1280, Bitmap.Config.ARGB_8888);

        ImageView logo = findViewById(R.id.logo);
        logo.setBackgroundDrawable(new BitmapDrawable(bg));

        Paint paint = new Paint();
        paint.setColor(Color.BLUE);

        Canvas canvas = new Canvas(bg);
        canvas.drawRect(355, 370, 425, 770, paint);
        canvas.drawCircle(305, 785, 120, paint);
        canvas.drawRect(355, 370, 550, 500, paint);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 2000);
    }
}