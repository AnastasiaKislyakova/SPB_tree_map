package db;

import com.lynden.gmapsfx.javascript.object.LatLong;
import model.Coordinate;
import model.Species;
import model.TreeMarker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by user on 18.05.2018.
 */
public class DBServiceImpl implements DBService {
    List<TreeMarker> markers = new ArrayList<>(Arrays.asList(
            new TreeMarker(0, new Species("Oak", "Дуб"), new Coordinate(59.866465, 30.309587), 500),
            new TreeMarker(1, new Species("Oak", "Дуб"), new Coordinate(59.866457, 30.307780), 475),
            new TreeMarker(2, new Species("Birch", "Берёза"), new Coordinate(59.866695, 30.307597), 200),
            new TreeMarker(3, new Species("Birch", "Берёза"), new Coordinate(59.867293, 30.307997), 270),
            new TreeMarker(4, new Species("Birch", "Берёза"), new Coordinate(59.867555, 30.308179), 230),
            new TreeMarker(5, new Species("Linden","Липа") , new Coordinate(59.867783, 30.308748), 240),
            new TreeMarker(6, new Species("Oak", "Дуб"), new Coordinate(59.868007, 30.308598), 370)
    ));

    @Override
    public List<TreeMarker> getAllMarkers() throws DBException {
        return new ArrayList<>(markers);
    }

    @Override
    public void insertMarker(TreeMarker marker) throws DBException {
        markers.add(marker);
    }

    @Override
    public void deleteMarker(long id) throws DBException {
        markers.removeIf((o)->o.getId() == id);
    }

    @Override
    public TreeMarker getMarkerByCoordAndSpecies(Coordinate coordinate, Species species) throws DBException {
        for(TreeMarker tm : markers){
            if (coordinate.equals(tm.getCoordinate()) && species.equals(tm.getSpecies())){
                return tm;
            }
        }
        return null;
    }
}
