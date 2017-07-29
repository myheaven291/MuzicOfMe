package com.example.apple.demomusic.managers;

import android.content.Context;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apple.demomusic.databases.TopSongModel;
import com.example.apple.demomusic.networks.services.GetSearchSong;
import com.example.apple.demomusic.networks.RetrofitFactory;
import com.example.apple.demomusic.networks.json_models.searchsongs.DocObject;
import com.example.apple.demomusic.networks.json_models.searchsongs.SearchMain;
import com.example.apple.demomusic.utils.FuzzyMatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hybridmediaplayer.HybridMediaPlayer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by apple on 7/22/17.
 */

public class MusicManager {
    private static final String TAG = MusicManager.class.toString();
    private static HybridMediaPlayer hybridMediaPlayer;
    private static SeekBar seekBar;
    private static boolean isChangeSeekbar = false;

    public static void loadSearchSong(final TopSongModel topSongModel, final Context context, final SeekBar sbMini) {
        seekBar = sbMini;
        GetSearchSong getSearchSong = RetrofitFactory.getInstance().create(GetSearchSong.class);
        getSearchSong.getSearchSong("{\"q\":\"" + topSongModel.getName() + " " + topSongModel.getArtist() + "\", \"sort\":\"hot\", \"start\":\"0\", \"length\":\"10\"}")
                .enqueue(new Callback<SearchMain>() {
                    @Override
                    public void onResponse(Call<SearchMain> call, Response<SearchMain> response) {
                        if (response.body().getDocs().size() > 0) {
                            //1. list ratio
                            List<Integer> ratioLists = new ArrayList<Integer>();
                            for (DocObject docObject : response.body().getDocs()) {
                                int ratio = FuzzyMatch.getRatio(topSongModel.getName() + " " + topSongModel.getArtist(), docObject.getTittle() + " " + docObject.getArtist(), false);
                                ratioLists.add(ratio);
                            }
                            //2. get max
                            for (int i = 0; i < ratioLists.size(); i++) {
                                if (Collections.max(ratioLists) == ratioLists.get(i)) {
                                    String linkSource = response.body().getDocs().get(i).getSource().getLinkSource();

                                    topSongModel.setLinkSource(linkSource);
                                    setupMusic(topSongModel, context);
                                    updateSongRealTime(seekBar);
                                }
                            }
                        } else {
                            Toast.makeText(context, "Not found", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<SearchMain> call, Throwable t) {
                        Toast.makeText(context, "There is no connection", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    public static void setupMusic(final TopSongModel topSongModel, Context context) {
        if (hybridMediaPlayer != null) {
            hybridMediaPlayer.release();
        }
        hybridMediaPlayer = HybridMediaPlayer.getInstance(context);
        hybridMediaPlayer.setDataSource(topSongModel.getLinkSource());
        hybridMediaPlayer.prepare();
        hybridMediaPlayer.setOnPreparedListener(new HybridMediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(HybridMediaPlayer hybridMediaPlayer) {
                hybridMediaPlayer.play();
            }
        });
    }

    public static void setStatus() {
        if (hybridMediaPlayer != null && hybridMediaPlayer.isPlaying()) {
            hybridMediaPlayer.pause();
        } else hybridMediaPlayer.play();
    }

    public static HybridMediaPlayer getHybridMediaPlayer() {
        return hybridMediaPlayer;
    }

    public static void updateSongRealTime(final SeekBar seekBar) {
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                seekBar.setMax(hybridMediaPlayer.getDuration());
                if (!isChangeSeekbar) {
                    seekBar.setProgress(hybridMediaPlayer.getCurrentPosition());
                }
                handler.postDelayed(this, 100);
            }
        };
        runnable.run();
        final int[] incurrentPosition = new int[1];
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                incurrentPosition[0] = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isChangeSeekbar = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                isChangeSeekbar = false;
                hybridMediaPlayer.seekTo(incurrentPosition[0]);
            }
        });
    }
}
