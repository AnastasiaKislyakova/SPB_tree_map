package model;

import javafx.beans.NamedArg;
import javafx.scene.image.Image;

/**
 * Created by user on 21.05.2018.
 */
public class LeafPhoto extends Image {
    private final String photo;
    public LeafPhoto(@NamedArg("url") String url) {
        super(url);
        photo = url;
    }

    public String getPhoto() {
        return photo;
    }

}
