/**
 * Funcion para cacular el salario.
 * @param hoursDay indicamos las horas trabajadas por la mañana.
 * @param hoursNight indiacmos las horas trabajadas de noche.
 * @param dayWorking indicamos el dia trabajado.
 * @returns devolvemos el salario obtenido por horas trabajadas.
 */


 const salarioManana=20
 const salarioNoche=35
 const salarioMananaDomingo=30
 const salarioNocheDomingo=50
 
 
 
 function esSalario(hoursDay: number, hoursNight: number, dayWorking:string ) {
     let salarioTotal:number;
     if(dayWorking=='domingo')
         salarioTotal = (hoursDay*salarioMananaDomingo) + (hoursNight*salarioNocheDomingo)
     else salarioTotal = (hoursDay*salarioManana) + (hoursNight*salarioNoche)
   
     return salarioTotal
 }
 export default {esSalario};