package validators

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.*
import errors.VehiculosErrors
import models.Vehiculo


fun Vehiculo.ValidatorVehiculo(): Result<Vehiculo, VehiculosErrors> {
    return when {
        uuid.isBlank() -> Err(VehiculosErrors.VehiculoNotValid("El Uuid no es valido"))
        marca.isBlank() -> Err(VehiculosErrors.VehiculoNotValid("La marca no es valida"))
        modelo.isBlank() -> Err(VehiculosErrors.VehiculoNotValid("El modelo no es valido"))
        matricula.isBlank() -> Err(VehiculosErrors.VehiculoNotValid("La matrícula no es valida"))
        else -> {
            Ok(this)
        }
    }

}
