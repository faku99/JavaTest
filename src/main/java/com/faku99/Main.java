package com.faku99;

import com.faku99.media.Playlist;
import com.faku99.media.PlaylistTrack;
import com.faku99.media.Track;
import com.faku99.sql.DBManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void dropDatabase() {
        String filePath = "database.db";
        File dbFile = new File(filePath);

        if (dbFile.exists()) {
            dbFile.delete();
        }
    }

    public static void main(String... args) {
        dropDatabase();

        DBManager dbManager = DBManager.sharedInstance();

        Track track1 = new Track("Track 1");
        Track track2 = new Track("Track 2");
        Track track3 = new Track("Track 3");

        dbManager.save(track1);
        dbManager.save(track2);
        dbManager.save(track3);

        Playlist playlist1 = new Playlist("Playlist 1");
        Playlist playlist2 = new Playlist("Playlist 2");

        dbManager.save(playlist1);
        dbManager.save(playlist2);

        PlaylistTrack playlistTrack11 = new PlaylistTrack(playlist1, track1);
        PlaylistTrack playlistTrack13 = new PlaylistTrack(playlist1, track3);
        PlaylistTrack playlistTrack22 = new PlaylistTrack(playlist2, track2);
        PlaylistTrack playlistTrack23 = new PlaylistTrack(playlist2, track3);

        dbManager.save(playlistTrack11);
        dbManager.save(playlistTrack13);
        dbManager.save(playlistTrack22);
        dbManager.save(playlistTrack23);

        Session session = dbManager.getSession();

        for (Playlist playlist : new Playlist[]{playlist1, playlist2}) {
            Query query = session.createQuery(String.format("from PlaylistTrack where playlist_id = '%d'", playlist.getId()));
            List list = query.list();

            System.out.printf("Tracks for %s:\n", playlist.getName());
            for (Object obj : list) {
                if (PlaylistTrack.class.isInstance(obj)) {
                    System.out.printf(" %s\n", ((PlaylistTrack) obj).getTrack());
                }
            }
        }

        dbManager.close();
    }
}