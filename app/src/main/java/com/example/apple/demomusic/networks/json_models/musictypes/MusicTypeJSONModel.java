package com.example.apple.demomusic.networks.json_models.musictypes;

/**
 * Created by apple on 7/15/17.
 */

public class MusicTypeJSONModel {
    private String id;
    private String translation_key;

    public MusicTypeJSONModel(String id, String translation_key) {
        this.id = id;
        this.translation_key = translation_key;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTranslation_key() {
        return translation_key;
    }

    public void setTranslation_key(String translation_key) {
        this.translation_key = translation_key;
    }
}
