package com.example.apple.demomusic.networks.json_models.topsongs;

/**
 * Created by apple on 7/22/17.
 */

public class ArtistDetailJSONModel {
    private String label;
    private AttributesArtistJSONModel attributes;

    public ArtistDetailJSONModel(String label, AttributesArtistJSONModel attributes) {
        this.label = label;
        this.attributes = attributes;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public AttributesArtistJSONModel getAttributes() {
        return attributes;
    }

    public void setAttributes(AttributesArtistJSONModel attributes) {
        this.attributes = attributes;
    }
}
