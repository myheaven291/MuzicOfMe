package com.example.apple.demomusic.networks.json_models.topsongs;

import java.util.List;

/**
 * Created by apple on 7/22/17.
 */

public class FeedJSONModel {
    private List<SongDetailJSONModel> entry;

    public FeedJSONModel(List<SongDetailJSONModel> entry) {
        this.entry = entry;
    }

    public List<SongDetailJSONModel> getEntry() {
        return entry;
    }

    public void setEntry(List<SongDetailJSONModel> entry) {
        this.entry = entry;
    }
}
