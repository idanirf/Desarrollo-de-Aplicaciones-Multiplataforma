/**
 * Funcion que calcula el salario.
 * @param horas Numero de horas trabajadas.
 * @returns devuleve el salario de la semana.
 */

 const SALARIO_HORA = 15;
 const HORAS_SEMANA = 40;
 const ADD_HR_EXTRA = 1.5;
 
 function esSalario(hours:number) {
     let salario:number;
     if (hours <= 40) {
         salario = hours*SALARIO_HORA;
     } else {
         salario = HORAS_SEMANA*SALARIO_HORA + (hours - HORAS_SEMANA)*(SALARIO_HORA*ADD_HR_EXTRA);
     }
     return salario;
 }

 export default {esSalario}