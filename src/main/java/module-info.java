module g3.g3_proyecto_contactos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens g3.g3_proyecto_contactos to javafx.fxml;
    exports g3.g3_proyecto_contactos;
    opens g3.g3_proyecto_contactos.controllers to javafx.fxml;
    exports g3.g3_proyecto_contactos.controllers;
}
