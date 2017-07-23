package com.example.apple.demomusic.networks.json_models.searchsongs;

import java.util.List;

/**
 * Created by apple on 7/22/17.
 */

public class SearchMain {
    private List<DocObject> docs;

    public SearchMain(List<DocObject> docs) {
        this.docs = docs;
    }

    public List<DocObject> getDocs() {
        return docs;
    }

    public void setDocs(List<DocObject> docs) {
        this.docs = docs;
    }
}
