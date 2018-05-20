package ui;

import com.lynden.gmapsfx.GoogleMapView;
import javafx.animation.TranslateTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;

import javax.swing.*;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

public class MainStageController implements Initializable {

    private Stage primaryStage;
    private Image selectedMarker;
    private String markerColor;

    @FXML
    private ChoiceBox choiceSpesies;

    @FXML
    private ImageView red;

    @FXML
    private ImageView yellow;
    @FXML
    private ImageView blue;
    @FXML
    private ImageView purple;
    @FXML
    private ImageView pink;
    @FXML
    private ImageView orange;
    @FXML
    private ImageView lightblue;
    @FXML
    private ImageView green;

    @FXML
    private StackPane mainContent;

    @FXML
    private Button filterButton;
    
    @FXML
    private Button addQueryButton;

    @FXML
    private VBox markersBox;

    @FXML
    private AnchorPane navList;

    @FXML
    private ListView<Query> queryList = new ListView<>();

    @FXML
    private Button recognizeButton;


    private TranslateTransition openNav;
    private TranslateTransition closeNav;

    public void setPrimaryStage(Stage stage){
        this.primaryStage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        markersBox.setOnMouseEntered(this::listenIcons);

        filterButton.setOnAction(this::openFilterPanel);
        addQueryButton.setOnAction(this::addNewQuery);
        recognizeButton.setOnAction(this::openWindow);

        openNav=new TranslateTransition(new Duration(350), navList);
        openNav.setToX(0);
        closeNav=new TranslateTransition(new Duration(350), navList);

    }


    @FXML
    public void openFilterPanel(javafx.event.ActionEvent actionEvent) {

        if(navList.getTranslateX()!=0){
            openNav.play();
            }
        else {
            closeNav.setToX(-(navList.getWidth()));
            closeNav.play();
        }

    }

    public void setMap(GoogleMapView map) {
        this.mainContent.getChildren().add(map);
    }

    @FXML
    public void addNewQuery(ActionEvent actionEvent) {
        Query q = new Query(choiceSpesies.getValue().toString(), selectedMarker);
        queryList.getItems().add(q);
    }


    public void openWindow(ActionEvent actionEvent) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("recognizingSystem.fxml"));
            Stage secondStage = new Stage();
            AnchorPane root = loader.load();
            //RecognizingSystemController controller = loader.getController();
            secondStage.setScene(new Scene(root));
            secondStage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }


    private void setSelected(Image selected, String color) {
            this.selectedMarker = selected;
            markerColor = util.iconPath + color + ".png";
    }

    public void listenIcons(MouseEvent mouseEvent) {

        red.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if (red.getBlendMode() ==  null){
                    setSelected(red.getImage(), "red");

                } else {
                    red.setBlendMode(null);
                }

                event.consume();
            }
        });

        yellow.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (yellow.getBlendMode() ==  null){
                    setSelected(yellow.getImage(), "yellow");

                } else {
                    yellow.setBlendMode(null);
                }

                event.consume();
            }
        });

        blue.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (blue.getBlendMode() ==  null){
                    setSelected(blue.getImage(), "blue");

                } else {
                    blue.setBlendMode(null);
                }

                event.consume();
            }
        });

        purple.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (purple.getBlendMode() ==  null){
                    setSelected(purple.getImage(), "purple");

                } else {
                    purple.setBlendMode(null);
                }

                event.consume();
            }
        });

        pink.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (pink.getBlendMode() ==  null){
                    setSelected(pink.getImage(), "pink");

                } else {
                    pink.setBlendMode(null);
                }

                event.consume();
            }
        });

        orange.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (orange.getBlendMode() ==  null){
                    setSelected(orange.getImage(), "orange");

                } else {
                    orange.setBlendMode(null);
                }

                event.consume();
            }
        });

        lightblue.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (lightblue.getBlendMode() ==  null){
                    setSelected(lightblue.getImage(), "lightblue");

                } else {
                    lightblue.setBlendMode(null);
                }

                event.consume();
            }
        });

        green.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (green.getBlendMode() ==  null){
                    setSelected(green.getImage(), "green");

                } else {
                    green.setBlendMode(null);
                }

                event.consume();
            }
        });

    }
}
