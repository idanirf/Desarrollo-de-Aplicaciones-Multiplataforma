// Zona de declaración de librerías y módulos propios o del sistema
import read from 'readline-sync';
import chalk from 'chalk';
import auxi from './mod/mod-06-03-DRodriguez'


//Declaración de variables
let salarioTotal:number;
let hours:number;

//Leemos los datos
hours = read.questionInt("Cuantas horas has trabajado esta semana?: ");

//Desarrollo del programa
salarioTotal= auxi.esSalario(hours);

//Imprimimos el resultado
console.log("Tu salario mensual es de: " +salarioTotal);