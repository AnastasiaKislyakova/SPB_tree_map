package ui;

import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;
import model.LeafPhoto;
import model.Tree;
import netscape.javascript.JSObject;

/**
 * Created by user on 20.05.2018.
 */
public class TreeMarker extends Marker{

    private final Tree tree;

    public TreeMarker(MarkerOptions markerOptions, Tree tree) {
        super(markerOptions);
        this.tree = tree;
    }

    public Tree getTree() {
        return tree;
    }


}
