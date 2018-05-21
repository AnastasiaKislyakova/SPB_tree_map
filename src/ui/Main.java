package ui;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.javascript.event.GMapMouseEvent;
import com.lynden.gmapsfx.javascript.object.Animation;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import db.DBException;
import db.DBService;
import db.DBServiceImpl;
import db.SPBTreeDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Coordinate;
import model.Species;
import model.Tree;

import java.util.ArrayList;
import java.util.List;

import static javax.swing.text.html.HTML.Tag.HEAD;

public class Main extends Application {

<<<<<<< HEAD
=======

>>>>>>> cb69cb1b0e31cc5e01caeef76094c83f14952292
//    SPBTreeDAO treeDAO = new SPBTreeDAO();

    DBService db = new DBServiceImpl();
    //List<TreeMarker> markers = new ArrayList<>();

    GoogleMapView mapView;

<<<<<<< HEAD

=======
>>>>>>> cb69cb1b0e31cc5e01caeef76094c83f14952292
    // example of work with database
    public void doSomething() throws DBException {
     //   List<Tree> trees = treeDAO.getAllTrees();
       // trees.forEach(System.out::println);
        List<Tree> trees = db.getAllMarkers();
        trees.forEach(System.out::println);
    }


    @Override
    public void start(Stage primaryStage) throws Exception{


//        List<Tree> trees = treeDAO.getAllTrees();


        primaryStage.setTitle("Карта деревьев");
        primaryStage.getIcons().add(new Image("file:src/leaf.png"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainStage.fxml"));
        AnchorPane root = loader.load();

        MapCreator mapController = new MapCreator();
<<<<<<< HEAD

        mapView = mapController.getMapView();

=======
>>>>>>> cb69cb1b0e31cc5e01caeef76094c83f14952292

        MainStageController controller = loader.getController();
        controller.setMap(mapController);

        controller.setPrimaryStage(primaryStage);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }

    private void createMarkers(MapCreator mapController){
//        for (TreeMarker tm : markers){
//            tm.paint(mapController);
//        }
    }


}
