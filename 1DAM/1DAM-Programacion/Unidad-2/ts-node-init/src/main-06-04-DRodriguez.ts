// Zona de declaración de librerías y módulos propios o del sistema
import read from 'readline-sync';
import chalk from 'chalk';
import aux from './mod/mod-06-04-DRodriguez'

//Declaracion de variables
let salario:number;
let horas:number;

//Lectura de datos
horas = read.questionInt("Cuantas horas has trabajado cada semana?");

//Desarrollo del programa
salario = aux.esSalario2(horas);

//Imprimir el resultado
console.log("Tu salario neto mensual sería de:" +salario);