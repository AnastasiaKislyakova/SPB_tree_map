package ui;

import com.lynden.gmapsfx.GoogleMapView;
import javafx.animation.TranslateTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class mainStageController implements Initializable {

    private Stage primaryStage;

    @FXML
    private StackPane mainContent;

    @FXML
    private Button filterButton;
    
    @FXML
    private Button addQueryButton;

    @FXML
    private AnchorPane navList;

    @FXML
    private ListView<Query> queryList = new ListView<>(FXCollections.observableArrayList());

    @FXML
    private ColorPicker picker;
    private Color pickedColor;

    @FXML
    private TextField pickedSpecies;

    @FXML
    private Button recognizeButton;


    private TranslateTransition openNav;
    private TranslateTransition closeNav;

    public void setPrimaryStage(Stage stage){
        this.primaryStage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        queryList.setEditable(true);
        filterButton.setOnAction(this::openFilterPanel);
        addQueryButton.setOnAction(this::addNewQuery);
        recognizeButton.setOnAction(this::openWindow);

        openNav=new TranslateTransition(new Duration(350), navList);
        openNav.setToX(0);
        closeNav=new TranslateTransition(new Duration(350), navList);

        picker.setOnAction(e -> {pickedColor = picker.getValue();});

    }

    @FXML
    public void openFilterPanel(javafx.event.ActionEvent actionEvent) {
        if(navList.getTranslateX()!=0){
            openNav.play();
        }else{
            closeNav.setToX(-(navList.getWidth()));
            closeNav.play();
        }

    }

    public void setMap(GoogleMapView map) {
        this.mainContent.getChildren().add(map);
    }

    @FXML
    public void addNewQuery(ActionEvent actionEvent) {

        if (!pickedSpecies.getText().isEmpty()){
            Query q = new Query(pickedSpecies.getText(), pickedColor);
            queryList.getItems().add(q);
        }
    }


    public void openWindow(ActionEvent actionEvent) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("recognizingSystem.fxml"));
            Stage secondStage = new Stage();
            AnchorPane root = loader.load();
            RecognizingSystemController controller = loader.getController();
            secondStage.setScene(new Scene(root));
            secondStage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }


}
