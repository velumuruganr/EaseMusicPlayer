package com.velu.easemusicplayer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OnlineFragment extends Fragment {

    private ImageView sliderView;
    private ArrayList<SongModel> dataList;
    private SongAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_online, container, false);


        sliderView = view.findViewById(R.id.slider);
        recyclerView = view.findViewById(R.id.RecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        dataList = new ArrayList<>();

        dataList.add(new SongModel("Song 1", "Album 1", "https://firebasestorage.googleapis.com/v0/b/loopmusic-velu.appspot.com/o/songs%2FJalabulajangu-MassTamilan.fm.mp3?alt=media&token=b0fa9584-b9d5-452e-924c-02f7cbdb5cef"));
        dataList.add(new SongModel("Song 2", "Album 2", "https://firebasestorage.googleapis.com/v0/b/loopmusic-velu.appspot.com/o/songs%2FJalabulajangu-MassTamilan.fm.mp3?alt=media&token=b0fa9584-b9d5-452e-924c-02f7cbdb5cef"));
        dataList.add(new SongModel("Song 3", "Album 3", "https://firebasestorage.googleapis.com/v0/b/loopmusic-velu.appspot.com/o/songs%2FJalabulajangu-MassTamilan.fm.mp3?alt=media&token=b0fa9584-b9d5-452e-924c-02f7cbdb5cef"));
        dataList.add(new SongModel("Song 4", "Album 4", "https://firebasestorage.googleapis.com/v0/b/loopmusic-velu.appspot.com/o/songs%2FJalabulajangu-MassTamilan.fm.mp3?alt=media&token=b0fa9584-b9d5-452e-924c-02f7cbdb5cef"));
        adapter = new SongAdapter(dataList, getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        sliderView.setTag(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    sliderView.post(new Runnable() {
                        @Override
                        public void run() {
                            if (sliderView.getTag().equals(1)) {
                                sliderView.setImageResource(R.drawable.song1);
                                sliderView.setTag(2);
                            } else if (sliderView.getTag().equals(2)) {
                                sliderView.setImageResource(R.drawable.song2);
                                sliderView.setTag(3);
                            } else if (sliderView.getTag().equals(3)) {
                                sliderView.setImageResource(R.drawable.song3);
                                sliderView.setTag(1);
                            }
                        }
                    });
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        
        return view;

    }

}