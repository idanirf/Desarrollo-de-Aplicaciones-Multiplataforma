module com.dam.gestionalmacendam {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;
    requires com.google.gson;


    opens com.dam.gestionalmacendam to javafx.fxml;
    exports com.dam.gestionalmacendam;
    exports com.dam.gestionalmacendam.controllers.menus;
    opens com.dam.gestionalmacendam.controllers.menus to javafx.fxml;
    exports com.dam.gestionalmacendam.controllers.login;
    opens com.dam.gestionalmacendam.controllers.login to javafx.fxml;
    exports com.dam.gestionalmacendam.controllers.viewMainCustomer;
    opens com.dam.gestionalmacendam.controllers.viewMainCustomer to javafx.fxml;
    exports com.dam.gestionalmacendam.controllers.viewArticle;
    opens com.dam.gestionalmacendam.controllers.viewArticle to javafx.fxml;
    exports com.dam.gestionalmacendam.controllers.viewCustomer;
    opens com.dam.gestionalmacendam.controllers.viewCustomer to javafx.fxml;
    exports com.dam.gestionalmacendam.controllers.viewOrder;
    opens com.dam.gestionalmacendam.controllers.viewOrder to javafx.fxml;
    exports com.dam.gestionalmacendam.controllers.viewSupplier;
    opens com.dam.gestionalmacendam.controllers.viewSupplier to javafx.fxml;
    exports com.dam.gestionalmacendam.controllers.viewEmployee;
    opens com.dam.gestionalmacendam.controllers.viewEmployee to javafx.fxml;
    exports com.dam.gestionalmacendam.controllers.viewReception;
    opens com.dam.gestionalmacendam.controllers.viewReception to javafx.fxml;
    opens com.dam.gestionalmacendam.models to com.google.gson;
    opens com.dam.gestionalmacendam.controllers.BackUp to com.google.gson;
    opens com.dam.gestionalmacendam.dto to com.google.gson;
}