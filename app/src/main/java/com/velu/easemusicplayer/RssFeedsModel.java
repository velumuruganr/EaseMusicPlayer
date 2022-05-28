package com.velu.easemusicplayer;

public class RssFeedsModel {

    public String title;
    public String link;
    public String description;

    public RssFeedsModel(String title, String link, String description) {
        this.title = title;
        this.link = link;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

}
