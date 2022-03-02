package hu.petrik.filmdb;

import java.util.ArrayList;
import java.util.List;

public class Film {
    private int id;
    private String cim;
    private String leiras;
    private int megjelenesiEv;
    private int ertekeles;
    private String imageUrl;

    public Film(int id, String cim, String leiras, int megjelenesiEv, int ertekeles, String imageUrl) {
        this.id = id;
        this.cim = cim;
        this.leiras = leiras;
        this.megjelenesiEv = megjelenesiEv;
        this.ertekeles = ertekeles;
        this.imageUrl=imageUrl;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
