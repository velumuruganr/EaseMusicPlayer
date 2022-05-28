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
import java.util.List;

public class RssFeedsAdapter extends RecyclerView.Adapter<RssFeedsAdapter.ViewHolder> {

    private List<RssFeedsModel> feedsList;
    private Context context;

    public RssFeedsAdapter(List<RssFeedsModel> feedsList, Context context) {
        this.feedsList = feedsList;
        this.context = context;
    }



    @NonNull
    @Override
    public RssFeedsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_item, parent, false);
        return new RssFeedsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RssFeedsAdapter.ViewHolder holder, int position) {
        holder.feed_title.setText(feedsList.get(position).getTitle());
        holder.feed_description.setText(feedsList.get(position).getDescription());

    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView feed_title;
        private TextView feed_description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            feed_title = itemView.findViewById(R.id.song_item_title);
            feed_description = itemView.findViewById(R.id.song_item_album);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            ((MainActivity)context).ClickFunction2(feedsList, getAdapterPosition());
        }
    }
    @Override
    public int getItemCount() {
        return feedsList.size();
    }
}
