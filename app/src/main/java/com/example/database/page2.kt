package com.example.database


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.database.ui.Adaptor.AdaptorDatabase
import com.example.database.data.model.Profile
import com.example.database.data.local.db.Profile_Table

class page2 : AppCompatActivity() {

    var db: Profile_Table? = null
    lateinit var list: ArrayList<Profile>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page2)

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
        val view = LayoutInflater.from(this).inflate(R.layout.popuplayout, null)
        val name = view.findViewById<TextView>(R.id.firstp)
        val last = view.findViewById<TextView>(R.id.lastp)
        val phone = view.findViewById<TextView>(R.id.phonep)
        val save = view.findViewById<Button>(R.id.savep)

        val alert = AlertDialog.Builder(this).setView(view).create()
        alert.show()

        save.setOnClickListener {
            if (!TextUtils.isEmpty(name.text.toString()
                    .trim()) && !TextUtils.isEmpty(last.text.toString()
                    .trim()) && !TextUtils.isEmpty(
                    phone.text.toString().trim())
            ) {
                val profile = Profile()
                profile.first_name = name.text.toString()
                profile.last_name = last.text.toString()
                profile.phone_number = phone.text.toString()
                db!!.createProfile(profile)
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
        db = Profile_Table(this)
        list = db!!.readProfile()
        list.reverse()

        val adaptor = AdaptorDatabase(this, list)
        val maneger = LinearLayoutManager(this)
        val arman = findViewById<RecyclerView>(R.id.arman)
        arman.adapter = adaptor
        arman.layoutManager = maneger
    }

}