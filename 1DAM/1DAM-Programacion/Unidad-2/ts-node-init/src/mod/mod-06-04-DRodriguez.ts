/**
 * Funcion que calcula el salario.
 * @param horas Numero de horas trabajadas.
 * @returns devuleve el salario de la semana.
 */

 const SALARIO_HORA = 15;
 const HORAS_SEMANA = 40;
 const HR_EXTRA_NORMAL = 5;
 const ADD_HR_EXTRA = 1.5;
 const MUL_HR_EXTRA_EXCEDE = 2;
 const SEMANAS_MES = 4;
 
 const SALARIO_BASE = 1000;
 const IMPUESTO_BASE = 0.1;
 const IMPUESTO_EXCEDE = 0.15;

 function esSalario2(horas: number) {
    let salario_neto;
    let salario_bruto_mensual;
    let salario;
    if (horas <= HORAS_SEMANA) {
        salario = horas*SALARIO_HORA;
    } else {
        if (horas <= HORAS_SEMANA + HR_EXTRA_NORMAL)
            salario = HORAS_SEMANA*SALARIO_HORA + (horas - HORAS_SEMANA)*(SALARIO_HORA*ADD_HR_EXTRA);
        else {
            salario = 
            HORAS_SEMANA*SALARIO_HORA
            + (HR_EXTRA_NORMAL)*(SALARIO_HORA*ADD_HR_EXTRA)
            + (horas - HORAS_SEMANA - HR_EXTRA_NORMAL)*(SALARIO_HORA*ADD_HR_EXTRA*MUL_HR_EXTRA_EXCEDE)
        }
    }
    salario_bruto_mensual = salario*SEMANAS_MES;
    if (salario_bruto_mensual < SALARIO_BASE) {
        salario_neto = salario_bruto_mensual*(1 - IMPUESTO_BASE);
    } else {
        salario_neto = salario_bruto_mensual*(1 - IMPUESTO_EXCEDE);
    }
    return salario_neto;
}

export default {esSalario2}