package com.drodriguez.reciclerview

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class UserListActivity : AppCompatActivity() {
    lateinit var contacts: ArrayList<Alumnos>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val rvContacts = findViewById<View>(R.id.rvContacts) as RecyclerView
        contacts = Alumnos.createContactsList(20)
        val adapter = ContactsAdapter(contacts)
        rvContacts.adapter = adapter
        rvContacts.layoutManager = LinearLayoutManager(this)
    }
}