package com.example.database.data.local.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.database.data.model.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class Profile_Table(context: Context) : SQLiteOpenHelper(context, name_db, null, ver_db) {

    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL("CREATE TABLE $name_table(" +
                "$id_c INTEGER PRIMARY KEY," +
                "$first_name_c TEXT," +
                "$last_name_c TEXT," +
                "$phone_number_c TEXT," +
                "$date_c LONG)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldversion: Int, newversion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $name_table")
        onCreate(db)

    }

    fun createProfile(profile: Profile) {

        val db: SQLiteDatabase = writableDatabase

        val values = ContentValues()
        values.put(first_name_c, profile.first_name)
        values.put(last_name_c, profile.last_name)
        values.put(phone_number_c, profile.phone_number)

        val dataSimple = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val date = Date(System.currentTimeMillis())
        val result = dataSimple.format(date)

        values.put(date_c, result)

        db.insert(name_table, null, values)
        db.close()


    }

    fun readProfile(): ArrayList<Profile> {

        val db: SQLiteDatabase = readableDatabase
        val date = ArrayList<Profile>()

        val selectAll = "SELECT * FROM $name_table"
        val cursor: Cursor = db.rawQuery(selectAll, null)

        if (cursor.moveToFirst()) {
            do {
                val profile: Profile = Profile()
                profile.id = cursor.getInt(cursor.getColumnIndex(id_c).toInt())
                profile.first_name = cursor.getString(cursor.getColumnIndex(first_name_c).toInt())
                profile.last_name = cursor.getString(cursor.getColumnIndex(last_name_c).toInt())
                profile.phone_number =
                    cursor.getString(cursor.getColumnIndex(phone_number_c).toInt())
                profile.data = cursor.getString(cursor.getColumnIndex(date_c).toInt())

                date.add(profile)

            } while (cursor.moveToNext())
        }
        return date
    }

    fun deleteProfile(profile: Profile) {
        val db: SQLiteDatabase = writableDatabase
        db.delete(name_table, "$id_c=?", arrayOf(profile.id.toString()))
        db.close()
    }

    fun deleteProfile(id: Int) {
        val db: SQLiteDatabase = writableDatabase
        db.delete(name_table, "$id_c=?", arrayOf(id.toString()))
        db.close()
    }

    fun updateProfile(profile: Profile) {

        val db: SQLiteDatabase = writableDatabase

        var values = ContentValues()
        values.put(first_name_c, profile.first_name)
        values.put(last_name_c, profile.last_name)
        values.put(phone_number_c, profile.phone_number)

        val dataSimple = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val date = Date(System.currentTimeMillis())
        val result = dataSimple.format(date)

        values.put(date_c, result)

        db.update(name_table, values, "$id_c=?", arrayOf(profile.id.toString()))
    }

    fun countProfile(): Int {
        val db: SQLiteDatabase = readableDatabase
        val select_all = "SELECT * FROM $name_table"
        val cursor: Cursor = db.rawQuery(select_all, null)
        return cursor.count

    }


}