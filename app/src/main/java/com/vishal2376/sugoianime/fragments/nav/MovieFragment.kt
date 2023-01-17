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
import com.vishal2376.sugoianime.databinding.FragmentMoviesBinding
import com.vishal2376.sugoianime.util.NetworkResult
import com.vishal2376.sugoianime.viewmodels.AnimeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    private val animeViewModel by viewModels<AnimeViewModel>()
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)

        adapter = MovieAdapter(requireContext(), ::onAnimeClicked)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //get data
        animeViewModel.getMovieAnime(1)

        setLayout()

        bindObservers()

    }

    private fun bindObservers() {
        animeViewModel.movieAnimeLiveData.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = false

            when (it) {
                is NetworkResult.Success -> {
                    adapter.submitList(it.data)
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
        binding.rvMovies.layoutManager = LinearLayoutManager(requireContext())
        binding.rvMovies.adapter = adapter
    }

    private fun onAnimeClicked(currentAnime: AnimeListItem) {
        //pass animeId to anime Detail fragment
        val action =
            MovieFragmentDirections.actionMoviesFragmentToAnimeDetailFragment(currentAnime.animeId)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}