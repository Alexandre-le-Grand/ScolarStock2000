module com.ScolarShare2000 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.commons.lang3;
    requires jakarta.mail;
    requires io.github.cdimascio.dotenv.java;


    opens application to javafx.fxml;
    exports application;
    opens controller to javafx.fxml;
    exports controller;
}