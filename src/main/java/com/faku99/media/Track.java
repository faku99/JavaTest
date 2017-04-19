package com.faku99.media;

public class Track {
    private int id;

    private String name;

    private int version;

    public Track(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return String.format("#%d - %s", id, name);
    }
}
