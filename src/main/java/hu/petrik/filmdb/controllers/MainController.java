package hu.petrik.filmdb.controllers;

import hu.petrik.filmdb.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.List;

public class MainController extends Controller {
    Film film;
    @FXML
    private TextArea txtAdatok;
    @FXML
    private TableView<Film> filmTable;
    @FXML
    private TableColumn<Film, String> colCim;
    @FXML
    private TableColumn<Film, String> colId;
    List<Film> filmList;


    public void initialize(){
        colCim.setCellValueFactory(new PropertyValueFactory<>("cim"));
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        filmListaFeltolt();
        try {
            FilmApi.ObjektumokLetrehozasa();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            filmList = FilmApi.ObjektumokLetrehozasa();
        } catch (IOException e) {
            e.printStackTrace();
        }
        filmTable.getItems().clear();
        for(Film film: filmList){
            filmTable.getItems().add(film);
        }
    }

    public void onItemSelect(MouseEvent mouseEvent) {
        if(filmTable.getSelectionModel().getSelectedIndex()!=-1) {
            Film kivalasztott = filmTable.getSelectionModel().getSelectedItem();
            StringBuilder  sb=new StringBuilder();
            sb.append("Megjelenési év: ").append(kivalasztott.getMegjelenesiEv()).append("\n");
            //sb.append("Rendező: ").append(kivalasztott.getRendezoNev()).append("\n");
            sb.append("Leírás: ").append(kivalasztott.getLeiras()).append("\n");
           // sb.append("Kategóriák: ").append(kivalasztott.getKategoriak()).append("\n");
            //sb.append("Színészek: ").append(kivalasztott.getSzineszek());
            sb.append("Értékelés: ").append(kivalasztott.getErtekeles()).append("\n");
            txtAdatok.setText(sb.toString());
        }
    }
}