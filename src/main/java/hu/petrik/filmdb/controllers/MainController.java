package hu.petrik.filmdb.controllers;

import hu.petrik.filmdb.*;
import hu.petrik.filmdb.classes.*;
import hu.petrik.filmdb.pivot.FilmKategoriai;
import hu.petrik.filmdb.pivot.FilmRendezoi;
import hu.petrik.filmdb.pivot.FilmSzineszei;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainController extends Controller {
    @FXML
    public TableView<Felhasznalo> felhasznaloTable;
    @FXML
    public TableColumn<Felhasznalo,String> colFelhasznaloId;
    @FXML
    public TableColumn<Felhasznalo,String> colFelhasznaloEmail;
    @FXML
    public TextField keresesFilm;
    @FXML
    public TextField keresesKategoria;
    @FXML
    public TextField keresesRendezo;
    @FXML
    public TextField keresesSzinesz;
    @FXML
    public TextField keresesFelhasznalo;
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
    @FXML
    public TableView<Rendezo> rendezoTable;
    @FXML
    public TableColumn<Rendezo,String> colRendezoId;
    @FXML
    public TableColumn<Rendezo,String> colRendezo;
    @FXML
    public TableView<Szinesz> szineszTable;
    @FXML
    public TableColumn<Szinesz,String> colSzineszId;
    @FXML
    public TableColumn<Szinesz,String> colSzinesz;


    List<Film> filmList;
    List<Felhasznalo> felhasznaloList;
    List<Kategoria> kategoriaList;
    List<Rendezo> rendezoList;
    List<Szinesz> szineszList;
    List<FilmKategoriai> filmKategoriai;
    List<FilmRendezoi> filmRendezoi;
    List<FilmSzineszei> filmSzineszei;


    public void initialize(){
        colCim.setCellValueFactory(new PropertyValueFactory<>("cim"));
        colFilmId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFelhasznaloEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colFelhasznaloId.setCellValueFactory(new PropertyValueFactory<>("id"));
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
        felhasznaloListaFeltolt();
        kapcsolatok();
    }


    private void filmListaFeltolt(){
        try {
            filmList = FilmApi.getFilmList();
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
    }private void felhasznaloListaFeltolt(){
        try {
            felhasznaloList = FilmApi.getFelhasznaloList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        felhasznaloTable.getItems().clear();
        for(Felhasznalo felhasznalo: felhasznaloList){
            felhasznaloTable.getItems().add(felhasznalo);
        }
    }
    private void kategoriaListaFeltolt(){
        try {
            kategoriaList = FilmApi.getKategoriaList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        kategoriaTable.getItems().clear();
        kategoriaList.forEach(kategoria -> kategoriaTable.getItems().add(kategoria));
    }
    private void rendezoListaFeltolt(){
        try {
            rendezoList = FilmApi.getRendezokList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        rendezoTable.getItems().clear();
        rendezoList.forEach(rendezo -> rendezoTable.getItems().add(rendezo));
    }
    private void szineszListaFeltolt(){
        try {
            szineszList = FilmApi.getSzineszekList();
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
                            if(!film.getKategoriak().contains(kategoria)){
                                film.getKategoriak().add(kategoria);
                            }

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
                            if (!film.getRendezok().contains(rendezo)){
                                film.getRendezok().add(rendezo);
                            }

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
                            if(!film.getSzineszek().contains(szinesz)){
                                film.getSzineszek().add(szinesz);
                            }

                        }
                    }
                }
            }
        }

    }

    public void onKategoriaHozzadasButtonClick(ActionEvent actionEvent) {
        try {
            Controller hozzadas = ujAblak("kategoriahozzaad-view.fxml", "Kategória hozzáadása",
                    320, 400);
            hozzadas.getStage().setOnCloseRequest(event -> reset());
            hozzadas.getStage().show();
        } catch (Exception e) {
            hibaKiir(e);
        }
    }

    public void onKategoriaModositasButtonClick(ActionEvent actionEvent) {
        int selectedIndex = kategoriaTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1){
            alert("A módosításhoz előbb válasszon ki egy elemet a táblázatból");
            return;
        }
        Kategoria modositando = kategoriaTable.getSelectionModel().getSelectedItem();
        try {
            KategoriaModosit modositas = (KategoriaModosit) ujAblak("kategoriaModosit-view.fxml",
                    "Kategória", 320, 400);
            modositas.setModositando(modositando);
            modositas.getStage().setOnHiding(event -> reset());
            modositas.getStage().show();
        } catch (IOException e) {
            hibaKiir(e);
        }
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
            reset();
        } catch (IOException e) {
            hibaKiir(e);
        }
    }

    public void onRendezoHozzadasButtonClick(ActionEvent actionEvent) {
        try {
            Controller hozzadas = ujAblak("rendezohozzaad-view.fxml", "Rendező",
                    320, 400);
            hozzadas.getStage().setOnCloseRequest(event -> reset());
            hozzadas.getStage().show();
        } catch (Exception e) {
            hibaKiir(e);
        }
    }

    public void onRendezoModositasButtonClick(ActionEvent actionEvent) {
        int selectedIndex = rendezoTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1){
            alert("A módosításhoz előbb válasszon ki egy elemet a táblázatból");
            return;
        }
        Rendezo modositando = rendezoTable.getSelectionModel().getSelectedItem();
        try {
            RendezoModosit modositas = (RendezoModosit) ujAblak("rendezomodosit-view.fxml",
                    "Kategória", 320, 400);
            modositas.setModositando(modositando);
            modositas.getStage().setOnHiding(event -> reset());
            modositas.getStage().show();
        } catch (IOException e) {
            hibaKiir(e);
        }
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
            reset();
        } catch (IOException e) {
            hibaKiir(e);
        }
    }

    public void onSzineszHozzadasButtonClick(ActionEvent actionEvent) {
        try {
            Controller hozzadas = ujAblak("szineszhozzaad-view.fxml", "Színész",
                    320, 400);
            hozzadas.getStage().setOnCloseRequest(event -> reset());
            hozzadas.getStage().show();
        } catch (Exception e) {
            hibaKiir(e);
        }
    }

    public void onSzineszModositasButtonClick(ActionEvent actionEvent) {
        int selectedIndex = szineszTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1){
            alert("A módosításhoz előbb válasszon ki egy elemet a táblázatból");
            return;
        }
        Szinesz modositando = szineszTable.getSelectionModel().getSelectedItem();
        try {
            SzineszModosit modositas = (SzineszModosit) ujAblak("szineszmodosit-view.fxml",
                    "Kategória", 320, 400);
            modositas.setModositando(modositando);
            modositas.getStage().setOnHiding(event -> reset());
            modositas.getStage().show();
        } catch (IOException e) {
            hibaKiir(e);
        }
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
            reset();
        } catch (IOException e) {
            hibaKiir(e);
        }
    }

    public void onFilmModositasButtonClick(ActionEvent actionEvent) {
        int selectedIndex = filmTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1){
            alert("A módosításhoz előbb válasszon ki egy elemet a táblázatból");
            return;
        }
        Film modositando = filmTable.getSelectionModel().getSelectedItem();
        try {
            FilmModosit modositas = (FilmModosit) ujAblak("filmmodosit-view.fxml",
                    "Film", 320, 400);
            modositas.setModositando(modositando);
            modositas.getStage().setOnHiding(event -> reset());
            modositas.getStage().show();
        } catch (IOException e) {
            hibaKiir(e);
        }
    }

    public void onFilmTorlesButtonClick(ActionEvent actionEvent) {
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
            reset();
        } catch (IOException e) {
            hibaKiir(e);
        }
    }

    public void onFilmHozzadasButtonClick(ActionEvent actionEvent) {
        try {
            Controller hozzadas = ujAblak("filmhozzaad-view.fxml", "Film",
                    320, 400);
            hozzadas.getStage().setOnCloseRequest(event -> reset());
            hozzadas.getStage().show();
        } catch (Exception e) {
            hibaKiir(e);
        }
    }

    public void onFelhasznaloTorlesButtonClick(ActionEvent actionEvent) {
        int selectedIndex = felhasznaloTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1){
            alert("A törléshez előbb válasszon ki egy elemet a táblázatból");
            return;
        }
        Felhasznalo torlendoFelhasznalo = felhasznaloTable.getSelectionModel().getSelectedItem();
        if (!confirm("Biztos hogy törölni szeretné az alábbi felhasználót: "+torlendoFelhasznalo.getEmail())){
            return;
        }
        try {
            boolean sikeres = FilmApi.felhasznaloTorles(torlendoFelhasznalo.getId());
            alert(sikeres ? "Sikeres törlés": "Sikertele törlés");
            reset();
        } catch (IOException e) {
            hibaKiir(e);
        }
    }

    public void onFilmKeresesButtonClick(ActionEvent actionEvent) {
        String keresendo= keresesFilm.getText();
        List<Film> talalatok=new ArrayList<>();
        if (keresendo.isEmpty()){
            alert("Nem adtál meg keresendő címet");
            return;
        }
        for (Film f:filmList
             ) {
            if (f.getCim().toLowerCase().contains(keresendo.toLowerCase())){
                talalatok.add(f);
            }
        }
        filmTable.getItems().clear();
        talalatok.forEach(film -> filmTable.getItems().add(film));
    }

    public void reset() {
        filmListaFeltolt();
        kategoriaListaFeltolt();
        rendezoListaFeltolt();
        szineszListaFeltolt();
        felhasznaloListaFeltolt();
        kapcsolatok();
        keresesFilm.setText("");
        keresesKategoria.setText("");
        keresesRendezo.setText("");
        keresesSzinesz.setText("");
        keresesFelhasznalo.setText("");
    }

    public void onKategoriaKeresesButtonClick(ActionEvent actionEvent) {
        String keresendo= keresesKategoria.getText();
        List<Kategoria> talalatok=new ArrayList<>();
        if (keresendo.isEmpty()){
            alert("Nem adtál meg keresendő szöveget");
            return;
        }
        for (Kategoria k:kategoriaList
        ) {
            if (k.getKategoria().toLowerCase().contains(keresendo.toLowerCase())){
                talalatok.add(k);
            }
        }
        kategoriaTable.getItems().clear();
        talalatok.forEach(kategoria -> kategoriaTable.getItems().add(kategoria));
    }

    public void onRendezoKeresesButtonClick(ActionEvent actionEvent) {
        String keresendo= keresesRendezo.getText();
        List<Rendezo> talalatok=new ArrayList<>();
        if (keresendo.isEmpty()){
            alert("Nem adtál meg keresendő szöveget");
            return;
        }
        for (Rendezo r:rendezoList
        ) {
            if (r.getRendezoNev().toLowerCase().contains(keresendo.toLowerCase())){
                talalatok.add(r);
            }
        }
        rendezoTable.getItems().clear();
        talalatok.forEach(rendezo -> rendezoTable.getItems().add(rendezo));
    }

    public void onSzineszKeresesButtonClick(ActionEvent actionEvent) {
        String keresendo= keresesSzinesz.getText();
        List<Szinesz> talalatok=new ArrayList<>();
        if (keresendo.isEmpty()){
            alert("Nem adtál meg keresendő szöveget");
            return;
        }
        for (Szinesz sz:szineszList
        ) {
            if (sz.getSzineszNev().toLowerCase().contains(keresendo.toLowerCase())){
                talalatok.add(sz);
            }
        }
        szineszTable.getItems().clear();
        talalatok.forEach(szinesz -> szineszTable.getItems().add(szinesz));
    }

    public void onFelhasznaloKeresesButtonClick(ActionEvent actionEvent) {
        String keresendo= keresesFelhasznalo.getText();
        List<Felhasznalo> talalatok=new ArrayList<>();
        if (keresendo.isEmpty()){
            alert("Nem adtál meg keresendő szöveget");
            return;
        }
        for (Felhasznalo f:felhasznaloList
        ) {
            if (f.getEmail().toLowerCase().contains(keresendo.toLowerCase())){
                talalatok.add(f);
            }
        }
        felhasznaloTable.getItems().clear();
        talalatok.forEach(felhasznalo -> felhasznaloTable.getItems().add(felhasznalo));
    }
}