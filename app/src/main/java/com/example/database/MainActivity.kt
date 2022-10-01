package com.example.database

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.database.data.Profile
import com.example.database.db.Profile_Table

class MainActivity : AppCompatActivity() {

    var arman: Profile_Table? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val first: EditText = findViewById(R.id.first)
        val last: EditText = findViewById(R.id.last)
        val phone: EditText = findViewById(R.id.phone)
        val save_btn: Button = findViewById(R.id.save)

        save_btn.setOnClickListener {
            if (!TextUtils.isEmpty(first.text.toString()) && !TextUtils.isEmpty(last.text.toString()) && !TextUtils.isEmpty(
                    phone.text.toString())
            ) {
                var profile: Profile = Profile()
                profile.first_name = first.text.toString()
                profile.last_name = last.text.toString()
                profile.phone_number = phone.text.toString()
                save(profile)
                Toast.makeText(this, "اظلاعات ذخیره شد.", Toast.LENGTH_SHORT).show()
                save(profile)
                startActivity(Intent(this,page2::class.java))

            } else {
                Toast.makeText(this, "اظلاعات را تکمیل کنید!!!!!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun save(profile: Profile) {
        arman?.create_profile(profile)
    }

}