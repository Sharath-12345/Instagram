package com.sharathkumar.instagram

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class VideoAdapter(
    private val context: Context,
    private val videoUris: List<Uri>,
    private val onvideoClick: (Uri) -> Unit
) :RecyclerView.Adapter<VideoAdapter.VideoViewHolder>()
{
    class VideoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.largeImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_video, parent, false)
        return VideoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return videoUris.size
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val videoUri = videoUris[position]
        holder.imageView.scaleType=ImageView.ScaleType.CENTER_CROP
        Glide.with(context).load(videoUri).thumbnail(0.1f).into(holder.imageView)
        holder.imageView.setImageURI(videoUri)
        holder.itemView.setOnClickListener {
              var intent=Intent(context,videouploadingfinal::class.java)
              intent.putExtra("videouri",videoUri.toString())
            context.startActivity(intent)
        }
    }

}