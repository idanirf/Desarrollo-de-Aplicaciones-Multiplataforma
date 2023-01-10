package models;

import java.text.spi.DateFormatProvider;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Coche {
    private String matricula;
    private String color;
    private LocalDateTime now = LocalDateTime.now();

    public LocalDateTime getNow() {
        return now;
    }

    public void setNow(LocalDateTime now) {
        this.now = now;
    }

    public Coche() {
        setMatricula(matricula);
        setColor(color);
        setNow(now);
    }

    public String getMatricula() {
        Scanner sc = new Scanner(System.in);
        sc.next();
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getColor() {
        Scanner sc = new Scanner(System.in);
        sc.next();
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return  "[" + matricula + color + now + "]";
    }
}
