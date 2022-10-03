package com.example.database.data

import java.text.SimpleDateFormat
import java.util.*

class Profile() {
    var id: Int? = null
    var first_name: String? = null
    var last_name: String? = null
    var phone_number: String? = null
    var data: Long? = null

    fun data(profile: Profile): String {
        var datasimple = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
        var date = Date(profile.data!!)
        return datasimple.format(date)
    }

    constructor(
        id: Int,
        first_name: String,
        last_name: String,
        phone_number: String,
        date: Long,
    ) : this() {
        this.id = id
        this.first_name = first_name
        this.last_name = last_name
        this.phone_number = phone_number
        this.data = data

    }

}