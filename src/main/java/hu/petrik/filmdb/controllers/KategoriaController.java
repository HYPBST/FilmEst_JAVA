package hu.petrik.filmdb.controllers;

import hu.petrik.filmdb.Controller;
import hu.petrik.filmdb.FilmApi;
import hu.petrik.filmdb.classes.Kategoria;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.List;


public class KategoriaController extends Controller {
    @FXML
    private TextField felvetelKategoria;

    public void onFelvetelButtonClick(ActionEvent actionEvent) {
        String kategoria=felvetelKategoria.getText().trim();
        if(kategoria.isEmpty()){
            alert("A kategória megadása kötelező");
            return;
        }

        try {
            List<Kategoria> kategoriaList=FilmApi.kategoriakLetrehozasa();
            for (Kategoria k:kategoriaList
                 ) {
                System.out.println(k.getKategoria());
                System.out.println(kategoria);
                if (k.getKategoria().equalsIgnoreCase(kategoria)){
                    alert("A kategória már létezik");

                    return;
                }
            }
            Kategoria ujKategoria =new Kategoria(kategoria);
            Kategoria letrehozott= FilmApi.kategoriaHozzaadas(ujKategoria);
            if (letrehozott != null ){
                alert("Kategória hozzáadása sikeres");
            } else {
                alert("Kategória hozzáadása sikeretelen");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
