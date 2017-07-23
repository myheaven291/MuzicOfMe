package com.example.apple.demomusic.networks.json_models.musictypes;

import java.util.List;

/**
 * Created by apple on 7/15/17.
 */

public class AllMusicTypesJSONModel {
    private List<MusicTypeJSONModel> subgenres;

    public AllMusicTypesJSONModel(List<MusicTypeJSONModel> subgenres) {
        this.subgenres = subgenres;
    }

    public List<MusicTypeJSONModel> getSubgenres() {
        return subgenres;
    }

    public void setSubgenres(List<MusicTypeJSONModel> subgenres) {
        this.subgenres = subgenres;
    }
}
