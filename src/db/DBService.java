package db;

import com.lynden.gmapsfx.javascript.object.LatLong;
import model.Coordinate;
import model.Species;
import model.TreeMarker;

import java.util.List;

/**
 * Created by user on 18.05.2018.
 */
public interface DBService {
    List<TreeMarker> getAllMarkers() throws DBException;

    void insertMarker(TreeMarker marker) throws DBException;

    void deleteMarker(long id) throws DBException;

    TreeMarker getMarkerByCoordAndSpecies(Coordinate coordinate, Species species) throws DBException;

}