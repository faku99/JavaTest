package com.faku99.media;

import java.io.Serializable;

public class PlaylistTrackId implements Serializable {

    private Playlist playlist;

    private Track track;

    public Playlist getPlaylist() {
        return playlist;
    }

    public Track getTrack() {
        return track;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public void setTrack(Track track) {
        this.track = track;
    }
}
