package hu.petrik.filmdb.controllers;

import hu.petrik.filmdb.*;
import hu.petrik.filmdb.classes.Film;
import hu.petrik.filmdb.classes.Kategoria;
import hu.petrik.filmdb.classes.Rendezo;
import hu.petrik.filmdb.classes.Szinesz;
import hu.petrik.filmdb.pivot.FilmKategoriai;
import hu.petrik.filmdb.pivot.FilmRendezoi;
import hu.petrik.filmdb.pivot.FilmSzineszei;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainController extends Controller {
    @FXML
    private TableView<Film> filmTable;
    @FXML
    private TableColumn<Film, String> colCim;
    @FXML
    private TableColumn<Film, String> colFilmId;
    @FXML
    private TextArea txtAdatok;
    @FXML
    public TableView<Kategoria> kategoriaTable;
    @FXML
    public TableColumn<Kategoria, String> colKategoriaId;
    @FXML
    public TableColumn<Kategoria, String> colKategoria;
    public TableView<Rendezo> rendezoTable;
    public TableColumn<Rendezo,String> colRendezoId;
    public TableColumn<Rendezo,String> colRendezo;
    public TableView<Szinesz> szineszTable;
    public TableColumn<Szinesz,String> colSzineszId;
    public TableColumn<Szinesz,String> colSzinesz;


    List<Film> filmList;
    List<Kategoria> kategoriaList;
    List<Rendezo> rendezoList;
    List<Szinesz> szineszList;
    List<FilmKategoriai> filmKategoriai;
    List<FilmRendezoi> filmRendezoi;
    List<FilmSzineszei> filmSzineszei;


    public void initialize(){
        colCim.setCellValueFactory(new PropertyValueFactory<>("cim"));
        colFilmId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colKategoriaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colKategoria.setCellValueFactory(new PropertyValueFactory<>("kategoria"));
        colRendezoId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colRendezo.setCellValueFactory(new PropertyValueFactory<>("rendezoNev"));
        colSzineszId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colSzinesz.setCellValueFactory(new PropertyValueFactory<>("szineszNev"));
        filmListaFeltolt();
        kategoriaListaFeltolt();
        rendezoListaFeltolt();
        szineszListaFeltolt();
        kapcsolatok();
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
            ModositController modositas = (ModositController) ujAblak("filmmodosit-view.fxml",
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
            Controller hozzadas = ujAblak("filmhozzaad-view.fxml", "Film hozzáadása",
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
            filmList = FilmApi.FilmekLetrehozasa();
        } catch (IOException e) {
            e.printStackTrace();
        }
        filmTable.getItems().clear();
        for(Film film: filmList){
            film.setKategoriak(new ArrayList<>());
            film.setRendezok(new ArrayList<>());
            film.setSzineszek(new ArrayList<>());
            filmTable.getItems().add(film);
        }
    }
    private void kategoriaListaFeltolt(){
        try {
            kategoriaList = FilmApi.kategoriakLetrehozasa();
        } catch (IOException e) {
            e.printStackTrace();
        }
        kategoriaTable.getItems().clear();
        kategoriaList.forEach(kategoria -> kategoriaTable.getItems().add(kategoria));
    }
    private void rendezoListaFeltolt(){
        try {
            rendezoList = FilmApi.RendezokLetrehozasa();
        } catch (IOException e) {
            e.printStackTrace();
        }
        rendezoTable.getItems().clear();
        rendezoList.forEach(rendezo -> rendezoTable.getItems().add(rendezo));
    }
    private void szineszListaFeltolt(){
        try {
            szineszList = FilmApi.SzineszekLetrehozasa();
        } catch (IOException e) {
            e.printStackTrace();
        }
        szineszTable.getItems().clear();
        szineszList.forEach(szinesz -> szineszTable.getItems().add(szinesz));
    }

    public void onItemSelect(MouseEvent mouseEvent) {
        if(filmTable.getSelectionModel().getSelectedIndex()!=-1) {
            Film kivalasztott = filmTable.getSelectionModel().getSelectedItem();
            StringBuilder  sb=new StringBuilder();
            sb.append("Megjelenési év: ").append(kivalasztott.getMegjelenesiEv()).append("\n");
            sb.append("Rendező: ").append(kivalasztott.rendezokToString()).append("\n");
            sb.append("Leírás: ").append(kivalasztott.getLeiras()).append("\n");
            sb.append("Kategóriák: ").append(kivalasztott.kategoriakToString()).append("\n");
            sb.append("Színészek: ").append(kivalasztott.szineszekToString()).append("\n");
            sb.append("Értékelés: ").append(kivalasztott.getErtekeles());
            txtAdatok.setText(sb.toString());
        }
    }
    private void kapcsolatok(){
        try {
            filmKategoriai=FilmApi.GetFilmKategoriai();
            filmRendezoi=FilmApi.GetFilmRendezoi();
            filmSzineszei=FilmApi.GetFilmSzineszei();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Film film:filmList
             ) {
            for (FilmKategoriai filmkategoria : filmKategoriai
            ) {
                if (filmkategoria.getFilmId() == film.getId()) {
                    for (Kategoria kategoria : kategoriaList
                    ) {
                        if (filmkategoria.getKategoriaId() == kategoria.getId()) {
                            film.getKategoriak().add(kategoria);
                        }
                    }
                }
            }
            for (FilmRendezoi filmrendezo : filmRendezoi
            ) {
                if (filmrendezo.getFilmId() == film.getId()) {
                    for (Rendezo rendezo : rendezoList
                    ) {
                        if (filmrendezo.getRendezoId() == rendezo.getId()) {
                            film.getRendezok().add(rendezo);
                        }
                    }
                }
            }
            for (FilmSzineszei filmszinesz : filmSzineszei
            ) {
                if (filmszinesz.getFilmId() == film.getId()) {
                    for (Szinesz szinesz : szineszList
                    ) {
                        if (filmszinesz.getSzineszId() == szinesz.getId()) {
                            film.getSzineszek().add(szinesz);
                        }
                    }
                }
            }
        }

    }

    public void onKategoriaHozzadasButtonClick(ActionEvent actionEvent) {
        try {
            Controller hozzadas = ujAblak("kategoria-view.fxml", "Kategória hozzáadása",
                    320, 400);
            hozzadas.getStage().setOnCloseRequest(event -> kategoriaListaFeltolt());
            hozzadas.getStage().show();
        } catch (Exception e) {
            hibaKiir(e);
        }
    }

    public void onKategoriaModositasButtonClick(ActionEvent actionEvent) {
    }

    public void onKategoriaTorlesButtonClick(ActionEvent actionEvent) {
        int selectedIndex = kategoriaTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1){
            alert("A törléshez előbb válasszon ki egy elemet a táblázatból");
            return;
        }
        Kategoria torlendoKategoria = kategoriaTable.getSelectionModel().getSelectedItem();
        if (!confirm("Biztos hogy törölni szeretné az alábbi kategórát: "+torlendoKategoria.getKategoria())){
            return;
        }
        try {
            boolean sikeres = FilmApi.kategoriaTorles(torlendoKategoria.getId());
            alert(sikeres ? "Sikeres törlés": "Sikertele törlés");
            kategoriaListaFeltolt();
        } catch (IOException e) {
            hibaKiir(e);
        }
    }

    public void onRendezoHozzadasButtonClick(ActionEvent actionEvent) {
        try {
            Controller hozzadas = ujAblak("kategoria-view.fxml", "Rendező",
                    320, 400);
            hozzadas.getStage().setOnCloseRequest(event -> rendezoListaFeltolt());
            hozzadas.getStage().show();
        } catch (Exception e) {
            hibaKiir(e);
        }
    }

    public void onRendezoModositasButtonClick(ActionEvent actionEvent) {
    }

    public void onRendezoTorlesButtonClick(ActionEvent actionEvent) {
        int selectedIndex = rendezoTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1){
            alert("A törléshez előbb válasszon ki egy elemet a táblázatból");
            return;
        }
        Rendezo torlendoRendezo = rendezoTable.getSelectionModel().getSelectedItem();
        if (!confirm("Biztos hogy törölni szeretné az alábbi rendezőt: "+torlendoRendezo.getRendezoNev())){
            return;
        }
        try {
            boolean sikeres = FilmApi.rendezoTorles(torlendoRendezo.getId());
            alert(sikeres ? "Sikeres törlés": "Sikertele törlés");
            rendezoListaFeltolt();
        } catch (IOException e) {
            hibaKiir(e);
        }
    }

    public void onSzineszHozzadasButtonClick(ActionEvent actionEvent) {
        try {
            Controller hozzadas = ujAblak("szinesz-view.fxml", "Rendező",
                    320, 400);
            hozzadas.getStage().setOnCloseRequest(event -> szineszListaFeltolt());
            hozzadas.getStage().show();
        } catch (Exception e) {
            hibaKiir(e);
        }
    }

    public void onSzineszModositasButtonClick(ActionEvent actionEvent) {
    }

    public void onSzineszTorlesButtonClick(ActionEvent actionEvent) {
        int selectedIndex = szineszTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1){
            alert("A törléshez előbb válasszon ki egy elemet a táblázatból");
            return;
        }
        Szinesz torlendoSzinesz = szineszTable.getSelectionModel().getSelectedItem();
        if (!confirm("Biztos hogy törölni szeretné az alábbi színészt: "+torlendoSzinesz.getSzineszNev())){
            return;
        }
        try {
            boolean sikeres = FilmApi.szineszTorles(torlendoSzinesz.getId());
            alert(sikeres ? "Sikeres törlés": "Sikertele törlés");
            szineszListaFeltolt();
        } catch (IOException e) {
            hibaKiir(e);
        }
    }
}