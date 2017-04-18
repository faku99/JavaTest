package com.faku99.media;

public class PlaylistTrack {

    private PlaylistTrackId id = new PlaylistTrackId();

    private int upvotes;

    public PlaylistTrack(Playlist playlist, Track track) {
        id.setPlaylist(playlist);
        id.setTrack(track);
    }

    public Playlist getPlaylist() {
        return id.getPlaylist();
    }

    public Track getTrack() {
        return id.getTrack();
    }

    public int getUpvotes() {
        return upvotes;
    }

    public void upvote() {
        upvotes++;
    }
}
