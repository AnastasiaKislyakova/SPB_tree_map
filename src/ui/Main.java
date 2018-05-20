package ui;

import com.lynden.gmapsfx.GoogleMapView;
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

import java.util.List;

public class Main extends Application {

    SPBTreeDAO treeDAO = new SPBTreeDAO();

    // example of work with database
    public void doSomething() throws DBException {
        List<Tree> trees = treeDAO.getAllTrees();
        trees.forEach(System.out::println);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        doSomething();
        primaryStage.setTitle("Карта деревьев");
        primaryStage.getIcons().add(new Image("file:src/leaf.png"));
       // primaryStage.setResizable(true);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainStage.fxml"));
        //BorderPane root = loader.load();
        AnchorPane root = loader.load();

        MapCreator mapController = new MapCreator();
        GoogleMapView m = mapController.getMapView();

        MainStageController controller = loader.getController();
        controller.setMap(m);
        controller.setPrimaryStage(primaryStage);

//        root.getChildren().add(m);
//        m.setMaxSize(800, 400);
//        root.setCenter(m);

//        final ui.MainController controller = loader.getController();
//        controller.ActivateFilterPanel();
        primaryStage.setScene(new Scene(root));
        //controller.setStage(primaryStage);


        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


}
