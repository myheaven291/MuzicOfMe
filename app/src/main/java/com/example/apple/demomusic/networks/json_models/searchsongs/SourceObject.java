package com.example.apple.demomusic.networks.json_models.searchsongs;

import com.google.gson.annotations.SerializedName;

/**
 * Created by apple on 7/22/17.
 */

public class SourceObject {
    @SerializedName("128")
    private String linkSource;

    public SourceObject(String linkSource) {
        this.linkSource = linkSource;
    }

    public String getLinkSource() {
        return linkSource;
    }

    public void setLinkSource(String linkSource) {
        this.linkSource = linkSource;
    }
}
