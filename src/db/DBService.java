package db;

import model.Coordinate;
import model.Species;
import model.Tree;

import java.util.List;

/**
 * Created by user on 18.05.2018.
 */
public interface DBService {
    List<Tree> getAllMarkers() throws DBException;

    void insertMarker(Tree marker) throws DBException;

    void deleteMarker(long id) throws DBException;

    Tree getMarkerByCoordAndSpecies(Coordinate coordinate, Species species) throws DBException;

}