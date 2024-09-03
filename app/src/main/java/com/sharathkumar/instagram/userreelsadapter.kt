package com.sharathkumar.instagram

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class userreelsadapter (private val context: Context, private val videoUris: List<Uri>)
    : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>()
    {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ImageAdapter.ImageViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_image, parent, false)
            return ImageAdapter.ImageViewHolder(view)
        }

        override fun getItemCount():Int=videoUris.size


        override fun onBindViewHolder(holder: ImageAdapter.ImageViewHolder, position: Int) {
            val videoUri = videoUris[position]

            Glide.with(context).load(videoUri).thumbnail(0.1f).into(holder.imageView)

            holder.imageView.scaleType= ImageView.ScaleType.CENTER_CROP
            holder.itemView.setOnClickListener {
                val intent= Intent(context,userreelszoom::class.java)
                context.startActivity(intent)
            }
        }
    }