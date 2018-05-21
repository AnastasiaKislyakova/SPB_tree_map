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
    private String selectedSpecies;

    public Query(String species, Image image, MainStageController controller) {
        super(15);
        this.species.setText(species);
        this.selectedSpecies = species;
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
                filter(visibility.isSelected());
            }
        });

        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                terminate();
            }
        });
    }

    private boolean matches(String species) {
        return selectedSpecies.equals(species);
    }

    public void filter(Boolean selected) {
        for (TreeMarker tm : controller.getMap().getMarkers()) {
            if (selected && matches(tm.getTree().getSpecies().getNameRus())) {
                tm.setVisible(true);
            } else if ((!selected && matches(tm.getTree().getSpecies().getNameRus()))) {
                tm.setVisible(false);
            }
        }
    }

    private void terminate() {
        controller.deleteQuery(this);
    }

    public String getSpecies() {
        return selectedSpecies;
    }
}
