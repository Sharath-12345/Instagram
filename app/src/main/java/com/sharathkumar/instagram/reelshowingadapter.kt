package com.sharathkumar.instagram

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class reelshowingadapter (private val context: Context,
                          private val userDetailsReels: List<UserDetailsReels>)
    : RecyclerView.Adapter<reelshowingadapter.ReelViewHolder>()
{

    var userdp = "content://media/external/images/media/"

    class ReelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val reeluploaderdp: ImageView = itemView.findViewById(R.id.reeluploaderdp)
        val videoview: VideoView = itemView.findViewById(R.id.videoview)
        val captionTextView: TextView = itemView.findViewById(R.id.txtreelcaption)
        val postuploadernameTextView: TextView = itemView.findViewById(R.id.txtreeluploadername)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReelViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_reelsshowing, parent, false)
        return reelshowingadapter.ReelViewHolder(view)
    }

    override fun getItemCount(): Int {
      return userDetailsReels.size
    }

    override fun onBindViewHolder(holder: ReelViewHolder, position: Int) {
        val reel = userDetailsReels[position]
        var userdpstring=reel.userdp.toString()

        if(userdpstring!="") {

            var lastSlashIndex = -1

            for (i in userdpstring.indices) {
                if (userdpstring[i] == '/') {
                    lastSlashIndex = i
                }
            }


            val userdpnumber = if (lastSlashIndex != -1) {
                userdpstring.substring(lastSlashIndex + 1)
            } else {
                null
            }

            Glide.with(context)
                .load(userdp + userdpnumber)
                .into(holder.reeluploaderdp)

        }

        holder.captionTextView.text=reel.caption.toString()
        holder.postuploadernameTextView.text=reel.username.toString()
        holder.videoview.setVideoURI(Uri.parse(reel.video.toString()))
        holder.videoview.start()
        holder.videoview.setOnCompletionListener {
            holder.videoview.start()  // Restart the video
        }

    }
}