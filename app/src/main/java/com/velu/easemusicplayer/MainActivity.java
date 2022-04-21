package com.velu.easemusicplayer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, new OnlineFragment())
                .commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.online:
                        fragment = new OnlineFragment();
                        break;
                    case R.id.local:
                        fragment = new LocalFragment();
                        break;
                    case R.id.profile:
                        fragment = new ProfileFragment();
                        break;
                }
                if (fragment != null) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frameLayout, fragment)
                            .commit();
                }
                return true;
            }
        });


    }

    public void ClickFunction(ArrayList<SongModel> songList, int adapterPosition) {

        SongModel songModel = songList.get(adapterPosition);


        Intent i = new Intent(MainActivity.this, PlayerActivity.class);
        i.putExtra("title", songModel.getTitle());
        i.putExtra("album", songModel.getAlbum());
        i.putExtra("link", songModel.getLink());
        startActivity(i);

    }
}