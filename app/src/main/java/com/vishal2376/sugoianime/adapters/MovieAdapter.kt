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
import com.vishal2376.sugoianime.fragments.nav.MovieFragmentDirections
import com.vishal2376.sugoianime.models.AnimeList

class MovieAdapter(private val context: Context, private val movieList: AnimeList) :
    Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.anime_list_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        val currentAnime = movieList[position]

        //anime info setup
        holder.animeTitle.text = currentAnime.animeTitle
        holder.animeYear.text = currentAnime.releasedDate

        //image setup
        Glide.with(context)
            .load(currentAnime.animeImg)
            .into(holder.movieImage)

        //on click
        holder.itemView.setOnClickListener {
            //pass animeId to anime Detail fragment
            val action = MovieFragmentDirections.actionMoviesFragmentToAnimeDetailFragment(currentAnime.animeId)
            it.findNavController().navigate(action)

            // TODO make a progress bar
            Toast.makeText(context,"Loading Data...",Toast.LENGTH_LONG).show()
        }

        //animation
        holder.itemView.animation = AnimationUtils.loadAnimation(context,R.anim.popup_anim)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    class MovieViewHolder(itemView: View) : ViewHolder(itemView) {
        val animeTitle = itemView.findViewById<TextView>(R.id.tvAnimeTitleLI)
        val animeYear = itemView.findViewById<TextView>(R.id.tvAnimeYearLI)
        val movieImage = itemView.findViewById<ImageView>(R.id.imgAnimeLI)
    }
}