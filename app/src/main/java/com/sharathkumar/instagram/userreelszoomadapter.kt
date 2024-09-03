package com.sharathkumar.instagram

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class userreelszoomadapter
    (private val context: Context,
     private val userDetails: List<UserDetailsReels>) : RecyclerView.Adapter<userreelszoomadapter.PostViewHolder>() {
    var username = ""
    var userdp = "content://media/external/images/media/"
    var userdpstring = ""
    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userDpImageView: ImageView = itemView.findViewById(R.id.postuploaderdpreel)
        val photoImageView: ImageView = itemView.findViewById(R.id.postphotoreel)
        val captionTextView: TextView = itemView.findViewById(R.id.postcaptionreel)
        val postuploadernameTextView: TextView = itemView.findViewById(R.id.postuploadernamereel)
        val videoView:VideoView=itemView.findViewById(R.id.postvideoreel)

    }



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): userreelszoomadapter.PostViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_userreelsingle, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: userreelszoomadapter.PostViewHolder, position: Int) {
        val post = userDetails[position]

        FirebaseFirestore.getInstance().collection("users")
            .document(FirebaseAuth.getInstance().currentUser!!.uid).collection("userdetails")
            .document("user").get().addOnSuccessListener {
                username = it.get("name").toString()
                userdpstring = it.get("dp").toString()

            }


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


        Glide.with(context).load(post.video).thumbnail(0.1f).into(holder.photoImageView)


        holder.photoImageView.scaleType = ImageView.ScaleType.CENTER_CROP
        holder.videoView.setVideoURI(Uri.parse(post.video))
        holder.videoView.start()




        holder.postuploadernameTextView.text = username
        holder.captionTextView.text = post.caption

    }

    override fun getItemCount(): Int {
       return userDetails.size
    }

}