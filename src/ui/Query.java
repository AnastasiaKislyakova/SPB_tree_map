package ui;


import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;


public class Query extends HBox{


    private Circle color = new Circle();
    private Text species = new Text();
    private CheckBox visibility = new CheckBox();

    public Query(String species, Color color){
        super(15);
        this.species.setText(species);

        this.color.setRadius(7);
        this.color.setFill(color);
        this.color.setStroke(Color.BLACK);

        this.visibility.setSelected(true);

        getChildren().addAll(this.species, this.color, visibility);


    }

    public CheckBox getVisibility() {
        return visibility;
    }

    public void setSelected(boolean value) {
        visibility.setSelected(value);
    }
}
