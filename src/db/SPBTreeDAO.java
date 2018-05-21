package db;

import com.lynden.gmapsfx.javascript.object.Animation;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.mysql.jdbc.Driver;
import db.executor.Executor;
import db.executor.ResultHandler;
import model.Coordinate;
import model.LeafPhoto;
import model.Species;
import model.Tree;
import ui.TreeMarker;
import ui.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by user on 14.05.2018.
 */
public class SPBTreeDAO {

    public static final String HOST = "localhost";
    public static final String PORT = "3306";
    public static final String DATABASE = "spb_tree";
    public static final String USER = "newuser";
    public static final String PASSWORD = "password";
    public static final String TIME_ZONE_CONFIG = "useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    private Connection connection;
    private Executor executor;


    public SPBTreeDAO() {
        this.connection = getMysqlConnection();
        this.executor = new Executor(connection);
    }


    public void addTree(Tree tree) throws DBException {
        try {
            executor.execUpdate(String.format(Locale.US, "insert into coordinate (latitude, longitude) values (%.6f, %.6f);",
                    tree.getCoordinate().getLatitude(), tree.getCoordinate().getLongitude()));

            executor.execUpdate(String.format("select @coordID := LAST_INSERT_ID() from coordinate"));
            executor.execUpdate(String.format("select @spID := id from species where nameLat = '%s' and nameRus = '%s'",
                    tree.getSpecies().getNameLat(), tree.getSpecies().getNameRus()));
            executor.execUpdate(String.format(Locale.US, "insert into tree (speciesID, coordinateID, trunk) values (@spID, @coordID, %.1f)",
                    tree.getTrunk()));
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public int getLastIDTree() throws DBException {
        try {
            return executor.execQuery("select @id := LAST_INSERT_ID() from tree", resultSet -> {
                if (!resultSet.next()) {
                    return 0;
                }
                return resultSet.getInt(1);
            });

        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public int getNumberOfSpecies() throws DBException {
        try {
            return executor.execQuery("select count(distinct speciesID) from tree", resultSet -> {
                if (!resultSet.next()) {
                    return 0;
                }
                return resultSet.getInt(1);
            });

        } catch (SQLException e) {
            throw new DBException(e);
        }
    }


    public Tree getTreeById(long id) throws DBException {
        try {
            return executor.execQuery("select tree.id, species.nameLat, species.nameRus, coordinate.latitude, " +
                    "coordinate.longitude, tree.trunk from tree inner join species on species.id = tree.speciesID " +
                    "inner join coordinate on coordinate.id = tree.coordinateID where tree.id = " + id, resultSet -> {
                if (!resultSet.next()) {
                    return null;
                }
                int treeID = resultSet.getInt(1);
                String nameLat = resultSet.getString(2);
                String nameRus = resultSet.getString(3);
                double latitude = resultSet.getDouble(4);
                double longitude = resultSet.getDouble(5);
                float trunk = resultSet.getFloat(6);

                return new Tree(treeID, new Species(nameLat, nameRus), new Coordinate(latitude, longitude), trunk, null);
            });
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public Tree getTreeByCoordAndSpecies(Coordinate coordinate, Species species) throws DBException {
        try {
            return executor.execQuery(String.format(Locale.US, "select tree.id, species.nameLat, species.nameRus, coordinate.latitude, " +
                            "coordinate.longitude, tree.trunk from tree inner join species on species.id = tree.speciesID " +
                            "inner join coordinate on coordinate.id = tree.coordinateID where species.nameLat = '%s' and " +
                            "coordinate.latitude = %.6f and coordinate.longitude = %.6f", species.getNameLat(), coordinate.getLatitude(),
                    coordinate.getLongitude()), resultSet -> {
                if (!resultSet.next()) {
                    return null;
                }
                int treeID = resultSet.getInt(1);
                String nameLat = resultSet.getString(2);
                String nameRus = resultSet.getString(3);
                double latitude = resultSet.getDouble(4);
                double longitude = resultSet.getDouble(5);
                float trunk = resultSet.getFloat(6);
                return new Tree(treeID, new Species(nameLat, nameRus), new Coordinate(latitude, longitude), trunk, null);
            });
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public List<LeafPhoto> getALLLeafs() throws DBException {
        try {
            return executor.execQuery("select url * from leaf", new ResultHandler<List<LeafPhoto>>() {

                List<LeafPhoto> leafs = new ArrayList<>();

                @Override
                public List<LeafPhoto> handle(ResultSet resultSet) throws SQLException {
                    if (!resultSet.next()) {
                        return null;
                    }

                    do {

                        leafs.add(new LeafPhoto(resultSet.getString(1)));
                    } while (resultSet.next());
                    return leafs;
                }
            });
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public LeafPhoto getLeafPhotoByID(int id) throws DBException {
        try {
            return executor.execQuery(String.format(Locale.US, "select url from leaf where id = %d", id), resultSet -> {
                if (!resultSet.next()) {
                    return null;
                }
                return new LeafPhoto(resultSet.getString(1));
            });
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public List<Tree> getAllTrees() throws DBException {
        try {
            return executor.execQuery("select tree.id, species.nameLat, species.nameRus, coordinate.latitude, " +
                    "coordinate.longitude, tree.trunk, species.id from tree inner join species on species.id = tree.speciesID " +
                    "inner join coordinate on coordinate.id = tree.coordinateID", new ResultHandler<List<Tree>>() {

                List<Tree> trees = new ArrayList<>();

                @Override
                public List<Tree> handle(ResultSet resultSet) throws SQLException {
                    if (!resultSet.next()) {
                        return null;
                    }

                    do {
                        int id = resultSet.getInt(1);
                        double latitude = resultSet.getDouble(4);
                        double longitude = resultSet.getDouble(5);
                        Species species = new Species(resultSet.getString(2), resultSet.getString(3));
                        Coordinate coordinate = new Coordinate(latitude, longitude);
                        float trunk = resultSet.getFloat(6);
                        int spid = resultSet.getInt(7);
                        if (spid > 8) {
                            spid = 8;
                        }
                        try {
                            LeafPhoto leafPhoto = getLeafPhotoByID(spid);
                            trees.add(new Tree(id, species, coordinate, trunk, leafPhoto));
                        } catch (DBException e) {
                            throw new SQLException(e);
                        }
                    } while (resultSet.next());
                    return trees;
                }
            });
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public void deleteTree(Tree tree) throws DBException {
        try {
            executor.execUpdate(String.format("delete from tree where tree.id = %d", tree.getId()));
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            String url = "jdbc:mysql://" +                   //db type
                    HOST + ":" +            //host name
                    PORT + "/" +            //port
                    DATABASE + "?" +//db name
                    TIME_ZONE_CONFIG + "&" +
                    "user=" + USER + "&" +    //login
                    "password=" + PASSWORD; //password

            System.out.println("DB url " + url);
            return DriverManager.getConnection(url);
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
