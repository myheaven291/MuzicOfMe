package com.example.apple.demomusic.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apple.demomusic.R;
import com.example.apple.demomusic.databases.MusicTypeModel;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by apple on 7/19/17.
 */

public class MusicTypeAdapter extends RecyclerView.Adapter<MusicTypeAdapter.MusicTypeViewHolder> {
    private List<MusicTypeModel> musicTypeModelList;
    private Context context;
    private View.OnClickListener onClickListener;

    public MusicTypeAdapter(List<MusicTypeModel> musicTypeModelList, Context context) {
        this.musicTypeModelList = musicTypeModelList;
        this.context = context;
    }

    public void setOnItemClick(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    //2. Create view
    @Override
    public MusicTypeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_list_music_types, parent, false);
        view.setOnClickListener(onClickListener);
        return new MusicTypeViewHolder(view);
    }

    //3. set data
    @Override
    public void onBindViewHolder(MusicTypeViewHolder holder, int position) {
        holder.setData(musicTypeModelList.get(position));
    }

    //1.
    @Override
    public int getItemCount() {
        return musicTypeModelList.size();
    }

    public class MusicTypeViewHolder extends RecyclerView.ViewHolder {
        ImageView ivMusicType;
        TextView tvMusicType;
        View view;

        public MusicTypeViewHolder(View itemView) {
            super(itemView);

            ivMusicType = itemView.findViewById(R.id.iv_item_music);
            tvMusicType = itemView.findViewById(R.id.tv_item_music_type);
            view = itemView;

        }

        public void setData(MusicTypeModel musicTypeModel) {
            if (musicTypeModel != null) {
                Picasso.with(context).load(musicTypeModel.getImageID()).into(ivMusicType);
                tvMusicType.setText(musicTypeModel.getKey());

                view.setTag(musicTypeModel);
            }
        }
    }
}
