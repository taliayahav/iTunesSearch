package yahav.itunessearch;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


import static org.junit.Assert.*;

public class ItunesSearchServiceTest {

        ItunesSearchServiceFactory factory = new ItunesSearchServiceFactory();

        @Test
        public void getSong () {
        // given
        ItunesSearchService service = factory.newInstance();

        // when
        ItunesSearchFeed feed = service.getSong("Taylor Swift")
                .blockingGet();

        // then
        assertNotNull(feed);
        assertNotNull(feed.results);
        assertNotNull(feed.results.get(0).trackName);
        assertNotNull(feed.results.get(0).artistName);
        assertNotNull(feed.results.get(0).artworkUrl60);
        assertEquals("Taylor Swift", feed.results.get(0).artistName);
        assertFalse(feed.results.isEmpty());
        assertFalse(feed.results.get(0).artistName.isEmpty());
    }
        @Test
        public void getAlbum () {
        // given
        ItunesSearchService service = factory.newInstance();

        // when
        AlbumSearchFeed albumFeed = service.getAlbum("Taylor Swift")
                .blockingGet();

        //then
        assertNotNull(albumFeed);
        assertNotNull(albumFeed.results);
        assertNotNull(albumFeed.results.get(0).collectionName);
        assertNotNull(albumFeed.results.get(0).artworkUrl100);
        assertFalse(albumFeed.results.isEmpty());
        assertFalse(albumFeed.results.get(0).artworkUrl100.isEmpty());
        assertEquals("1989", albumFeed.results.get(0).collectionName);
    }

}
