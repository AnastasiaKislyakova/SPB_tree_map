package ui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class Controller {
    private Stage stage;

    @FXML
    private BorderPane mainPane;

    @FXML
    private Label label;

    @FXML
    private Button recognize_btn;

    @FXML
    private Button filter_btn;

    @FXML
    private TextArea statistics;

    @FXML
    private Pane map;


    @FXML
    void initialize() {
        mapController mapController = new mapController();
        map = new Pane(mapController.getMapView());

        mainPane.setCenter(map);
        //AnchorPane.setTopAnchor(map.getMapView(), 0.0);
        //leftPane.getChildren().add(map.getMapView());
        //leftPane = new AnchorPane(map.getMapView());
    }

    void setStage(Stage stage){
        this.stage = stage;
    }

    @FXML
    void showMarkerInfo(){}

    @FXML
    void openFilterPanel(){

    }

}
