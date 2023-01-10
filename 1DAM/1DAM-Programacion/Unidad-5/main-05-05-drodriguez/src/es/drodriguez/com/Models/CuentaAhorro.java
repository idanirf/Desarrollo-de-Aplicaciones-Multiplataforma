package es.drodriguez.com.Models;

public class CuentaAhorro extends Cuenta{
    private float cuotaMantenimiento;
    private float interesAnual;

    public CuentaAhorro(String NCuenta, String titulares, float saldo, float cuotaMantenimiento, float interesAnual) {
        super(NCuenta, titulares, saldo);
        this.cuotaMantenimiento = cuotaMantenimiento;
        this.interesAnual = interesAnual;

    }


    public float getCuotaMantenimiento() {
        return cuotaMantenimiento;
    }

    public float getInteresAnual() {
        return interesAnual;
    }

    public void setCuotaMantenimiento(float cuotaMantenimiento) {
        this.cuotaMantenimiento = cuotaMantenimiento;
    }

    public void setInteresAnual(float interesAnual) {
        this.interesAnual = interesAnual;
    }

    public float comisionBanco(){
        float saldoActualizado;
        saldoActualizado = getSaldo() - getInteresAnual() - (getCuotaMantenimiento() *12);

        return saldoActualizado;

    }

    @Override
    public String toString() {
        return "CuentaAhorro 💸" + "\n" +
                "Nº Cuenta: " + getNCuenta() + "\n" +
                "Titular: " + getTitulares() + "\n" +
                "Saldo anterior: " +getSaldo()+ "\n" +
                "Cuota Mantenimiento: " + getCuotaMantenimiento() + "\n" +
                "Interés anual: " + getInteresAnual() + "\n" +
                "Saldo actualizado, después de comisión anual bancaria: " + comisionBanco() + "\n";
    }
}
