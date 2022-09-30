package com.example.database.data

class Profile() {
    var id: Int? = null
    var first_name: String? = null
    var last_name: String? = null
    var phone_number: String? = null
    var data: Long? = null

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