package ui;

import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.Animation;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
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
        markerOptions.position( new LatLong(tree.getCoordinate().getLatitude(), tree.getCoordinate().getLongitude()) )
                .visible(Boolean.TRUE)
                .icon(util.iconPath + util.MarkerStyle.orange + ".png")
                .animation(Animation.NULL);
    }

    public Tree getTree() {
        return tree;
    }


    public void paint(){

//        map.addUIEventHandler(this,  UIEventType.click, (JSObject event) -> {
//            this.setVisible(false);
//        } );
//
//        map.addMarker(this);
    }
}
