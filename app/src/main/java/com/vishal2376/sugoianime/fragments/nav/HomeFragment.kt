package com.vishal2376.sugoianime.fragments.nav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vishal2376.sugoianime.adapters.AnimeAdapter
import com.vishal2376.sugoianime.adapters.RecentAdapter
import com.vishal2376.sugoianime.databinding.FragmentHomeBinding
import com.vishal2376.sugoianime.util.NetworkResult
import com.vishal2376.sugoianime.viewmodels.AnimeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val animeViewModel by viewModels<AnimeViewModel>()
    private lateinit var recentAdapter: RecentAdapter
    private lateinit var popularAdapter: AnimeAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        recentAdapter = RecentAdapter(requireContext(), ::onAnimeItemClicked)
        popularAdapter = AnimeAdapter(requireContext(), ::onAnimeItemClicked)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //get data
        animeViewModel.getRecentAnime()
        animeViewModel.getPopularAnime()

        setLayout()

        bindObservers()

    }

    private fun bindObservers() {
        animeViewModel.popularAnimeLiveData.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = false

            when (it) {
                is NetworkResult.Success -> {
                    popularAdapter.submitList(it.data)
                }
                is NetworkResult.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> {
                    binding.progressBar.isVisible = true
                }
            }

        }

        animeViewModel.recentAnimeLiveData.observe(viewLifecycleOwner) {

            when (it) {
                is NetworkResult.Success -> {
                    recentAdapter.submitList(it.data)
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

    private fun setLayout() {
        binding.rvPopular.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvPopular.adapter = popularAdapter

        binding.rvRecent.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvRecent.adapter = recentAdapter
    }

    private fun onAnimeItemClicked(currentAnimeId: String) {
        //pass animeId to anime Detail fragment
        val action =
            HomeFragmentDirections.actionHomeFragmentToAnimeDetailFragment(currentAnimeId)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}