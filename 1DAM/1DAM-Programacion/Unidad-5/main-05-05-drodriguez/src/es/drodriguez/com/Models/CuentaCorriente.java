package es.drodriguez.com.Models;

public class CuentaCorriente extends Cuenta {
    private String nombreDomiciliacion;

    public CuentaCorriente(String NCuenta, String titulares, float saldo, String nombreDomiciliacion) {
        super(NCuenta, titulares, saldo);
        this.nombreDomiciliacion = nombreDomiciliacion;
    }

    public String getNombreDomiciliacion() {
        return nombreDomiciliacion;
    }

    public void setNombreDomiciliacion(String nombreDomiciliacion) {
        this.nombreDomiciliacion = nombreDomiciliacion;
    }

    @Override
    public String toString() {
        return "CuentaAhorro 💸" + "\n" +
                "Nº Cuenta: " + getNCuenta() + "\n" +
                "Titular: " + getTitulares() + "\n" +
                "Nombre Domiciliación: " + getNombreDomiciliacion();
    }
}
