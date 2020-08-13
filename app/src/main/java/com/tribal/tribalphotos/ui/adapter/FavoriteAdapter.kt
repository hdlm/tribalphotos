package com.tribal.tribalphotos.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tribal.tribalphotos.R
import com.tribal.tribalphotos.model.Favorite

class FavoriteAdapter(private val list: List<Favorite>,
                      private val context: Context?) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.favorite_item_row, parent, false)

        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: FavoriteAdapter.ViewHolder, position: Int) {
        holder.bindItem(list[position])

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(favorite: Favorite) {
            var username = itemView.findViewById<TextView>(R.id.tvFavoriteUsername)
            val information = itemView.findViewById<TextView>(R.id.tvFavoriteInfo)

            username.text = favorite.username
            information.text = favorite.info

        }

    }
}