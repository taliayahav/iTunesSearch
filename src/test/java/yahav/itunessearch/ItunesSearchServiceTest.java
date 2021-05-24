package yahav.itunessearch;

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
        ItunesSearchFeed feed = service.getSong("Justin Bieber").blockingGet();

        // then
        assertNotNull(feed);
        assertNotNull(feed.results);
        assertNotNull(feed.results.get(0).trackName);
        assertNotNull(feed.results.get(0).artistName);
        assertNotNull(feed.results.get(0).artworkUrl60);
        assertFalse(feed.results.isEmpty());
        assertEquals("Justin Bieber", feed.results);
    }
        @Test
        public void getAlbum () {
        // given
        ItunesSearchService service = factory.newInstance();

        // when
        ItunesSearchFeed feed = service.getSong("Justin Bieber").blockingGet();

        //then
        assertNotNull(feed);
        assertNotNull(feed.results);
        assertNotNull(feed.results.get(0).trackName);
        assertNotNull(feed.results.get(0).artistName);
        assertNotNull(feed.results.get(0).artworkUrl60);
        assertFalse(feed.results.isEmpty());
        assertEquals("Justin Bieber", feed.results);
    }

}
