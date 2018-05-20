package ui;


import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;



public class Query extends HBox {

    //private Circle color = new Circle();

    private ImageView marker;
    private Text species = new Text();
    private CheckBox visibility = new CheckBox();
    private Button delete = new Button("X");

    public Query(String species, Image image){
        super(20);
        this.species.setText(species);
//        this.color.setRadius(7);
//        this.color.setFill(color);
//        this.color.setStroke(Color.BLACK);
        this.marker = new ImageView(image);
        this.visibility.setSelected(true);

        getChildren().addAll(this.species, marker, visibility);
    }



    public CheckBox getVisibility() {
        return visibility;
    }

    public void setSelected(boolean value) {
        visibility.setSelected(value);
    }
}
