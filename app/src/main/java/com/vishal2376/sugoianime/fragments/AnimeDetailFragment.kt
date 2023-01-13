package com.vishal2376.sugoianime.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.vishal2376.sugoianime.adapters.AnimeAdapter
import com.vishal2376.sugoianime.databinding.FragmentAnimeDetailBinding
import com.vishal2376.sugoianime.models.detail.AnimeDetail
import com.vishal2376.sugoianime.util.NetworkResult
import com.vishal2376.sugoianime.viewmodels.AnimeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnimeDetailFragment : Fragment() {

    private var _binding: FragmentAnimeDetailBinding? = null
    private val binding get() = _binding!!

    private val animeViewModel by viewModels<AnimeViewModel>()

    //get arguments from HomeFragment through AnimeAdapter
    private val args: AnimeDetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAnimeDetailBinding.inflate(inflater, container, false)

        //fetching the data
        animeViewModel.getAnimeDetail(args.animeID)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindObservers()

    }

    private fun bindObservers() {
        animeViewModel.animeDetailLiveData.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = false

            when (it) {
                is NetworkResult.Success -> {
                    setLayout(it.data!!)
                }
                is NetworkResult.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> {
                    binding.progressBar.isVisible = true
                }
            }
        }
    }

    private fun setLayout(it: AnimeDetail) {
        binding.tvAnimeTitleAD.text = it.animeTitle
        binding.tvAnimeSynopsisAD.text = it.synopsis
        binding.tvInfoDateAD.text = it.releasedDate
        binding.tvInfoEpisodeAD.text = it.totalEpisodes
        binding.tvInfoStatusAD.text = it.status

        Glide.with(requireContext())
            .load(it.animeImg)
            .into(binding.imgAnimeAD)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}