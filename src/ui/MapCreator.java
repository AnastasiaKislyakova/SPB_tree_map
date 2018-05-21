package ui;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;

import db.DBException;
import db.DBService;
import db.DBServiceImpl;
import db.SPBTreeDAO;
import model.Tree;
import netscape.javascript.JSObject;

import java.util.ArrayList;
import java.util.List;

public class MapCreator implements MapComponentInitializedListener {

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

        MarkerOptions markerOptions = new MarkerOptions();
      //  SPBTreeDAO treeDAO = new SPBTreeDAO();
        DBService  db = new DBServiceImpl();

        try {
          //  trees = treeDAO.getAllTrees();
            trees = db.getAllMarkers();
            for (Tree t : trees){
                markerOptions.position( new LatLong(t.getCoordinate().getLatitude(), t.getCoordinate().getLongitude()) )
                        .visible(Boolean.TRUE)
                        .icon(util.iconPath + util.MarkerStyle.red
                                + ".png")
                        .animation(Animation.NULL);

                TreeMarker m = new TreeMarker( markerOptions, t );

                map.addUIEventHandler(m,  UIEventType.click, (JSObject event) -> {

                    InfoWindowOptions options = new InfoWindowOptions()
                            .position( new LatLong(m.getTree().getCoordinate().getLatitude(), m.getTree().getCoordinate().getLongitude()))
                            .content("Диаметр: " + m.getTree().getTrunk() + "\n"
                            );
                    InfoWindow window = new InfoWindow(options);
                    window.open(map, m);
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

