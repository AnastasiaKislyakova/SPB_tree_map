package ui;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;
<<<<<<< HEAD
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Coordinate;
import model.Species;
import model.Tree;
import netscape.javascript.JSObject;


public class MapCreator implements MapComponentInitializedListener  {
=======
import com.sun.org.apache.xpath.internal.SourceTree;
import db.DBException;
import db.SPBTreeDAO;
import model.Tree;
import netscape.javascript.JSObject;

import java.util.ArrayList;
import java.util.List;


public class MapCreator implements MapComponentInitializedListener {
>>>>>>> b6af162ff190fac50d38bde236fb2ae9e3b934dc
    private GoogleMapView mapView;
    public GoogleMap map;
    public List<Tree> trees = new ArrayList<Tree>();
    public List<TreeMarker> markers = new ArrayList<TreeMarker>();


    MapCreator() {
        mapView = new GoogleMapView();
        mapView.addMapInitializedListener(this);
    }

    GoogleMapView getMapView(){
        return mapView;
    }

    @Override
    public void mapInitialized() {
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(59.9342, 30.33501))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(true)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(12);

        map = mapView.createMap(mapOptions);

        //Add a marker to the map
//        MarkerOptions markerOptions = new MarkerOptions();
//
//
//        markerOptions.position( new LatLong(59.8977, 30.33501) )
//                .visible(Boolean.TRUE)
//                .icon(util.iconPath + util.MarkerStyle.orange + ".png")
//                .animation(Animation.NULL);
//
//        TreeMarker marker = new TreeMarker(markerOptions, new Tree(0, new Species("Oak", "Дуб"), new Coordinate(59.866465, 30.309587), 500));
//
//        marker.paint(this);


//        markerOptions.position( new LatLong(59.8777, 30.33501) )
//                .visible(Boolean.TRUE)
//                .icon(util.iconPath + util.MarkerStyle.blue + ".png")
//                .animation(Animation.NULL);

//        Marker marker1 = new Marker(markerOptions);
//
//        map.addUIEventHandler(marker,  UIEventType.click, (JSObject event) -> {
//            marker.setVisible(false);
//        } );
//
//        map.addMarker(marker);
//        map.addMarker(marker1);
//
//        InfoWindowOptions options = new InfoWindowOptions()
//                .position(new LatLong(59.8777, 30.33501))
//                .content("Test");

        SPBTreeDAO treeDAO = new SPBTreeDAO();
        try {
            trees = treeDAO.getAllTrees();
            for (Tree t : trees){
                markerOptions.position( new LatLong(t.getCoordinate().getLatitude(), t.getCoordinate().getLongitude()) )
                        .visible(Boolean.TRUE)
                        .icon(util.iconPath + util.MarkerStyle.red
                                + ".png")
                        .animation(Animation.NULL);

                TreeMarker m = new TreeMarker( markerOptions, t );

                map.addUIEventHandler(m,  UIEventType.click, (JSObject event) -> {
                    m.setVisible(false);
                } );

                map.addMarker(m);
                markers.add(m);
            }
        }
        catch (DBException e) {
            System.out.println("DBException from getALLTrees");;
        }


    }
}
