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
import com.vishal2376.sugoianime.databinding.AnimeGridItemBinding
import com.vishal2376.sugoianime.models.AnimeListItem

class AnimeAdapter(
    private val context: Context,
    private val onAnimeClicked: (String) -> Unit
) :
    ListAdapter<AnimeListItem, AnimeAdapter.AnimeViewHolder>(ComparatorDiffUtil()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AnimeAdapter.AnimeViewHolder {
        val binding =
            AnimeGridItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnimeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnimeAdapter.AnimeViewHolder, position: Int) {
        val currentAnime = getItem(position)

        currentAnime.let {
            holder.bind(it)
        }

        //animation
        holder.itemView.animation = AnimationUtils.loadAnimation(context, R.anim.popup_anim)

    }

    class ComparatorDiffUtil : DiffUtil.ItemCallback<AnimeListItem>() {
        override fun areItemsTheSame(
            oldItem: AnimeListItem,
            newItem: AnimeListItem
        ): Boolean {
            return oldItem.animeId == newItem.animeId
        }

        override fun areContentsTheSame(
            oldItem: AnimeListItem,
            newItem: AnimeListItem
        ): Boolean {
            return oldItem == newItem
        }

    }

    inner class AnimeViewHolder(private val binding: AnimeGridItemBinding) :
        ViewHolder(binding.root) {

        fun bind(anime: AnimeListItem) {

            //set anime info
            binding.tvAnimeTitleGI.text = anime.animeTitle

            //image setup
            Glide.with(context)
                .load(anime.animeImg)
                .into(binding.imgAnimeGI)

            //onclick
            binding.root.setOnClickListener {
                onAnimeClicked(anime.animeId)
            }
        }
    }
}