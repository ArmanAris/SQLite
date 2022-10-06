package com.example.database.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.database.data.*
import java.text.DateFormat
import java.util.*
import kotlin.collections.ArrayList

class Profile_Table(context: Context) : SQLiteOpenHelper(context, name_db, null, ver_db) {

    override fun onCreate(db: SQLiteDatabase?) {

        var create_table =
            "CREATE TABLE $name_table($id_c INTEGER PRIMARY KEY,$first_name_c TEXT,$last_name_c TEXT,$phone_number_c TEXT,$date_c LONG)"

        db?.execSQL(create_table)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldversion: Int, newversion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $name_table")
        onCreate(db)

    }

    fun create_profile(profile: Profile) {
        var db: SQLiteDatabase = this.writableDatabase

        var values: ContentValues = ContentValues()
        values.put(first_name_c, profile.first_name)
        values.put(last_name_c, profile.last_name)
        values.put(phone_number_c, profile.phone_number)
        values.put(date_c, System.currentTimeMillis())

        db.insert(name_table, null, values)
        db.close()


    }

    fun get_profile(id: Int): Profile? {
        var db: SQLiteDatabase = writableDatabase

        var cursor: Cursor = db.query(name_table, arrayOf(id_c, first_name_c, last_name_c,
            phone_number_c, date_c),
            "$id_c=?", arrayOf(id.toString()), null, null, null)

        if (cursor != null) {
            cursor.moveToFirst()

            var profile: Profile = Profile()
            profile.id = cursor.getInt(cursor.getColumnIndex(id_c).toInt())
            profile.first_name = cursor.getString(cursor.getColumnIndex(first_name_c).toInt())
            profile.last_name = cursor.getString(cursor.getColumnIndex(last_name_c).toInt())
            profile.phone_number = cursor.getString(cursor.getColumnIndex(phone_number_c).toInt())
            profile.data = cursor.getLong(cursor.getColumnIndex(date_c).toInt())


            // dont need
            var dateformat: DateFormat = DateFormat.getDateInstance()
            var datedate =
                dateformat.format(Date(cursor.getLong(cursor.getColumnIndex(date_c).toInt())).time)

            return profile
        }
        return null

    }

    fun read_profile(): ArrayList<Profile> {
        var db: SQLiteDatabase = readableDatabase
        var list = ArrayList<Profile>()

        var select_all = "SELECT * FROM $name_table"
        var cursor: Cursor = db.rawQuery(select_all, null)

        if (cursor.moveToFirst()) {
            do {
                var profile: Profile = Profile()
                profile.id = cursor.getInt(cursor.getColumnIndex(id_c).toInt())
                profile.first_name = cursor.getString(cursor.getColumnIndex(first_name_c).toInt())
                profile.last_name = cursor.getString(cursor.getColumnIndex(last_name_c).toInt())
                profile.phone_number =
                    cursor.getString(cursor.getColumnIndex(phone_number_c).toInt())
                profile.data = cursor.getLong(cursor.getColumnIndex(date_c).toInt())

                list.add(profile)
            } while (cursor.moveToNext())

        }
        return list
    }

    fun delete_profile(profile: Profile) {
        var db: SQLiteDatabase = writableDatabase
        db.delete(name_table, "$id_c=?", arrayOf(profile.id.toString()))
    }

    fun delete_profile(id: Int) {
        var db: SQLiteDatabase = writableDatabase
        db.delete(name_table, "$id_c=?", arrayOf(id.toString()))
    }

    fun update_profile(profile: Profile){
        var db: SQLiteDatabase = this.writableDatabase

        var values: ContentValues = ContentValues()
        values.put(first_name_c, profile.first_name)
        values.put(last_name_c, profile.last_name)
        values.put(phone_number_c, profile.phone_number)
        values.put(date_c, System.currentTimeMillis())

        db.update(name_table,values,"$id_c=?", arrayOf(profile.id.toString()))
    }

    fun count_profile(): Int {
        var db: SQLiteDatabase = readableDatabase
        var select_all = "SELECT * FROM $name_table"
        var cursor: Cursor = db.rawQuery(select_all, null)
        return cursor.count.toInt()

    }


}