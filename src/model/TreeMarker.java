package model;

/**
 * Created by user on 18.05.2018.
 */
public class TreeMarker {
    private final int id;
    private final String species;
    private final Coordinate coordinate;
    private final double trunk; // mm

    public TreeMarker(int id, String species, Coordinate coordinate, double trunk) {
        this.id = id;
        this.species = species;
        this.coordinate = coordinate;
        this.trunk = trunk;
    }

    public int getId() {
        return id;
    }

    public String getSpecies() {
        return species;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public double getTrunk() {
        return trunk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TreeMarker)) return false;

        TreeMarker that = (TreeMarker) o;

        if (id != that.id) return false;
        if (Double.compare(that.trunk, trunk) != 0) return false;
        if (species != null ? !species.equals(that.species) : that.species != null) return false;
        return coordinate != null ? coordinate.equals(that.coordinate) : that.coordinate == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (species != null ? species.hashCode() : 0);
        result = 31 * result + (coordinate != null ? coordinate.hashCode() : 0);
        temp = Double.doubleToLongBits(trunk);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "TreeMarker{" +
                "id=" + id +
                ", species='" + species + '\'' +
                ", coordinate=" + coordinate +
                ", trunk=" + trunk +
                '}';
    }
}
