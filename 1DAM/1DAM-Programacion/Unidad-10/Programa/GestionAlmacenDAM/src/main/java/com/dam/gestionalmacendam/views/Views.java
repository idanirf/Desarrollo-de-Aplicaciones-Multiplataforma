package com.dam.gestionalmacendam.views;

public enum Views {
    MENU_MANAGER("views/MenuManager.fxml"),
    MENU_EMPLEADO("views/MenuEmployee.fxml"),
    MENU_CUSTOMER("views/MenuCustomer.fxml"),

    SPLASH("views/Splash.fxml"),
    LOGIN("views/Login.fxml"),
    REGISTER("views/Register.fxml"),
    ACERCA_DE("views/AcercaDe.fxml"),

    INFO_ARTICLE("views/InfoArticle.fxml"),
    CARRITO_VIEW("views/CarritoView.fxml"),
    SHOW_DATA_CUSTOMER("views/ShowCustomerData.fxml"),
    MODIFY_DATA_CUSTOMER("views/ModifyCustomerData.fxml"),
    SHOW_DATA_CUSTOMER_ORDER("views/ShowCustomerOrderData.fxml"),

    EMPLOYEE_VIEW("views/EmployeeView.fxml"),
    NEW_EMPLOYEE("views/NewEmployeeView.fxml"),
    MODIFY_EMPLOYEE("views/ModifyEmployeeView.fxml"),

    CUSTOMER_VIEW("views/CustomerView.fxml"),
    NEW_CUSTOMER("views/NewCustomerView.fxml"),
    MODIFY_CUSTOMER("views/ModifyCustomerView.fxml"),

    ARTICLE_VIEW("views/ArticleView.fxml"),
    NEW_ARTICLE("views/NewArticleView.fxml"),
    RESUME_ARTICLE("views/ResumeArticleView.fxml"),

    RECEPTION_VIEW("views/ReceptionView.fxml"),
    NEW_RECEPTION("views/NewReceptionView.fxml"),
    RESUME_RECEPTION("views/ResumeReceptionView.fxml"),

    SUPPLIER_VIEW("views/SupplierView.fxml"),
    NEW_SUPPLIER("views/NewSupplierView.fxml"),
    MODIFY_SUPPLIER("views/ModifySupplierView.fxml"),

    ORDER_VIEW("views/OrderView.fxml"),
    LINE_ORDER_VIEW("views/LineOrderView.fxml");


    private final String view;

    Views(String view) {
        this.view = view;
    }

    public String get() {
        return view;
    }
}
