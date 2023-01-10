package com.dam.gestionalmacendam.managers;

import com.dam.gestionalmacendam.HelloApplication;
import com.dam.gestionalmacendam.controllers.login.LoginController;
import com.dam.gestionalmacendam.controllers.login.RegisterController;
import com.dam.gestionalmacendam.controllers.login.SplashController;
import com.dam.gestionalmacendam.controllers.menus.MenuCustomerController;
import com.dam.gestionalmacendam.controllers.menus.MenuEmployeeController;
import com.dam.gestionalmacendam.controllers.menus.MenuManagerController;
import com.dam.gestionalmacendam.controllers.viewArticle.ProductoController;
import com.dam.gestionalmacendam.controllers.viewArticle.ResumenController;
import com.dam.gestionalmacendam.controllers.viewCustomer.CustomerViewController;
import com.dam.gestionalmacendam.controllers.viewCustomer.EditarCustomerController;
import com.dam.gestionalmacendam.controllers.viewCustomer.NewCustomerController;
import com.dam.gestionalmacendam.controllers.viewEmployee.EditarEmployeeController;
import com.dam.gestionalmacendam.controllers.viewEmployee.NewEmployeeController;
import com.dam.gestionalmacendam.controllers.viewMainCustomer.*;
import com.dam.gestionalmacendam.controllers.viewOrder.LineaOrderController;
import com.dam.gestionalmacendam.controllers.viewReception.NewRecepcionController;
import com.dam.gestionalmacendam.controllers.viewReception.ResumenReceptionController;
import com.dam.gestionalmacendam.controllers.viewSupplier.EditarSuplierController;
import com.dam.gestionalmacendam.controllers.viewSupplier.SupplierVistaController;
import com.dam.gestionalmacendam.models.*;
import com.dam.gestionalmacendam.utils.Properties;
import com.dam.gestionalmacendam.utils.Resources;
import com.dam.gestionalmacendam.views.Views;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;


public class SceneManager {
    private static SceneManager instance;
    private final Class<?> appClass;

    private Stage mainStage;
    private Stage splash;

    private SceneManager(Class<?> appClass) {
        this.appClass = appClass;
    }

    public static SceneManager getInstance(Class<?> appClass) {
        if (instance == null) {
            instance = new SceneManager(appClass);
        }
        return instance;
    }

    public static SceneManager get() {
        return instance;
    }


