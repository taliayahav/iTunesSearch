package yahav.itunessearch;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class ItunesSearchController {
    @FXML
    TextField answerResult;
    @FXML
    ArrayList<Label> songList;
    @FXML
    ArrayList<Label> artist;
    @FXML
    ArrayList<ImageView> image;
    @FXML
    ArrayList<Label> albumNames;
    @FXML
    ArrayList<ImageView> albumImages;

    ItunesSearchService service;

//    public ItunesSearchController(ItunesSearchService service) {
//        this.service = service;
//    }

    public void onSubmit(MouseEvent mouseEvent) {
        ItunesSearchServiceFactory factory = new ItunesSearchServiceFactory();
        ItunesSearchService service = factory.newInstance();

        Disposable disposable = service.getSong(answerResult.getText())
                // request the data in the background
                .subscribeOn(Schedulers.io())
                // work with the data in the foreground
                .observeOn(Schedulers.trampoline())
                // work with the feed whenever it gets downloaded
                .subscribe(this::onItunesSearchFeed, this::onError);

        Disposable currentDisposable = service.getAlbum(answerResult.getText())
                // request the data in the background
                .subscribeOn(Schedulers.io())
                // work with the data in the foreground
                .observeOn(Schedulers.trampoline())
                // work with the feed whenever it gets downloaded
                .subscribe(this::onAlbumSearchFeed, this::onError);
    }

    private void onAlbumSearchFeed(AlbumSearchFeed albumSearchFeed) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                for (int i= 0; i <= 5; i++) {
                    albumNames.get(i).setText(String.valueOf(albumSearchFeed.results.get(i).collectionName));
                    albumImages.get(i).setImage(new Image(albumSearchFeed.results.get(i).artworkUrl100));
                }
            }
        });
    }

    private void onItunesSearchFeed(ItunesSearchFeed itunesSearchFeed) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                for (int i= 0; i <= 5; i++) {
                    songList.get(i).setText(String.valueOf(itunesSearchFeed.results.get(i).trackName));
                    artist.get(i).setText(String.valueOf(itunesSearchFeed.results.get(i).artistName));
                    image.get(i).setImage(new Image(itunesSearchFeed.results.get(i).artworkUrl60));
                }
            }
        });
    }

    private void onError(Throwable throwable) {
    }
}
