package com.vishal2376.sugoianime.fragments

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
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.vishal2376.sugoianime.adapters.SearchAdapter
import com.vishal2376.sugoianime.databinding.FragmentSearchBinding
import com.vishal2376.sugoianime.util.NetworkResult
import com.vishal2376.sugoianime.viewmodels.AnimeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val animeViewModel by viewModels<AnimeViewModel>()
    private lateinit var adapter: SearchAdapter

    //get nav arguments
    private val args: SearchFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        adapter = SearchAdapter(requireContext(), ::onAnimeItemClicked)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //get data
        animeViewModel.getAnimeSearch(args.query)

        setLayout()

        bindObservers()

        //buttons
        binding.searchBarFS.setOnEditorActionListener { _, i, _ ->
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                val query = binding.searchBarFS.text.toString()
                animeViewModel.getAnimeSearch(query)
                true
            } else {
                false
            }
        }
    }

    private fun bindObservers() {

        animeViewModel.searchAnimeLiveData.observe(viewLifecycleOwner) {
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
        binding.rvResults.layoutManager = LinearLayoutManager(requireContext())
        binding.rvResults.adapter = adapter
    }

    private fun onAnimeItemClicked(currentAnimeId: String) {
        //pass animeId to anime Detail fragment
        val action =
            SearchFragmentDirections.actionSearchFragmentToAnimeDetailFragment(currentAnimeId)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}