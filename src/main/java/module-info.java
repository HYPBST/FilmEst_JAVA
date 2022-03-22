module hu.petrik.filmdb {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.google.gson;
    requires java.desktop;

    opens hu.petrik.filmdb to javafx.fxml, com.google.gson;
    exports hu.petrik.filmdb;
    exports hu.petrik.filmdb.controllers;
    opens hu.petrik.filmdb.controllers to javafx.fxml;
    opens hu.petrik.filmdb.classes to com.google.gson, javafx.fxml;
    opens hu.petrik.filmdb.pivot to com.google.gson, javafx.fxml;
    exports hu.petrik.filmdb.classes;
    exports hu.petrik.filmdb.pivot;
}