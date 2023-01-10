// Zona de declaración de librerías y módulos propios o del sistema
import read from 'readline-sync';
import chalk from 'chalk';
import aux from './mod/mod-05-10-DRodriguez'

/**
 * Nombre: Daniel Rodríguez Fernández
 * GitHub: idanirf
 * Fecha: 15/10/2021
 * Descripción: programa modular para saber si un numero leido por teclado es positivo o negativo.
*/

// Zona de declaración de variables
let dayWorking: string
let hoursDay:number
let hoursNight:number
let salarioTotal:number

//Lectura de datos
dayWorking= read.question("¿Que dia trabajaste: ");
hoursDay= read.questionInt("¿Cuantas horas trabajaste de MAÑANA?: ");
hoursNight= read.questionInt("¿Cuantas horas trabajaste por la NOCHE?: ");


salarioTotal= aux.esSalario(hoursDay,hoursNight,dayWorking);

// Imprimimos el resultado
console.log("El salario total es: " +salarioTotal);