package com.dam.gestionalmacendam.utils;

public class Patterns {
    public static boolean patterPhone(String phone){
        var regex = "[6-9]\\d{8}";
        return phone.matches(regex);
    }
    public static boolean patternName(String name) {
        var regex = "([a-záéíóúA-ZÁÉÍÓÚ][a-zñáéíóú]*)$";
        return name.matches(regex);
    }
    public static boolean patternCif(String cif) {
        var regex = "^([1-9]{1}[0-9]{7}[a-zA-Z])$";
        return cif.matches(regex);
    }
    public static boolean patternSurnames(String surNames) {
        var regex = "^([a-záéíóúA-ZÁÉÍÓÚ]{1}[a-zñáéíóú]*)\\s([a-záéíóúA-ZÁÉÍÓÚ]{1}[a-zñáéíóú]*)$";
        return surNames.matches(regex);
    }
    public static boolean patternPassword(String password){
        var regex= "^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\d\\s:])(\\S){8,16}$";
        return password.matches(regex);
    }
    public static boolean patternEmail(String email) {
        var regex = "^\\w+@(\\w+\\.)+[a-z]{2,3}$";
        return email.matches(regex);
    }
    public static boolean  isNumberInt(String num){
        String regex = "^\\d+$";
        return regex.matches(num);
    }
    public int parseNumber(String campo){
        return Integer.parseInt(campo);
    }
    public static boolean isNumerDouble(String num) {
        String regex = "^[0-9]+([,][0-9]+)?$";
        return regex.matches(num);
    }
}
