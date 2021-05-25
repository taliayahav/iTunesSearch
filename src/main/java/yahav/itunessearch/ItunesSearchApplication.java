package yahav.itunessearch;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ItunesSearchApplication extends Application {

        @Override
        public void start(Stage stage) throws Exception {

            ItunesSearchService service = new ItunesSearchServiceFactory().newInstance();
            ItunesSearchController controller = new ItunesSearchController(service);
            FXMLLoader loader = new FXMLLoader(getClass().getResource( "/itunes_application.fxml"));
            loader.setController(controller);
            Parent parent = loader.load();

            Scene scene = new Scene(parent, 800, 500);

            stage.setTitle("iTunes");
            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }

