package yahav.itunessearch;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ItunesSearchController {
    @FXML
    TextField songAnswer;
    @FXML
    Label songList;

    public void onSubmit(MouseEvent mouseEvent) {
        ItunesSearchServiceFactory factory = new ItunesSearchServiceFactory();
        ItunesSearchService service = factory.newInstance();

        Disposable currentDisposable = service.getSong(songAnswer.getText())
                // request the data in the background
                .subscribeOn(Schedulers.io())
                // work with the data in the foreground
                .observeOn(Schedulers.trampoline())
                // work with the feed whenever it gets downloaded
                .subscribe(this::onItunesSearchFeed, this::onError);
    }

    private void onItunesSearchFeed(ItunesSearchFeed itunesSearchFeed) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                songList.setText(String.valueOf(itunesSearchFeed.trackName));
            }
        });
    }

    private void onError(Throwable throwable) {
    }

}
