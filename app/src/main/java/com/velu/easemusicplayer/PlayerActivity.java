package com.velu.easemusicplayer;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class PlayerActivity extends AppCompatActivity {
    public MediaPlayer mediaPlayer;
    private TextView titleView, albumView;
    private ImageView button, download_icon;
    private Intent i;
    private String song_link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        titleView = findViewById(R.id.titleView);
        albumView = findViewById(R.id.albumView);
        button = findViewById(R.id.playpausebtn);
        download_icon = findViewById(R.id.download_icon);

        i = getIntent();
        albumView.setText(i.getStringExtra("album"));
        titleView.setText(i.getStringExtra("title"));
        song_link = i.getStringExtra("link");

        File file = new File(Environment.getExternalStorageDirectory().toString()+"/Songs/"
                +i.getStringExtra("title").replace(" ", "")+".mp3");
        if (file.exists()){
            download_icon.setImageResource(R.drawable.ic_downloaded);
            download_icon.setEnabled(false);
            song_link = file.getPath();
        }

        if(mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        mediaPlayer = new MediaPlayer();

        try {
            mediaPlayer.setDataSource(song_link);
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
            NotificationChannel channel = new NotificationChannel("Song Playing", "Song Playing",
                    NotificationManager.IMPORTANCE_DEFAULT);
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

    public void download(View view){
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    PackageManager.PERMISSION_GRANTED);
        }
        new DownloadFile().execute(i.getStringExtra("link"), i.getStringExtra("title"));
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


    class DownloadFile extends AsyncTask<String, String, String>{
        @Override
        protected String doInBackground(String... link) {
            try {
                URL url = new URL(link[0]);
                URLConnection connection = url.openConnection();
                connection.connect();

                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                OutputStream output = new FileOutputStream( Environment.getExternalStorageDirectory().toString()+"/Songs/"+link[1].replace(" ", "")+".mp3");

                byte[] data = new byte[1024];

                int count = 0;
                while ((count = input.read(data)) != -1) {
                    output.write(data, 0, count);
                }
                download_icon.setImageResource(R.drawable.ic_downloaded);
                download_icon.setEnabled(false);
                output.flush();
                output.close();
                input.close();
            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return null;
        }
    }
}