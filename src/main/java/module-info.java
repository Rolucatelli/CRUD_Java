module projeto.crud_java {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires java.sql;

    opens projeto.crud_java to javafx.fxml;
    exports projeto.crud_java;
}