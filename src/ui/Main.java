package ui;

import com.lynden.gmapsfx.GoogleMapView;
import db.DBException;
import db.DBService;
import db.DBServiceImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.TreeMarker;

import java.util.List;

public class Main extends Application {

    DBService dbService = new DBServiceImpl();

    GoogleMapView mapView;

    // example of work with database
    public void doSomething() throws DBException {
        List<TreeMarker> markers = dbService.getAllMarkers();
        markers.forEach(System.out::println);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        doSomething();
        primaryStage.setTitle("Карта деревьев");
        primaryStage.getIcons().add(new Image("file:src/leaf.png"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainStage.fxml"));
        AnchorPane root = loader.load();

        MapCreator mapController = new MapCreator();
        mapView = mapController.getMapView();

        MainStageController controller = loader.getController();
        controller.setMap(mapView);
        controller.setPrimaryStage(primaryStage);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private void createMarkers(){

    }


}
