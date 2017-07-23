package com.example.apple.demomusic.fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.PointerIconCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.apple.demomusic.R;
import com.example.apple.demomusic.databases.TopSongModel;
import com.example.apple.demomusic.events.OnClickMusicSong;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * A simple {@link Fragment} subclass.
 */
public class MiniPlayerFragment extends Fragment implements View.OnClickListener{
    private static final String TAG = MiniPlayerFragment.class.toString();
    @BindView(R.id.seek_bar_player)
    SeekBar seekBar;
    @BindView(R.id.fab_pause)
    FloatingActionButton fabPause;
    @BindView(R.id.iv_song)
    ImageView ivSong;
    @BindView(R.id.tv_song_name)
    TextView tvSongName;
    @BindView(R.id.tv_artist)
    TextView tvArtist;

    public MiniPlayerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mini_player, container, false);

        setUI(view);
        return view;
    }

    private void setUI(View view) {
        ButterKnife.bind(this,view);
    }

    @Override
    public void onClick(View view) {

    }
}
