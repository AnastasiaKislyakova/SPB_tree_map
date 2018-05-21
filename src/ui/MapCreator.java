package ui;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;

import model.Tree;
import netscape.javascript.JSObject;


import db.DBException;
import db.DBService;
import db.DBServiceImpl;
import db.SPBTreeDAO;

import java.util.ArrayList;
import java.util.List;

public class MapCreator implements MapComponentInitializedListener {

    private GoogleMapView mapView;
    public GoogleMap map;
    private List<Tree> trees = new ArrayList<Tree>();
    private List<TreeMarker> markers = new ArrayList<TreeMarker>();
    private Statistics statistics = new Statistics(0,0);

    public Statistics getStatistics() {
        return statistics;
    }

    public List<TreeMarker> getMarkers() {
        return markers;
    }

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

        mapOptions.center(new LatLong(59.869754, 30.308350))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(true)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(16);

        map = mapView.createMap(mapOptions);

        //Add a marker to the map
        MarkerOptions markerOptions = new MarkerOptions();

        SPBTreeDAO db = new SPBTreeDAO();
      //  DBService  db = new DBServiceImpl();


        try {
          //  trees = treeDAO.getAllTrees();
            trees = db.getAllTrees();
            for (Tree t : trees){
                markerOptions.position( new LatLong(t.getCoordinate().getLatitude(), t.getCoordinate().getLongitude()) )
                        .visible(Boolean.TRUE)
                        .icon(util.iconPath + util.MarkerStyle.red
                                + ".png")
                        .animation(Animation.NULL);

                TreeMarker m = new TreeMarker( markerOptions, t );

                map.addUIEventHandler(m,  UIEventType.click, (JSObject event) -> {

                    String html =
                            "<html>" +
                                    "<img src='" +
                                    m.getTree().getLeafPhoto().getPhoto() +
                                    "'width=50 height=auto > ";

                    InfoWindowOptions options = new InfoWindowOptions()
                            .position( new LatLong(m.getTree().getCoordinate().getLatitude(), m.getTree().getCoordinate().getLongitude()))
                            .content("Вид: "+ m.getTree().getSpecies().getNameRus() + "\n" + "Диаметр: " + m.getTree().getTrunk() + "\n"
                                    + html
                            );
                    InfoWindow window = new InfoWindow(options);
                    window.open(map, m);
                } );

                map.addMarker(m);
                markers.add(m);
            }
            statistics = new Statistics(markers.size(), db.getNumberOfSpecies());
        }
        catch (DBException e) {
            System.out.println("DBException from getALLTrees");;
        }


    }
}

