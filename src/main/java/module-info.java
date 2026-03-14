module udeo.demogrid {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens udeo.demogrid to javafx.fxml;
    exports udeo.demogrid;
}