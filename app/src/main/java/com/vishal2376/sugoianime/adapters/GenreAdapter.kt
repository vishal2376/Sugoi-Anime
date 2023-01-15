package com.vishal2376.sugoianime.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.vishal2376.sugoianime.R

class GenreAdapter(val genreList: List<String>) : Adapter<GenreAdapter.GenreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.genre_item, parent, false)
        return GenreViewHolder(view)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.genreTitle.text = genreList[position]
    }

    override fun getItemCount(): Int {
        return genreList.size
    }

    inner class GenreViewHolder(itemView: View) : ViewHolder(itemView) {
        val genreTitle = itemView.findViewById<TextView>(R.id.tvGenreGI)
    }
}