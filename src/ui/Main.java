package ui;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.List;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("Карта деревьев");
        primaryStage.getIcons().add(new Image("file:src/leaf.png"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainStage.fxml"));

        AnchorPane root = loader.load();

        MainStageController controller = loader.getController();

        MapCreator mapController = new MapCreator(controller);

        controller.setMap(mapController);

        controller.setPrimaryStage(primaryStage);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }

}
