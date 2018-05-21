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
//    SPBTreeDAO treeDAO = new SPBTreeDAO();
=======

>>>>>>> b6af162ff190fac50d38bde236fb2ae9e3b934dc
    DBService db = new DBServiceImpl();
    //List<TreeMarker> markers = new ArrayList<>();

    GoogleMapView mapView;

<<<<<<< HEAD

=======
>>>>>>> b6af162ff190fac50d38bde236fb2ae9e3b934dc
    // example of work with database
    public void doSomething() throws DBException {
     //   List<Tree> trees = treeDAO.getAllTrees();
       // trees.forEach(System.out::println);
        List<Tree> trees = db.getAllMarkers();
        trees.forEach(System.out::println);
    }
<<<<<<< HEAD
=======

>>>>>>> b6af162ff190fac50d38bde236fb2ae9e3b934dc

    @Override
    public void start(Stage primaryStage) throws Exception{

<<<<<<< HEAD
//        List<Tree> trees = treeDAO.getAllTrees();
=======
>>>>>>> b6af162ff190fac50d38bde236fb2ae9e3b934dc

        primaryStage.setTitle("Карта деревьев");
        primaryStage.getIcons().add(new Image("file:src/leaf.png"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainStage.fxml"));
        AnchorPane root = loader.load();

        MapCreator mapController = new MapCreator();
<<<<<<< HEAD
=======
        mapView = mapController.getMapView();
>>>>>>> b6af162ff190fac50d38bde236fb2ae9e3b934dc

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
