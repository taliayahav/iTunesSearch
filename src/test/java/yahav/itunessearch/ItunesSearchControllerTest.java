package yahav.itunessearch;

import io.reactivex.rxjava3.core.Single;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ItunesSearchControllerTest {
    private ItunesSearchController controller;
    ItunesSearchService service;
    ItunesSearchServiceFactory factory = new ItunesSearchServiceFactory();
    private ItunesSearchFeed itunesSearchFeed;
    private AlbumSearchFeed albumSearchFeed;
    private List<Label>songList;
    private List<Label>artist;
    private List<ImageView>image;
    private List<Label>albumNames;
    TextField answerResult;

    @BeforeClass
    public static void beforeClass() {
        com.sun.javafx.application.PlatformImpl.startup(() -> {
        });
    }
    @Test
    public void Initialize()
    {
        // given
        onGiven();
        ItunesSearchServiceFactory factory = mock(ItunesSearchServiceFactory.class);
        controller.factory = factory;
        doReturn(service).when(factory).newInstance();

        // when
        controller.Initialize();

        // then
        verify(factory).newInstance();
        Assert.assertEquals(service, factory.newInstance());
    }
    @Test
    public void onSubmit(){
        //given
        onGiven();
        answerResult = mock(TextField.class);
        controller.answerResult =  answerResult;
        doReturn(Single.never()).when(service).getSong("Taylor Swift");
        doReturn(Single.never()).when(service).getAlbum("Taylor Swift");
//        doReturn("Taylor Swift").when(controller.answerResult).getText();

        //when
        controller.onSubmit(mock(MouseEvent.class));

        //then
//        verify(service).getSong("Taylor Swift");
        Assert.assertEquals(controller.answerResult,"Taylor Swift");
    }

    @Test
    public void onItunesSearchFeed(){
        //given
        onGiven();
        itunesSearchFeed.results = Arrays.asList(mock(ItunesSearchFeed.Song.class));
        songList = Arrays.asList(mock(Label.class));
        controller.songList = songList;
        artist = Arrays.asList(mock(Label.class));
        controller.artist = artist;
        image = Arrays.asList(mock(ImageView.class));
        controller.image = image;

        //when
        controller.onItunesSearchFeed(itunesSearchFeed);

        //then
        verify(songList.get(0)).setText((String.valueOf(itunesSearchFeed.results.get(0).trackName)));
        verify(artist.get(0)).setText((String.valueOf(itunesSearchFeed.results.get(0).artistName)));
        verify(image.get(0)).setImage(any(Image.class));
    }

    @Test
    public void onAlbumSearchFeed(){
        //given
        onGiven();
        albumSearchFeed.results = Arrays.asList(mock(AlbumSearchFeed.Album.class));
        albumNames = Arrays.asList(mock(Label.class));
        controller.albumNames = albumNames;

        //when
        controller.onAlbumSearchFeed(albumSearchFeed);

        //then
        verify(albumNames.get(0)).setText((String.valueOf(albumSearchFeed.results.get(0).collectionName)));
    }

    public void onError(){
        // given
        onGiven();
        Throwable throwable = mock(Throwable.class);

        // when
        controller.onError(throwable);

        //then
        verify(throwable).printStackTrace();
    }
    public void onGiven(){
        service = mock(ItunesSearchService.class);
        controller = new ItunesSearchController(service);
        factory = mock(ItunesSearchServiceFactory.class);
        controller.factory = factory;
        itunesSearchFeed = mock(ItunesSearchFeed.class);
        albumSearchFeed = mock(AlbumSearchFeed.class);
    }
}
