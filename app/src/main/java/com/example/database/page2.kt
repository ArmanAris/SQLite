package com.example.database

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.database.Adaptor.Adaptor_database
import com.example.database.data.Profile
import com.example.database.db.Profile_Table

class page2 : AppCompatActivity() {

    var dbprof: Profile_Table? = null
    lateinit var list: ArrayList<Profile>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page2)

        dbprof = Profile_Table(this)

         list = dbprof!!.read_profile()

        val adaptor = Adaptor_database(this, list)
        val maneger = LinearLayoutManager(this)

        var arman = findViewById<RecyclerView>(R.id.arman)
        arman.adapter = adaptor
        arman.layoutManager = maneger

        adaptor.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.popup, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        pop()
        return super.onOptionsItemSelected(item)
    }

    fun pop() {
        var view = LayoutInflater.from(this).inflate(R.layout.popuplayout, null)
        var name = view.findViewById<TextView>(R.id.firstp)
        var last = view.findViewById<TextView>(R.id.lastp)
        var phone = view.findViewById<TextView>(R.id.phonep)
        var save = view.findViewById<Button>(R.id.savep)

        AlertDialog.Builder(this).setView(view).create().show()


    }

}