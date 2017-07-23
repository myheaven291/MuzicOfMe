package com.example.apple.demomusic.networks.json_models.topsongs;

/**
 * Created by apple on 7/22/17.
 */

public class ImageDetailJSONModel {
    private AttributesImageJSONModel attributes;
    private String label;

    public ImageDetailJSONModel(AttributesImageJSONModel attributes, String label) {
        this.attributes = attributes;
        this.label = label;
    }

    public AttributesImageJSONModel getAttributes() {
        return attributes;
    }

    public void setAttributes(AttributesImageJSONModel attributes) {
        this.attributes = attributes;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
