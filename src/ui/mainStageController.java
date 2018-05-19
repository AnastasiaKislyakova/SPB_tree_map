package ui;

import com.lynden.gmapsfx.GoogleMapView;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class mainStageController implements Initializable{

    @FXML
    private StackPane mainContent;

    @FXML
    private Button filterButton;

    @FXML
    private AnchorPane navList;

    private TranslateTransition openNav;
    private TranslateTransition closeNav;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        filterButton.setOnAction(this::openFilterPanel);
        openNav=new TranslateTransition(new Duration(350), navList);
        openNav.setToX(0);
        closeNav=new TranslateTransition(new Duration(350), navList);
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


}
