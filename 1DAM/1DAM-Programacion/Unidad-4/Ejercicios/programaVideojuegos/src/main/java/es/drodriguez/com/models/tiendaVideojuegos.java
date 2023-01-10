package es.drodriguez.com.models;


import es.drodriguez.com.creator.videojuegosCreator;

public class tiendaVideojuegos {
    private int MAXIMO_VIDEOJUEGOS = 10;
    private int numeroJuegos = 0;

    private String nombreTienda;
    private Videojuegos[] videojuegos;

    // Creamos el constructor
    public tiendaVideojuegos(String nombreTienda) {
        this.nombreTienda = nombreTienda;
        videojuegos = new Videojuegos[MAXIMO_VIDEOJUEGOS];
    }


    //leer todos los juegos
    public String leerTodosVideojuegos(){
        String resultado = "";
        if (numeroJuegos != 0){
            for(int i = 0; i < videojuegos.length; i++){
                if (videojuegos[i] != null){
                    resultado += (i+1) + "-." + videojuegos[i].toString() + " \n";
                }
            }
        } else {
            resultado = "No he encontrado videojuegos disponibles en la tienda";
        }
        return resultado;
    }


    //leer un solo juego
    public void leerVideojuego(int juego){
        for(int i = 0; i <videojuegos.length; i++){
            if (videojuegos[i] != null){
                System.out.println(videojuegos[i]);
            } else {
                System.out.println("Este juego no está disponible");
            }
        }

    }

    //crear un videojuego
    public void crearVideojuego(Videojuegos videojuegos, int posicion){
       if (posicion < MAXIMO_VIDEOJUEGOS && posicion >=0){
           numeroJuegos++;
       } else {
           System.err.println("No se puede añadir el videojuego a la posición.");
       }
    }

    //Crear videojuego
    public void crearVideojuego(Videojuegos Videojuegos){
        for (int i = 0; i <MAXIMO_VIDEOJUEGOS; i++){
            if (videojuegos[i] == null){
                videojuegos[i] = Videojuegos;
                numeroJuegos++;
                return;
            }
        }
        System.err.println("No se puede añadir el videojuego, nos salimos del vector");
    }

    //borrar un solo juego
    public void borrarVideojuego(int posicion) {
        if (posicion < MAXIMO_VIDEOJUEGOS && posicion >= 0){
            if (videojuegos[posicion] != null){
                videojuegos[posicion]= null;
            } else {
                System.err.println("No hay videojuego en la posición que has seleccionado");
            }
        } else {
            System.err.println("No se puede actualizar el videojuego en la posición que has seleccionado.");
        }
    }


    //actualizar videojuego
    public void actualizarVideojuego(Videojuegos videojuego, int posicion){
        if(posicion < MAXIMO_VIDEOJUEGOS && posicion >= 0){
            if (videojuegos[posicion] != null){
                videojuegos[posicion]= videojuego;
            } else {
                System.err.println("No hay videojuego en la posición que has seleccionado");
            }
        } else {
            System.err.println("No se puede actualizar el videojuego en la posición que has seleccionado.");
        }
    }

    public String getNombreTienda() {
        return nombreTienda;
    }

    public void setNombreTienda(String nombreTienda) {
        this.nombreTienda = nombreTienda;
    }

    public int getMAXIMO_VIDEOJUEGOS() {
        return MAXIMO_VIDEOJUEGOS;
    }

    public void setMAXIMO_VIDEOJUEGOS(int MAXIMO_VIDEOJUEGOS) {
        this.MAXIMO_VIDEOJUEGOS = MAXIMO_VIDEOJUEGOS;
    }

    public int getNumeroJuegos() {
        return numeroJuegos;
    }

    public void setNumeroJuegos(int numeroJuegos) {
        this.numeroJuegos = numeroJuegos;
    }

    public Videojuegos[] getVideojuegos() {
        return videojuegos;
    }

    public void crearVideojuego() {
        videojuegosCreator v = new videojuegosCreator();

        for(int i = 0; i < videojuegos.length; i++){
            if (videojuegos[i] == null){
                videojuegos[i] = v.constructorRandom();
            } else {
                System.err.println("Tenemos un videojuego en esa posición.");
            }
        }
    }

    public void initRandom() {
        numeroJuegos = 0;
        videojuegosCreator creator = new videojuegosCreator();
        for (int i = 0; i < MAXIMO_VIDEOJUEGOS; i++) {
            videojuegos[i] = creator.constructorRandom();
            numeroJuegos++;
        }
    }

    public void orderPrecioASC() {
        Videojuegos aux;
        for (int i = 0; i < MAXIMO_VIDEOJUEGOS; i++) {
            for (int j = i + 1; j < MAXIMO_VIDEOJUEGOS; j++) {
                if (videojuegos[i] != null && videojuegos[j] != null &&
                        videojuegos[i].getPrecio() > videojuegos[j].getPrecio()) {
                    aux = videojuegos[i];
                    videojuegos[i] = videojuegos[j];
                    videojuegos[j] = aux;
                }
            }
        }
    }
    public void orderPrecioDESC() {
        Videojuegos aux;
        for (int i = 0; i < MAXIMO_VIDEOJUEGOS; i++) {
            for (int j = i + 1; j < MAXIMO_VIDEOJUEGOS; j++) {
                if (videojuegos[i] != null && videojuegos[j] != null &&
                        videojuegos[i].getPrecio() < videojuegos[j].getPrecio()) {
                    aux = videojuegos[i];
                    videojuegos[i] = videojuegos[j];
                    videojuegos[j] = aux;
                }
            }
        }
    }

