package com.example.apple.demomusic.networks.json_models.topsongs;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by apple on 7/22/17.
 */

public class SongDetailJSONModel {
    @SerializedName("im:name")
    NameSongJSONModel name;
    @SerializedName("im:image")
    List<ImageDetailJSONModel> images;
    @SerializedName("im:artist")
    ArtistDetailJSONModel artist;

    public SongDetailJSONModel(NameSongJSONModel name, List<ImageDetailJSONModel> images, ArtistDetailJSONModel artist) {
        this.name = name;
        this.images = images;
        this.artist = artist;
    }

    public NameSongJSONModel getName() {
        return name;
    }

    public void setName(NameSongJSONModel name) {
        this.name = name;
    }

    public List<ImageDetailJSONModel> getImages() {
        return images;
    }

    public void setImages(List<ImageDetailJSONModel> images) {
        this.images = images;
    }

    public ArtistDetailJSONModel getArtist() {
        return artist;
    }

    public void setArtist(ArtistDetailJSONModel artist) {
        this.artist = artist;
    }
}
