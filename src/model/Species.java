package model;

/**
 * Created by user on 19.05.2018.
 */
public class Species {
    private final String nameLat;
    private final String nameRus;

    public Species(String nameLat, String nameRus) {
        this.nameLat = nameLat;
        this.nameRus = nameRus;
    }

    public String getNameLat() {
        return nameLat;
    }

    public String getNameRus() {
        return nameRus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Species)) return false;

        Species species = (Species) o;

        if (!nameLat.equals(species.nameLat)) return false;
        return nameRus.equals(species.nameRus);
    }

    @Override
    public int hashCode() {
        int result = nameLat.hashCode();
        result = 31 * result + nameRus.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Species{" +
                "nameLat='" + nameLat + '\'' +
                ", nameRus='" + nameRus + '\'' +
                '}';
    }

}
