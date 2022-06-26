module com.example.atp_part_3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.atp_part_3 to javafx.fxml;
    exports com.example.atp_part_3;
}