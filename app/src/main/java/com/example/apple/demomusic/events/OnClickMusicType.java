package com.example.apple.demomusic.events;

import com.example.apple.demomusic.databases.MusicTypeModel;

/**
 * Created by apple on 7/22/17.
 */

public class OnClickMusicType {
    private MusicTypeModel musicTypeModel;

    public OnClickMusicType(MusicTypeModel musicTypeModel) {
        this.musicTypeModel = musicTypeModel;
    }

    public MusicTypeModel getMusicTypeModel() {
        return musicTypeModel;
    }

    public void setMusicTypeModel(MusicTypeModel musicTypeModel) {
        this.musicTypeModel = musicTypeModel;
    }
}
