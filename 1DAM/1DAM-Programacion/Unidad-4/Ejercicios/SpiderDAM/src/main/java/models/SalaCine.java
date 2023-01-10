package models;

//import creator.cineCreator;

import com.diogonunes.jcolor.Attribute;

import java.util.Scanner;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.*;

public class SalaCine {

    //Número de filas que tiene la sala
    private int NUMERO_FILAS = 5;
    //Número de columnas que tiene la sala
    private int NUMERO_COLUMNAS = 9;
    //Representación de la sala
    private Butaca[][] matrizSala;
    private int numeroButacas = 0;
    private int precioEntradas = 6;
    private int contabilidad;
    private int butacaLibre;
    private int butacaReservada;
    private int butacaOcupada;

    public int getButacaLibre() {
        return butacaLibre;
    }

    public void setButacaLibre(int butacaLibre) {
        this.butacaLibre = butacaLibre;
    }

    public int getButacaReservada() {
        return butacaReservada;
    }

    public void setButacaReservada(int butacaReservada) {
        this.butacaReservada = butacaReservada;
    }

    public int getButacaOcupada() {
        return butacaOcupada;
    }

    public void setButacaOcupada(int butacaOcupada) {
        this.butacaOcupada = butacaOcupada;
    }

    //Constructor
    public SalaCine() {
        matrizSala = new Butaca[NUMERO_FILAS][NUMERO_COLUMNAS];
        this.registroButaca();
    }

    //CREAR UNA POSICIÓN
    public void registroButaca(){
        for (int i = 0; i < NUMERO_FILAS; i++){
            for (int j = 0; j < NUMERO_COLUMNAS; j++){
                this.matrizSala[i][j] = new Butaca();
            }
        }
    }

    //LEER TODOS LOS DATOS
    public void leerSalaCompleta() {
        String resultado = "";
        for (int i = 0; i < NUMERO_FILAS; i++) {
            System.out.print("|");
            for (int j = 0; j < NUMERO_COLUMNAS; j++) {
                System.out.print(matrizSala[i][j]);
            }
                System.out.println("|" + "\n");
            }
    }

    public int getPrecioEntradas() {
        return precioEntradas;
    }

    public void setPrecioEntradas(int precioEntradas) {
        this.precioEntradas = precioEntradas;
    }

    public int getContabilidad() {
        return contabilidad;
    }

    public void setContabilidad(int contabilidad) {
        this.contabilidad = contabilidad;
    }

    public int getNUMERO_FILAS() {
        return NUMERO_FILAS;
    }

    public void setNUMERO_FILAS(int NUMERO_FILAS) {
        this.NUMERO_FILAS = NUMERO_FILAS;
    }

    public int getNUMERO_COLUMNAS() {
        return NUMERO_COLUMNAS;
    }

    public void setNUMERO_COLUMNAS(int NUMERO_COLUMNAS) {
        this.NUMERO_COLUMNAS = NUMERO_COLUMNAS;
    }

    public Butaca[][] getMatrizSala() {
        return matrizSala;
    }

    public void setMatrizSala(Butaca[][] matrizSala) {
        this.matrizSala = matrizSala;
    }

    public int getNumeroButacas() {
        return numeroButacas;
    }

    public void setNumeroButacas(int numeroButacas) {
        this.numeroButacas = numeroButacas;
    }

    private int seleccionarColumna() {
        System.out.println("Introduce la columna, están numeradas entre 1 y 9.");
        Scanner sc = new Scanner(System.in);
        int seleccionIt = 0;
        boolean ok = false;
        do {
            try {
            seleccionIt = sc.nextInt();
                if (seleccionIt > 0 && seleccionIt <=9){
                    ok = true;
                } else {
                    System.out.println("El dato que has introducido no es correcto, opciones validas entre 1 y 9");
                }
            } catch (Exception e) {
                sc.next();
                System.out.println("Recuerda que para posicionar la columna debes introducir un número entre 1 y 9");
            }
        } while (!ok);
        return seleccionIt-1;
    }

    public int seleccionFila(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce la fila:");
        String seleccionSt = "";
        int seleccionIt = 0;
        boolean ok = false;
        do {
           try{
               seleccionSt = sc.next();
                if (seleccionSt.equalsIgnoreCase("A") ||
                        seleccionSt.equalsIgnoreCase("B") || seleccionSt.equalsIgnoreCase("C") ||
                        seleccionSt.equalsIgnoreCase("D") || seleccionSt.equalsIgnoreCase("E"))
                {
                    ok = true;
                } else {
                    System.out.println(colorize("Dato incorrecto, recuerda que las filas son entre la A y la E."));
                }
           } catch (Exception e) {
               System.out.println("Dato Incorrecto, intente lo de nuevo");
               sc.next();

           }
        } while (!ok);
        if (seleccionSt.equalsIgnoreCase("a")){
            seleccionIt=0;
        }
        if (seleccionSt.equalsIgnoreCase("b")){
            seleccionIt=1;
        }
        if (seleccionSt.equalsIgnoreCase("c")){
            seleccionIt=2;
        }
        if (seleccionSt.equalsIgnoreCase("d")){
            seleccionIt=3;
        }
        if (seleccionSt.equalsIgnoreCase("e")){
            seleccionIt=4;
        }
        return seleccionIt;
    }

