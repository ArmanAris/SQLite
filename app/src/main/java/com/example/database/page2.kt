package com.example.database

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
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

        get()


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

        var alert = AlertDialog.Builder(this).setView(view).create()
        alert.show()

        save.setOnClickListener {
            if (!TextUtils.isEmpty(name.text.toString()
                    .trim()) && !TextUtils.isEmpty(last.text.toString()
                    .trim()) && !TextUtils.isEmpty(
                    phone.text.toString().trim())
            ) {
                var profile = Profile()
                profile.first_name = name.text.toString()
                profile.last_name = last.text.toString()
                profile.phone_number = phone.text.toString()
                dbprof?.create_profile(profile)
                Toast.makeText(this, "اظلاعات ذخیره شد.", Toast.LENGTH_SHORT).show()
                alert.dismiss()
                get()

                // startActivity(Intent(this, page2::class.java))
                //  finish()
            } else {
                Toast.makeText(this, "اظلاعات را تکمیل کنید!!!!!", Toast.LENGTH_SHORT).show()
            }

        }


    }

    fun get() {
        list = dbprof!!.read_profile()
        list.reverse()
        val adaptor = Adaptor_database(this, list)
        val maneger = LinearLayoutManager(this)
        var arman = findViewById<RecyclerView>(R.id.arman)
        arman.adapter = adaptor
        arman.layoutManager = maneger
        adaptor.notifyDataSetChanged()
    }
}