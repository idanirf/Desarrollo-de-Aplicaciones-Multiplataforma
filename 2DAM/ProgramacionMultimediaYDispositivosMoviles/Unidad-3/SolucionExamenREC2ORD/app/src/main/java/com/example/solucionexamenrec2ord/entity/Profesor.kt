package com.example.solucionexamenrec2ord.entity

import android.os.Parcelable
import com.google.firebase.firestore.Exclude
import kotlinx.parcelize.Parcelize

@Parcelize
data class Profesor(
    @get: Exclude var id: String? = null,
    var nombre: String? = null,
    var email: String? = null,
    var turno: String? = null,
    var antiguedad: Int? = null,
): Parcelable {

}