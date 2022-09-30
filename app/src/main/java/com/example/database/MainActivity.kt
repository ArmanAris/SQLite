package com.example.database

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.database.data.Profile
import com.example.database.db.Profile_Table

class MainActivity : AppCompatActivity() {

    var arman: Profile_Table? = null
    val first:EditText=findViewById(R.id.first)
    val last:EditText=findViewById(R.id.last)
    val phone:EditText=findViewById(R.id.phone)
    val save_btn:Button=findViewById(R.id.save)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        arman = Profile_Table(this)

        var aris: Profile = Profile()
        aris.id=2
        aris.first_name = "ashil"
        aris.last_name = "troy"
        aris.phone_number = "00000000000"

        arman?.delete_profile(aris)

        println(arman?.count_profile())




    }

}