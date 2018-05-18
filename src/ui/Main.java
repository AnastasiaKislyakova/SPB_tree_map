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
import javafx.stage.Stage;
import model.TreeMarker;

import java.util.List;

public class Main extends Application implements MapComponentInitializedListener {
    GoogleMap map;
    GoogleMapView mapView;

    DBService dbService = new DBServiceImpl();

    // example of work with database
    public void doSomething() throws DBException {
        List<TreeMarker> markers = dbService.getAllMarkers();
        markers.forEach(System.out::println);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        doSomething();

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        mapView = new GoogleMapView();
        mapView.addMapInitializedListener(this);

        //mapView.setKey("It's simply for web");

        Scene scene = new Scene(mapView);

        primaryStage.setTitle("Saint-Petersburg trees");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void mapInitialized() {
        MapOptions mapOptions;
        mapOptions = new MapOptions();

        mapOptions.center(new LatLong(59.9342, 30.33501))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(12);

        map = mapView.createMap(mapOptions);

        //Add a marker to the map
        MarkerOptions markerOptions = new MarkerOptions();

        markerOptions.position( new LatLong(47.6, -122.3) )
                .visible(Boolean.TRUE)
                .title("My Marker");

        Marker marker = new Marker( markerOptions );

        map.addMarker(marker);
    }
}
