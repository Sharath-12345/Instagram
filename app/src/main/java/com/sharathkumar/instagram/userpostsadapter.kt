package com.sharathkumar.instagram

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class userpostsadapter
    (private val context: Context,private val imageUris: List<Uri>)
    : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>(){

    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView2: ImageView = view.findViewById(R.id.userimage)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImageAdapter.ImageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_image, parent, false)
        return ImageAdapter.ImageViewHolder(view)
    }

    override fun getItemCount():Int=imageUris.size

    override fun onBindViewHolder(holder: ImageAdapter.ImageViewHolder, position: Int) {
        val imageUri = imageUris[position]
        holder.imageView.setImageURI(imageUri)
        holder.imageView.scaleType=ImageView.ScaleType.CENTER_CROP
        holder.itemView.setOnClickListener {
             val intent=Intent(context,userpostzoom::class.java)
              context.startActivity(intent)
        }
    }

}