package hu.petrik.filmdb;

import java.util.ArrayList;
import java.util.List;

public class Film {
    private int id;
    private String cim;
    private String leiras;
    private int megjelenesiEv;
    private int ertekeles;
    private String rendezoNev;
    private String kategoriak;
    private String szineszek;
    private List<Film> filmek;

    public Film(int id, String cim, String leiras, int megjelenesiEv, int ertekeles, String rendezoNev, String kategoriak, String szineszek) {
        this.id = id;
        this.cim = cim;
        this.leiras = leiras;
        this.megjelenesiEv = megjelenesiEv;
        this.ertekeles = ertekeles;
        this.rendezoNev = rendezoNev;
        this.kategoriak = kategoriak;
        this.szineszek = szineszek;
    }

    public int getId() {
        return id;
    }


    public String getCim() {
        return cim;
    }

    public void setCim(String cim) {
        this.cim = cim;
    }

    public String getLeiras() {
        return leiras;
    }

    public void setLeiras(String leiras) {
        this.leiras = leiras;
    }

    public int getMegjelenesiEv() {
        return megjelenesiEv;
    }

    public void setMegjelenesiEv(int megjelenesiEv) {
        this.megjelenesiEv = megjelenesiEv;
    }

    public int getErtekeles() {
        return ertekeles;
    }

    public void setErtekeles(int ertekeles) {
        this.ertekeles = ertekeles;
    }

    public String getRendezoNev() {
        return rendezoNev;
    }

    public void setRendezoNev(String rendezoNev) {
        this.rendezoNev = rendezoNev;
    }

    public String getKategoriak() {
        return kategoriak;
    }

    public void setKategoriak(String kategoriak) {
        this.kategoriak = kategoriak;
    }

    public String getSzineszek() {
        return szineszek;
    }

    public void setSzineszek(String szineszek) {
        this.szineszek = szineszek;
    }

    public List<Film> getFilmek() {
        return filmek;
    }
}
