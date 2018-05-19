package ui;

import com.lynden.gmapsfx.GoogleMapView;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class mainStageController implements Initializable{

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


    private TranslateTransition openNav;
    private TranslateTransition closeNav;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        queryList.setEditable(true);
        filterButton.setOnAction(this::openFilterPanel);
        addQueryButton.setOnAction(this::addNewQuery);
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
        Query q = new Query(pickedSpecies.getText(), pickedColor);
        queryList.getItems().add(q);
    }
}
