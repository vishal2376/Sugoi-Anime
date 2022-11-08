package com.vishal2376.sugoianime.fragments.nav

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vishal2376.sugoianime.AnimeApplication
import com.vishal2376.sugoianime.R
import com.vishal2376.sugoianime.adapters.AnimeAdapter
import com.vishal2376.sugoianime.repository.AnimeRepository
import com.vishal2376.sugoianime.viewmodels.AnimeViewModalFactory
import com.vishal2376.sugoianime.viewmodels.AnimeViewModel

class HomeFragment : Fragment() {

    private lateinit var repository: AnimeRepository
    private lateinit var viewModel: AnimeViewModel

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

        repository = (requireActivity().application as AnimeApplication).repository
        viewModel =
            ViewModelProvider(this, AnimeViewModalFactory(repository))[AnimeViewModel::class.java]

        val recyclerView1 = requireActivity().findViewById<RecyclerView>(R.id.rvRecent)
        val recyclerView2 = requireActivity().findViewById<RecyclerView>(R.id.rvPopular)

        recyclerView1.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView2.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, true)

        viewModel.popularAnime.observe(requireActivity()) {
            recyclerView1.adapter = AnimeAdapter(requireContext(), animeList = it)
            recyclerView2.adapter = AnimeAdapter(requireContext(), animeList = it)
        }

        //------------------------------------------------------------------------------
    }
}