package com.velu.easemusicplayer;


public class SongModel {
    String title, album, link;

    public SongModel(){

    }

    public SongModel(String title, String album, String link) {
        this.title = title;
        this.album = album;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
