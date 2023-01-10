package com.dam.gestionalmacendam.utils;

import java.io.File;

public class Properties {
    public static final int APP_HEIGHT = 720;
    public static final int APP_WIDTH = 1280;
    public static final int ACERCA_DE_WIDTH = 720;
    public static final int ACERCA_DE_HEIGHT = 720;
    public static final String APLICACION_NAME = "GADAM Gestión de Almacenes";
    public static final String APLICACION_VERSION = " 1.0.0";
    public static final String APLICACION_LICENCIA = "GADAM GROUP - MIT";
    public static final String APP_ICON = "images/logoMini.png";
    private static final String APP_PATH = System.getProperty("user.dir");
    public static final String BACKUP = APP_PATH + File.separator + "backup";

    public static final String BACKUP_FILE = BACKUP + File.separator + "copySecurity.json";


}
