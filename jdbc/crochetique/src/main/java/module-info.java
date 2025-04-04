module mauri4real.crochetique {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens mauri4real.crochetique to javafx.fxml;
    exports mauri4real.crochetique;
}