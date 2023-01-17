package com.vishal2376.sugoianime.fragments.nav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vishal2376.sugoianime.adapters.RecentAdapter
import com.vishal2376.sugoianime.adapters.TopAdapter
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
    private lateinit var topAdapter: TopAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        recentAdapter = RecentAdapter(requireContext(), ::onAnimeItemClicked)
        topAdapter = TopAdapter(requireContext(), ::onAnimeItemClicked)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //get data
        animeViewModel.getRecentEpisodes()
        animeViewModel.getTopAnime()

        setLayout()

        bindObservers()

        //buttons
        binding.searchBar.setOnEditorActionListener { _, i, _ ->
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                val query = binding.searchBar.text.toString()
                val action = HomeFragmentDirections.actionHomeFragmentToSearchFragment(query)
                findNavController().navigate(action)

                true
            } else {
                false
            }
        }

    }

    private fun bindObservers() {
        animeViewModel.topAnimeLiveData.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = false

            when (it) {
                is NetworkResult.Success -> {
                    topAdapter.submitList(it.data!!.results)
                }
                is NetworkResult.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> {
                    binding.progressBar.isVisible = true
                }
            }

        }

        animeViewModel.recentEpisodesLiveData.observe(viewLifecycleOwner) {

            when (it) {
                is NetworkResult.Success -> {
                    recentAdapter.submitList(it.data!!.results)
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
        binding.rvTop.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvTop.adapter = topAdapter

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