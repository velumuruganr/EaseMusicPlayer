package com.velu.easemusicplayer;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.io.IOException;

public class PlayerActivity extends AppCompatActivity {
    public MediaPlayer mediaPlayer;
    private TextView titleView, albumView;
    private ImageView button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        titleView = findViewById(R.id.titleView);
        albumView = findViewById(R.id.albumView);
        button = findViewById(R.id.playpausebtn);

        Intent i = getIntent();
        albumView.setText(i.getStringExtra("album"));
        titleView.setText(i.getStringExtra("title"));
        if(mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        mediaPlayer = new MediaPlayer();

        try {
            mediaPlayer.setDataSource(i.getStringExtra("link"));
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                    button.setImageResource(R.drawable.ic_pause);
                }
            });

            mediaPlayer.prepareAsync();
            createNotification(i.getStringExtra("title"), i.getStringExtra("album"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createNotification(String title, String album) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("Song Playing", "Song Playing", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(PlayerActivity.this, "Song Playing");
        builder.setContentTitle(title);
        builder.setContentText(album);
        builder.setSmallIcon(R.drawable.ic_music);
        builder.setAutoCancel(true);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(PlayerActivity.this);
        managerCompat.notify(1, builder.build());
    }

    public void PlayMusic(View v){
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
            button.setImageResource(R.drawable.ic_play);
        }
        else {
            mediaPlayer.start();
            button.setImageResource(R.drawable.ic_pause);
        }
    }

}