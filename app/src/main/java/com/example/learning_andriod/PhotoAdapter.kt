package com.example.learning_andriod

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.learning_andriod.R.id.photo_url
import com.example.learning_andriod.R.layout.photo_item
import com.example.learning_andriod.domain.PhotoItem

class PhotoAdapter(private var photos: MutableList<PhotoItem>): RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val photoUrl: ImageView = itemView.findViewById(photo_url)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(photo_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = photos[position]
        holder.photoUrl.setImageBitmap(currentItem.photoUrl)
    }

    override fun getItemCount() = photos.size

    fun updatePhotos(newPhotos: List<PhotoItem>) {
        photos.clear()
        photos.addAll(newPhotos)
        notifyDataSetChanged()
    }
}