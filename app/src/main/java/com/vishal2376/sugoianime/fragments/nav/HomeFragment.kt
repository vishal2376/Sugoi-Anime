package com.vishal2376.sugoianime.fragments.nav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vishal2376.sugoianime.R
import com.vishal2376.sugoianime.adapters.AnimeAdapter
import com.vishal2376.sugoianime.viewmodels.AnimeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val animeViewModel by viewModels<AnimeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //--------------------------testing-------------------------------------
        // TODO : remove this after data binding

        animeViewModel.getPopularAnime()

        val recyclerView1 = requireActivity().findViewById<RecyclerView>(R.id.rvRecent)
        val recyclerView2 = requireActivity().findViewById<RecyclerView>(R.id.rvPopular)

        recyclerView1.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView2.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, true)

        animeViewModel.popularAnime.observe(viewLifecycleOwner, Observer {
            recyclerView1.adapter = AnimeAdapter(requireContext(), animeList = it)
            recyclerView2.adapter = AnimeAdapter(requireContext(), animeList = it)
        })

        //------------------------------------------------------------------------------
    }
}