
package hu.petrik.filmdb.controllers;

import hu.petrik.filmdb.Controller;
import hu.petrik.filmdb.classes.Film;
import hu.petrik.filmdb.FilmApi;
import hu.petrik.filmdb.classes.Kategoria;
import hu.petrik.filmdb.classes.Rendezo;
import hu.petrik.filmdb.classes.Szinesz;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmModosit extends Controller {
    @FXML
    public MenuButton menuKategoria;
    @FXML
    public MenuButton menuSzineszek;
    @FXML
    public MenuButton menuRendezok;
    @FXML
    private TextField txtCim;
    @FXML
    private TextArea txtLeiras;
    @FXML
    private TextField txtEv;
    @FXML
    private TextField txtErtekeles;
    @FXML
    private TextField txtImgUrl;
    private Film modositando;
    private List<Kategoria> kategoriaList;
    private List<Rendezo> rendezoList;
    private List<Szinesz> szineszList;
    public void onModositButtonClick(ActionEvent actionEvent) {
    }

    public Film getModositando() {
        return modositando;
    }

    public void setModositando(Film modositando) {
        this.modositando = modositando;
        ertekekBeallitasa();
    }

    private void ertekekBeallitasa() {
        txtCim.setText(modositando.getCim());
        txtLeiras.setText(modositando.getLeiras());
        txtEv.setText(String.valueOf( modositando.getMegjelenesiEv()));
        txtErtekeles.setText(String.valueOf( modositando.getErtekeles()));
        txtImgUrl.setText(modositando.getImageUrl());
        kategoriaList=modositando.getKategoriak();
        szineszList=modositando.getSzineszek();
        rendezoList=modositando.getRendezok();
        ArrayList<MenuItem> kategoriak=new ArrayList<>();
        ArrayList<MenuItem> szineszek=new ArrayList<>();
        ArrayList<MenuItem> rendezok=new ArrayList<>();
        try {
            for (Kategoria k: FilmApi.getKategoriaList()
            ) {
                boolean bennevan=false;
                for (Kategoria kat:kategoriaList
                     ) {
                    if (kat.getKategoria().equalsIgnoreCase(k.getKategoria())){
                        CheckBox cb=new CheckBox(k.getKategoria());
                        cb.setSelected(true);
                        CustomMenuItem item=new CustomMenuItem(cb);
                        item.setText(k.getKategoria());
                        item.setHideOnClick(false);
                        kategoriak.add(item);
                        bennevan=true;
                    }
                }
                if(!bennevan){
                    CheckBox cb=new CheckBox(k.getKategoria());
                    CustomMenuItem item=new CustomMenuItem(cb);
                    item.setText(k.getKategoria());
                    item.setHideOnClick(false);
                    kategoriak.add(item);
                }
            }
            for (Szinesz sz:FilmApi.getSzineszekList()
                 ) {
                boolean bennevan=false;
                for (Szinesz szin:szineszList
                ) {
                    if (szin.getSzineszNev().equalsIgnoreCase(sz.getSzineszNev())){
                        CheckBox cb=new CheckBox(sz.getSzineszNev());
                        cb.setSelected(true);
                        CustomMenuItem item=new CustomMenuItem(cb);
                        item.setText(sz.getSzineszNev());
                        item.setHideOnClick(false);
                        szineszek.add(item);
                        bennevan=true;
                    }
                }
                if(!bennevan){
                    CheckBox cb=new CheckBox(sz.getSzineszNev());
                    CustomMenuItem item=new CustomMenuItem(cb);
                    item.setText(sz.getSzineszNev());
                    item.setHideOnClick(false);
                    szineszek.add(item);
                }
            }
            for (Rendezo r:FilmApi.getRendezokList()
            ) {
                boolean bennevan=false;
                for (Rendezo ren:rendezoList
                ) {
                    if (ren.getRendezoNev().equalsIgnoreCase(r.getRendezoNev())){
                        CheckBox cb=new CheckBox(r.getRendezoNev());
                        cb.setSelected(true);
                        CustomMenuItem item=new CustomMenuItem(cb);
                        item.setText(r.getRendezoNev());
                        item.setHideOnClick(false);
                        rendezok.add(item);
                        bennevan=true;
                    }
                }
                if (!bennevan){
                    CheckBox cb=new CheckBox(r.getRendezoNev());
                    CustomMenuItem item=new CustomMenuItem(cb);
                    item.setText(r.getRendezoNev());
                    item.setHideOnClick(false);
                    rendezok.add(item);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        menuKategoria.getItems().setAll(kategoriak);
        menuSzineszek.getItems().setAll(szineszek);
        menuRendezok.getItems().setAll(rendezok);
        for (MenuItem mi:menuKategoria.getItems()
             ) {
            System.out.println(mi.getProperties());

        }
    }

    /*
    @FXML
    public void onModositButtonClick(ActionEvent actionEvent) {
        String cim = inputCim.getText().trim();
        String kategoria = inputKategoria.getText().trim();
        int hossz = 0;
        int ertekelesIndex = inputErtekeles.getSelectionModel().getSelectedIndex();
        if (cim.isEmpty()){
            alert("Cím megadása kötelező");
            return;
        }
        if (kategoria.isEmpty()){
            alert("Kategória megadása kötelező");
            return;
        }
        try {
            hossz = inputHossz.getValue();
        } catch (NullPointerException ex){
            alert("A hossz megadása kötelező");
            return;
        } catch (Exception ex){
            alert("A hossz csak 1 és 999 közötti szám lehet");
            return;
        }
        if (hossz < 1 || hossz > 999) {
            alert("A hossz csak 1 és 999 közötti szám lehet");
            return;
        }
        if (ertekelesIndex == -1){
            alert("Értékelés kiválasztása köztelező");
            return;
        }
        int ertekeles = inputErtekeles.getValue();

        modositando.setCim(cim);
        modositando.setKategoria(kategoria);
        modositando.setErtekeles(ertekeles);
        modositando.setHossz(hossz);

        try {
            Film modositott = FilmApi.filmModositasa(modositando);
            if (modositott != null){
                alertWait("Sikeres módosítás");
                this.stage.close();
            } else {
                alert("Sikertelen módosítás");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/
}

