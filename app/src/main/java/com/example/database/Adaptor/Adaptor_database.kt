package com.example.database.Adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.database.R
import com.example.database.data.Profile

class Adaptor_database(var context: Context, var list: ArrayList<Profile>) :
    RecyclerView.Adapter<Adaptor_database.viewholder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        var view = LayoutInflater.from(context).inflate(R.layout.profile_view, parent, false)
        return viewholder(view)
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    class viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var first_name_view = itemView.findViewById<TextView>(R.id.first_name_viewtext)
        var last_name_view = itemView.findViewById<TextView>(R.id.last_name_textView)
        var phone_number_view = itemView.findViewById<TextView>(R.id.phone_number_textView)
        var date_view = itemView.findViewById<TextView>(R.id.date_textView)

        fun bind(profile: Profile) {
            first_name_view.text = profile.first_name
            last_name_view.text = profile.last_name
            phone_number_view.text = profile.phone_number
            date_view.text = profile.data(profile)
        }
    }
}