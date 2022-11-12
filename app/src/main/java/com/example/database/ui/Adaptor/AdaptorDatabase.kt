package com.example.database.ui.Adaptor

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.database.R
import com.example.database.data.model.Profile
import com.example.database.data.local.db.Profile_Table

class AdaptorDatabase(var context: Context, private val list: ArrayList<Profile>) :
    RecyclerView.Adapter<AdaptorDatabase.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.profile_view, parent, false)
        return ViewHolder(view, context, list)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    inner class ViewHolder(itemView: View, val context: Context, var mlist: ArrayList<Profile>) :
        RecyclerView.ViewHolder(itemView) {

        var first_name_view = itemView.findViewById<TextView>(R.id.first_name_viewtext)
        var last_name_view = itemView.findViewById<TextView>(R.id.last_name_textView)
        var phone_number_view = itemView.findViewById<TextView>(R.id.phone_number_textView)
        var date_view = itemView.findViewById<TextView>(R.id.date_textView)
        var delete = itemView.findViewById<Button>(R.id.button_delete)
        var edite = itemView.findViewById<Button>(R.id.button_edit)


        fun bind(profile: Profile) {
            first_name_view.text = profile.first_name
            last_name_view.text = profile.last_name
            phone_number_view.text = profile.phone_number
            date_view.text = profile.data

            //---------delete----------
            delete.setOnClickListener {
                val db = Profile_Table(context)
                db.deleteProfile(profile)
                mlist.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
            }

            //---------edit----------
            edite.setOnClickListener {
                val view = LayoutInflater.from(context).inflate(R.layout.popuplayout, null)
                val name = view.findViewById<TextView>(R.id.firstp)
                val last = view.findViewById<TextView>(R.id.lastp)
                val phone = view.findViewById<TextView>(R.id.phonep)
                val save = view.findViewById<Button>(R.id.savep)

                name.text = profile.first_name
                last.text = profile.last_name
                phone.text = profile.phone_number

                val alert = AlertDialog.Builder(context).setView(view).create()
                alert.show()

                save.setOnClickListener {
                    if (!TextUtils.isEmpty(name.text.toString()
                            .trim()) && !TextUtils.isEmpty(last.text.toString()
                            .trim()) && !TextUtils.isEmpty(
                            phone.text.toString().trim())
                    ) {

                        profile.first_name = name.text.toString()
                        profile.last_name = last.text.toString()
                        profile.phone_number = phone.text.toString()

                        val db = Profile_Table(context)
                        db.updateProfile(profile)

                        Toast.makeText(context, "اظلاعات ذخیره شد.", Toast.LENGTH_SHORT).show()

                        notifyItemChanged(adapterPosition, profile)

                        alert.dismiss()

                    } else {
                        Toast.makeText(context, "اظلاعات را تکمیل کنید!!!!!", Toast.LENGTH_SHORT)
                            .show()
                    }

                }
            }

        }
    }

}