package es.drodriguez.com.Models;

public class Transacciones extends CuentaCorriente {
    private int dia;
    private int mes;
    private int año;
    private String concepto;
    private float importeTransacion;

    public Transacciones(String NCuenta, String titulares, float saldo, String nombreDomiciliacion, int dia, int mes, int año, String concepto, float importeTransacion) {
        super(NCuenta, titulares, saldo, nombreDomiciliacion);
        this.dia = dia;
        this.mes = mes;
        this.año = año;
        this.importeTransacion = importeTransacion;
        this.concepto = concepto;
    }


    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public float getImporteTransacion() {
        return importeTransacion;
    }

    public void setImporteTransacion(float importeTransacion) {
        this.importeTransacion = importeTransacion;
    }

    @Override
    public String toString() {
        return "\n" + "Transacción" + "\n" +
                "Nombre Domiciliación: " + getNombreDomiciliacion() + "\n"+
                "Día" + dia + "\n" +
                "Mes" + mes + "\n" +
                "Año" + año + "\n" +
                "Concepto" + concepto + "\n" +
                "ImporteTransacción" + importeTransacion;

    }
}
