package ui;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;
import netscape.javascript.JSObject;

<<<<<<< HEAD:src/ui/MapCreator.java
=======

>>>>>>> 4381b6094897687ab124536db1f839d3380ce3d8:src/ui/MapCreator.java
public class MapCreator implements MapComponentInitializedListener {
    private GoogleMapView mapView;
    private GoogleMap map;

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
        MarkerOptions markerOptions = new MarkerOptions();


        markerOptions.position( new LatLong(59.8977, 30.33501) )
                .visible(Boolean.TRUE)
                .icon(util.iconPath + util.MarkerStyle.orange + ".png")
                .animation(Animation.NULL);

        Marker marker = new Marker( markerOptions );

        map.addUIEventHandler(marker,  UIEventType.click, (JSObject event) -> {
            marker.setVisible(false);
        } );

        map.addMarker(marker);

    }
}