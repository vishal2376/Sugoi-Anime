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
import com.vishal2376.sugoianime.databinding.AnimeSearchListItemBinding
import com.vishal2376.sugoianime.models.search.SearchResultList

class SearchAdapter(
    private val context: Context,
    private val onAnimeClicked: (String) -> Unit
) :
    ListAdapter<SearchResultList, SearchAdapter.SearchViewHolder>(ComparatorDiffUtil()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchAdapter.SearchViewHolder {
        val binding =
            AnimeSearchListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchAdapter.SearchViewHolder, position: Int) {
        val currentAnime = getItem(position)

        currentAnime.let {
            holder.bind(it)
        }

        //animation
        holder.itemView.animation = AnimationUtils.loadAnimation(context, R.anim.popup_anim)

    }

    class ComparatorDiffUtil : DiffUtil.ItemCallback<SearchResultList>() {
        override fun areItemsTheSame(
            oldItem: SearchResultList,
            newItem: SearchResultList
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: SearchResultList,
            newItem: SearchResultList
        ): Boolean {
            return oldItem == newItem
        }

    }

    inner class SearchViewHolder(private val binding: AnimeSearchListItemBinding) :
        ViewHolder(binding.root) {

        fun bind(anime: SearchResultList) {

            //set anime info
            binding.tvAnimeTitleLI.text = anime.title
            binding.tvAnimeStatusLI.text = anime.releaseDate

            //image setup
            Glide.with(context)
                .load(anime.image)
                .into(binding.imgAnimeLI)

            //onclick
            binding.root.setOnClickListener {
                onAnimeClicked(anime.id)
            }
        }
    }
}