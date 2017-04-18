package com.faku99.media;

public class Playlist {

    private int id;

    private String name;

    private int version;

    public Playlist(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
