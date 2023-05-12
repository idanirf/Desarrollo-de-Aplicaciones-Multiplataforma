package com.drodriguez.reciclerview

class Alumnos(val name: String, val isOnline: Boolean) {
    companion object {
        private var lastContactId = 0
        fun createContactsList(numContacts: Int): ArrayList<Alumnos> {
            val contacts = ArrayList<Alumnos>()
            for (i in 1..numContacts) {
                contacts.add(Alumnos("Alumno " + ++lastContactId, i <= numContacts / 2))
            }
            return contacts
        }
    }
}