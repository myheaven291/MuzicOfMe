package com.example.apple.demomusic.networks.json_models.topsongs;

/**
 * Created by apple on 7/21/17.
 */

public class TopSongsJSONModel {
    private FeedJSONModel feed;

    public TopSongsJSONModel(FeedJSONModel feed) {
        this.feed = feed;
    }

    public FeedJSONModel getFeed() {
        return feed;
    }

    public void setFeed(FeedJSONModel feed) {
        this.feed = feed;
    }
}
