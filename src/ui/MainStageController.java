package ui;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.javascript.object.MapShape;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
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
import javafx.scene.Node;
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
import javax.xml.soap.Text;
import java.net.URL;
import java.util.*;

public class MainStageController implements Initializable {

    private Stage primaryStage;
    private Image selectedMarker;
    private String markerColor;
    private MapCreator map;
    private ImageView m = new ImageView();

    @FXML
    private Button treesAmount;

    @FXML
    private Button speciesAmount;

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

    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        markersBox.setOnMouseEntered(this::listenIcons);
        queryList.setOnMouseMoved(Event::consume);


        filterButton.setOnAction(this::openFilterPanel);
        addQueryButton.setOnAction(this::addNewQuery);
        recognizeButton.setOnAction(this::openWindow);

        openNav = new TranslateTransition(new Duration(350), navList);
        openNav.setToX(0);
        closeNav = new TranslateTransition(new Duration(350), navList);

    }


    @FXML
    public void openFilterPanel(javafx.event.ActionEvent actionEvent) {

        if (navList.getTranslateX() != 0) {
            openNav.play();
        } else {
            closeNav.setToX(-(navList.getWidth()));
            closeNav.play();
        }

    }

    public void setMap(MapCreator map) {
        this.map = map;
        this.mainContent.getChildren().add(map.getMapView());
        System.out.println(String.valueOf(map.getStatistics().getNumberOfSpecies()));
        speciesAmount.setText(String.valueOf(map.getStatistics().getNumberOfSpecies()));

        treesAmount.setText(String.valueOf(map.getStatistics().getNumberOfTrees()));
    }

    public MapCreator getMap() {
        return map;
    }

    public void updateVisibility(boolean state) {
        MarkerOptions mo = new MarkerOptions();
        for (TreeMarker tm : map.getMarkers()) {
            tm.setVisible(state);
            tm.setOptions(mo.icon(util.iconPath + util.MarkerStyle.red + ".png"));
        }
    }

    @FXML
    public void addNewQuery(ActionEvent actionEvent) {
        this.m.setBlendMode(null);
        if (queryList.getItems().isEmpty()){
            updateVisibility(false);
        }
        Query q = new Query(choiceSpesies.getValue().toString(), selectedMarker, this, markerColor);
        queryList.getItems().add(q);
        q.filter(true);
    }

    public void deleteQuery(Query q) {
        queryList.getItems().remove(q);
        q.filter(false);
        if (queryList.getItems().isEmpty()) {
            updateVisibility(true);

        }
    }

    public void openWindow(ActionEvent actionEvent) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("recognizingSystem.fxml"));
            Stage secondStage = new Stage();
            AnchorPane root = loader.load();
            //RecognizingSystemController controller = loader.getController();
            secondStage.setScene(new Scene(root));
            secondStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void setSelected(Image selected, String color, ImageView imageView) {
        this.selectedMarker = selected;
        markerColor = util.iconPath + color + ".png";
        this.m = imageView;

    }

    public void listenIcons(MouseEvent mouseEvent) {



        red.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                m.setBlendMode(null);
                setSelected(red.getImage(), "red", red);
                m.setBlendMode(BlendMode.ADD);
            }
        });

        yellow.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                m.setBlendMode(null);
                setSelected(yellow.getImage(), "yellow", yellow);
                m.setBlendMode(BlendMode.ADD);
            }
        });

        blue.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                m.setBlendMode(null);
                setSelected(blue.getImage(), "blue", blue);
                m.setBlendMode(BlendMode.ADD);

            }
        });

        purple.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                m.setBlendMode(null);
                setSelected(purple.getImage(), "purple", purple);
                m.setBlendMode(BlendMode.ADD);
            }
        });

        pink.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                m.setBlendMode(null);
                setSelected(pink.getImage(), "pink",pink);
                m.setBlendMode(BlendMode.ADD);
            }
        });

        orange.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                m.setBlendMode(null);
                setSelected(orange.getImage(), "orange", orange);
                m.setBlendMode(BlendMode.ADD);
            }
        });

        lightblue.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                m.setBlendMode(null);
                setSelected(lightblue.getImage(), "lightblue", lightblue);
                m.setBlendMode(BlendMode.ADD);
            }
        });

        green.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                m.setBlendMode(null);
                setSelected(green.getImage(), "green", green);;
                m.setBlendMode(BlendMode.ADD);
            }
        });

}


}

