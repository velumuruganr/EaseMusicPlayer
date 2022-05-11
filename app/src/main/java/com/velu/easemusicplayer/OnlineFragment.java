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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_online, container, false);


        sliderView = view.findViewById(R.id.slider);

        sliderView.setTag(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    sliderView.post(new Runnable() {
                        @Override
                        public void run() {

                            if (sliderView.getTag().equals(1)) {
                                sliderView.setImageResource(R.drawable.ic_music);
                                sliderView.setTag(2);
                            } else if (sliderView.getTag().equals(2)) {
                                sliderView.setImageResource(R.drawable.ic_play);
                                sliderView.setTag(3);
                            } else if (sliderView.getTag().equals(3)) {
                                sliderView.setImageResource(R.drawable.ic_pause);
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