package com.vishal2376.sugoianime.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.vishal2376.sugoianime.R
import com.vishal2376.sugoianime.fragments.nav.HomeFragmentDirections
import com.vishal2376.sugoianime.models.AnimeList
import com.vishal2376.sugoianime.models.AnimeRecentResponse

class AnimeAdapter(
    private val context: Context,
    private val animeList: AnimeList? = null,
    private val recentResponse: AnimeRecentResponse? = null
) :
    Adapter<AnimeAdapter.AnimeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.anime_grid_item, parent, false)
        return AnimeViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {

        if (animeList != null) {
            val currentAnime = animeList[position]

            holder.animeTitle.text = currentAnime.animeTitle
            //image setup
            Glide.with(context)
                .load(currentAnime.animeImg)
                .into(holder.animeImage)

            //on click
            holder.itemView.setOnClickListener {
                //pass current animeID to AnimeDetail Fragment
                val action =
                    HomeFragmentDirections.actionHomeFragmentToAnimeDetailFragment(currentAnime.animeId)
                it.findNavController().navigate(action)
            }
        } else {
            val currentAnime = recentResponse!![position]

            holder.animeTitle.text = currentAnime.animeTitle
            //image setup
            Glide.with(context)
                .load(currentAnime.animeImg)
                .into(holder.animeImage)

            //on click
            holder.itemView.setOnClickListener {
                //pass current animeID to AnimeDetail Fragment
                val action =
                    HomeFragmentDirections.actionHomeFragmentToAnimeDetailFragment(currentAnime.animeId)
                it.findNavController().navigate(action)

                // TODO make a progress bar
                Toast.makeText(context, "Loading Data...", Toast.LENGTH_LONG).show()
            }
        }

        //animation
        holder.itemView.animation = AnimationUtils.loadAnimation(context, R.anim.popup_anim)
    }

    override fun getItemCount(): Int {
        return animeList?.size ?: recentResponse!!.size
    }

    class AnimeViewHolder(itemView: View) : ViewHolder(itemView) {
        val animeTitle = itemView.findViewById<TextView>(R.id.tvAnimeTitleGI)
        val animeImage = itemView.findViewById<ImageView>(R.id.imgAnimeGI)
    }
}