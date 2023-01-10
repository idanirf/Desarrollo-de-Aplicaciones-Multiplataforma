package es.drodriguez.com.Models;

public class Cuenta {
    private String NCuenta;
    private String Titulares;
    private float Saldo;

    public Cuenta(String NCuenta, String titulares, float saldo) {
        this.NCuenta = NCuenta;
        Titulares = titulares;
        Saldo = saldo;
    }

    public String getNCuenta() {
        return NCuenta;
    }

    public void setNCuenta(String NCuenta) {
        this.NCuenta = NCuenta;
    }

    public String getTitulares() {
        return Titulares;
    }

    public void setTitulares(String titulares) {
        Titulares = titulares;
    }

    public float getSaldo() {
        return Saldo;
    }

    public void setSaldo(float saldo) {
        Saldo = saldo;
    }

    @Override
    public String toString() {
        return "CuentaAhorro 💸" + "\n" +
                "Nº Cuenta: " + getNCuenta() + "\n" +
                "Titular: " + getTitulares() + "\n";
    }
}
