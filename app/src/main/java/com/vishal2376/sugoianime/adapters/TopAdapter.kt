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
import com.vishal2376.sugoianime.models.top.TopResultList

class TopAdapter(
    private val context: Context,
    private val onAnimeClicked: (String) -> Unit
) :
    ListAdapter<TopResultList, TopAdapter.TopViewHolder>(ComparatorDiffUtil()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TopAdapter.TopViewHolder {
        val binding =
            AnimeGridItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopAdapter.TopViewHolder, position: Int) {
        val currentAnime = getItem(position)

        currentAnime.let {
            holder.bind(it)
        }

        //animation
        holder.itemView.animation = AnimationUtils.loadAnimation(context, R.anim.popup_anim)

    }

    class ComparatorDiffUtil : DiffUtil.ItemCallback<TopResultList>() {
        override fun areItemsTheSame(
            oldItem: TopResultList,
            newItem: TopResultList
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: TopResultList,
            newItem: TopResultList
        ): Boolean {
            return oldItem == newItem
        }

    }

    inner class TopViewHolder(private val binding: AnimeGridItemBinding) :
        ViewHolder(binding.root) {

        fun bind(anime: TopResultList) {

            //set anime info
            binding.tvAnimeTitleGI.text = anime.title

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