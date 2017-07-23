package com.example.apple.demomusic.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apple.demomusic.R;
import com.example.apple.demomusic.databases.MusicTypeModel;
import com.example.apple.demomusic.databases.TopSongModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * Created by apple on 7/21/17.
 */

public class TopSongsAdapter extends RecyclerView.Adapter<TopSongsAdapter.TopSongsViewHolder>{
    private static final String TAG = TopSongsAdapter.class.toString();
    private List<TopSongModel> topSongModelList;
    private Context context;
    private View.OnClickListener onClickListener;

    public TopSongsAdapter(List<TopSongModel> topSongModelList, Context context) {
        this.topSongModelList = topSongModelList;
        this.context = context;
    }

    public void setOnItemClick(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public TopSongsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_list_top_songs, parent, false);
        view.setOnClickListener(onClickListener);
        return new TopSongsAdapter.TopSongsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TopSongsViewHolder holder, int position) {
        holder.setData(topSongModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return topSongModelList.size();
    }

    public class TopSongsViewHolder extends RecyclerView.ViewHolder {
        ImageView ivSong;
        TextView tvName;
        TextView tvArtist;
        View view;

        public TopSongsViewHolder(View itemView) {
            super(itemView);

            ivSong = itemView.findViewById(R.id.iv_item_top_song);
            tvName = itemView.findViewById(R.id.tv_name);
            tvArtist = itemView.findViewById(R.id.tv_artist);
            view = itemView;
        }

        public void setData(TopSongModel topSongModel) {
            if (topSongModel != null) {
                Picasso.with(context).load(topSongModel.getImage()).transform(new CropCircleTransformation()).into(ivSong);
                tvName.setText(topSongModel.getName());
                tvArtist.setText(topSongModel.getArtist());

                view.setTag(topSongModel);
            }
        }
    }
}
