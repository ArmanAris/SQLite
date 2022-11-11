package com.example.database

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.database.data.model.Profile
import com.example.database.data.local.db.Profile_Table

class MainActivity : AppCompatActivity() {

    var db: Profile_Table? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val first: EditText = findViewById(R.id.firstp)
        val last: EditText = findViewById(R.id.lastp)
        val phone: EditText = findViewById(R.id.phonep)
        val save_btn: Button = findViewById(R.id.savep)


        db = Profile_Table(this)

        if (db!!.countProfile() > 0) {
            startActivity(Intent(this, page2::class.java))
            finish()
        }


        save_btn.setOnClickListener {
            if (!TextUtils.isEmpty(first.text.toString().trim()) && !TextUtils.isEmpty(last.text.toString().trim()) && !TextUtils.isEmpty(
                    phone.text.toString().trim())
            ) {
                val profile = Profile()
                profile.first_name = first.text.toString().trim()
                profile.last_name = last.text.toString().trim()
                profile.phone_number = phone.text.toString().trim()

                db?.createProfile(profile)

                Toast.makeText(this, "اظلاعات ذخیره شد.", Toast.LENGTH_SHORT).show()

                startActivity(Intent(this, page2::class.java))
            } else {
                Toast.makeText(this, "اظلاعات را تکمیل کنید!!!!!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}