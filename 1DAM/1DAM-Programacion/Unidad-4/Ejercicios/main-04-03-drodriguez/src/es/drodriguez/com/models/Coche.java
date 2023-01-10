package es.drodriguez.com.models;

import java.util.Scanner;

public class Coche {
    private String marca;
    private String modelo;
    private String color;
    private String matricula;
    private int marcha;
    private int velocidad;
    private int contadorTiempo;

    public int getContadorTiempo() {
        return contadorTiempo;
    }

    public void setContadorTiempo(int contadorTiempo) {
        this.contadorTiempo = contadorTiempo;
    }

    public String getMarca() {
        Scanner sc = new Scanner(System.in);
        marca = sc.next();
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    //Pido el modelo al usuario
    public String getModelo() {
        Scanner sc = new Scanner(System.in);
        modelo = sc.next();
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    //Pido el color al usuario
    public String getColor() {
        Scanner sc = new Scanner(System.in);
        modelo = sc.next();
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    //Pido matricula al usuario
    public String getMatricula() {
        Scanner sc = new Scanner(System.in);
        matricula = sc.next();
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    //Pido marcha al usuario
    public int getMarcha() {
        return marcha;
    }

    public void setMarcha(int marcha) {
        this.marcha = marcha;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    private int velocidadActual(){
        return getVelocidad();
    }

    //Principal, donde se ejecuta el programa
    public void ConduccionGuiada(){
        Scanner sc = new Scanner(System.in);
        //Vamos a pedirle al conductor que introduzca los datos del vehículo
        System.out.print("Introduce la matricula: ");
        getMatricula();
        System.out.print("Introduce la marca: ");
        getMarca();
        System.out.print("Introduce el modelo: ");
        getModelo();
        System.out.print("Introduce el color: ");
        getColor();

        //Una vez que tenemos los datos del vehículo, vamos a iniciar el coche
        statusCar("stop");
        startMotor();
        System.out.println("Coche arrancado ✅, por favor ponte el el cinturón 🚗");

        System.out.print("Indica la velocidad máxima de la vía 💨: ");
        int velocidadUser = sc.nextInt();

        System.out.print("Indica el tiempo de conducción ⌛: ");
        int tiempoConduccion = sc.nextInt();

        subirVelocidad(velocidadUser);

        //Ejecución del programa, el programa va a estar funcionando hasta que llegue al tiempo máximo
        // o aparezca un gato

        boolean lifeCat = false;
        do {
            velocidadFija(tiempoConduccion);
            System.out.println("Me matengo en VELOCIDAD ");
            lifeCat = (Math.random()*100 < 10);
            contadorTiempo++;
            if (lifeCat){
                calarCoche();
            }
        }while (contadorTiempo > tiempoConduccion || !lifeCat);
        reducirVelocidad(velocidad);
    }

    //Método que utilizamos para iniciar el motor
    public String startMotor(){
        Scanner sc = new Scanner(System.in);
        String starStop = "";
        do {
            //Controlar la excepción por si introduce mal la palabra de arrancar
            try {
                System.out.println("Para arrancar el coche debe escribir START");
                starStop = sc.next();
            } catch (Exception e) {
                System.out.println("Ha introducido mal la palabra de acción, sí desea arrancar escriba = START ▶");
            }
        } while (!starStop.equals("START") && !starStop.equals("STOP"));
        return starStop;
    }

    //Estado en el que se encuentra nuestro vehículo
    public boolean statusCar(String starStop){
        boolean ok = false;
        if (starStop.equals("START")){
            System.out.println("Status: start");
            return true;
        } else {
            System.out.println("Status: stop");
            return false;
        }
    }

    //Posición actual de ejecución de la marcha
    public void posicionActualMarcha(int marcha){
        this.marcha = marcha;
    }

    //Marchas del vehículo según la velocidad a la que vaya.
    public void relacionMarchas(int velocidad){
        if (velocidad >0 && velocidad <=30){
            marcha = 1;
            posicionActualMarcha(marcha);
            setVelocidad(velocidad);
        } else if (velocidad >30 && velocidad <=50){
            marcha = 2;
            posicionActualMarcha(marcha);
        } else if (velocidad >50 && velocidad <=70){
            marcha = 3;
            posicionActualMarcha(marcha);
        } else if (velocidad >70 && velocidad <=100){
            marcha = 4;
            posicionActualMarcha(marcha);
        } else if (velocidad >100){
            marcha = 5;
            posicionActualMarcha(marcha);
        }
    }

    //Aumentamos velocidad
    public void subirVelocidad(int velocidadUser){
        do {
            velocidad++;
            relacionMarchas(velocidad);
            System.out.println("Velocidad actual: " +velocidad + " y la marcha actual es: " + getMarcha());
        } while(velocidad < velocidadUser);
    }

    //Reducimos velocidad
    public void reducirVelocidad(int velocidad) {
        do{
            velocidad--;
            System.out.println("Velocidad actual: " +velocidad + " y la marcha actual es: " + getMarcha());
            if (velocidad == 0){
                puntoMuerto();
            }
        }while (velocidad != 0);
    }

    //Punto muerto, es decir velocidad = 0 y marcha = 0
    public void puntoMuerto(){
        velocidad = 0;
        marcha = 0;
    }

    //Calar coche cuando aparezca gato
    public void calarCoche(){
        System.out.println(" 🛑 🛑 🛑 🛑 🛑 🛑 🛑 🛑");
        System.out.println(" He tenido que frenar 🛑, para no llevarme el gato 🐱‍‍");
        System.out.println(" 🛑 🛑 🛑 🛑 🛑 🛑 🛑 🛑");
    }

    //Mantener velocidad durante un periodo que nos indique el usuario
    public void velocidadFija(int tiempoConduccion){
        try {
            Thread.sleep(tiempoConduccion);
        } catch (InterruptedException e) {
            System.err.println("Err en tiempo y vel");
        }
    }
}

