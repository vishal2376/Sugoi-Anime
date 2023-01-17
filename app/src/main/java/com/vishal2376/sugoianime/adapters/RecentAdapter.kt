package com.vishal2376.sugoianime.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.vishal2376.sugoianime.R
import com.vishal2376.sugoianime.databinding.AnimeGridItemBinding
import com.vishal2376.sugoianime.models.recent.RecentResultList

class RecentAdapter(
    private val context: Context,
    private val onAnimeClicked: (String) -> Unit
) :
    ListAdapter<RecentResultList, RecentAdapter.RecentViewHolder>(ComparatorDiffUtil()) {

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

    class ComparatorDiffUtil : DiffUtil.ItemCallback<RecentResultList>() {
        override fun areItemsTheSame(
            oldItem: RecentResultList,
            newItem: RecentResultList
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: RecentResultList,
            newItem: RecentResultList
        ): Boolean {
            return oldItem == newItem
        }

    }

    inner class RecentViewHolder(private val binding: AnimeGridItemBinding) :
        ViewHolder(binding.root) {

        fun bind(anime: RecentResultList) {

            //set anime info
            binding.tvAnimeTitleGI.text = anime.title
            binding.tvEpisodeNumber.text = anime.episodeNumber.toString()
            binding.tvEpisodeNumber.visibility = View.VISIBLE

            //image setup
            Glide.with(context)
                .load(anime.image)
                .into(binding.imgAnimeGI)

            //onclick
            binding.root.setOnClickListener {
                onAnimeClicked(anime.id)
            }
        }
    }
}