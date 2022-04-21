package com.velu.easemusicplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {

    private ArrayList<SongModel> songList;
    private Context context;

    public SongAdapter(ArrayList<SongModel> songList, Context context) {
        this.songList = songList;
        this.context = context;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.song_title.setText(songList.get(position).getTitle());
        holder.song_album.setText(songList.get(position).getAlbum());
        holder.song_img.setImageResource(R.drawable.ic_music);

    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView song_img;
        private TextView song_title;
        private TextView song_album;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            song_img = itemView.findViewById(R.id.song_item_img);
            song_title = itemView.findViewById(R.id.song_item_title);
            song_album = itemView.findViewById(R.id.song_item_album);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            ((MainActivity)context).ClickFunction(songList, getAdapterPosition());
        }
    }
    @Override
    public int getItemCount() {
        return songList.size();
    }
}
