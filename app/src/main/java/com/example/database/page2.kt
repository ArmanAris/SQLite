package com.example.database

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
}