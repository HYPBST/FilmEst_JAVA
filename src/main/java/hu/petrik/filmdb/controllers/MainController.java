package hu.petrik.filmdb.controllers;

import hu.petrik.filmdb.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.util.List;

public class MainController extends Controller {

    @FXML
    private TableView<Film> filmTable;
    @FXML
    private TableColumn<Film, String> colId;
    @FXML
    private TableColumn<Film, String> colCim;
    @FXML
    private TableColumn<Film, String> colLeiras;
    @FXML
    private TableColumn<Film, Integer> colMegjelenesiEv;
    @FXML
    private TableColumn<Film, Integer> colRendezoNev;
    @FXML
    private TableColumn<Film, Integer> colErtekeles;
    @FXML
    private TableColumn<Film, String> colKategoria;
    @FXML
    public TableColumn<Film,String> colSzineszek;

    public void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCim.setCellValueFactory(new PropertyValueFactory<>("cim"));
        colLeiras.setCellValueFactory(new PropertyValueFactory<>("leiras"));
        colMegjelenesiEv.setCellValueFactory(new PropertyValueFactory<>("megjelenesiEv"));
        colErtekeles.setCellValueFactory(new PropertyValueFactory<>("ertekeles"));
        colRendezoNev.setCellValueFactory(new PropertyValueFactory<>("rendezoNev"));
        colKategoria.setCellValueFactory(new PropertyValueFactory<>("kategoriak"));
        colSzineszek.setCellValueFactory(new PropertyValueFactory<>("szineszek"));
        filmListaFeltolt();
    }

   /* @FXML
    public void onModositasButtonClick(ActionEvent actionEvent) {
        int selectedIndex = filmTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1){
            alert("A módosításhoz előbb válasszon ki egy elemet a táblázatból");
            return;
        }
        Film modositando = filmTable.getSelectionModel().getSelectedItem();
        try {
            ModositController modositas = (ModositController) ujAblak("modosit-view.fxml",
                    "Film módosítása", 320, 400);
            modositas.setModositando(modositando);
            modositas.getStage().setOnHiding(event -> filmTable.refresh());
            modositas.getStage().show();
        } catch (IOException e) {
            hibaKiir(e);
        }
    }

    @FXML
    public void onTorlesButtonClick(ActionEvent actionEvent) {
        int selectedIndex = filmTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1){
            alert("A törléshez előbb válasszon ki egy elemet a táblázatból");
            return;
        }
        Film torlendoFilm = filmTable.getSelectionModel().getSelectedItem();
        if (!confirm("Biztos hogy törölni szeretné az alábbi filmet: "+torlendoFilm.getCim())){
            return;
        }
        try {
            boolean sikeres = FilmApi.filmTorlese(torlendoFilm.getId());
            alert(sikeres ? "Sikeres törlés": "Sikertele törlés");
            filmListaFeltolt();
        } catch (IOException e) {
            hibaKiir(e);
        }
    }

    @FXML
    public void onHozzadasButtonClick(ActionEvent actionEvent) {
        try {
            Controller hozzadas = ujAblak("hozzaad-view.fxml", "Film hozzáadása",
                    320, 400);
            hozzadas.getStage().setOnCloseRequest(event -> filmListaFeltolt());
            hozzadas.getStage().show();
        } catch (Exception e) {
            hibaKiir(e);
        }
    }
*/
    private void filmListaFeltolt(){
        try {
            List<Film> filmList = FilmApi.getFilmek();
            filmTable.getItems().clear();
            for(Film film: filmList){
                filmTable.getItems().add(film);
            }
        } catch (IOException e) {
            hibaKiir(e);
        }
    }
}