package hu.petrik.filmdb;

public class Szinesz {
    private int id;
    private String szineszNev;

    public Szinesz(int id, String szineszNev) {
        this.id = id;
        this.szineszNev = szineszNev;
    }

    public int getId() {
        return id;
    }


    public String getSzineszNev() {
        return szineszNev;
    }

    public void setSzineszNev(String szineszNev) {
        this.szineszNev = szineszNev;
    }
}
