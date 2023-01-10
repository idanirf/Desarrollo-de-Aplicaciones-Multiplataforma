/**
 * Nombre: Daniel Rodríguez Fernández
 * GitHub: idanirf
 * Fecha: 17/10/2021
 * Descripción: Comprobar por teclado si un numero tiene decimales o no.
 */

import read from 'readline-sync'; // --> Librería para leer datos por consola. Puedes leer sobre ella y aprender a usarla en: https://github.com/anseki/readline-sync
import chalk from 'chalk'; // --> Podemos obtener colores para decorar nuestra salida. Puedes leer sobre ella y aprender a usarla en: https://github.com/chalk/chalk
import aux from './mod/mod-05-06-DRodriguez'

//Declaracion de variables:

let dimeNumero:number;

//Lectura de datos:

dimeNumero = read.questionFloat("¿Me puedes decir el numero?: ");

//Desarrollo del programa
if(aux.haveDecimales(dimeNumero)== true){
  //Imprimir datos con el resultado
  console.log("El numero que has introducido es decimal")
} else {
  //Imprimir datos con el resultado
  console.log("El numero que has introducido no es decimal")
}






