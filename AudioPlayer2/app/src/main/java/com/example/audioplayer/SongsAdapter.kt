package com.example.audioplayer

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.audioplayer.model.MusicFiles

class SongsAdapter(private var musicList:List<MusicFiles>):RecyclerView.Adapter<SongsAdapter.SongsHolder>() {

    private lateinit var myContext: Context
    private lateinit var view: View

    class SongsHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val music_name = itemView.findViewById<TextView>(R.id.song_name)
        val music_artist=itemView.findViewById<TextView>(R.id.song_artist)
        val song_card=itemView.findViewById<CardView>(R.id.songCard)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongsAdapter.SongsHolder {
        myContext=parent.context
        view = LayoutInflater.from(parent.context).inflate(R.layout.song, parent, false)
        return SongsHolder(view)
    }

    override fun onBindViewHolder(holder: SongsAdapter.SongsHolder, position: Int) {
        holder.music_name.text=musicList.get(position).title
        holder.music_artist.text=musicList.get(position).artist
        holder.song_card.setOnClickListener {
            val number=0
            val intent:Intent= Intent(myContext,MediaPlaybackService::class.java)
            intent.putExtra("position",position)
            intent.putExtra("number",number)
            myContext.startService(intent)
        }
    }

    override fun getItemCount(): Int {
        return musicList.size
    }

}