    public void orderPlataformaASC() {
        Videojuegos aux;
        for (int i = 0; i < MAXIMO_VIDEOJUEGOS; i++) {
            for (int j = i + 1; j < MAXIMO_VIDEOJUEGOS; j++) {
                if (videojuegos[i] != null && videojuegos[j] != null &&
                        videojuegos[i].getPlataforma().toLowerCase().compareTo(videojuegos[j].getPlataforma().toLowerCase()) > 0) {
                    aux = videojuegos[i];
                    videojuegos[i] = videojuegos[j];
                    videojuegos[j] = aux;
                }
            }
        }
    }

    public void orderPlataformaDESC() {
        Videojuegos aux;
        for (int i = 0; i < MAXIMO_VIDEOJUEGOS; i++) {
            for (int j = i + 1; j < MAXIMO_VIDEOJUEGOS; j++) {
                if (videojuegos[i] != null && videojuegos[j] != null &&
                        videojuegos[i].getPlataforma().toLowerCase().compareTo(videojuegos[j].getPlataforma().toLowerCase()) < 0) {
                    aux = videojuegos[i];
                    videojuegos[i] = videojuegos[j];
                    videojuegos[j] = aux;
                }
            }
        }
    }

    public void orderTituloASC() {
        Videojuegos aux;
        for (int i = 0; i < MAXIMO_VIDEOJUEGOS; i++) {
            for (int j = i + 1; j < MAXIMO_VIDEOJUEGOS; j++) {
                if (videojuegos[i] != null && videojuegos[j] != null &&
                        videojuegos[i].getTitulo().toLowerCase().compareTo(videojuegos[j].getTitulo().toLowerCase()) > 0) {
                    aux = videojuegos[i];
                    videojuegos[i] = videojuegos[j];
                    videojuegos[j] = aux;
                }
            }
        }
    }
    public void orderTituloDESC() {
        Videojuegos aux;
        for (int i = 0; i < MAXIMO_VIDEOJUEGOS; i++) {
            for (int j = i + 1; j < MAXIMO_VIDEOJUEGOS; j++) {
                if (videojuegos[i] != null && videojuegos[j] != null &&
                        videojuegos[i].getTitulo().toLowerCase().compareTo(videojuegos[j].getTitulo().toLowerCase()) < 0) {
                    aux = videojuegos[i];
                    videojuegos[i] = videojuegos[j];
                    videojuegos[j] = aux;
                }
            }
        }
    }

    public String buscarPlataforma(String plataforma) {
        String result = "";
        for (int i = 0; i < MAXIMO_VIDEOJUEGOS; i++) {
            if (videojuegos[i] != null &&
                    videojuegos[i].getPlataforma().equalsIgnoreCase(plataforma.trim())) {
                result += (i + 1) + "- " + videojuegos[i].toString() + "\n";
            }
        }
        return result;
    }

    public String buscarTitulo(String titulo) {
        String result = "";
        for (int i = 0; i < MAXIMO_VIDEOJUEGOS; i++) {
            if (videojuegos[i] != null &&
                    videojuegos[i].getTitulo().equalsIgnoreCase(titulo.trim())) {
                result += (i + 1) + "- " + videojuegos[i].toString() + "\n";
            }
        }
        return result;
    }

    public String getPrecioMayor() {
        String result = "";
        if (numeroJuegos != 0) {
            for (int i = 0; i < MAXIMO_VIDEOJUEGOS; i++) {
                if (videojuegos[i] != null && videojuegos[i].getPrecio() >= 45.00f) {
                    result += (i + 1) + "- " + videojuegos[i].toString() + "\n";
                }
            }
        } else {
            result = "No hay alumnos en la clase";
        }

        return result;
    }

    public String getPrecioMenor() {
        String result = "";
        if (numeroJuegos != 0) {
            for (int i = 0; i < MAXIMO_VIDEOJUEGOS; i++) {
                if (videojuegos[i] != null && videojuegos[i].getPrecio() <= 45.00f) {
                    result += (i + 1) + "- " + videojuegos[i].toString() + "\n";
                }
            }
        } else {
            result = "No hay alumnos en la clase";
        }

        return result;
    }

    public String getPrecioEquals() {
        String result = "";
        if (numeroJuegos != 0) {
            for (int i = 0; i < MAXIMO_VIDEOJUEGOS; i++) {
                if (videojuegos[i] != null && videojuegos[i].getPrecio() == 0.00f) {
                    result += (i + 1) + "- " + videojuegos[i].toString() + "\n";
                }
            }
        } else {
            result = "No hay alumnos en la clase";
        }

        return result;
    }

    public int getNumeroPC() {
        int pc = 0;
        for (int i = 0; i < MAXIMO_VIDEOJUEGOS; i++) {
            if (videojuegos[i] != null && videojuegos[i].getPlataforma() == "pc") {
                pc++;
            }
        }
        return pc;
    }

    public int getNumeroxbox() {
        int xbox = 0;
        for (int i = 0; i < MAXIMO_VIDEOJUEGOS; i++) {
            if (videojuegos[i] != null && videojuegos[i].getPlataforma() == "xbox") {
                xbox++;
            }
        }
        return xbox;
    }

    public int getNumeroSwitch() {
        int nswitch = 0;
        for (int i = 0; i < MAXIMO_VIDEOJUEGOS; i++) {
            if (videojuegos[i] != null && videojuegos[i].getPlataforma() == "switch") {
                nswitch++;
            }
        }
        return nswitch;
    }

    public int getNumeroPlay() {
        int play = 0;
        for (int i = 0; i < MAXIMO_VIDEOJUEGOS; i++) {
            if (videojuegos[i] != null && videojuegos[i].getPlataforma() == "play") {
                play++;
            }
        }
        return play;
    }
}
