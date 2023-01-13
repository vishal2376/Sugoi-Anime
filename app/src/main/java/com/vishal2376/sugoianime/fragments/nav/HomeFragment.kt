package com.vishal2376.sugoianime.fragments.nav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.vishal2376.sugoianime.adapters.AnimeAdapter
import com.vishal2376.sugoianime.databinding.FragmentHomeBinding
import com.vishal2376.sugoianime.viewmodels.AnimeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val animeViewModel by viewModels<AnimeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        //get data
        animeViewModel.getPopularAnime()
        animeViewModel.getRecentAnime()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setLayout()

        bindObservers()

    }

    private fun bindObservers() {
        animeViewModel.popularAnimeLiveData.observe(viewLifecycleOwner, Observer {
            binding.rvPopular.adapter = AnimeAdapter(requireContext(), animeList = it.data)
        })

        animeViewModel.recentAnimeLiveData.observe(viewLifecycleOwner, Observer {
            binding.rvRecent.adapter = AnimeAdapter(requireContext(), recentResponse = it.data)
        })
    }

    private fun setLayout() {
        binding.rvPopular.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvRecent.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}