
package hu.petrik.filmdb.controllers;

import hu.petrik.filmdb.Controller;
import hu.petrik.filmdb.classes.Film;
import hu.petrik.filmdb.FilmApi;
import hu.petrik.filmdb.classes.Kategoria;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.ArrayList;

public class FilmFelvetel extends Controller {
    @FXML
    public MenuButton menub;
    @FXML
    private TextField inputCim;
    @FXML
    private TextField inputKategoria;
    @FXML
    private Spinner<Integer> inputHossz;
    @FXML
    private ChoiceBox<Integer> inputErtekeles;
    public void initialize(){
        ArrayList<MenuItem> mil=new ArrayList<>();
        try {
            for (Kategoria k: FilmApi.getKategoriaList()
                 ) {
                CheckBox cb=new CheckBox(k.getKategoria());
                CustomMenuItem item=new CustomMenuItem(cb);
                item.setText(k.getKategoria());
                item.setHideOnClick(false);
                mil.add(item);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        menub.getItems().setAll(mil);

    }

    public void onFelvetelButtonClick(ActionEvent actionEvent) {
        for (MenuItem menuItem:menub.getItems()
        ) {
            ;
            System.out.println(menuItem.getText());
        }
    }
    /*
    @FXML
    public void onHozzadButtonClick(ActionEvent actionEvent) {
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

        try {
            Film ujFilm = new Film(0, cim, kategoria, hossz, ertekeles);
            Film letrehozott = FilmApi.filmHozzaadasa(ujFilm);
            if (letrehozott != null ){
                alert("Film hozzáadása sikeres");
            } else {
                alert("Film hozzáadása sikeretelen");
            }
        } catch (Exception e) {
            hibaKiir(e);
        }

    }*/
}

