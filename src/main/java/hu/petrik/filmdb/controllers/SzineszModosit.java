package hu.petrik.filmdb.controllers;

import hu.petrik.filmdb.Controller;
import hu.petrik.filmdb.FilmApi;
import hu.petrik.filmdb.classes.Szinesz;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.List;

public class SzineszModosit extends Controller {
    @FXML
    public TextField modositSzinesz;
    private Szinesz modositando;
    public void initialize(){
        modositSzinesz.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                modositSzinesz.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
    }
    public Szinesz getModositando() {
        return modositando;
    }

    public void setModositando(Szinesz modositando) {
        this.modositando = modositando;
        ertekekBeallitasa();
    }

    private void ertekekBeallitasa() {
        modositSzinesz.setText(modositando.getSzineszNev());
    }

    public void onModositButtonClick(ActionEvent actionEvent) {
        String szinesz=modositSzinesz.getText().trim();
        if (szinesz.isEmpty()){
            alert("Színész megadása kötelező");
            return;
        }
        modositando.setSzineszNev(szinesz);
        try {
            List<Szinesz> szineszList= FilmApi.getSzineszekList();
            for (Szinesz sz:szineszList
            ) {
                if (sz.getSzineszNev().equalsIgnoreCase(szinesz)){
                    alert("A rendező már létezik");

                    return;
                }
            }
            Szinesz modositott = FilmApi.szineszModositas(modositando);
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
}
