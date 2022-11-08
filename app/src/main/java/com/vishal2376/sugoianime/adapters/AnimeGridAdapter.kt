package com.vishal2376.sugoianime.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.vishal2376.sugoianime.R
import com.vishal2376.sugoianime.models.AnimeList

class AnimeAdapter(private val context: Context, private val animeList: AnimeList) :
    Adapter<AnimeAdapter.AnimeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.anime_grid_item, parent, false)
        return AnimeViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        holder.animeTitle.text = animeList[position].animeTitle

        //image setup
        Glide.with(context)
            .load(animeList[position].animeImg)
            .into(holder.animeImage)
    }

    override fun getItemCount(): Int {
        return animeList.size
    }

    class AnimeViewHolder(itemView: View) : ViewHolder(itemView) {
        val animeTitle = itemView.findViewById<TextView>(R.id.tvAnimeTitleGI)
        val animeImage = itemView.findViewById<ImageView>(R.id.imgAnimeGI)
    }
}