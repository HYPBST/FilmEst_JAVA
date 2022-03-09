package hu.petrik.filmdb.controllers;

import hu.petrik.filmdb.Controller;
import hu.petrik.filmdb.FilmApi;
import hu.petrik.filmdb.classes.Kategoria;
import hu.petrik.filmdb.classes.Rendezo;
import hu.petrik.filmdb.classes.Szinesz;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.List;

public class SzineszFelvetel extends Controller {
    @FXML
    public TextField felvetelSzinesz;



    public void initialize(){
        felvetelSzinesz.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                felvetelSzinesz.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
    }

    public void onFelvetelButtonClick(ActionEvent actionEvent) {
        String szinesz=felvetelSzinesz.getText().trim();
        if(szinesz.isEmpty()){
            alert("A színész megadása kötelező");
            return;
        }

        try {
            List<Szinesz> szineszList= FilmApi.getSzineszekList();
            for (Szinesz sz:szineszList
            ) {
                if (sz.getSzineszNev().equalsIgnoreCase(szinesz)){
                    alert("A rendező már létezik");

                    return;
                }
            }
            Szinesz ujSzinesz =new Szinesz(szinesz);
            Szinesz letrehozott= FilmApi.szineszHozzaadas(ujSzinesz);
            if (letrehozott != null ){
                alert("Színész hozzáadása sikeres");
            } else {
                alert("Színész hozzáadása sikeretelen");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
