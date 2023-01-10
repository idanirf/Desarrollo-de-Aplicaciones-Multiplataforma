/**
 * Funcion que nos indica si un numero tiene decimales o no.
 * @param dimeNumero Numero que queremos comprobar si tiene decimales o no.
 * @returns devuleve el resultado pedido V o F.
 */

 function haveDecimales (dimeNumero: number): boolean {
    let esDecimal: boolean = true
    
    if(dimeNumero%1!=0){
     esDecimal= true
}else{
    esDecimal= false
}
return esDecimal
}
export default {haveDecimales}