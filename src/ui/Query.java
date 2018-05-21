package ui;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import javafx.scene.text.Text;



public class Query extends HBox {


    private ImageView marker;
    private Text species = new Text();
    private CheckBox visibility = new CheckBox();
    private Button delete = new Button("X");
    private MainStageController controller;

    public Query(String species, Image image, MainStageController controller){
        super(15);
        this.species.setText(species);

        this.marker = new ImageView(image);
        this.visibility.setSelected(true);

        getChildren().addAll(this.species, marker, visibility, delete);

        this.controller = controller;
        startListen();
    }

    private void startListen() {
        visibility.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                controller.updateVisibility(visibility.isSelected(),  species.toString());
            }
        });

        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                del();
            }
        });
    }

    private void del() {
        controller.deleteQuery(this);
    }

    public String getSpecies() {
        return species.toString();
    }
}
