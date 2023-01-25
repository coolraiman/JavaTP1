module com.manager {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    //requires mysql.connector.java;
    opens com.manager to javafx.fxml;
    exports com.manager;
}
