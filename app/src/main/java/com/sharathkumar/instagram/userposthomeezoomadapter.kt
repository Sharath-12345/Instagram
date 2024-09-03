package com.sharathkumar.instagram

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class userposthomeezoomadapter  (private val context: Context,
                                 private val userDetails: List<UserDetails>)
    : RecyclerView.Adapter<userposthomeezoomadapter.PostViewHolder>()

{
    var userdp = "content://media/external/images/media/"
    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userDpImageView: ImageView = itemView.findViewById(R.id.postuploaderdp)
        val photoImageView: ImageView = itemView.findViewById(R.id.postphoto)
        val captionTextView: TextView = itemView.findViewById(R.id.postcaption)
        val postuploadernameTextView: TextView = itemView.findViewById(R.id.postuploadername)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_userpostsingle, parent, false)
        return userposthomeezoomadapter.PostViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userDetails.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = userDetails[position]
        var userdpstring=post.userdp.toString()

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
                .into(holder.userDpImageView)

        }





        Glide.with(context)
            .load(post.photo)
            .into(holder.photoImageView)
        holder.photoImageView.scaleType = ImageView.ScaleType.CENTER_CROP

        holder.postuploadernameTextView.text = post.username
        holder.captionTextView.text = post.caption
    }
}