    public void newStatus(Enum estado){
        int fila = this.seleccionFila();
        int columna = this.seleccionarColumna();
        this.matrizSala[fila][columna].setEstado((EstadoSala) estado);
    }

    public boolean isLibre(){
        boolean ok = false;
        int fila = this.seleccionFila();
        int columna = this.seleccionarColumna();
        if (this.matrizSala[fila][columna].getEstado() == EstadoSala.libre){
            ok = true;
        }
        return ok;
    }

    public void comprarEntradas() {
        this.leerSalaCompleta();
        boolean ok = false;
        int fila = this.seleccionFila();
        int columna = this.seleccionarColumna();
        do {
            if (this.isLibre()){
                this.newStatus(EstadoSala.ocupada);
                ok = true;
                this.contabilidad += precioEntradas;
            } else {
                System.out.println(colorize("La butaca que ha indicado no está disponible, seleccione otra."));
            }
        } while (!ok);
    }

    public void reservarEntradas() {
        this.leerSalaCompleta();
        boolean ok = false;
        int fila = this.seleccionFila();
        int columna = this.seleccionarColumna();
        do {
            if (this.isLibre()){
                this.newStatus(EstadoSala.reservada);
                ok = true;
            } else {
                System.out.println(colorize("La butaca que ha indicado no está disponible, seleccione otra."));
            }
        } while (!ok);
    }

    public boolean isReservada() {
        boolean ok = false;
        int fila = this.seleccionFila();
        int columna = this.seleccionarColumna();
        if (this.matrizSala[fila][columna].getEstado() == EstadoSala.reservada){
            ok = true;
        }
        return ok;
    }

    public void confirmarReserva(){
        this.leerSalaCompleta();
        boolean ok = false;
        System.out.println("Introduce el asiento que tienes en reserva, recuerda [FILA][COLUMNA]");
        int fila = this.seleccionFila();
        int columna = this.seleccionarColumna();
        do {
            if (this.isReservada()){
                this.newStatus(EstadoSala.ocupada);
                ok = true;
                this.contabilidad -= precioEntradas;
            } else {
                System.out.println("Operación invalidada.");
                fila = this.seleccionFila();
                columna = this.seleccionarColumna();
            }
        } while (!ok);
    }

    public void anularReserva() {
        this.leerSalaCompleta();
        System.out.println("Introduce la reserva que quieres anular:");
        boolean ok = false;
        int fila = this.seleccionFila();
        int columna = this.seleccionarColumna();
        do{
            if (!this.isReservada()){
                this.newStatus(EstadoSala.libre);
                ok = true;
            }else {
                System.out.println(colorize("Operación invalidada",RED_TEXT()));
            }
        } while (!ok);
    }

    public void anularEntradas() {
        this.leerSalaCompleta();
        System.out.println(colorize("Introduce la posición de la butaca que quieres anular", BLUE_TEXT()));
        boolean ok = false;
        int fila = this.seleccionFila();
        int columna = this.seleccionarColumna();
        do{
            if (!this.isLibre()){
                this.newStatus(EstadoSala.libre);
                ok = true;
                this.contabilidad -= precioEntradas;
            }else {
                System.out.println(colorize("Operación invalidada.",RED_TEXT()));
            }
        } while (!ok);
    }

    public void estadisticasContabilidad(){
        if (this.contabilidad > 0){
            System.out.println(colorize("La contabilidad del cine es de: ",GREEN_BACK()) + this.contabilidad);
        } else if (this.contabilidad == 0){
            System.out.println(colorize("No hay facturación disponible saldo = 0", RED_BACK()));
        }
    }

    public void estadisticasOcupacion() {
        this.leerSalaCompleta();
        int reservas;
        for (int i = 0; i < NUMERO_FILAS; i++){
            for (int j = 0; j < NUMERO_COLUMNAS; j++){
                if (this.matrizSala[i][j].getEstado() == EstadoSala.libre){
                    this.butacaLibre++;
                } else if (this.matrizSala[i][j].getEstado() == EstadoSala.reservada){
                    this.butacaReservada++;
                } else if (this.matrizSala[i][j].getEstado() == EstadoSala.ocupada){
                    this.butacaOcupada++;
                }
            }
        }
        reservas = this.butacaLibre - this.butacaOcupada;
        System.out.println();
        System.out.println();
        System.out.println("N-Butacas libres de 45: " + this.butacaLibre);
        System.out.println("N-Butacas reservadas de 45: " + reservas);
        System.out.println("N-Butacas ocupadas de 45: " + this.butacaOcupada);
        System.out.println();
        System.out.println();
    }



}
