package com.example.apple.demomusic.events;

import com.example.apple.demomusic.databases.TopSongModel;

/**
 * Created by apple on 7/23/17.
 */

public class OnClickMusicSong {
    private TopSongModel topSongModel;
    boolean hideTablayout;

    public OnClickMusicSong(TopSongModel topSongModel, boolean hideTablayout) {
        this.topSongModel = topSongModel;
        this.hideTablayout = hideTablayout;
    }

    public TopSongModel getTopSongModel() {
        return topSongModel;
    }

    public void setTopSongModel(TopSongModel topSongModel) {
        this.topSongModel = topSongModel;
    }

    public boolean isHideTablayout() {
        return hideTablayout;
    }

    public void setHideTablayout(boolean hideTablayout) {
        this.hideTablayout = hideTablayout;
    }
}
