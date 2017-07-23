package com.example.apple.demomusic.networks.json_models.searchsongs;

/**
 * Created by apple on 7/22/17.
 */

public class DocObject {
    private String tittle;
    private String artist;
    private SourceObject source;

    public DocObject(String tittle, String artist, SourceObject source) {
        this.tittle = tittle;
        this.artist = artist;
        this.source = source;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public SourceObject getSource() {
        return source;
    }

    public void setSource(SourceObject source) {
        this.source = source;
    }
}
