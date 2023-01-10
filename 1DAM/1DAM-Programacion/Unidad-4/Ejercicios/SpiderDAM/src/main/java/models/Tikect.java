package models;

public class Tikect {
    private int idNumber = 0;
    private static int idNumberContador = 1;
    private int precioEntrada = 6;
    private Butaca Butaca;

    public Tikect(int idNumber, int precioEntrada, models.Butaca butaca) {
        this.idNumber = idNumber;
        this.precioEntrada = precioEntrada;
        Butaca = butaca;
    }

    //TODO borrar los set, es solo de lectura

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public static int getIdNumberContador() {
        return idNumberContador;
    }

    public static void setIdNumberContador(int idNumberContador) {
        Tikect.idNumberContador = idNumberContador;
    }

    public int getPrecioEntrada() {
        return precioEntrada;
    }

    public void setPrecioEntrada(int precioEntrada) {
        this.precioEntrada = precioEntrada;
    }

    public models.Butaca getButaca() {
        return Butaca;
    }

    public void setButaca(models.Butaca butaca) {
        Butaca = butaca;
    }

    @Override
    public String toString() {
        return "Tikect{" +
                "idNumber=" + idNumber +
                ", precioEntrada=" + precioEntrada +
                ", Butaca=" + Butaca +
                '}';
    }

}
