package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class util {

    public static final String iconPath = "http://maps.google.com/mapfiles/ms/icons/";

    public enum MarkerStyle {
        blue,
        yellow,
        red,
        purple,
        pink,
        orange,
        lightblue,
        green
    }

    public static final List<Image> iconList = Collections.unmodifiableList(
            new ArrayList<Image>() {{
                add(new Image(iconPath + MarkerStyle.blue + ".png"));
                add(new Image(iconPath + MarkerStyle.yellow + ".png"));
            }});

    public static final ObservableList<String> species =
            FXCollections.observableArrayList("Берёза", "Дуб", "Каштан");
}
