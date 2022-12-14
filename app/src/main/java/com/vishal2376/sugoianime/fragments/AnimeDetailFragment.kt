package com.vishal2376.sugoianime.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.vishal2376.sugoianime.AnimeApplication
import com.vishal2376.sugoianime.R
import com.vishal2376.sugoianime.repository.AnimeRepository
import com.vishal2376.sugoianime.viewmodels.AnimeViewModalFactory
import com.vishal2376.sugoianime.viewmodels.AnimeViewModel
import kotlinx.coroutines.launch

class AnimeDetailFragment : Fragment() {

    //get arguments from HomeFragment through AnimeAdapter
    private val args: AnimeDetailFragmentArgs by navArgs()

    private lateinit var repository: AnimeRepository
    private lateinit var viewModel: AnimeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_anime_detail, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //--------------------------testing-------------------------------------
        // TODO : remove this after data binding
        val animeTitle = requireActivity().findViewById<TextView>(R.id.tvAnimeTitleAD)
        val animeOtherName = requireActivity().findViewById<TextView>(R.id.tvOtherNameAD)
        val animeStatus = requireActivity().findViewById<TextView>(R.id.tvInfoStatusAD)
        val animeDate = requireActivity().findViewById<TextView>(R.id.tvInfoDateAD)
        val animeEpisodes = requireActivity().findViewById<TextView>(R.id.tvInfoEpisodeAD)
        val animeSynopsis = requireActivity().findViewById<TextView>(R.id.tvAnimeSynopsisAD)
        val animeImg = requireActivity().findViewById<ImageView>(R.id.imgAnimeAD)
        val animeShare = requireActivity().findViewById<FloatingActionButton>(R.id.fabShareAD)

        repository = (requireActivity().application as AnimeApplication).repository
        viewModel =
            ViewModelProvider(this, AnimeViewModalFactory(repository))[AnimeViewModel::class.java]

        //fetching the data
        lifecycleScope.launch {
            viewModel.getAnimeDetail(args.animeID)
        }

        viewModel.animeDetail.observe(viewLifecycleOwner) {
            animeTitle.text = it.animeTitle
            animeSynopsis.text = it.synopsis
            animeDate.text = it.releasedDate
            animeEpisodes.text = it.totalEpisodes
            animeStatus.text = it.status
//            animeOtherName.text = it.otherNames

            Glide.with(requireContext())
                .load(it.animeImg)
                .into(animeImg)
        }
        //----------------------------------------------------------------------
    }
}