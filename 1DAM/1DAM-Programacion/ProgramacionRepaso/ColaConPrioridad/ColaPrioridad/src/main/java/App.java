public class App {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Cola de procesos con prioridad, primero en entrar es el primero en salir");
        //Llamamos al controller donde se realiza la lógica de los procesos
        Controller cont = new Controller();
        cont.controller();
    }
}
