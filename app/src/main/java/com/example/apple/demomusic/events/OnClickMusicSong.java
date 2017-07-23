package com.example.apple.demomusic.events;

import com.example.apple.demomusic.databases.TopSongModel;

/**
 * Created by apple on 7/23/17.
 */

public class OnClickMusicSong {
    private TopSongModel topSongModel;

    public OnClickMusicSong(TopSongModel topSongModel) {
        this.topSongModel = topSongModel;
    }

    public TopSongModel getTopSongModel() {
        return topSongModel;
    }

    public void setTopSongModel(TopSongModel topSongModel) {
        this.topSongModel = topSongModel;
    }
}
