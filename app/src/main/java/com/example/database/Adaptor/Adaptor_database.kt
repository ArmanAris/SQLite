package com.example.database.Adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.database.R
import com.example.database.data.Profile
import com.example.database.db.Profile_Table

class Adaptor_database(var context: Context, private val list: ArrayList<Profile>) :
    RecyclerView.Adapter<Adaptor_database.viewholder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        var view = LayoutInflater.from(context).inflate(R.layout.profile_view, parent, false)
        return viewholder(view, context, list)
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    inner class viewholder(itemView: View, val context: Context, var mlist: ArrayList<Profile>) :
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
            date_view.text = profile.data(profile)


            var pos = adapterPosition
            var profiles: Profile = mlist[pos]

            delete.setOnClickListener {
                deleteitem(profiles)
                mlist.removeAt(pos)
                notifyItemRemoved(pos)
            }

            edite.setOnClickListener {
                Toast.makeText(context, "button edite", Toast.LENGTH_SHORT).show()
            }


        }

        fun deleteitem(x:Profile) {
            val db = Profile_Table(context)
            db.delete_profile(x)
        }


    }
}