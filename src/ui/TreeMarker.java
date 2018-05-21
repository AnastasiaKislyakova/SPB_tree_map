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

<<<<<<< HEAD
    public String getDescription(){
        return "Вид: " + tree.getSpecies().getNameRus() + " (" + tree.getSpecies().getNameLat()+"), Диаметр:" + tree.getTrunk();
    }
=======
>>>>>>> f8bd51bfb86ffa4c7b24b3b50c30ca883c101712

}
