package hu.petrik.filmdb.controllers;

import hu.petrik.filmdb.Controller;
import hu.petrik.filmdb.FilmApi;
import hu.petrik.filmdb.classes.Kategoria;
import hu.petrik.filmdb.classes.Rendezo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.List;

public class RendezoFelvetel extends Controller {
    @FXML
    public TextField felvetelRendezo;


    public void initialize(){
        felvetelRendezo.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                felvetelRendezo.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
    }

    public void onFelvetelButtonClick(ActionEvent actionEvent) {
        String rendezo=felvetelRendezo.getText().trim();
        if(rendezo.isEmpty()){
            alert("A rendező megadása kötelező");
            return;
        }

        try {
            List<Rendezo> rendezoList= FilmApi.getRendezokList();
            for (Rendezo r:rendezoList
            ) {
                if (r.getRendezoNev().equalsIgnoreCase(rendezo)){
                    alert("A rendező már létezik");

                    return;
                }
            }
            Rendezo ujRendezo =new Rendezo(rendezo);
            Rendezo letrehozott= FilmApi.rendezoHozzaadas(ujRendezo);
            if (letrehozott != null ){
                alert("Rendező hozzáadása sikeres");
            } else {
                alert("Rendező hozzáadása sikeretelen");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
