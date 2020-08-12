package com.tribal.tribalphotos.ui.adapter

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tribal.tribalphotos.MainActivity
import com.tribal.tribalphotos.R
import com.tribal.tribalphotos.model.Photo
import com.tribal.tribalphotos.utils.inflate
import kotlinx.android.synthetic.main.recyclerview_item_row.view.*

class RecyclerAdapter(private val photos:List<Photo?>): RecyclerView.Adapter<RecyclerAdapter.PhotoHolder>() {

    override fun getItemCount(): Int = photos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.PhotoHolder {
        val inflatedView = parent.inflate(R.layout.recyclerview_item_row, false)
        return PhotoHolder(inflatedView)
    }


    override fun onBindViewHolder(holder: RecyclerAdapter.PhotoHolder, position: Int) {
        val itemPhoto = photos[position]
        holder.bindPhoto(itemPhoto)
    }


    class PhotoHolder(v:View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View = v
        private var photo: Photo? = null

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v:View) {
            Log.d(MainActivity.TAG, "Click on RecyclerView")
        }

        fun bindPhoto(photo: Photo?) {
            this.photo = photo
            photo?.let {
                Picasso.get().load(photo.urls!!.full).into(view.imgvItem)
                view.tvLikes.text = photo.likes.toString()
                view.tvUsername.text = photo.user!!.name
            }
        }

        companion object {
            private val PHOTO_KEY = "PHOTO"
        }

    }
}