    // Vista de menús principales.
    public void initMainCustomer(Stage login, Customer customer) throws IOException {
        System.out.println("Entrando a la vista del cliente.");
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(appClass.getResource(Views.MENU_CUSTOMER.get())));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        Stage stage = new Stage();
        MenuCustomerController controller = fxmlLoader.getController();
        controller.setDialogStage(stage);
        controller.setCustomer(customer);
        mainStage = stage;
        stage.setTitle("GADAM S.L.");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.getIcons().add(new Image(Resources.get(HelloApplication.class, Properties.APP_ICON)));
        stage.show();
        login.close();
        if (!login.isShowing()) {
            splash.close();
        }
        setOnClose(stage);

    }

    public void initAPPManager(Stage login, Employee employee) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(Views.MENU_MANAGER.get()));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        MenuManagerController controller = fxmlLoader.getController();
        controller.setEmployee(employee);
        controller.setStage(stage);
        stage.setTitle("GADAM Gestión de Almacenes");
        stage.getIcons().add(new Image(Resources.get(HelloApplication.class, Properties.APP_ICON)));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        login.close();
        if (!login.isShowing()) {
            splash.close();
        }
        setOnClose(stage);
    }

    public void initAPPEmployee(Stage login, Employee employee) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(Views.MENU_EMPLEADO.get()));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        MenuEmployeeController controller = fxmlLoader.getController();
        controller.setEmployee(employee);
        controller.setStage(stage);
        stage.setTitle("GADAM Gestión de Almacenes");
        stage.getIcons().add(new Image(Resources.get(HelloApplication.class, Properties.APP_ICON)));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        login.close();
        if (!login.isShowing()) {
            splash.close();
        }
        setOnClose(stage);
    }

    // Vista de acerca de
    public void initAcercaDe() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(Views.ACERCA_DE.get()));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Acerca de");
        stage.setResizable(false);
        AcercaDeController controller = fxmlLoader.getController();
        controller.setDialogStage(stage);
        stage.getIcons().add(new Image(Resources.get(HelloApplication.class, Properties.APP_ICON)));
        stage.setScene(scene);
        stage.showAndWait();
    }

    // Vista del splash
    public void initSplash(Stage stage) throws IOException {
        Platform.setImplicitExit(false);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(Views.SPLASH.get()));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        SplashController controller = fxmlLoader.getController();
        controller.setDialogStage(stage);
        stage.setResizable(false);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.getIcons().add(new Image(Resources.get(HelloApplication.class, Properties.APP_ICON)));
        stage.show();

    }

    // Vista del login y sus opciones
    public void initLogin(Stage splash) throws IOException {
        Platform.setImplicitExit(false);
        System.out.println("Iniciando Login");
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(appClass.getResource(Views.LOGIN.get())));
        Scene scene = new Scene(fxmlLoader.load(), 540, 550);
        Stage stage = new Stage();
        LoginController controller = fxmlLoader.getController();
        controller.setDialogStage(stage);
        this.splash = splash;
        stage.setTitle("Login");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.getIcons().add(new Image(Resources.get(HelloApplication.class, Properties.APP_ICON)));
        stage.show();

        setOnClose(stage);
    }

    public void initRegister() throws IOException {
        System.out.println("Registrando nuevo usuario.");
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(appClass.getResource(Views.REGISTER.get())));
        Scene scene = new Scene(fxmlLoader.load(), 641, 720);
        Stage stage = new Stage();
        RegisterController controller = fxmlLoader.getController();
        controller.setDialogStage(stage);
        stage.setTitle("Registrar Usuario Nuevo");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.getIcons().add(new Image(Resources.get(HelloApplication.class, Properties.APP_ICON)));
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Saliendo...");
            alert.setHeaderText("¿Esta seguro que desea salir?");
            alert.setContentText("Pulse aceptar para salir.");
            var res = alert.showAndWait();
            if (res.get() == ButtonType.OK) {
                stage.close();
            } else {
                event.consume();
            }
        });
    }

    // Vista detallada del artículo en el menú del cliente
    public void initViewArticle(Article article) throws IOException {
        System.out.println("Viendo resumen del articulo " + article.getArticle().get());
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(appClass.getResource(Views.INFO_ARTICLE.get())));
        Scene scene = new Scene(fxmlLoader.load(), 560, 520);
        Stage stage = new Stage();
        ViewArticleController controller = fxmlLoader.getController();
        controller.setArticle(article);
        controller.setDialogStage(stage);
        stage.setTitle("Producto " + article.getArticle().get());
        stage.setResizable(false);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.getIcons().add(new Image(Resources.get(HelloApplication.class, Properties.APP_ICON)));
        stage.show();

    }

    // Vista detallada del perfil del cliente en el menú del cliente y sus opciones.
    public void initViewDataCustomer(Customer customer) throws IOException {
        System.out.println("Viendo el perfil del usuario.");
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(appClass.getResource(Views.SHOW_DATA_CUSTOMER.get())));
        Scene scene = new Scene(fxmlLoader.load(), 400, 400);
        Stage stage = new Stage();
        ShowCustomerDataController controller = fxmlLoader.getController();
        controller.setStage(stage);
        controller.setCustomer(customer);
        stage.setTitle("Tu perfil");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.getIcons().add(new Image(Resources.get(HelloApplication.class, Properties.APP_ICON)));
        stage.show();

    }

    public void initViewModifyDataCustomer(Customer customer, Stage view) throws IOException {
        System.out.println("Modificando el perfil.");
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(appClass.getResource(Views.MODIFY_DATA_CUSTOMER.get())));
        Scene scene = new Scene(fxmlLoader.load(), 400, 400);
        Stage stage = new Stage();
        ModifyCustomerDataController controller = fxmlLoader.getController();
        controller.setCustomer(customer);
        controller.setStage(stage);
        view.close();
        stage.setTitle("Modificar los datos.");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.getIcons().add(new Image(Resources.get(HelloApplication.class, Properties.APP_ICON)));
        stage.showAndWait();
        if (!stage.isShowing()) {
            view.show();
        }
        stage.setOnCloseRequest(event -> {
            view.show();
        });

    }

    // Vista detallada de los pedidos del cliente
    public void initViewOrderCustomer(Customer customer) throws IOException {
        System.out.println("Acciendo a Mis Pedidos");
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(appClass.getResource(Views.SHOW_DATA_CUSTOMER_ORDER.get())));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        ShowCustomerOrderController controller = fxmlLoader.getController();
        controller.setCustomer(customer);
        stage.setTitle("Tus Pedidos");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.getIcons().add(new Image(Resources.get(HelloApplication.class, Properties.APP_ICON)));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.showAndWait();
    }


    //  Vista tabla articulos y sus opciones
    public void initArticleView(Stage init) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(appClass.getResource(Views.ARTICLE_VIEW.get())));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        Stage stage = new Stage();
        init.close();
        stage.setTitle("VISTA PRODUCTOS MANAGER-EMPLEADO");
        stage.setResizable(false);
        stage.getIcons().add(new Image(Resources.get(HelloApplication.class, Properties.APP_ICON)));
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
            init.show();
        });
    }

    public void initResumeArticle(Article producto) throws IOException {
        System.out.println("Viendo el resumen del pedido....");
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(Views.RESUME_ARTICLE.get()));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Resumen_Producto_GADAM_ " + producto.getArticle().get());
        stage.getIcons().add(new Image(Resources.get(HelloApplication.class, Properties.APP_ICON)));
        stage.initOwner(mainStage);
        stage.setResizable(false);
        stage.setScene(scene);
        ResumenController controller = fxmlLoader.getController();
        controller.setProducto(producto);
        stage.showAndWait();

    }

    public boolean initProducto(boolean editarModo, Article producto) throws IOException {
        System.out.println("Iniciando....");
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(Views.NEW_ARTICLE.get()));
        Scene scene = new Scene(fxmlLoader.load(), 561, 507);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.getIcons().add(new Image(Resources.get(HelloApplication.class, Properties.APP_ICON)));
        stage.initOwner(mainStage);
        stage.setTitle(editarModo ? "Editar Producto" : "Crear Nuevo Producto");
        stage.setResizable(false);
        ProductoController controller = fxmlLoader.getController();
        controller.setDialogStage(stage);
        controller.setEditarModo(editarModo);
        controller.setProducto(producto);
        stage.setScene(scene);
        stage.showAndWait();
        return controller.isAceptarClicked();
    }

    // Vista Tabla Recepción y sus opciones

    public void initReception(Stage init) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(HelloApplication.class.getResource(Views.RECEPTION_VIEW.get())));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        init.close();
        stage.setTitle("Recepciones_GADAM");
        stage.getIcons().add(new Image(Resources.get(HelloApplication.class, Properties.APP_ICON)));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
            init.show();
        });
    }

    public boolean initNewReception(Reception recepcion, LineReception lineReception) throws IOException {
        System.out.println("Iniciando....");
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(Views.NEW_RECEPTION.get()));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.getIcons().add(new Image(Resources.get(HelloApplication.class, Properties.APP_ICON)));
        stage.setResizable(false);
        stage.setTitle("Nueva_Recepcion_GADAM");
        NewRecepcionController controller = fxmlLoader.getController();
        controller.setDialogStage(stage);
        controller.setReception(recepcion, lineReception);
        stage.setScene(scene);
        stage.showAndWait();
        return controller.isAceptarClicked();
    }

    public void initResumeReception(Reception x) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(Views.RESUME_RECEPTION.get()));
        Scene scene = new Scene(fxmlLoader.load(), 657, 481);
        Stage stage = new Stage();
        ResumenReceptionController controller = fxmlLoader.getController();
        controller.setRecepcion(x);
        stage.setTitle("Resume_Recepcion_GADAM " + x.getSupplierName().get());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.getIcons().add(new Image(Resources.get(HelloApplication.class, Properties.APP_ICON)));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.showAndWait();

    }
    // Vista tabla empleados y sus opciones

    public void initEmployee(Stage init) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(Views.EMPLOYEE_VIEW.get()));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        init.close();
        stage.initModality(Modality.APPLICATION_MODAL); //TODO ESTO EVITA Q LA VENTANA DE DETRAS NO SE PUEDA TOCAR
        stage.setTitle("Empleados_GADAM");
        stage.setResizable(false);
        stage.getIcons().add(new Image(Resources.get(HelloApplication.class, Properties.APP_ICON)));
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
            init.show();
        });
    }

    public boolean initNewEmployee(boolean empty, Employee employee) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(Views.NEW_EMPLOYEE.get()));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        NewEmployeeController controller = fxmlLoader.getController();
        controller.setDialogStage(stage);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Nuevo_Empleado_GADAM");
        stage.setResizable(false);
        stage.getIcons().add(new Image(Resources.get(HelloApplication.class, Properties.APP_ICON)));
        stage.setScene(scene);
        stage.showAndWait();
        return controller.isAceptarClicked();
    }

    public void initModifyEmployee(Employee employee) throws IOException {
        System.out.println(employee);
        Platform.setImplicitExit(true);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(Views.MODIFY_EMPLOYEE.get()));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Modificar_Empleado_GADAM " + employee.getName());
        EditarEmployeeController controller = fxmlLoader.getController();
        controller.setDialogStage(stage);
        controller.setEmployee(employee);
        stage.setResizable(false);
        stage.getIcons().add(new Image(Resources.get(HelloApplication.class, Properties.APP_ICON)));
        stage.setTitle("Editar - Empleados");
        stage.setScene(scene);
        stage.show();
    }

    // Vista tabla Proveedores y sus opciones
    public void initSupplierView(Stage init) throws IOException {
        Platform.setImplicitExit(true);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(Views.SUPPLIER_VIEW.get()));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        SupplierVistaController controller = fxmlLoader.getController();
        controller.setStage(stage);
        init.close();
        stage.setResizable(false);
        stage.getIcons().add(new Image(Resources.get(HelloApplication.class, Properties.APP_ICON)));
        stage.setTitle("Proveedores_GADAM");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
            init.show();
        });
    }

    public void initNewSuplier() throws IOException {
        System.out.println("Iniciando vista nuevo supplier");
        Platform.setImplicitExit(true);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(Views.NEW_SUPPLIER.get()));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Nuevo Proveedor");
        stage.getIcons().add(new Image(Resources.get(HelloApplication.class, Properties.APP_ICON)));
        stage.setScene(scene);
        stage.showAndWait();

    }

    public void initModificarSuplier(Supplier supplier) throws IOException {
        System.out.println("Iniciando vista modificación supplier");
        Platform.setImplicitExit(true);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(Views.MODIFY_SUPPLIER.get()));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        EditarSuplierController controller = fxmlLoader.getController();
        controller.setSupplier(supplier);
        stage.setResizable(false);
        stage.setTitle("Modificar Proveedor");
        stage.getIcons().add(new Image(Resources.get(HelloApplication.class, Properties.APP_ICON)));
        stage.setScene(scene);
        stage.showAndWait();
    }

    // Vista Tabla Pedidos con sus opciones
    public void initOrderView(Stage init) throws IOException {
        Platform.setImplicitExit(true);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(Views.ORDER_VIEW.get()));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        init.close();
        stage.setResizable(false);
        stage.setTitle("Pedidos_GADAM");
        stage.setScene(scene);
        stage.getIcons().add(new Image(Resources.get(HelloApplication.class, Properties.APP_ICON)));
        stage.show();
        stage.setOnCloseRequest(event -> {
            init.show();
        });
    }
    public void initLineOrderView(Order order) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(Views.LINE_ORDER_VIEW.get()));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        LineaOrderController controller = fxmlLoader.getController();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.getIcons().add(new Image(Resources.get(HelloApplication.class, Properties.APP_ICON)));
        controller.setOrder(order);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.showAndWait();
    }

    // Vista tabla customer con sus opciones.
    public void initViewCustomer(Stage init) throws IOException {
        System.out.println("Entrando a la vista de clientes.");
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(appClass.getResource(Views.CUSTOMER_VIEW.get())));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        CustomerViewController controller = fxmlLoader.getController();
        controller.setStage(stage);
        init.close();
        stage.setTitle("Consultas Clientes");
        stage.setResizable(false);
        stage.getIcons().add(new Image(Resources.get(HelloApplication.class, Properties.APP_ICON)));
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
            init.show();
        });

    }

    public void initModifyCustomer(Customer customer) throws IOException {
        System.out.println(customer);
        Platform.setImplicitExit(true);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(Views.MODIFY_CUSTOMER.get()));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        EditarCustomerController controller = fxmlLoader.getController();
        controller.setDialogStage(stage);
        controller.setCustomer(customer);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.getIcons().add(new Image(Resources.get(HelloApplication.class, Properties.APP_ICON)));
        stage.setTitle("Editar - Clientes");
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void initNewCustomer() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(Views.NEW_CUSTOMER.get()));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        NewCustomerController controller = fxmlLoader.getController();
        controller.setDialogStage(stage);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Empleados");
        stage.setResizable(false);
        stage.getIcons().add(new Image(Resources.get(HelloApplication.class, Properties.APP_ICON)));
        stage.setScene(scene);
        stage.showAndWait();
    }

    // Vista Carrito
    public void initCarrito(Customer customer, Stage init) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(Views.CARRITO_VIEW.get()));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        ViewCarritoController controller = fxmlLoader.getController();
        controller.setDialogStage(mainStage);
        controller.setStage(stage);
        controller.setCustomer(customer);
        stage.setResizable(false);
        stage.getIcons().add(new Image(Resources.get(HelloApplication.class, Properties.APP_ICON)));
        stage.setTitle("Cesta de Productos");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
            init.show();
        });

    }
    /**
     * Método que muestra un alert de confirmación cuando se pulsa en la X de salir.
     *
     * @param stage Stage que realiza el evento
     */
    private void setOnClose(Stage stage) {
        stage.setOnCloseRequest(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Saliendo...");
            alert.setHeaderText("¿Esta seguro que desea salir?");
            alert.setContentText("Pulse aceptar para salir.");
            var res = alert.showAndWait();
            if (res.get() == ButtonType.OK) {
                Platform.exit();
            } else {
                event.consume();
            }
        });
    }
}
