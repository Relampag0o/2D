module com.example.vboxexercise {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.opencsv;


    opens com.example.vboxexercise to javafx.fxml;
    exports com.example.vboxexercise;
}