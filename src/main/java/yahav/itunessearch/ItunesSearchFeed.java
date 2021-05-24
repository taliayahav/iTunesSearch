package yahav.itunessearch;

import java.util.List;

public class ItunesSearchFeed {
    List<Song> results;

    static class Song{
        String trackName;
        String artistName;
        String artworkUrl60;
    }
}
