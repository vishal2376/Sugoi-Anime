package com.vishal2376.sugoianime.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.vishal2376.sugoianime.R
import com.vishal2376.sugoianime.databinding.AnimeListItemBinding
import com.vishal2376.sugoianime.models.AnimeListItem


class MovieAdapter(
    private val context: Context,
    private val onAnimeClicked: (AnimeListItem) -> Unit
) :
    ListAdapter<AnimeListItem, MovieAdapter.MovieViewHolder>(ComparatorDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding =
            AnimeListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentAnime = getItem(position)

        currentAnime.let {
            holder.bind(it)
        }

        //animation
        holder.itemView.animation = AnimationUtils.loadAnimation(context, R.anim.popup_anim)

    }

    class ComparatorDiffUtil : DiffUtil.ItemCallback<AnimeListItem>() {
        override fun areItemsTheSame(oldItem: AnimeListItem, newItem: AnimeListItem): Boolean {
            return oldItem.animeId == newItem.animeId
        }

        override fun areContentsTheSame(oldItem: AnimeListItem, newItem: AnimeListItem): Boolean {
            return oldItem == newItem
        }

    }

    inner class MovieViewHolder(private val binding: AnimeListItemBinding) :
        ViewHolder(binding.root) {

        fun bind(anime: AnimeListItem) {

            //set anime info
            binding.tvAnimeTitleLI.text = anime.animeTitle
            binding.tvAnimeYearLI.text = anime.releasedDate

            //image setup
            Glide.with(context)
                .load(anime.animeImg)
                .into(binding.imgAnimeLI)

            //onclick
            binding.root.setOnClickListener {
                onAnimeClicked(anime)
            }
        }
    }
}
//class MovieAdapter(private val context: Context, private val movieList: AnimeList) :
//    Adapter<MovieAdapter.MovieViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
//        val view = LayoutInflater.from(context).inflate(R.layout.anime_list_item, parent, false)
//        return MovieViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
//
//        val currentAnime = movieList[position]
//
//        //anime info setup
//        holder.animeTitle.text = currentAnime.animeTitle
//        holder.animeYear.text = currentAnime.releasedDate
//
//        //image setup
//        Glide.with(context)
//            .load(currentAnime.animeImg)
//            .into(holder.movieImage)
//
//        //on click
//        holder.itemView.setOnClickListener {
//            //pass animeId to anime Detail fragment
//            val action = MovieFragmentDirections.actionMoviesFragmentToAnimeDetailFragment(currentAnime.animeId)
//            it.findNavController().navigate(action)
//        }
//
//        //animation
//        holder.itemView.animation = AnimationUtils.loadAnimation(context,R.anim.popup_anim)
//    }
//
//    override fun getItemCount(): Int {
//        return movieList.size
//    }
//
//    class MovieViewHolder(itemView: View) : ViewHolder(itemView) {
//        val animeTitle = itemView.findViewById<TextView>(R.id.tvAnimeTitleLI)
//        val animeYear = itemView.findViewById<TextView>(R.id.tvAnimeYearLI)
//        val movieImage = itemView.findViewById<ImageView>(R.id.imgAnimeLI)
//    }
//}