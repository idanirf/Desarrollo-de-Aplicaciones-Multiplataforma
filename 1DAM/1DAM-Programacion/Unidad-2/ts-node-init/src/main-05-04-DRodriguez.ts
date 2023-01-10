// Zona de declaración de librerías y módulos propios o del sistema
import read from 'readline-sync';
import chalk from 'chalk';
import aux from './mod/mod-05-04-DRodriguez'

/**
 * Nombre: Daniel Rodríguez Fernández
 * GitHub: idanirf
 * Fecha: 15/10/2021
 * Descripción: programa modular para saber si un numero leido por teclado es positivo o negativo.
*/
 let numero:number
let resultadototal: number

numero= read.questionInt("Dime el numero")

if(numero<0){
    console.log("El numero es negativo y no se puede hacer la operacion")

} else (numero>=0)
    resultadototal= aux.esRaiz(numero);
console.log("El resultado de la operacion es: " +resultadototal)