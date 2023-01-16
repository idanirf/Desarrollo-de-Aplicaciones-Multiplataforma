public class App {
    public static void main(String[] args) {
        // Una aplicación contiene procesos que se están ejecutando.
        // En este ejemplo cogemos la ruta donde se encuentra nuestro programa.
        String path = System.getProperty("user.dir");
        System.out.println(path);
    }
}
