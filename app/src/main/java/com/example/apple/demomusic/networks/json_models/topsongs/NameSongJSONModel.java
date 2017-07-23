package com.example.apple.demomusic.networks.json_models.topsongs;

/**
 * Created by apple on 7/22/17.
 */

public class NameSongJSONModel {
    private String label;

    public NameSongJSONModel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
