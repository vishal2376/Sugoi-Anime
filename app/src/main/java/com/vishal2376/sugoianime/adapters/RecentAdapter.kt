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
import com.vishal2376.sugoianime.models.AnimeRecentResponseItem

class RecentAdapter(
    private val context: Context,
    private val onAnimeClicked: (String) -> Unit
) :
    ListAdapter<AnimeRecentResponseItem, RecentAdapter.RecentViewHolder>(ComparatorDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentViewHolder {
        val binding =
            AnimeGridItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecentViewHolder, position: Int) {
        val currentAnime = getItem(position)

        currentAnime.let {
            holder.bind(it)
        }

        //animation
        holder.itemView.animation = AnimationUtils.loadAnimation(context, R.anim.popup_anim)

    }

    class ComparatorDiffUtil : DiffUtil.ItemCallback<AnimeRecentResponseItem>() {
        override fun areItemsTheSame(
            oldItem: AnimeRecentResponseItem,
            newItem: AnimeRecentResponseItem
        ): Boolean {
            return oldItem.animeId == newItem.animeId
        }

        override fun areContentsTheSame(
            oldItem: AnimeRecentResponseItem,
            newItem: AnimeRecentResponseItem
        ): Boolean {
            return oldItem == newItem
        }

    }

    inner class RecentViewHolder(private val binding: AnimeGridItemBinding) :
        ViewHolder(binding.root) {

        fun bind(anime: AnimeRecentResponseItem) {

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