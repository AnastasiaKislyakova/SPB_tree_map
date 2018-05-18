package db;

import model.Coordinate;
import model.TreeMarker;

import java.util.List;

/**
 * Created by user on 18.05.2018.
 */
public interface DBService {
    List<TreeMarker> getAllMarkers() throws DBException;

    void insertMarker(TreeMarker marker) throws DBException;

    void deleteMarker(long id) throws DBException;

    TreeMarker getMarkerByCoordAndSpecies(Coordinate coordinate, String species) throws DBException;

}