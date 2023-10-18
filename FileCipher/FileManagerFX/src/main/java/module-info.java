module com.example.filemanagerfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.filemanagerfx to javafx.fxml;
    exports com.example.filemanagerfx;
}