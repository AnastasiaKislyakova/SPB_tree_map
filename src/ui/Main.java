package ui;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.*;
import db.DBException;
import db.DBService;
import db.DBServiceImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.TreeMarker;

import java.util.List;

public class Main extends Application {

    DBService dbService = new DBServiceImpl();

    // example of work with database
    public void doSomething() throws DBException {
        List<TreeMarker> markers = dbService.getAllMarkers();
        markers.forEach(System.out::println);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        doSomething();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        BorderPane mainPane =  loader.load();
        primaryStage.setTitle("Карта деревьев");

        final ui.Controller controller = loader.getController();


        primaryStage.setScene(new Scene(mainPane));
        controller.setStage(primaryStage);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


}
