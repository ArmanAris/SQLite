package com.example.database

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
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

            val first: EditText = findViewById(R.id.firstp)
            val last: EditText = findViewById(R.id.lastp)
            val phone: EditText = findViewById(R.id.phonep)
            val save_btn: Button = findViewById(R.id.savep)


        arman = Profile_Table(this)
        page2()


        save_btn.setOnClickListener {
            if (!TextUtils.isEmpty(first.text.toString()) && !TextUtils.isEmpty(last.text.toString()) && !TextUtils.isEmpty(
                    phone.text.toString())
            ) {
                var profile = Profile()
                profile.first_name = first.text.toString()
                profile.last_name = last.text.toString()
                profile.phone_number = phone.text.toString()
                save(profile)
                Toast.makeText(this, "اظلاعات ذخیره شد.", Toast.LENGTH_SHORT).show()

                startActivity(Intent(this, page2::class.java))

            } else {
                Toast.makeText(this, "اظلاعات را تکمیل کنید!!!!!", Toast.LENGTH_SHORT).show()
            }
        }


    }

    fun save(profile: Profile) {
        arman?.create_profile(profile)
    }

    fun page2() {
        if (arman!!.count_profile() > 0) {
            startActivity(Intent(this, page2::class.java))
            finish()
        }
    }

}